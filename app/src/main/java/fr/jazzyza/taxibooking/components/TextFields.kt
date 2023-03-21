package fr.jazzyza.taxibooking.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import fr.jazzyza.taxibooking.R
import fr.jazzyza.taxibooking.utils.AppColors

@Composable
fun TextInput(text: String, error: String? = null, textLabel: String, keyboardOptions: KeyboardOptions = KeyboardOptions(), keyboardActions: KeyboardActions = KeyboardActions(), maxChar: Int, onTextChanged: (String) -> Unit) {

    OutlinedTextField(value = text,
        onValueChange = {
            if (it.length <= maxChar) onTextChanged(it) },
        label = {TextLabel(labelText = textLabel)},
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = AppColors.mDarkBackground,
            unfocusedIndicatorColor = AppColors.mDarkGrey,
            focusedIndicatorColor = AppColors.mPurple,
            unfocusedLabelColor = AppColors.mDarkGrey,
            focusedLabelColor = AppColors.mPurple),
        singleLine = true,
        isError = error != null
    )
    error?.let { DisplayError(it) }
}

@Composable
fun DisplayError(errorText: String) {
    Text(
        text = errorText,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(color = MaterialTheme.colors.error)
    )
}

@Composable
fun PasswordTextInput(text: String, error: String? = null, textLabel: String, keyboardOptions: KeyboardOptions = KeyboardOptions(), keyboardActions: KeyboardActions = KeyboardActions(), maxChar: Int, onTextChanged: (String) -> Unit) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(value = text,
        onValueChange = { if (it.length <= maxChar) onTextChanged(it) },
        label = {TextLabel(labelText = textLabel)},
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = AppColors.mDarkBackground,
            unfocusedIndicatorColor = AppColors.mDarkGrey,
            focusedIndicatorColor = AppColors.mPurple,
            unfocusedLabelColor = AppColors.mDarkGrey,
            focusedLabelColor = AppColors.mPurple),
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton( onClick = {
                isPasswordVisible = !isPasswordVisible
            })
            { Image(painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_visibility_off_24)
            else painterResource(id = R.drawable.ic_visibility_24),
                contentDescription = "toggle visibility")
            }
        },
        isError = error != null
    )
    error?.let { if (error != "") DisplayError(it) }
}

@Composable
fun TextLabel(labelText: String) {
    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        Text(text = labelText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic)
    }
}