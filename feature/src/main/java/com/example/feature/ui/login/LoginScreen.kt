package com.example.feature.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.example.common.BiometricHelper

@Composable
fun LoginScreen(
    onNavigate: () -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        if (BiometricHelper.checkBiometricAvailability(context)) {
            BiometricHelper.showBiometricDialog(
                activity = context as FragmentActivity,
                onFailed = {
                    onClose.invoke()
                },
                onSuccess = {
                    onNavigate.invoke()
                }
            )
        } else {
            onNavigate.invoke()
        }
    }
}