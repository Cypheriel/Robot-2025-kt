package org.hangar84.robot2025.subsystems

import edu.wpi.first.hal.FRCNetComm.tInstances
import edu.wpi.first.hal.FRCNetComm.tResourceType
import edu.wpi.first.hal.HAL
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.math.kinematics.SwerveDriveKinematics
import edu.wpi.first.math.kinematics.SwerveDriveOdometry
import edu.wpi.first.math.kinematics.SwerveModulePosition
import edu.wpi.first.math.kinematics.SwerveModuleState
import edu.wpi.first.units.Units.Inches
import edu.wpi.first.units.Units.MetersPerSecond
import edu.wpi.first.units.Units.RotationsPerSecond
import edu.wpi.first.wpilibj.ADIS16470_IMU
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis
import edu.wpi.first.wpilibj2.command.Subsystem
import org.hangar84.robot2025.swerve.MAXSwerveModule
import org.hangar84.robot2025.swerve.SwerveConfigs

object DriveSubsystem : Subsystem {
    // - Constants -
    // TODO: Tune/verify these values

    private val MAX_SPEED = MetersPerSecond.of(4.8)
    private val MAX_ANGULAR_SPEED = RotationsPerSecond.of(1.0)
    private val WHEEL_BASE = Inches.of(31.0)
    private val TRACK_WIDTH = Inches.of(26.5)

    // - MAXSwerve modules -
    private val frontRightModule =
        MAXSwerveModule(1, 2, -Math.PI / 2, SwerveConfigs.drivingConfig, SwerveConfigs.turningConfig)
    private val frontLeftModule =
        MAXSwerveModule(3, 4, 0.0, SwerveConfigs.drivingConfig, SwerveConfigs.turningConfig)
    private val backRightModule =
        MAXSwerveModule(5, 6, Math.PI, SwerveConfigs.drivingConfig, SwerveConfigs.turningConfig)
    private val backLeftModule =
        MAXSwerveModule(7, 8, Math.PI / 2, SwerveConfigs.drivingConfig, SwerveConfigs.turningConfig)

    private val allModules
        get() = arrayOf(frontLeftModule, frontRightModule, backLeftModule, backRightModule)
    private val allModulePositions: Array<SwerveModulePosition>
        get() = allModules.map { it.position }.toTypedArray()

    // - Sensors -
    private val imu = ADIS16470_IMU()
    private val rotation
        get() = Rotation2d.fromDegrees(imu.getAngle(IMUAxis.kZ))

    // - Odometry & Kinematics -
    private val kinematics =
        SwerveDriveKinematics(
            Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
            Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
            Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
            Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
        )

    private val odometry = SwerveDriveOdometry(kinematics, rotation, allModulePositions)

    init {
        HAL.report(tResourceType.kResourceType_RobotDrive, tInstances.kRobotDriveSwerve_MaxSwerve)
    }

    override fun periodic() {
        odometry.update(rotation, allModulePositions)
    }

    fun drive(
        throttleX: Double,
        throttleY: Double,
        throttleAngular: Double,
        fieldRelative: Boolean,
    ) {
        val speedX = MAX_SPEED * throttleX
        val speedY = MAX_SPEED * throttleY
        val speedAngular = MAX_ANGULAR_SPEED * throttleAngular

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

    fun park() {
        val positions =
            arrayOf(
                Rotation2d.fromDegrees(45.0),
                Rotation2d.fromDegrees(-45.0),
                Rotation2d.fromDegrees(-45.0),
                Rotation2d.fromDegrees(45.0),
            )

        allModules.forEachIndexed { i, module -> module.desiredState = SwerveModuleState(0.0, positions[i]) }
    }
}
