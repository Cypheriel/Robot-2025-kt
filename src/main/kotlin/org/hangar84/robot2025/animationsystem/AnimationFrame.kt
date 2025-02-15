package org.hangar84.robot2025.animationsystem

import edu.wpi.first.wpilibj.AddressableLEDBuffer

/**
 * Represents a single frame of an animation.
 * @param buffer A list of color maps, each representing a strip of LED slices set to a certain color.
 * @param frameLength The duration of this frame in seconds.
 * @param frameGap The duration of the gap between this frame and the next in seconds. Defaults to `0.0`.
 * @see ColorMap
 */
open class AnimationFrame(
    buffer: List<ColorMap>,
    val frameLength: Double,
    val frameGap: Double = 0.0,
) {
    val ledBuffer: AddressableLEDBuffer = AddressableLEDBuffer(ZiaComponents.LED_COUNT)

    init {
        buffer.forEach { colorMap ->
            colorMap.indices.forEach { index ->
                ledBuffer.setRGB(index, colorMap.color.r, colorMap.color.g, colorMap.color.b)
            }
        }
    }
}
