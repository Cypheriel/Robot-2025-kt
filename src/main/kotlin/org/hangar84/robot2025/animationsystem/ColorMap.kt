package org.hangar84.robot2025.animationsystem

/**
 * A color map that maps LED strip indices to colors.
 */
data class ColorMap(
    val indices: Set<Int>,
    val color: RGB,
)
