package com.sepa.sepatestkotlin.view.fragment

import com.sepa.sepatestkotlin.service.SmsBroadcastReceiver
import android.content.IntentFilter
import android.graphics.Paint
import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sepa.sepatestkotlin.R
import com.sepa.sepatestkotlin.databinding.OtpFragmentBinding
import com.sepa.sepatestkotlin.viewModel.OtpViewModel
import com.sepa.sepatestkotlin.viewModel.PhoneNumberViewModel

class OtpFragment : Fragment() {
    private lateinit var binding: OtpFragmentBinding
    private lateinit var smsBroadcastReceiver: SmsBroadcastReceiver
    private val otpViewModel: OtpViewModel by activityViewModels()
    private val phoneNumberViewModel: PhoneNumberViewModel by activityViewModels()
    private var phoneNumber: String? = null // Instance variable to store phone number

    companion object {
        fun newInstance(phoneNumber: String): OtpFragment {
            val fragment = OtpFragment()
            fragment.phoneNumber = phoneNumber // Store the phone number in the instance variable
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.otp_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.otpViewModel = otpViewModel
        binding.phoneViewModel = phoneNumberViewModel
        binding.login.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        // Listen for SMS broadcasts and update the OTP code in the view model
        smsBroadcastReceiver = SmsBroadcastReceiver { otpCode ->
            otpViewModel.otpCode.postValue(otpCode)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        otpViewModel.clearOtpCode()
        val intentFilter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        requireActivity().registerReceiver(smsBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        // Unregister the SMS broadcast receiver
        requireActivity().unregisterReceiver(smsBroadcastReceiver)
    }
}

