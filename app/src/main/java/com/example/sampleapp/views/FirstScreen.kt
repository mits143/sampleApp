package com.example.sampleapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleapp.R
import com.example.sampleapp.common.Events
import com.example.sampleapp.viewmodels.AddUserViewModel
import com.example.sampleapp.views.customsViews.CommonButton
import com.example.sampleapp.views.customsViews.CommonTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FirstScreen(
    viewModel: AddUserViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onNavigate: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                CommonTextField(
                    text = viewModel.username.value.text,
                    onValueChange = {
                        viewModel.setUsername(it)
                        viewModel.setUsernameError("")
                    },
                    hint = stringResource(R.string.enter_username),
                    icon = Icons.Filled.Person,
                    error = viewModel.username.value.error ?: "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })
                )
                Spacer(modifier = Modifier.height(10.dp))
                CommonTextField(
                    text = viewModel.email.value.text,
                    onValueChange = {
                        viewModel.setEmail(it)
                        viewModel.setEmailError("")
                    },
                    hint = stringResource(R.string.enter_email_address),
                    icon = Icons.Filled.Email,
                    error = viewModel.email.value.error ?: "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })
                )
                Spacer(modifier = Modifier.height(10.dp))
                CommonTextField(
                    text = viewModel.mobile.value.text,
                    onValueChange = {
                        viewModel.setMobile(it)
                        viewModel.setMobileError("")
                    },
                    hint = stringResource(R.string.enter_phone_number),
                    icon = Icons.Filled.Phone,
                    error = viewModel.mobile.value.error ?: "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })
                )
                Spacer(modifier = Modifier.height(10.dp))
                CommonTextField(
                    text = viewModel.address.value.text,
                    onValueChange = {
                        viewModel.setAddress(it)
                        viewModel.setAddressError("")
                    },
                    hint = stringResource(R.string.enter_address),
                    icon = Icons.Filled.LocationOn,
                    error = viewModel.address.value.error ?: "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    })
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CommonButton(onClick = {
            keyboardController?.hide()
            viewModel.addUserData()
        }, text = stringResource(R.string.submit))
    }
}

@Preview
@Composable
private fun Preview() {
    FirstScreen(snackbarHostState = SnackbarHostState(), onNavigate = {})
}