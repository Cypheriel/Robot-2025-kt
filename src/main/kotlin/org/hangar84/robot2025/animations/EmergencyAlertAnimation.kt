package org.hangar84.robot2025.animations

import org.hangar84.robot2025.animationsystem.Animation
import org.hangar84.robot2025.animationsystem.AnimationFrame
import org.hangar84.robot2025.animationsystem.ColorMap
import org.hangar84.robot2025.animationsystem.RGB
import org.hangar84.robot2025.animationsystem.ZiaComponents

object EmergencyAlertAnimation : Animation(
    frames =
        listOf(
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.BRIGHT_BLUE),
                    ),
                frameLength = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.BRIGHT_RED),
                    ),
                frameLength = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.BRIGHT_BLUE),
                    ),
                frameLength = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_CORNERS, RGB.BRIGHT_RED),
                    ),
                frameLength = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING_EDGES, RGB.BRIGHT_BLUE),
                    ),
                frameLength = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BRIGHT_WHITE),
                        ColorMap(ZiaComponents.ALL_RAYS, RGB.BRIGHT_RED),
                    ),
                frameLength = 0.075,
                frameGap = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BRIGHT_WHITE),
                        ColorMap(ZiaComponents.ALL_RAYS, RGB.BRIGHT_RED),
                    ),
                frameLength = 0.075,
                frameGap = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BRIGHT_WHITE),
                        ColorMap(ZiaComponents.ALL_RAYS, RGB.BRIGHT_RED),
                    ),
                frameLength = 0.075,
                frameGap = 0.075,
            ),
            AnimationFrame(
                buffer =
                    listOf(
                        ColorMap(ZiaComponents.CENTER_RING, RGB.BRIGHT_WHITE),
                        ColorMap(ZiaComponents.ALL_RAYS, RGB.BRIGHT_RED),
                    ),
                frameLength = 0.075,
            ),
        ),
)
