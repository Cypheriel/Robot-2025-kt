package org.hangar84.robot2025.animationsystem

/**
 * Represents an entire animation, consisting of multiple frames.
 * @param frames The frames that make up the animation.
 * @property currentFrameIndex The index of the current frame.
 * @property currentFrame The current frame.
 * @see AnimationFrame
 */
open class Animation(private val frames: List<AnimationFrame>) {
    var currentFrameIndex = 0
        private set

    val currentFrame
        get() = frames[currentFrameIndex]

    fun advanceFrame() {
        currentFrameIndex = (currentFrameIndex + 1) % frames.size
    }

    fun resetFrameIndex() {
        currentFrameIndex = 0
    }
}
