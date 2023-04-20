package com.sepa.sepatestkotlin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sepa.sepatestkotlin.R
import com.sepa.sepatestkotlin.databinding.PhoneNumberFragmentBinding
import com.sepa.sepatestkotlin.utils.Extension.isValidPhoneNumber
import com.sepa.sepatestkotlin.viewModel.PhoneNumberViewModel

class PhoneNumberFragment : Fragment() {
    private lateinit var binding: PhoneNumberFragmentBinding

    companion object {
        fun newInstance() = PhoneNumberFragment()
    }

    private val phoneNumberViewModel: PhoneNumberViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.phone_number_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.phoneNumberViewModel = phoneNumberViewModel
        binding.btnContinue.setOnClickListener {
            val phoneNumber = binding.inputEdtTextPhone.text.toString()
            if (phoneNumber.isValidPhoneNumber()) {
                // Make an API call to server that sends the OTP
                phoneNumberViewModel.phoneNumber = phoneNumber
                navigateToOtpFragment(phoneNumber)
            } else {
                // Display error message to user
                binding.inputEdtTextPhone.error = getString(R.string.invalid_phone)
            }
        }

        binding.cpp.setOnCountryChangeListener {
            val countryCode = binding.cpp.selectedCountryCode
            val countryCodeName = binding.cpp.selectedCountryNameCode
            phoneNumberViewModel.countryCodePickerCode = countryCode
            phoneNumberViewModel.countryCodePickerName = countryCodeName
        }

        return binding.root
    }

    fun navigateToOtpFragment(phoneNumber: String) {
        // Create an instance of the OtpFragment and pass the phone number as a parameter
        val otpFragment = OtpFragment.newInstance(phoneNumber)
        // Replace the current fragment with the OtpFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, otpFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        binding.cpp.setDefaultCountryUsingNameCode(phoneNumberViewModel.countryCodePickerName)
    }
}
