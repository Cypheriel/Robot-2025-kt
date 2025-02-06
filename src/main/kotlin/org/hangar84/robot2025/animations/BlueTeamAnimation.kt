package org.hangar84.robot2025.animations

import org.hangar84.robot2025.animationsystem.Animation
import org.hangar84.robot2025.animationsystem.AnimationFrame
import org.hangar84.robot2025.animationsystem.ColorMap
import org.hangar84.robot2025.animationsystem.RGB
import org.hangar84.robot2025.animationsystem.ZiaComponents

object BlueTeamAnimation : Animation(
    frames =
        listOf(
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BLUE),
                        ColorMap(ZiaComponents.RING_1, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.RING_1, RGB.BLUE),
                        ColorMap(ZiaComponents.RING_2, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.RING_2, RGB.BLUE),
                        ColorMap(ZiaComponents.RING_3, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.RING_3, RGB.BLUE),
                        ColorMap(ZiaComponents.ALL_TIPS, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_TIPS, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.BLUE),
                    ),
                frameLength = 0.25,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_TIPS, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.RING_3, RGB.BLUE),
                        ColorMap(ZiaComponents.ALL_TIPS, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.RING_2, RGB.BLUE),
                        ColorMap(ZiaComponents.RING_3, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.RING_1, RGB.BLUE),
                        ColorMap(ZiaComponents.RING_2, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BLUE),
                        ColorMap(ZiaComponents.RING_1, RGB.BLUE),
                    ),
                frameLength = 0.15,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BLUE),
                    ),
                frameLength = 0.15,
                frameGap = 0.15,
            ),
        ),
)
