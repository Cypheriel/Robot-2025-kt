package org.hangar84.robot2025.subsystems

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.hal.FRCNetComm.tInstances
import edu.wpi.first.hal.FRCNetComm.tResourceType
import edu.wpi.first.hal.HAL
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.math.kinematics.SwerveDriveKinematics
import edu.wpi.first.math.kinematics.SwerveDriveOdometry
import edu.wpi.first.math.kinematics.SwerveModulePosition
import edu.wpi.first.wpilibj.ADIS16470_IMU
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis
import edu.wpi.first.wpilibj2.command.Subsystem
import org.hangar84.robot2025.swerve.MAXSwerveModule

object DriveSubsystem : Subsystem {
    // - Constants -
    // TODO: Tune/verify these values
    // NOTE: Use SI units where possible

    /** The max speed in meters per second */
    private const val MAX_SPEED = 0.0
    private const val MAX_ANGULAR_SPEED = 0.0
    private const val WHEEL_BASE = 31.0
    private const val TRACK_WIDTH = 26.5
    private const val CHASSIS_ANGULAR_OFFSET = 0.0

    private const val DRIVING_FACTOR = 0.0
    private const val TURNING_FACTOR = 0.0
    private const val DRIVING_FEEDFORWARD = 0.0

    // - Drive configs -
    private val drivingConfig = SparkMaxConfig()
    private val turningConfig = SparkMaxConfig()

    // - MAXSwerve modules - TODO: Verify CAN IDs
    private val frontLeftModule = MAXSwerveModule(1, 2, CHASSIS_ANGULAR_OFFSET, drivingConfig, turningConfig)
    private val frontRightModule = MAXSwerveModule(3, 4, CHASSIS_ANGULAR_OFFSET, drivingConfig, turningConfig)
    private val backLeftModule = MAXSwerveModule(7, 8, CHASSIS_ANGULAR_OFFSET, drivingConfig, turningConfig)
    private val backRightModule = MAXSwerveModule(5, 6, CHASSIS_ANGULAR_OFFSET, drivingConfig, turningConfig)
    private val allModules
        get() = arrayOf(frontLeftModule, frontRightModule, backLeftModule, backRightModule)
    private val allModulePositions: Array<SwerveModulePosition>
        get() = allModules.map { it.position }.toTypedArray()

    // - Sensors -
    private val imu = ADIS16470_IMU() // TODO: Verify correct model of IMU
    private val rotation
        get() = Rotation2d.fromDegrees(imu.getAngle(IMUAxis.kZ))

    // - Odometry & Kinematics -
    private val kinematics =
        SwerveDriveKinematics(
            Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2),
            Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2),
            Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2),
            Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2),
        )
    private val odometry = SwerveDriveOdometry(kinematics, rotation, allModulePositions)

    init {
        HAL.report(tResourceType.kResourceType_RobotDrive, tInstances.kRobotDriveSwerve_MaxSwerve)
    }

    override fun periodic() {
        odometry.update(rotation, allModulePositions)
    }

    fun configure() {
        drivingConfig
            .idleMode(IdleMode.kBrake)

        drivingConfig.encoder
            .positionConversionFactor(DRIVING_FACTOR)
            .velocityConversionFactor(DRIVING_FACTOR / 60)

        drivingConfig.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(0.04, 0.0, 0.0) // TODO: Tune these values (if needed)
            .velocityFF(DRIVING_FACTOR)
            .outputRange(-1.0, 1.0)

        turningConfig
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(20)

        turningConfig.absoluteEncoder
            .inverted(true)
            .positionConversionFactor(TURNING_FACTOR)
            .velocityConversionFactor(TURNING_FACTOR / 60)

        turningConfig.closedLoop
            .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
            .pid(0.0, 0.0, 0.0) // TODO: Tune these values (if needed)
            .outputRange(-1.0, 1.0)
            .positionWrappingEnabled(true)
            .positionWrappingInputRange(0.0, TURNING_FACTOR)
    }

    fun resetOdometry(pose: Pose2d) {
        odometry.resetPosition(
            rotation,
            allModulePositions,
            pose,
        )
    }

    fun drive(
        throttleX: Double,
        throttleY: Double,
        throttleAngular: Double,
        fieldRelative: Boolean,
    ) {
        val speedX = throttleX * MAX_SPEED
        val speedY = throttleY * MAX_SPEED
        val speedAngular = throttleAngular * MAX_ANGULAR_SPEED

        val swerveStates =
            kinematics.toSwerveModuleStates(
                if (fieldRelative) {
                    ChassisSpeeds.fromFieldRelativeSpeeds(speedX, speedY, speedAngular, rotation)
                } else {
                    ChassisSpeeds(speedX, speedY, speedAngular)
                },
            )

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveStates, MAX_SPEED)

        allModules.forEachIndexed { i, module -> module.desiredState = swerveStates[i] }
    }
}
