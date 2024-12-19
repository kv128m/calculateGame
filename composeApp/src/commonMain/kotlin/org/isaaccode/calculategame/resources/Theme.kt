package org.isaaccode.calculategame.resources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font

sealed class Theme(
    val colors: ThemeColors,
    val fontSizes: ThemeFontSizes,
    val paddingSizes: ThemePaddingSizes
) {


    data object LightTheme : Theme(
        colors = ThemeColors(
            primaryColor = Color(35, 116, 85),
            secondaryColor = Color(0.2f, 0.36f, 0.50f),
            primaryBackgroundColor = Colors.deepBlue,
            secondaryBackgroundColor = Colors.yellow,
            primaryFontColor = Color(24, 24, 24),
            secondaryFontColor = Color(244, 244, 244),
            accentColor = Colors.green,
            warningColor = Colors.red,
            inactiveColor = Colors.gray
        ),
        fontSizes = ThemeFontSizes(
            title = FontSizes.extraLarge,
            smallButton = FontSizes.small,
            largeButton = FontSizes.large,
            tableCell = FontSizes.large
        ),
        paddingSizes = ThemePaddingSizes(
            title = PaddingSizes.large,
            column = PaddingSizes.medium,
            row = PaddingSizes.medium,
            button = PaddingSizes.medium,
            tableCell = PaddingSizes.medium,
            text = PaddingSizes.regular
        )
    )

    companion object {
        var currentTheme: Theme = LightTheme
    }

    object Colors {
        val white = Color(0.95f, 0.95f, 0.95f)
        val red = Color(244, 67,54)
        val green = Color(143, 206, 0)
        val blue = Color(41, 134, 204)
        val yellow = Color(249, 209, 4)
        val gray = Color(170, 170, 170)
        val deepBlue = Color(0.2f, 0.36f, 0.50f)
    }

    data class ThemeColors(
        val primaryColor: Color,
        val secondaryColor: Color,
        val primaryBackgroundColor: Color,
        val secondaryBackgroundColor: Color,
        val primaryFontColor: Color,
        val secondaryFontColor: Color,
        val accentColor: Color,
        val warningColor: Color,
        val inactiveColor: Color
    )

    object FontSizes {
        val small = 12.sp
        val medium = 24.sp
        val large = 32.sp
        val extraLarge = 42.sp
    }

    data class ThemeFontSizes(
        val title: TextUnit,
        val smallButton: TextUnit,
        val largeButton: TextUnit,
        val tableCell: TextUnit,
    )

    object PaddingSizes {
        val small = 12.dp
        val regular = 16.dp
        val medium = 20.dp
        val large = 24.dp
        val extraLarge = 32.dp
    }

    data class ThemePaddingSizes(
        val title: Dp,
        val column: Dp,
        val row: Dp,
        val button: Dp,
        val tableCell: Dp,
        val text: Dp
    )
}