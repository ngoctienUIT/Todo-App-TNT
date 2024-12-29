package com.ngoctientnt.todoapp.presentation.on_boarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme

@Composable
fun BoardingComponent() {
    BoxWithConstraints {
        val boxWithConstraintsScope = this
        val yOffset = 0.8 * boxWithConstraintsScope.maxHeight.value

        Box {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(onClick = {}) {
                        Text("Skip", color = colorResource(R.color.lightSeaGreen))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(R.drawable.onboarding_image_1),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(modifier = Modifier.offset(y = yOffset.dp)) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        "Your convenience in making a todo list",
                        color = colorResource(R.color.eerieBlack),
                        fontWeight = FontWeight.W700,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        "Here's a mobile platform that helps you create task or to list so that it can help you in every job easier and faster.",
                        color = colorResource(R.color.slateGray),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardingComponentPreview() {
    TodoAppTheme {
        BoardingComponent()
    }
}