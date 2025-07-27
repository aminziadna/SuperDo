package com.gymkmp.userapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gymkmp.userapp.ui.screens.HomeScreen
import com.gymkmp.userapp.ui.screens.ClassesScreen
import com.gymkmp.userapp.ui.screens.ProfileScreen

@Composable
fun UserAppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToClasses = { navController.navigate("classes") },
                onNavigateToProfile = { navController.navigate("profile") }
            )
        }
        composable("classes") {
            ClassesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}