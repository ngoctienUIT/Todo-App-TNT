package com.ngoctientnt.todoapp.presentation.on_boarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme

@Composable
fun FirstBoarding() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "app logo"
        )
        Text(
            "Todo App",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "The best to do list application for you",
            color = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FirstBoardingPreview() {
    TodoAppTheme {
        FirstBoarding()
    }
}