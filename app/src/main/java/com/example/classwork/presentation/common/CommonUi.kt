package com.example.classwork.presentation.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.classwork.presentation.MainViewModel

@Composable
fun NotificationMessage(vm: MainViewModel) {
    val notifState = vm.popupNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_LONG).show()
    }
}