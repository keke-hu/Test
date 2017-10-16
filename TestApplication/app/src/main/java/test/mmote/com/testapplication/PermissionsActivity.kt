package test.mmote.com.testapplication

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tbruyelle.rxpermissions.RxPermissions
import org.jetbrains.anko.longToast
import java.util.*

class PermissionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        RxPermissions(this)
                .requestEach(Manifest.permission.RECORD_AUDIO)
                .subscribe { permission ->
                    if (permission.granted) {
                        Log.d("permission--->", "granted   " + permission.name)
                    } else {
                        Log.d("permission--->", "no granted   " + permission.name)
                    }
                }

//        ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.RECORD_AUDIO), 900)
    }
}
