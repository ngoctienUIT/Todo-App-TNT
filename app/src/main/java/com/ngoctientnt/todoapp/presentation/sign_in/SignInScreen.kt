package com.ngoctientnt.todoapp.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.ngoctientnt.todoapp.ui.component.TextFieldTitle
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.core.extension.replace
import com.ngoctientnt.todoapp.core.resource.UIState
import com.ngoctientnt.todoapp.core.router.ScreenRoute

@Composable
fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel = hiltViewModel()) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val signInState by viewModel.signInState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        when (uiState) {
            is UIState.Success -> {
                navController.replace(ScreenRoute.HomeIntro.route)
            }

            is UIState.Error -> {
                Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Welcome Back!",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Text(
                "Your work faster and structured with Todo app",
                color = colorResource(R.color.slateGray),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldTitle(
                title = "Email Address",
                value = signInState.email,
                error = signInState.emailErrMsg,
                onValueChange = { viewModel.onChangeEmailValue(it) },
                placeholder = "name@example.com",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                })
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldTitle(
                title = "Password",
                value = signInState.password,
                error = signInState.passwordErrMsg,
                onValueChange = { viewModel.onChangePasswordValue(it) },
                placeholder = "password",
                onChangeVisiblePassword = { viewModel.onChangePasswordVisibility() },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
                keyboardActions = KeyboardActions(onDone = {}),
                passwordVisible = signInState.passwordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onSignIn() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.lightSeaGreen)
                ),
                enabled = !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                } else {
                    Text("Login")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append("You don't have an account yet? ")
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
                                navController.replace(ScreenRoute.SignUp.route)
                            }
                        )
                    ) {
                        append("Sign up")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    TodoAppTheme {
        SignInScreen(rememberNavController())
    }
}