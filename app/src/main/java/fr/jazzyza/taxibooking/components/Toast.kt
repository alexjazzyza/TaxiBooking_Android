package fr.jazzyza.taxibooking.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun CustomToast(text: String, length: Int) {
    Toast.makeText(LocalContext.current, text, length).show()
}