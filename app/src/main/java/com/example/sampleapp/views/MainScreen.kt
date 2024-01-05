package com.example.sampleapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sampleapp.R
import com.example.sampleapp.common.DrawerScreens
import com.example.sampleapp.ui.theme.SampleAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    SampleAppTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(stringResource(R.string.app_name))
                    }
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.onPrimary,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        )
                ) {
                    SetNavigation(
                        navController,
                        snackbarHostState
                    )
                }
            }
        }
    }
}

@Composable
private fun SetNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = DrawerScreens.FS.route,//viewModel.openScreen().route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(DrawerScreens.FS.route) {
            FirstScreen(snackbarHostState = snackbarHostState, onNavigate = {
                navController.navigate(it)
            })
        }
        composable(DrawerScreens.SS.route) {
            SecondScreen(snackbarHostState = snackbarHostState, onNavigate = {
                navController.navigate(it)
            })
        }
        composable(DrawerScreens.TS.route) {
            ThirdScreen(onNavigate = {
                navController.navigate(it) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SampleAppTheme {
        MainScreen()
    }
}