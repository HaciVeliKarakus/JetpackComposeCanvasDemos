package com.hvk.jetpackcomposecanvasdemos.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AndroidLogoCanvas() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier.size(300.dp)) {
            val green = Color(0xFF3DDC84)
            val black = Color.Black
            val white = Color.White

            // Gövdeyi çiz
            drawCircle(
                color = green,
                radius = size.minDimension / 2,
                center = center
            )

            // Baş
            val headRadius = size.minDimension * 0.15f
            drawCircle(
                color = green,
                radius = headRadius,
                center = Offset(
                    x = center.x,
                    y = center.y - size.minDimension * 0.35f
                )
            )

            // Gözler
            val eyeRadius = headRadius * 0.3f
            drawCircle(
                color = white,
                radius = eyeRadius,
                center = Offset(
                    x = center.x - headRadius * 0.5f,
                    y = center.y - size.minDimension * 0.35f
                )
            )
            drawCircle(
                color = white,
                radius = eyeRadius,
                center = Offset(
                    x = center.x + headRadius * 0.5f,
                    y = center.y - size.minDimension * 0.35f
                )
            )

            // Antenler
            val antennaPathLeft = Path().apply {
                moveTo(
                    x = center.x - headRadius * 0.5f,
                    y = center.y - size.minDimension * 0.35f - eyeRadius
                )
                lineTo(
                    x = center.x - headRadius * 0.7f,
                    y = center.y - size.minDimension * 0.5f
                )
            }
            val antennaPathRight = Path().apply {
                moveTo(
                    x = center.x + headRadius * 0.5f,
                    y = center.y - size.minDimension * 0.35f - eyeRadius
                )
                lineTo(
                    x = center.x + headRadius * 0.7f,
                    y = center.y - size.minDimension * 0.5f
                )
            }
            drawPath(path = antennaPathLeft, color = black)
            drawPath(path = antennaPathRight, color = black)

            // Kollar
            val armLength = size.minDimension * 0.3f
            drawLine(
                color = black,
                start = Offset(center.x - size.minDimension / 2, center.y),
                end = Offset(
                    center.x - size.minDimension / 2 + armLength,
                    center.y + size.minDimension * 0.2f
                ),
                strokeWidth = 8f
            )
            drawLine(
                color = black,
                start = Offset(center.x + size.minDimension / 2, center.y),
                end = Offset(
                    center.x + size.minDimension / 2 - armLength,
                    center.y + size.minDimension * 0.2f
                ),
                strokeWidth = 8f
            )

            // Bacaklar
            val legLength = size.minDimension * 0.3f
            drawLine(
                color = black,
                start = Offset(
                    x = center.x - size.minDimension * 0.2f,
                    y = center.y + size.minDimension / 2
                ),
                end = Offset(
                    x = center.x - size.minDimension * 0.2f,
                    y = center.y + size.minDimension / 2 + legLength
                ),
                strokeWidth = 8f
            )
            drawLine(
                color = black,
                start = Offset(
                    x = center.x + size.minDimension * 0.2f,
                    y = center.y + size.minDimension / 2
                ),
                end = Offset(
                    x = center.x + size.minDimension * 0.2f,
                    y = center.y + size.minDimension / 2 + legLength
                ),
                strokeWidth = 8f
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AndroidLogoCanvasPreview() {
    AndroidLogoCanvas()
}
