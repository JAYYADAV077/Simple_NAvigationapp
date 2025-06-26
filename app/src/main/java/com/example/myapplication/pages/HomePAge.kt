package com.example.myapplication.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.AuthState
import com.example.myapplication.AuthVIewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier, navController: NavController, vIewModel: AuthVIewModel) {

    val authState = vIewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        when (authState.value) {
             AuthState.Unautheticated -> navController.navigate("Login")
            else -> null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home PAge", fontSize = 40.sp)

        TextButton({vIewModel.singout()}) {
            Text("Sing Out")
        }
    }

}

