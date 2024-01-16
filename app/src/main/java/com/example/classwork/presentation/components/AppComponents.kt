package com.example.classwork.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.classwork.R
import com.example.classwork.presentation.ui.theme.AccentColor
import com.example.classwork.presentation.ui.theme.BgColor
import com.example.classwork.presentation.ui.theme.GrayColor
import com.example.classwork.presentation.ui.theme.Primary
import com.example.classwork.presentation.ui.theme.Secondary
import com.example.classwork.presentation.ui.theme.TextColor


@Composable
        /**
         * Displays the provided text value as a centered text with a specific style and color.
         *
         * @param value The text value to be displayed.
         */
fun NormalTextComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), style = TextStyle(
            fontSize = 24.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal
        ), color = TextColor, textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn(), style = TextStyle(
            fontSize = 30.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal
        ), color = TextColor, textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /**
         * Creates a text field component with an outlined style.
         *
         * @param labelValue The label for the text field.
         * @param icon The leading icon for the text field.
         */
fun MyTextFieldComponent(labelValue: String, icon: ImageVector) {
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(label = {
        Text(text = labelValue)
    }, value = textValue, onValueChange = {
        textValue = it
    }, colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = AccentColor,
        focusedLabelColor = AccentColor,
        cursorColor = Primary,
        containerColor = BgColor,
        focusedLeadingIconColor = AccentColor,
        textColor = TextColor
    ), modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium, leadingIcon = {
        Icon(
            imageVector = icon, contentDescription = "profile"
        )
    },
        /**
         * Sets the keyboard options for an input field to the default options.
         *
         * Example Usage:
         * ```
         * keyboardOptions = KeyboardOptions.Default
         * ```
         *
         * Inputs:
         * None
         *
         * Flow:
         * 1. Sets the `keyboardOptions` property of an input field.
         * 2. Sets the `keyboardOptions` property to the default options.
         *
         * Outputs:
         * None
         */
        keyboardOptions = KeyboardOptions.Default
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /**
         * Creates a composable function that generates a password text field with a leading icon, a trailing icon for toggling password visibility, and custom styling.
         *
         * @param labelValue The label text for the text field.
         * @param icon The icon to be displayed as the leading icon.
         */
fun PasswordTextFieldComponent(labelValue: String, icon: ImageVector) {
    /**
     * Defines a mutable state variable called 'password' using the 'remember' function from the Jetpack Compose library.
     *
     * Example Usage:
     * ```kotlin
     * var password by remember {
     *     mutableStateOf("")
     * }
     * ```
     *
     * Inputs: None
     *
     * Flow:
     * 1. The 'remember' function is called with a lambda expression as its argument.
     * 2. Inside the lambda expression, the 'mutableStateOf' function is called with an empty string as its argument.
     * 3. The 'mutableStateOf' function returns a mutable state variable of type 'String' with an initial value of an empty string.
     * 4. The 'by' keyword is used to delegate the variable assignment to the mutable state variable returned by 'mutableStateOf'.
     * 5. The 'password' variable is assigned the value of the mutable state variable.
     *
     * Outputs: None
     */
    var password by remember {
        mutableStateOf("")
    }

    /**
     * Defines a mutable state variable to track whether the password text should be visible or hidden.
     *
     * Example Usage:
     * ```
     * var isPasswordVisible by remember {
     *     mutableStateOf(false)
     * }
     * ```
     *
     * Inputs: None
     *
     * Flow:
     * 1. The `remember` function is used to create a mutable state variable called `isPasswordVisible`.
     * 2. The `isPasswordVisible` variable is initialized with a default value of `false`.
     *
     * Outputs:
     * - The `isPasswordVisible` variable is a boolean value that can be used to determine whether the password text should be visible or hidden.
     */
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    /**
     * Creates a password text field component in a Kotlin Compose UI.
     *
     * @param labelValue The label text for the password text field.
     * @param icon The icon to display as the leading icon in the text field.
     * @param password The current value of the password.
     * @param onValueChange A callback function to update the value of the password.
     * @param isPasswordVisible A flag indicating whether the password is currently visible or hidden.
     * @param modifier The modifier for the text field.
     * @param shape The shape of the text field.
     * @param colors The colors for the text field.
     * @param keyboardOptions The keyboard options for the text field.
     * @param visualTransformation The visual transformation for the text field.
     * @param trailingIconClickHandler The click handler for the trailing icon.
     */
    OutlinedTextField(
        label = {
            Text(text = labelValue)
        },
        value = password,
        onValueChange = {
            password = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = AccentColor,
            focusedLabelColor = AccentColor,
            cursorColor = Primary,
            containerColor = BgColor,
            focusedLeadingIconColor = AccentColor,
            textColor = TextColor
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                imageVector = icon, contentDescription = "profile"
            )
        },

        )
}

/**
 * Composable function that displays a checkbox and a clickable text component in a row.
 * Allows the user to toggle the checkbox and provides a callback when the checkbox state changes.
 *
 * Example Usage:
 * ```
 * CheckboxComponent()
 * ```
 *
 * @return A row layout containing a checkbox and a clickable text component.
 * The user can toggle the checkbox, and the `isChecked` state variable will be updated accordingly.
 */
@Composable
fun CheckboxComponent() {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Checkbox(checked = isChecked, onCheckedChange = {
            isChecked = it
        })
        ClickableTextComponent()
    }
}


/**
 * This function creates a clickable text component with styled text. The text consists of an initial text, followed by a privacy policy text, an "and" text, and a term of use text. The text is styled using different colors for different parts. When the user clicks on the text, the function logs a message indicating which part of the text was clicked.
 *
 * Example Usage:
 * ```
 * ClickableTextComponent()
 * ```
 *
 * Inputs: None
 *
 * Outputs: None
 */
@Composable
fun ClickableTextComponent() {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termOfUseText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextColor)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = Secondary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(style = SpanStyle(color = TextColor)) {
            append(andText)
        }
        withStyle(style = SpanStyle(color = Secondary)) {
            pushStringAnnotation(tag = termOfUseText, annotation = termOfUseText)
            append(termOfUseText)
        }
        append(".")
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it).firstOrNull()?.also { annotation ->
                Log.d("ClickableTextComponent", "You have Clicked ${annotation.tag}")
            }
    })
}

/**
 * Composable function that displays a UI component at the bottom of the screen.
 *
 * @param textQuery the query text
 * @param textClickable the clickable text
 * @param action the action text
 * @param navController the NavHostController for navigation
 *
 * Example Usage:
 * BottomComponent("Query", "Clickable", "Action", navController)
 *
 * Code Analysis:
 * - Creates a Box composable that fills the maximum size of the screen and aligns its content at the bottom center.
 * - Inside the Box, a Column composable is created with a modifier that fills the maximum width and centers its content horizontally.
 * - The first child of the Column is a Button with a transparent color and a modifier that fills the maximum width. It has a Box composable as its content, which has a gradient background and a rounded corner shape. Inside the Box, a Text composable displays the action text in white color and with a font size of 20.sp.
 * - After the Button, a Spacer composable with a height of 10.dp is added.
 * - Next, a Row composable is created with a modifier that fills the maximum width and aligns its content vertically at the center.
 * - Inside the Row, two Divider composable are added with a weight of 1f, creating equal spacing on both sides of the "Or" text.
 * - Another Row composable is created with a modifier that fills the maximum width and centers its content horizontally.
 * - Inside the Row, two Button composables are added. Each Button has a transparent color, a padding of 4.dp, and a border with a rounded corner shape. Inside each Button, an Image composable displays an image with a size of 30.dp.
 * - After the Row, a Spacer composable with a height of 15.dp is added.
 * - Finally, an AccountQueryComponent is displayed by calling the AccountQueryComponent function with the provided inputs.
 *
 * Outputs:
 * - The UI component displayed at the bottom of the screen, consisting of a Button, dividers, two buttons with images, and an AccountQueryComponent.
 */
@Composable
fun BottomComponent(
    textQuery: String, textClickable: String, action: String, navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(listOf(Secondary, AccentColor)),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .fillMaxWidth()
                        .heightIn(48.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = action, color = Color.White, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    thickness = 1.dp,
                    color = GrayColor
                )
                Text(
                    text = "Or", modifier = Modifier.padding(10.dp), fontSize = 20.sp
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    thickness = 1.dp,
                    color = GrayColor
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier
                        .padding(4.dp)
                        .border(
                            width = 2.dp,
                            color = Color(android.graphics.Color.parseColor("#d2d2d2")),
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_svg),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier
                        .padding(4.dp)
                        .border(
                            width = 2.dp,
                            color = Color(android.graphics.Color.parseColor("#d2d2d2")),
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_svg),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            AccountQueryComponent(textQuery, textClickable, navController)
        }
    }
}

/**
 * Builds an annotated string with different styles applied to different parts of the string.
 *
 * @param textQuery The main text to be displayed.
 * @param textClickable The clickable text.
 * @param navController The navigation controller used for navigating to different destinations.
 * @return An annotated string with different styles applied to different parts of the string.
 */
@Composable
fun AccountQueryComponent(
    textQuery: String, textClickable: String, navController: NavHostController
) {
    val annonatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = TextColor, fontSize = 15.sp)) {
            append(textQuery)
        }
        withStyle(style = SpanStyle(color = Secondary, fontSize = 15.sp)) {
            pushStringAnnotation(tag = textClickable, annotation = textClickable)
            append(textClickable)
        }
    }

    /**
     * A Composable function that creates a ClickableText component with annotations for navigation actions.
     *
     * @param text The text to be displayed in the ClickableText component.
     * @param onClick The callback function to be executed when the text is clicked.
     */
    @Composable

    fun createClickableText(text: AnnotatedString, onClick: () -> Unit) {
        ClickableText(text = text, onClick = {
            text.getStringAnnotations(it, it).firstOrNull()?.also { annotation ->
                    when (annotation.item) {
                        "Login" -> navController.navigate("Login")
                        "Register" -> navController.navigate("Signup")
                    }
                }
        })
    }
}