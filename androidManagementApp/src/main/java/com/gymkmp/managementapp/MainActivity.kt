package com.gymkmp.managementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gymkmp.managementapp.ui.theme.GymKMPManagementTheme
import com.gymkmp.managementapp.ui.navigation.ManagementAppNavigation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.gymkmp.shared.di.sharedModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        startKoin {
            androidContext(this@MainActivity)
            modules(sharedModule)
        }
        
        setContent {
            GymKMPManagementTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ManagementAppNavigation()
                }
            }
        }
    }
}