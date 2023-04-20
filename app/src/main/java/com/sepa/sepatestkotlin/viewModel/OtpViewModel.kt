package com.sepa.sepatestkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtpViewModel : ViewModel() {
    var otpCode: MutableLiveData<String> = MutableLiveData()
    fun clearOtpCode() {
        otpCode.value = ""
    }
}
