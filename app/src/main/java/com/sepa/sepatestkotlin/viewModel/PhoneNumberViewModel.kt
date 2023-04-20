package com.sepa.sepatestkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhoneNumberViewModel: ViewModel() {

    var phoneNumber: String = ""
    var countryCodePickerName: String = "BG"
    var countryCodePickerCode: String = "359"


    fun requestOtp() {
        viewModelScope.launch {
            //otpRepository.requestOtp(phoneNumber)
        }
    }

}
