package fr.jazzyza.taxibooking.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple,
    primaryVariant = Purple,
    secondary = Cyan,
    background = BackgroundColor,
    surface = DarkGrey,
    onPrimary = BackgroundColor,
    onSecondary = BackgroundColor,
    onBackground = BackgroundColor,
    onSurface = LightGrey
)


private val LightColorPalette = lightColors(
    primary = Purple,
    primaryVariant = Purple,
    secondary = Cyan,
    background = BackgroundColor,
    surface = LightGrey,
    onPrimary = BackgroundColor,
    onSecondary = BackgroundColor,
    onBackground = BackgroundColor,
    onSurface = LightGrey

)

@Composable
fun TaxiBookingTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}