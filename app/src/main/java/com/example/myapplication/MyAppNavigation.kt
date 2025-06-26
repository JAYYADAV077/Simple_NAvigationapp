package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.pages.HomePage
import com.example.myapplication.pages.LoginPage
import com.example.myapplication.pages.SingupPage

@Composable
fun MyAppNAvigation(modifier: Modifier = Modifier,vIewModel: AuthVIewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login", builder = {
        composable("Login"){ LoginPage(modifier,navController,vIewModel) }
        composable("Home"){ HomePage(modifier,navController,vIewModel )}
        composable("Signup"){ SingupPage(modifier,navController,vIewModel) }
    })

}