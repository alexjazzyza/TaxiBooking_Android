package fr.jazzyza.taxibooking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.jazzyza.taxibooking.R

@Composable
fun BottomButton(color: Color, text: String, isEnabled: Boolean = true, onCLick: () -> Unit) {
    Button(onClick = onCLick,
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        enabled = isEnabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color)) {
        Text(text = text,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp)
    }
}

@Composable
fun TextButton(textColor: Color, backgroundColor: Color, text: String, onCLick: () -> Unit) {
    Button(onClick = { onCLick },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor)) {
        Text(text = text,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp)
    }
}

@Composable
fun BackButton(modifier: Modifier, onCLick: () -> Unit) {
    Row(Modifier.padding(20.dp, 15.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_arrow_back_24),
            contentDescription = "arrow_back",
            modifier = modifier.clickable { onCLick() })
    }
}