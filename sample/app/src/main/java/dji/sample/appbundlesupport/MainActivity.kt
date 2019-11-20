package dji.sample.appbundlesupport

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var splitInstallManager: SplitInstallManager
    private var mSessionId = 0
    private val MY_REQUEST_CODE = 101

    private val listener = SplitInstallStateUpdatedListener { state ->
        if (state.sessionId() == mSessionId) {
            onSessionStateUpdate(state)
        }
        when (state.status()) {
            SplitInstallSessionStatus.INSTALLED -> {
                Toast.makeText(this@MainActivity, "load dynamic success", Toast.LENGTH_SHORT).show()
                sdkTv.isEnabled = true
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splitInstallManager = SplitInstallManagerFactory.create(this)
        loadTv.setOnClickListener {
            loadDynamic()
        }

        sdkTv.setOnClickListener {
            Intent().setClassName(BuildConfig.APPLICATION_ID, "com.dji.sdk.sample.internal.controller.MainActivity")
                .also {
                    startActivity(it)
                }
        }
        sdkTv.isEnabled = false
    }

    private fun loadDynamic() {
        val request = SplitInstallRequest
                .newBuilder()
                .addModule("dynamic_dji_sdk")
                .build()
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener { sessionId ->
                mSessionId = sessionId
            }
            .addOnFailureListener { exception ->
                Toast.makeText(applicationContext, "load fail", Toast.LENGTH_SHORT).show()
            }.addOnCompleteListener {
            }
    }

    private fun onSessionStateUpdate(state: SplitInstallSessionState) {
        if (state.status() == SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION) {
            // Displays a dialog for the user to either “Download”
            // or “Cancel” the request.
            splitInstallManager.startConfirmationDialogForResult(
                state,
                /* activity = */ this,
                /* requestCode = */ MY_REQUEST_CODE
            )
        }
    }

    override fun onResume() {
        splitInstallManager.registerListener(listener)
        super.onResume()
    }

    override fun onPause() {
        splitInstallManager.unregisterListener(listener)
        super.onPause()
    }
}
