package org.isaaccode.calculategame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme

@Composable
fun BoxText(ft: FontWeight, str: String, backgroundColor: Color = Color.Transparent, widthPerc: Int = 20) {
    Text(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth(widthPerc / 100F)
            ,
        fontWeight = ft,
        fontFamily = FontFamily.Serif,
        text = str,
        fontSize = 40.sp,
        textAlign = TextAlign.Center
    )
}

sealed class ScoreComponentOptions(val isBold: Boolean) {
    object Regular: ScoreComponentOptions(false)
    object Title: ScoreComponentOptions(true)
    object Cumulative: ScoreComponentOptions(true)

    fun fontWeight(): FontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
}

@Composable
fun ScoreComponent(options: ScoreComponentOptions, score: Score) {

    fun Score.resultColor(): Color {
        return when {
            this.points < 0 -> currentTheme.colors.warningColor
            this.points == 0 -> currentTheme.colors.inactiveColor
            this.points > 0 -> currentTheme.colors.accentColor
            else -> currentTheme.colors.warningColor
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(score.resultColor())
            .clip(shape = RoundedCornerShape((5.dp)))
    ) {
        BoxText(options.fontWeight(), score.id.toString(), widthPerc = 20)
        BoxText(options.fontWeight(), score.task, widthPerc = 60)
        BoxText(options.fontWeight(), score.result.toString(), widthPerc = 50)
        BoxText(options.fontWeight(), score.points.toString(), widthPerc = 100)
    }
}