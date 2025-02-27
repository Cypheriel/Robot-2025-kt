package org.hangar84.robot2025.swerve

import com.revrobotics.spark.SparkBase.ControlType
import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkLowLevel.MotorType
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.SwerveModulePosition
import edu.wpi.first.math.kinematics.SwerveModuleState
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class MAXSwerveModule(
    drivingControllerID: Int,
    turningControllerID: Int,
    private val chassisAngularOffset: Double,
    drivingConfig: SparkMaxConfig,
    turningConfig: SparkMaxConfig,
) {
    private val drivingController = SparkMax(drivingControllerID, MotorType.kBrushless)
    private val turningController = SparkMax(turningControllerID, MotorType.kBrushless)

    var desiredState = SwerveModuleState(0.0, Rotation2d())
        set(value) {
            val correctedDesiredState =
                SwerveModuleState(
                    value.speedMetersPerSecond,
                    value.angle + Rotation2d.fromDegrees(chassisAngularOffset),
                )

            correctedDesiredState.optimize(Rotation2d(turningController.encoder.position))

            val drivingStatus =
                drivingController.closedLoopController.setReference(
                    correctedDesiredState.speedMetersPerSecond,
                    ControlType.kVelocity,
                )

            val turningStatus =
                turningController.closedLoopController.setReference(
                    correctedDesiredState.angle.radians,
                    ControlType.kPosition,
                )

            SmartDashboard.putString("DriveSubsystem/Status", "D:$drivingStatus|T:$turningStatus")

            field = value
        }

    val state
        get() =
            SwerveModuleState(
                drivingController.encoder.velocity,
                Rotation2d(turningController.encoder.position - chassisAngularOffset),
            )

    val position
        get() =
            SwerveModulePosition(
                drivingController.encoder.position,
                Rotation2d(turningController.encoder.position - chassisAngularOffset),
            )

    init {
        drivingController.configure(drivingConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
        turningController.configure(turningConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)

        drivingController.encoder.position = 0.0
    }
}
