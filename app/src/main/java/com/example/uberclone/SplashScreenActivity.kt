package com.example.uberclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuth.getInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import java.util.Arrays
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        private val LOGIN_REQUEST_CODE = 2190
    }

    // Auth providers
    private lateinit var providers: List<AuthUI.IdpConfig>

    // Firebase instance
    private lateinit var firebaseauth: FirebaseAuth

    //Auth state listener
    private lateinit var _listener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    public override fun onStart() {
        super.onStart()
        displaySplashScreen()
    }

    private fun displaySplashScreen() {
        //Adding Toast to show that spalashscreen is working
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe() {
            Toast.makeText(this@SplashScreenActivity, "Welcome to uber...", Toast.LENGTH_LONG)
                .show()

        }
    }

    public override fun onStop() {
        if (firebaseauth != null && _listener != null) {
            firebaseauth.removeAuthStateListener(_listener)
        }
        super.onStop()
    }

    private fun init() {
    providers = Arrays.asList(
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )
        firebaseauth = getInstance()
        _listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if(user != null){
                Toast.makeText(this,"Welcome ${user.uid}", Toast.LENGTH_LONG).show()}
            else{
                showLoginLayout()
            }
            }
        }

    private fun showLoginLayout() {
        val authMethodPickerLayout = AuthMethodPickerLayout(R.layout.)
    }


}
}