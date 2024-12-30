package com.ngoctientnt.todoapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme

@Composable
fun TextFieldTitle(
    title: String,
    value: String,
    error: String? = null,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    suffix: @Composable (() -> Unit)? = null,
    onChangeVisiblePassword: ((Boolean) -> Unit)? = null,
    passwordVisible: Boolean? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            placeholder = if (placeholder != null) {
                { Text(placeholder) }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.cultured),
                unfocusedContainerColor = colorResource(R.color.cultured),
                focusedBorderColor = colorResource(R.color.azureishWhite),
                unfocusedBorderColor = colorResource(R.color.azureishWhite),
                focusedPlaceholderColor = colorResource(R.color.cadetBlue),
                unfocusedPlaceholderColor = colorResource(R.color.cadetBlue),
            ),
            isError = error != null,
            suffix =
            {
                if (suffix == null && passwordVisible != null)
                    Image(
                        painter = painterResource(
                            if (passwordVisible == true) R.drawable.ic_visibility_off
                            else R.drawable.ic_visibility
                        ),
                        modifier = Modifier.clickable {
                            if (onChangeVisiblePassword != null) onChangeVisiblePassword(!passwordVisible)
                        },
                        colorFilter = ColorFilter.tint(
                            color = colorResource(R.color.cadetBlue),
                            blendMode = BlendMode.SrcIn
                        ),
                        contentDescription = "icon"
                    )
                else if (suffix != null) suffix()
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (passwordVisible != false) VisualTransformation.None else PasswordVisualTransformation(),
        )
        if (error != null) Text(
            error,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Preview
@Composable
fun TextFieldTitlePreview() {
    TodoAppTheme {
        TextFieldTitle(
            title = "Email Address",
            value = "",
            onValueChange = {},
            placeholder = "name@example.com"
        )
    }
}