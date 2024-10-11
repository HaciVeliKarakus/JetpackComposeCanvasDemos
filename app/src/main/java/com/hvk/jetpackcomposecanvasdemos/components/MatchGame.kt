package com.hvk.jetpackcomposecanvasdemos.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random


@Composable
fun MatchGameText(
    text: String
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for ((index, char) in text.withIndex()) {
                RotatingChar(char, index)
            }
        }
    }
}

@Composable
private fun RotatingChar(char: Char, index: Int) {
    val rotation = rememberInfiniteTransition(label = "infinite animation")
    val rotationAngle by rotation.animateFloat(
        initialValue = (index * 20).toFloat(),
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_000 + Random.nextInt(1,10) * 100 , easing = LinearEasing), // 2 saniye
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotate animation"
    )

    // Her harfi döndür
    Text(
        text = char.toString(),
        fontSize = 40.sp,
        modifier = Modifier
            .padding(4.dp)
            .graphicsLayer(rotationZ = rotationAngle)
    )
}

@Preview(showBackground = true)
@Composable
fun MatchGameTextPreview() {
    MatchGameText("MATCH GAME")
}
