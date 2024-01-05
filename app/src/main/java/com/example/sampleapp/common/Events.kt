package com.example.sampleapp.common

sealed class Events {
    data class SnackbarEvent(val message: String) : Events()
    data class NavigateEvent(val route: String) : Events()
}