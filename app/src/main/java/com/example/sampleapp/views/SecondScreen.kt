package com.example.sampleapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleapp.R
import com.example.sampleapp.common.DrawerScreens
import com.example.sampleapp.common.Events
import com.example.sampleapp.viewmodels.FetchUserViewModel
import com.example.sampleapp.views.customsViews.CommonButton
import com.example.sampleapp.views.customsViews.CommonText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SecondScreen(
    viewModel: FetchUserViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onNavigate: (String) -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.eventData.collectLatest { event ->
            when (event) {
                is Events.SnackbarEvent -> {
                    snackbarHostState.showSnackbar(
                        message = event.message, duration = SnackbarDuration.Short
                    )
                }

                is Events.NavigateEvent -> {
                    onNavigate.invoke(event.route)
                }

            }
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    CommonText(
                        text = "Username:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CommonText(
                        text = viewModel.userData.value.username,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    CommonText(
                        text = "Email:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CommonText(
                        text = viewModel.userData.value.email,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    CommonText(
                        text = "Mobile Number:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CommonText(
                        text = viewModel.userData.value.mobile,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    CommonText(
                        text = "Address:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CommonText(
                        text = viewModel.userData.value.address,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Left
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CommonButton(onClick = {
            onNavigate.invoke(DrawerScreens.TS.route)
        }, text = stringResource(R.string.submit))
    }
}

@Preview
@Composable
private fun Preview() {
    SecondScreen(snackbarHostState = SnackbarHostState(), onNavigate = {})
}