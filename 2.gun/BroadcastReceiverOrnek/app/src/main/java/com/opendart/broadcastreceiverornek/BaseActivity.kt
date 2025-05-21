package com.opendart.broadcastreceiverornek
import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    @SuppressLint("WrongConstant")
    private fun showMessage(isConnected: Boolean) {

        if (!isConnected) {

            val messageToUser = "İnternetinizi kontrol ediniz!"
            mSnackBar = Snackbar.make(
                findViewById(R.id.main), messageToUser,
                Snackbar.LENGTH_LONG
            )
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }


    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

}