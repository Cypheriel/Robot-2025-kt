package org.hangar84.robot2025

import edu.wpi.first.math.MathUtil
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import org.hangar84.robot2025.subsystems.DriveSubsystem

object RobotContainer {
    private const val JOYSTICK_DEADBAND = 0.1

    private val controller = CommandXboxController(0)

    val autonomousCommand: Command? = null

    init {
        configureBindings()

        // LEDSubsystem.register()
    }

    private fun configureBindings() {
        DriveSubsystem.defaultCommand =
            DriveSubsystem.run {
                DriveSubsystem.drive(
                    -MathUtil.applyDeadband(controller.leftY, JOYSTICK_DEADBAND),
                    -MathUtil.applyDeadband(controller.leftX, JOYSTICK_DEADBAND),
                    -MathUtil.applyDeadband(controller.rightX, JOYSTICK_DEADBAND),
                    true,
                )
            }

        // Park on left trigger
        controller.leftTrigger().onTrue(DriveSubsystem.runOnce { DriveSubsystem.park() })
    }
}
