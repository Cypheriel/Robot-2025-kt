package org.hangar84.robot2025.subsystems

import edu.wpi.first.wpilibj.AddressableLED
import edu.wpi.first.wpilibj.AddressableLEDBuffer
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.hangar84.robot2025.Robot
import org.hangar84.robot2025.animations.AutonomousEnabledAnimation
import org.hangar84.robot2025.animations.BlueTeamAnimation
import org.hangar84.robot2025.animations.ClockAnimation
import org.hangar84.robot2025.animations.EmergencyAlertAnimation
import org.hangar84.robot2025.animations.EnabledAnimation
import org.hangar84.robot2025.animations.RedTeamAnimation
import org.hangar84.robot2025.animations.WaveAnimation
import org.hangar84.robot2025.animationsystem.Animation
import org.hangar84.robot2025.animationsystem.ZiaComponents
import kotlin.jvm.optionals.getOrNull

object LEDSubsystem : SubsystemBase() {
    private val EMPTY_BUFFER = AddressableLEDBuffer(48)

    // -- Components --
    private val ziaLED = AddressableLED(0)
    private val frameGapTimer = Timer()
    private val frameTimeTimer = Timer()

    // -- Status Animations --
    private var lastAnimation: Animation? = null
    private val currentAnimation: Animation
        get() {
            if (!DriverStation.isDSAttached()) {
                return ClockAnimation
            } else if (DriverStation.getMatchTime() != -1.0 && Robot.isDisabled) {
                return EmergencyAlertAnimation
            } else if (Robot.isDisabled && !DriverStation.isEStopped()) {
                return WaveAnimation
            } else if (DriverStation.isEStopped()) {
                return EmergencyAlertAnimation
            } else if (Robot.isAutonomousEnabled) {
                return AutonomousEnabledAnimation
            } else if (Robot.isEnabled) {
                if (!DriverStation.isFMSAttached() && !Robot.isTest) {
                    return EnabledAnimation
                }

                return when (DriverStation.getAlliance().getOrNull()) {
                    DriverStation.Alliance.Red -> RedTeamAnimation
                    DriverStation.Alliance.Blue -> BlueTeamAnimation
                    else -> EnabledAnimation
                }
            }

            return EmergencyAlertAnimation
        }

    private var withinFrameGap = false

    init {
        ziaLED.setLength(ZiaComponents.LED_COUNT)
        ziaLED.setData(EMPTY_BUFFER)
        ziaLED.start()

        frameTimeTimer.start()
    }

    override fun periodic() {
        // Reset the animation frame index when the animation changes
        if (lastAnimation != currentAnimation) {
            currentAnimation.resetFrameIndex()
            lastAnimation = currentAnimation
        }

        /*
         * If the frame time has elapsed, advance the frame. If the frame has a gap, wait for the gap to elapse before
         * advancing to the next frame.
         */
        if (frameTimeTimer.hasElapsed(currentAnimation.currentFrame.frameLength)) {
            frameTimeTimer.stop()
            frameTimeTimer.reset()

            withinFrameGap = currentAnimation.currentFrame.frameGap > 0
            if (!withinFrameGap) {
                currentAnimation.advanceFrame()
                frameTimeTimer.start()
                return
            }

            ziaLED.setData(EMPTY_BUFFER)

            frameGapTimer.start()
            return
        }

        // If within a frame gap, wait for the gap to elapse before advancing to the next frame
        if (withinFrameGap && frameGapTimer.hasElapsed(currentAnimation.currentFrame.frameGap)) {
            frameGapTimer.stop()
            frameGapTimer.reset()

            currentAnimation.advanceFrame()
            withinFrameGap = false

            frameTimeTimer.start()
        }

        // If within a frame gap, do not update the LED buffer
        if (withinFrameGap) {
            return
        }

        SmartDashboard.putNumber("Animation/Frame", currentAnimation.currentFrameIndex.toDouble())
        SmartDashboard.putNumber("Animation/Frame Length", currentAnimation.currentFrame.frameLength)
        SmartDashboard.putNumber("Animation/Frame Gap", currentAnimation.currentFrame.frameGap)
        ziaLED.setData(currentAnimation.currentFrame.ledBuffer)
    }
}
