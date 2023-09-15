package com.example.permissionhandler

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.permissionhandler.permissionhandler.PermissionCallBack
import com.example.permissionhandler.permissionhandler.PermissionHandler

class MainActivity : AppCompatActivity(), PermissionCallBack {
    private lateinit var permissionHandler: PermissionHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerPermission()
        launchPermission()
    }

    override fun onPermissionCallBack(isGranted: Boolean) {
        Log.d(TAG, "onPermissionCallBack: $isGranted ")
    }

    private fun registerPermission() {
        permissionHandler = PermissionHandler(this)
        permissionHandler.permissionsRegister(this)
    }

    private fun launchPermission() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA)
        permissionHandler.permissionsLaunch(permissions)
    }
    companion object{
        val TAG ="MAIN_ACTIVITY_LOGS"
    }
}