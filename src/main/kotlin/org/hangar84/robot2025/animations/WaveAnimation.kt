package org.hangar84.robot2025.animations

import org.hangar84.robot2025.animationsystem.Animation
import org.hangar84.robot2025.animationsystem.AnimationFrame
import org.hangar84.robot2025.animationsystem.ColorMap
import org.hangar84.robot2025.animationsystem.RGB
import org.hangar84.robot2025.animationsystem.ZiaComponents

object WaveAnimation : Animation(
    frames =
        listOf(
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.CYAN),
                    ),
                frameLength = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.CYAN),
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.GREEN),
                    ),
                frameLength = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.CYAN),
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_1, RGB.GREEN),
                    ),
                frameLength = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.CYAN),
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_1, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_2, RGB.GREEN),
                    ),
                frameLength = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.CYAN),
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_1, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_2, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_3, RGB.GREEN),
                    ),
                frameLength = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.CYAN),
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_1, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_2, RGB.CYAN),
                        ColorMap(ZiaComponents.RING_3, RGB.CYAN),
                        ColorMap(ZiaComponents.ALL_TIPS, RGB.GREEN),
                    ),
                frameLength = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.CYAN),
                    ),
                frameLength = 1.0,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.CYAN),
                    ),
                frameLength = 0.5,
                frameGap = 0.25,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.ALL_PIXELS, RGB.CYAN),
                    ),
                frameLength = 0.5,
                frameGap = 1.0,
            ),
        ),
)
