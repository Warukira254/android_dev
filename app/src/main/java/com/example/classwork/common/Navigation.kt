package com.example.classwork.common

import SignupScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classwork.presentation.MainViewModel
import com.example.classwork.presentation.common.NotificationMessage
import com.example.classwork.presentation.screens.auth.LoginScreen
import com.example.classwork.presentation.screens.main.MyProfileScreen
import com.example.classwork.presentation.screens.main.MyServicesScreen
import com.example.classwork.presentation.screens.main.SearchScreen
import com.example.classwork.presentation.screens.main.ServiceScreen

@Composable
fun DemandApp() {
    val vm: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    NotificationMessage(vm = vm)
    NavHost(navController = navController, startDestination = Routes.Signup.route) {
        composable(Routes.Profile.route) {
            MyProfileScreen(navController = navController, vm = vm)
        }
        // Import the SignupScreen composable
        composable(Routes.Signup.route) {
            SignupScreen(navController = navController, vm = vm)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(Routes.Services.route) {
            ServiceScreen(navController = navController, vm = vm)
        }
        composable(Routes.Search.route) {
            SearchScreen(navController = navController, vm = vm)
        }
        composable(Routes.MyServices.route) {
            MyServicesScreen(navController = navController, vm = vm)
        }
    }
}