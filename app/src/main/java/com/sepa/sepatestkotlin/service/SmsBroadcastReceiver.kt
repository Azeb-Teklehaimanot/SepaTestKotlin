package com.sepa.sepatestkotlin.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.sepa.sepatestkotlin.utils.Extension.parseOtpCode

class SmsBroadcastReceiver(private val onSmsReceived: (otpCode: String) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            messages.forEach { message ->
                val otpCode = message.displayMessageBody.parseOtpCode()
                otpCode?.let(onSmsReceived)
            }
        }
    }
}
