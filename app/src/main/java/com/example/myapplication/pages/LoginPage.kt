package com.example.myapplication.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.AuthState
import com.example.myapplication.AuthVIewModel

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    vIewModel: AuthVIewModel
) {
    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val authState = vIewModel.authState.observeAsState()

    val context = LocalContext.current

    LaunchedEffect(authState) {
        when(authState.value){
            is AuthState.Autheticated -> navController.navigate("Home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Login Page",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value =  email,
            onValueChange = {email = it},
            label = { Text("Email") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") })

        Spacer(modifier = Modifier.height(10.dp))

        Button({vIewModel.login(email,password)}) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton({navController.navigate("Signup")}) {
            Text("Dont have an Account, Signup")
        }
    }
}