package com.example.sampleapp.common

sealed class DrawerScreens(val route: String) {
    data object FS : DrawerScreens("FirstScreen")
    data object SS : DrawerScreens("SecondScreen")
    data object TS : DrawerScreens("ThirdScreen")
}