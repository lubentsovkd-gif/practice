package com.example.myapplication

sealed class NavigationState(val route: String, val title: String) {
    object Home : NavigationState("home", "Главная")
    object Profile : NavigationState("profile", "Профиль")
    object Settings : NavigationState("settings", "Настройки")
}

val navigationItems = listOf(
    NavigationState.Home,
    NavigationState.Profile,
    NavigationState.Settings
)