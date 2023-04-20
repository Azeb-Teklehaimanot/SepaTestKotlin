package com.sepa.sepatestkotlin.utils
import java.util.regex.Pattern

object Extension {

    fun String.isValidPhoneNumber(): Boolean {
        // Regular expression for phone numbers
        // Allows for + symbol at the beginning of the number, and spaces, dots, hyphens, or parentheses as separators
        val regex = """^\+?[\d\s.-]{8,}$"""
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }

    fun String.parseOtpCode(): String? {
        val otpRegex = Regex("(?<!\\d)\\d{6}(?!\\d)") // Match 6-digit OTP codes
        val matchResult = otpRegex.find(this)
        return matchResult?.value
    }
}