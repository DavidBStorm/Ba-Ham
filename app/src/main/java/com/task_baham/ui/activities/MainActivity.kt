package com.task_baham.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.task_baham.ui.composable.universal.DisplayPermissionNeed
import com.task_baham.ui.theme.Task_BahamTheme
import com.task_baham.util.MainNavigation
import com.task_baham.util.requestPermission
import com.task_baham.viewModel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isPermissionGranted: MutableState<Boolean> = mutableStateOf(false)
    private val mainViewModel: MainViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            isPermissionGranted.value = true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task_BahamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(Modifier.fillMaxSize()) {

                        if (isPermissionGranted.value)
                            MainNavigation(paddingValues = innerPadding)
                        else
                            DisplayPermissionNeed(onRetryClicked = {
                                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            })

                    }
                }
            }

        }

        requestPermission(
            activity = this,
            permissionGranted = {
                isPermissionGranted.value = true

            }, permissionDenied = {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

            })
    }


}