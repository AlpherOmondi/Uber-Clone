package com.example.uberclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
    // Getting results
    private lateinit var getResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
    if(result.resultCode == Activity.RESULT_OK){

    }

}
        init()
    }

    public override fun onStart() {
        super.onStart()
        displaySplashScreen()
    }

    private fun displaySplashScreen() {
        //Adding Toast to show that spalashscreen is working
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe() {

            firebaseauth.addAuthStateListener(_listener)

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
            if (user != null) {
                Toast.makeText(this, "Welcome ${user.uid}", Toast.LENGTH_LONG).show()
            } else {
                showLoginLayout()
            }
        }
    }

    private fun showLoginLayout() {
        val authMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.sign_in_ui)
            .setPhoneButtonId(R.id.button_phone_sign_in)
            .setGoogleButtonId(R.id.button_google_sign_in)
            .build()
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAuthMethodPickerLayout(authMethodPickerLayout)
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build()
        getResult.launch(signInIntent)
    }


}
