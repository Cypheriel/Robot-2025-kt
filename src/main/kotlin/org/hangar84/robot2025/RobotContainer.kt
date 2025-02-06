package org.hangar84.robot2025

import edu.wpi.first.wpilibj2.command.Command
import org.hangar84.robot2025.subsystems.LEDSubsystem

object RobotContainer {
    val autonomousCommand: Command? = null

    init {
        configureBindings()

        LEDSubsystem.register()
    }

    private fun configureBindings() {

    }
}