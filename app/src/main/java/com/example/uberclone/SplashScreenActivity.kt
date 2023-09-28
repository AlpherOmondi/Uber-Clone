package com.example.uberclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Adding Toast to show that spalashscreen is working
        Completable.timer(3,TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(){
            Toast.makeText(this@SplashScreenActivity, "Welcome to uber...",Toast.LENGTH_LONG ).show()

        }
        }
}