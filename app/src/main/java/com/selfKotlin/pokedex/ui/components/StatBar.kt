package com.selfKotlin.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StatBar(
    statName: String,
    statValue: Int,
    maxValue: Int = 150
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = statName,
            color = Color.LightGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(75.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(18.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF555255))
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(
                        fraction = (statValue.toFloat() / maxValue)
                            .coerceIn(0f, 1f)
                    )
                    .height(18.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(getStatColor(statName))
            )

            Text(
                text = statValue.toString(),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 4.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun getStatColor(statName: String): Color {
    return when (statName) {
        "HP" -> Color(0xFFE63946)
        "Attack" -> Color(0xFFFFA62B)
        "Defense" -> Color(0xFF219EBC)
        "Sp. Atk" -> Color(0xFF9B5DE5)
        "Sp. Def" -> Color(0xFF43AA8B)
        "Speed" -> Color(0xFFFFD166)
        else -> Color.Gray
    }
}