package org.muhammadsayed.bookstorecmp

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.muhammadsayed.bookstorecmp.presentation.components.BottomNavBar
import org.muhammadsayed.bookstorecmp.presentation.navigation.Navigation
import org.muhammadsayed.bookstorecmp.presentation.navigation.NavigationItem
import org.muhammadsayed.bookstorecmp.theme.AppTheme
import org.muhammadsayed.bookstorecmp.utils.changeLocale

@Composable
fun App() {
    PreComposeApp {

        AppTheme {

            val navigator = rememberNavigator()

            val topLevelDestinations = listOf(
                NavigationItem.Home,
                NavigationItem.Categories,
                NavigationItem.Cart,
                NavigationItem.Account
            )

            val isTopLevelDestination =
                navigator.currentEntry.collectAsState(null).value?.route?.route in topLevelDestinations.map { it.route }

            Scaffold(
                bottomBar = {
                    if (isTopLevelDestination) {
                        BottomNavBar(
                            bottomNavItems = topLevelDestinations,
                            navigator = navigator
                        )
                    }
                }
            ) { paddingValues ->

                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Navigation(
                            navigator = navigator
                        ) {
                            changeLocale(it)
                        }
                    }
                }
            }
        }
    }

}



