package com.example.permissionhandler.permissionhandler

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class PermissionHandler(private val activityOrFragment: Any) {
    private lateinit var launcher: ActivityResultLauncher<Array<String>>
    private var callback: PermissionCallBack? = null

    fun permissionsRegister(callback: PermissionCallBack) {
        this.callback = callback
        launcher = when (activityOrFragment) {
            is Fragment -> {
                activityOrFragment.registerForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    if (permissions.containsValue(false)) {
                        // Not all permissions are granted, show message or handle it in some other way
                        // ...
                        permissionsResult(false)
                    } else {
                        // All permissions are granted, continue with operation
                        // ...
                        permissionsResult(true)
                    }
                }
            }

            is ComponentActivity -> {
                activityOrFragment.registerForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    if (permissions.containsValue(false)) {
                        // Not all permissions are granted, show message or handle it in some other way
                        // ...
                        permissionsResult(false)
                    } else {
                        // All permissions are granted, continue with operation
                        // ...
                        permissionsResult(true)
                    }
                }
            }

            else -> throw IllegalArgumentException("Invalid type: ${activityOrFragment.javaClass.name}, probably this happened because not use in fragment or activity :) ")
        }
    }

    fun permissionsLaunch(permissionArray: Array<String>) {
        launcher.launch(permissionArray)
    }

    private fun permissionsResult(isGranted: Boolean) {
        callback?.onPermissionCallBack(isGranted)
    }


}