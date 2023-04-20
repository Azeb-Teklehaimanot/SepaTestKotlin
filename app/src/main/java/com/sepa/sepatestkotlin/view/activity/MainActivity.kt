package com.sepa.sepatestkotlin.view.activity

import android.Manifest.permission.RECEIVE_SMS
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sepa.sepatestkotlin.R
import com.sepa.sepatestkotlin.view.fragment.PhoneNumberFragment

class MainActivity : AppCompatActivity() {

    private val REQUEST_SMS_PERMISSION = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // check if the permission has been granted
        if (ContextCompat.checkSelfPermission(this, RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(RECEIVE_SMS),
                REQUEST_SMS_PERMISSION
            )
        }

        if (savedInstanceState == null) {
            navigateToPhoneNumberFragment()
        }
    }

    // handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_SMS_PERMISSION -> {
                // if the permission is granted
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                  navigateToPhoneNumberFragment()
                } else {
                    // if the permission is not granted, show a message to the user
                    Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
                // handle other permission requests
            }
        }
    }

    fun navigateToPhoneNumberFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PhoneNumberFragment.newInstance())
            .commitNow()
    }
}
