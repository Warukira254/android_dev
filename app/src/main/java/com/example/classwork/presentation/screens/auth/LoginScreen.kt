package com.example.classwork.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.classwork.presentation.MainViewModel
import com.example.classwork.presentation.components.BottomComponent
import com.example.classwork.presentation.components.HeadingTextComponent
import com.example.classwork.presentation.components.MyTextFieldComponent
import com.example.classwork.presentation.components.NormalTextComponent
import com.example.classwork.presentation.components.PasswordTextFieldComponent



@Composable
fun LoginScreen(navController: NavHostController, vm: MainViewModel) {
    CheckSignedIn(vm = vm, navController = navController  )
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                NormalTextComponent(value = "Hey, there")
                HeadingTextComponent(value = "Welcome Back")
            }
            Spacer(modifier = Modifier.height(25.dp))
            /**
             * Creates a column layout with two components: `MyTextFieldComponent` for email input and `PasswordTextFieldComponent` for password input.
             *
             * @param labelValue The label value for the text field.
             * @param icon The icon to be displayed next to the text field.
             *
             * @return A column layout with an email text field and a password text field.
             */
            Column {
                MyTextFieldComponent(labelValue = "Email", icon = Icons.Outlined.Email)
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextFieldComponent(labelValue = "Password", icon = Icons.Outlined.Lock)
            }
            /**
             * Renders a bottom component in the UI with the specified text and action.
             *
             * @param textQuery A string representing the query text to be displayed.
             * @param textClickable A string representing the clickable text to be displayed.
             * @param action A string representing the action to be performed.
             * @param navController A `NavHostController` object representing the navigation controller.
             *
             * @return The rendered bottom component in the UI.
             */
            BottomComponent(
                textQuery = "Don't have an account? ",
                textClickable = "Register",
                action = "Login",
                navController
            )
        }
    }
}