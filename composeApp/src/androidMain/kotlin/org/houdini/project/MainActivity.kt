package org.houdini.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import core.Dependencies
import core.theme.AppTheme
import presentation.Authorization.root.RealRootComponent
import presentation.root.RootUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Dependencies.init(applicationContext) // Инициализация контекста
        Dependencies.initDatabase() // Инициализация базы данных

        val rootComponent = RealRootComponent(defaultComponentContext())

        setContent {
            AppTheme (false) {
                RootUI(component = rootComponent)
            }
        }
    }
}