package com.example.classwork.presentation.screens.auth

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.classwork.common.Routes
import com.example.classwork.presentation.MainViewModel
import kotlinx.coroutines.launch

/*
* Add window soft input mode in ActivityManifest.xml
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController, vm: MainViewModel) {
    CheckSignedIn(vm = vm, navController = navController)
    var usernameState by remember { mutableStateOf(TextFieldValue()) }
    var emailState by remember { mutableStateOf(TextFieldValue()) }
    var passState by remember { mutableStateOf(TextFieldValue()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()

                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {



            Text(
                text = "Signup",
                modifier = Modifier.padding(8.dp),

                style = TextStyle(fontSize = 35.sp,
                    color = Color.Magenta,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
            ),
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(
                value = usernameState,
                onValueChange = { usernameState = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Username",
                        style = TextStyle(Color.Black)) },
                textStyle = TextStyle(Color.Black)
            )
            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Enter A Valid Email",
                        style = TextStyle(Color.Black)) },
                textStyle = TextStyle(Color.Black)
            )


            OutlinedTextField(
                value = passState,
                onValueChange = { passState = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(
                    text = "Password",
                            style = TextStyle(Color.Black)
                ) },
                textStyle = TextStyle(
                    color = Color.Black,

               ),
                visualTransformation = PasswordVisualTransformation()
            )


            BlinkingButton(
                navController =  navController, vm =vm,
                usernameState = usernameState, emailState = emailState, passState = passState)

            Text(text = "Already a user? Go to login ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Routes.Login.route)

                    }
            )
        }


    }
}
@Composable
fun BlinkingButton(
    navController: NavController, vm: MainViewModel,
    usernameState: TextFieldValue, emailState: TextFieldValue, passState: TextFieldValue
) {
    var isBlinking by remember { mutableStateOf<Boolean>(false) }

    val coroutineScope = rememberCoroutineScope()

    val alpha by animateFloatAsState(
        targetValue = if (isBlinking) 0.2f else 1f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing), label = ""
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                isBlinking = true
                coroutineScope.launch {
                    val signUpSuccessful = vm.onSignup(
                        usernameState.text,
                        emailState.text,
                        passState.text
                    )
                    if (signUpSuccessful) {
                        navController.navigate(Routes.Login.route)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .alpha(alpha),
            colors = ButtonDefaults.run {
                buttonColors(
                    contentColor = Color.White
                )
            },
            contentPadding = PaddingValues(16.dp),
            content = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "SignUp")
                }
            }
        )
    }
}


@Composable
fun CheckSignedIn(vm: MainViewModel, navController: NavController) {

}
