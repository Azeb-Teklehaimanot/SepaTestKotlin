package com.sepa.sepatestkotlin.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sepa.sepatestkotlin.R
import com.sepa.sepatestkotlin.view.fragment.PhoneNumberFragment
import android.provider.Telephony

import android.content.Intent
import android.net.Uri
import android.provider.Settings


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navigateToPhoneNumberFragment()
    }

    override fun onResume() {
        super.onResume()
        navigateToPhoneNumberFragment()
    }

    fun navigateToPhoneNumberFragment() {
        if (packageName != Telephony.Sms.getDefaultSmsPackage(this)) {
            // Ask the user to make this app the default SMS app
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        } else {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhoneNumberFragment.newInstance())
                .commitNow()
        }
    }
}
