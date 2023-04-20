# SepaTestKotlin
Question  _____ can sms reading be done without asking for permission?
Anawer    _____ implemented on branch NonPermission

Reading SMS messages without requesting permission is only possible if an app is set as the default SMS app on the device, and has declared the SMS_DELIVER intent filter in your app's manifest file. If these conditions are met, it read SMS messages without requesting permission.

1. Implementation SmsBroadcastReceiver that can read SMS messages without requesting permission:
2. And in project manifest file, declare the SMS_DELIVER intent filter and set the BROADCAST_SMS permission:
  <receiver
    android:name=".service.SmsBroadcastReceiver"
    android:enabled="true"
    android:exported="true"
    android:permission="android.permission.BROADCAST_SMS">
    <intent-filter>
        <action android:name="android.provider.Telephony.SMS_DELIVER" />
    </intent-filter>
 </receiver>
 
 3.Check if your app is currently set as the default SMS app. e.g 
    if (packageName != Telephony.Sms.getDefaultSmsPackage(this)) {
    // Ask the user to make this app the default SMS app
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}
