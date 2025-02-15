package org.hangar84.robot2025

import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.RunCommand
import org.hangar84.robot2025.subsystems.DriveSubsystem
import org.hangar84.robot2025.subsystems.LEDSubsystem

object RobotContainer {
    private final val controller = XboxController(0)

    val autonomousCommand: Command? = null

    init {
        configureBindings()

        LEDSubsystem.register()

        DriveSubsystem.defaultCommand =
            RunCommand({ DriveSubsystem.drive(controller.leftY, controller.leftX, controller.rightX, true) })
    }

    private fun configureBindings() {
    }
}
