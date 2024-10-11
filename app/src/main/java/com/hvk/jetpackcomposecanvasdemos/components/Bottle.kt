package com.hvk.jetpackcomposecanvasdemos.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Bottle(
    modifier: Modifier = Modifier,
    totalWaterAmount: Int,
    unit: String,
    usedWaterAmount: Int,
    colors: DefaultBottleColors = DefaultBottleColors()
) {
    val percentage =
        animateFloatAsState(
            targetValue = usedWaterAmount.toFloat() / totalWaterAmount.toFloat(),
            label = "water wave animation",
            animationSpec = tween(durationMillis = 1_000)
        ).value

    val usedLiquitAmountAnimation =
        animateIntAsState(
            targetValue = usedWaterAmount,
            label = "used liquit amount animation",
            animationSpec = tween(durationMillis = 1_000)
        ).value

    Box(
        modifier = modifier
            .width(200.dp)
            .height(600.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val capWidth = size.width * 0.5f
            val capHeight = size.width * 0.1f

            val bottleBodyPath = Path().apply {
                moveTo(
                    x = width * 0.3f, y = height * 0.1f
                )
                lineTo(
                    x = width * 0.3f, y = height * 0.2f
                )
                quadraticTo(
                    x1 = 0f, y1 = height * 0.3f,
                    x2 = 0f, y2 = height * 0.4f
                )
                lineTo(
                    x = 0f, y = height * 0.95f
                )
                quadraticTo(
                    x1 = 0f, y1 = height,
                    x2 = width * 0.05f, y2 = height
                )
                lineTo(
                    x = width * 0.95f, y = height
                )
                quadraticTo(
                    x1 = width, y1 = height,
                    x2 = width, y2 = height * 0.95f
                )
                lineTo(
                    x = width,
                    y = height * 0.4f
                )
                quadraticTo(
                    x1 = width, y1 = height * 0.3f,
                    x2 = width * 0.7f, y2 = height * 0.2f
                )
                lineTo(
                    x = width * 0.7f,
                    y = height * 0.1f
                )
                close()
            }
            clipPath(bottleBodyPath) {
                drawRect(
                    color = colors.bottleColor,
                    size = size
                )

                val waterWavePos = (1 - percentage) * size.height
                val waterPath = Path().apply {
                    moveTo(
                        x = 0f,
                        y = waterWavePos
                    )
                    lineTo(
                        x = size.width,
                        y = waterWavePos
                    )
                    lineTo(
                        x = size.width,
                        y = size.height
                    )
                    lineTo(
                        x = 0f,
                        y = size.height
                    )
                    close()
                }
                drawPath(
                    path = waterPath,
                    color = colors.accentColor
                )

            }
            drawRoundRect(
                color = colors.capColor,
                size = Size(capWidth, capHeight),
                topLeft = Offset(size.width / 2 - capWidth / 2f, 0f),
                cornerRadius = CornerRadius(10f)
            )
        }
        val text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = if (percentage > .5f) colors.bottleColor else colors.accentColor,
                    fontSize = 44.sp
                )
            ) {
                append(usedLiquitAmountAnimation.toString())
            }
            withStyle(
                style = SpanStyle(
                    color = if (percentage > .5f) colors.bottleColor else colors.accentColor,
                    fontSize = 44.sp
                )
            ) {
                append(" ")
                append(unit)
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    var usedWaterAmount by remember { mutableStateOf(400) }
    val totalWaterAmount = remember { 2400 }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Bottle(
            totalWaterAmount = totalWaterAmount,
            unit = "l",
            usedWaterAmount = usedWaterAmount
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { usedWaterAmount += 100 }) {
            Text("drink...")
        }

    }

}


data class DefaultBottleColors(
    val accentColor: Color = Color(0xFF279EFF),
    val bottleColor: Color = Color(0xFFFFFFFF),
    val capColor: Color = Color(0xFF0065B9)
)