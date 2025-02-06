package org.hangar84.robot2025.animations

import org.hangar84.robot2025.animationsystem.Animation
import org.hangar84.robot2025.animationsystem.AnimationFrame
import org.hangar84.robot2025.animationsystem.ColorMap
import org.hangar84.robot2025.animationsystem.RGB
import org.hangar84.robot2025.animationsystem.ZiaComponents

object ClockAnimation : Animation(
    frames =
        listOf(
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.TOP_RAY_2, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.TOP_RAY_1, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.RIGHT_RAY_3, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.RIGHT_RAY_2, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.RIGHT_RAY_1, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.BOTTOM_RAY_3, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.BOTTOM_RAY_2, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.BOTTOM_RAY_1, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.LEFT_RAY_3, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.LEFT_RAY_2, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.LEFT_RAY_1, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.GREEN),
                        ColorMap(ZiaComponents.TOP_RAY_3, RGB.GREEN),
                    ),
                frameLength = 0.15,
            ),
        ),
)
