package com.example.baitaptuan3_bai2.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    
    private val auth = FirebaseAuth.getInstance()
    
    private val _currentUser = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError
    
    init {
        auth.addAuthStateListener { firebaseAuth ->
            _currentUser.value = firebaseAuth.currentUser
        }
    }
    
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("557190769478-5d119hakhroc60t1bqbvlfvb64i3agv3.apps.googleusercontent.com")
            .requestEmail()
            .build()
        
        return GoogleSignIn.getClient(context, gso)
    }
    
    fun handleSignInResult(data: Intent?) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w("AuthViewModel", "Google sign in failed", e)
                _authError.value = "Google sign in failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        try {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).await()
            _authError.value = null
        } catch (e: Exception) {
            Log.w("AuthViewModel", "signInWithCredential:failure", e)
            _authError.value = "Authentication failed: ${e.message}"
        }
    }
    
    fun signOut() {
        auth.signOut()
    }
} 