package fr.jazzyza.taxibooking.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String, color: Color, fontSize: Int = 22) {
    Text(text = text,
        fontWeight = FontWeight.ExtraBold,
        fontSize = fontSize.sp,
        color = color)
}

@Composable
fun SubText(text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End) {
        Text(text = text,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = color)
    }
}