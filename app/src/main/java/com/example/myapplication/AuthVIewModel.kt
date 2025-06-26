package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlin.concurrent.timerTask

class AuthVIewModel: ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        charckAuthStatus()
    }

    fun charckAuthStatus(){
        if (auth.currentUser == null){
            _authState.value = AuthState.Unautheticated
        }else{
            _authState.value = AuthState.Autheticated
        }
    }

    fun login(email: String,password: String){

        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("SomeThingWent Wrong")
            return
        }
        _authState.value = AuthState.Loding
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Autheticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"SomeThingWent Wrong")
                }
            }

    }

    fun Signup(email: String,password: String){

        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("SomeThingWent Wrong")
            return
        }
        _authState.value = AuthState.Loding
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Autheticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"SomeThingWent Wrong")
                }
            }

    }

    fun singout(){
        auth.signOut()
        _authState.value = AuthState.Unautheticated
    }
 }

sealed class AuthState{
    object Autheticated: AuthState()
    object Unautheticated: AuthState()
    object Loding: AuthState()
    data class Error(val message: String) : AuthState()


}