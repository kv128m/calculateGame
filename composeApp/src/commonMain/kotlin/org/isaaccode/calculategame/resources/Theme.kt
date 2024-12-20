package org.isaaccode.calculategame.resources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val md_theme_light_primary = Color(0xFF6200EE)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFBB86FC)
val md_theme_light_onPrimaryContainer = Color(0xFF3700B3)

val md_theme_light_secondary = Color(0x4003DAC6)
val md_theme_light_onSecondary = Color(0xFF000000)

val md_theme_light_background = Color(0xFFFFFFFF)
val md_theme_light_onBackground = Color(0xFF000000)

val md_theme_light_surface = Color(0xFFFFFFFF)
val md_theme_light_onSurface = Color(0xFF000000)

val md_theme_light_error = Color(0x40802020)
val md_theme_light_onError = Color(0xFFFFFFFF)

val md_theme_light_disabled = Color(0xFFB0B0B0)  // Disabled text (gray)
val md_theme_light_disabledButton = Color(0xFFE0E0E0)  // Disabled button background
val md_theme_light_disabledIcon = Color(0xFFB0B0B0)  // Disabled icons (gray)


sealed class Theme(
    val colors: ThemeColors,
    val fontSizes: ThemeFontSizes,
    val paddingSizes: ThemePaddingSizes
) {


    data object LightTheme : Theme(
        colors = ThemeColors(
            primaryColor = md_theme_light_primary,
            secondaryColor = md_theme_light_secondary,
            primaryBackgroundColor = md_theme_light_background,
            secondaryBackgroundColor = md_theme_light_onBackground,
            primaryFontColor = md_theme_light_onBackground,
            secondaryFontColor = md_theme_light_onSurface,
            accentColor = md_theme_light_secondary,
            warningColor = md_theme_light_error,
            inactiveColor = md_theme_light_disabled
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