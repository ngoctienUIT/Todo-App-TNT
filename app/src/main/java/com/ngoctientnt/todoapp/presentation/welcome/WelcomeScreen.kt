package com.ngoctientnt.todoapp.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.core.router.ScreenRoute
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 26.sp,
                        )
                    ) {
                        append("Welcome to ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 26.sp,
                            color = colorResource(R.color.lightSeaGreen)
                        )
                    ) { // AnnotatedString.Builder
                        append("Todo App")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(R.drawable.welcome_image),
                contentDescription = "Welcome Image",
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    navController.navigate(ScreenRoute.SignIn.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.lightSeaGreen)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_message),
                        contentDescription = "ic_message",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Continue with email")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    "or continue with",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                SocialButton(
                    icon = R.drawable.ic_github,
                    text = "Github",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                SocialButton(
                    icon = R.drawable.ic_google,
                    text = "Google",
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Composable
fun SocialButton(icon: Int, text: String, modifier: Modifier) {
    Button(
        onClick = {},
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.cultured)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "ic_google",
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text, color = colorResource(R.color.eerieBlack))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    TodoAppTheme {
        WelcomeScreen(rememberNavController())
    }
}