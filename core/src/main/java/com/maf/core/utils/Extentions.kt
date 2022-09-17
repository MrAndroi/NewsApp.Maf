package com.maf.core.utils

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.maf.core.R

fun Fragment.openUrl(url: String?) {
    if (url.isNullOrEmpty()) return
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    } catch (e: Exception) {
        return
    }
}


fun Fragment.showPrimaryMessage(message: String, duration: Int = 1000) {
    Snackbar.make(requireView(), message, duration)
        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.purple_500))
        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        .show()
}