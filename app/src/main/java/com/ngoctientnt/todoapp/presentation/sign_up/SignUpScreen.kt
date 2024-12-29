package com.ngoctientnt.todoapp.presentation.sign_up

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.core.extension.replace
import com.ngoctientnt.todoapp.core.router.ScreenRoute
import com.ngoctientnt.todoapp.ui.component.TextFieldTitle
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme

@Composable
fun SignUpScreen(navController: NavHostController) {
    val focusManager = LocalFocusManager.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Create account",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                "Create your account and feel the benefits",
                color = colorResource(R.color.slateGray),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldTitle(
                title = "Full Name",
                value = "",
                onValueChange = {},
                placeholder = "Trần Ngọc Tiến",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldTitle(
                title = "Email Address",
                value = "",
                onValueChange = {},
                placeholder = "name@example.com",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldTitle(
                title = "Password",
                value = "",
                onValueChange = {},
                placeholder = "Password",
                onChangeVisiblePassword = {},
                passwordVisible = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldTitle(
                title = "Confirm Password",
                value = "",
                onValueChange = {},
                placeholder = "Confirm password",
                onChangeVisiblePassword = {},
                passwordVisible = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.lightSeaGreen)
                )
            ) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append("You have an account? ")
                    }
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "SIGN_UP",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = colorResource(R.color.lightSeaGreen),
                                ),
                            ),
                            linkInteractionListener = {
                                navController.replace(ScreenRoute.SignIn.route)
                            }
                        )
                    ) {
                        append("Sign in")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    TodoAppTheme {
        SignUpScreen(rememberNavController())
    }
}