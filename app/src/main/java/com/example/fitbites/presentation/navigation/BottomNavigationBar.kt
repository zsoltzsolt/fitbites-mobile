package com.example.fitbites.presentation.navigation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavigationItem(
            title = "Chat",
            selectedIcon = Icons.Filled.Chat,
            unselectedIcon = Icons.Outlined.Chat,
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
        ),
    )

    var _selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        FitbitesmobileTheme(dynamicColor = false) {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.background
                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = _selectedItemIndex == index,
                                onClick = {
                                    _selectedItemIndex = index
                                },
                                label = {
                                    Text(text = item.title)
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Transparent
                                ),
                                icon = {
                                    Icon(
                                        imageVector = if (index == _selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title,
                                        tint = if (index == _selectedItemIndex) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.onBackground
                                        }
                                    )
                                }
                            )
                        }
                    }
                },
                content = { }
            )
        }
    }
}



