package com.example.missedcallalert.ui.Components

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Shadow Component
fun Modifier.bottomEdgeShadow(
    color: Color = Color(0x55000000), // Default semi-transparent black
    shadowHeight: Dp = 10.dp, // Height of the shadow
    cornerRadius: Dp = 0.dp // Rounded corner radius
): Modifier = this.drawBehind {
    drawBottomShadow(color, shadowHeight.toPx(), cornerRadius.toPx())
}

// Shadow Drawing Logic
private fun DrawScope.drawBottomShadow(
    color: Color,
    shadowHeight: Float,
    cornerRadius: Float
) {
    val paint = Paint().apply {
        this.color = color
        this.isAntiAlias = true
    }

    drawIntoCanvas { canvas ->
        canvas.drawRoundRect(
            left = 0f,
            top = size.height, // Start from the bottom edge of the box
            right = size.width,
            bottom = size.height + shadowHeight,
            radiusX = cornerRadius, // Apply corner radius
            radiusY = cornerRadius,
            paint = paint
        )
    }
}
