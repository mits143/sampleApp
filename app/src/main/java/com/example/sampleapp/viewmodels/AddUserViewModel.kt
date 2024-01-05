package com.example.sampleapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.common.DrawerScreens
import com.example.sampleapp.common.Events
import com.example.sampleapp.common.TextFieldState
import com.example.sampleapp.models.User
import com.example.sampleapp.preferences.UserManager
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    private val _eventData = MutableSharedFlow<Events>()
    val eventData = _eventData.asSharedFlow()

    private val _username = mutableStateOf(TextFieldState())
    val username: State<TextFieldState> = _username

    fun setUsername(value: String) {
        _username.value = username.value.copy(text = value)
    }

    fun setUsernameError(value: String) {
        _username.value = username.value.copy(error = value)
    }

    private val _email = mutableStateOf(TextFieldState())
    val email: State<TextFieldState> = _email

    fun setEmail(value: String) {
        _email.value = email.value.copy(text = value)
    }

    fun setEmailError(value: String) {
        _email.value = email.value.copy(error = value)
    }

    private val _mobile = mutableStateOf(TextFieldState())
    val mobile: State<TextFieldState> = _mobile

    fun setMobile(value: String) {
        _mobile.value = mobile.value.copy(text = value)
    }

    fun setMobileError(value: String) {
        _mobile.value = mobile.value.copy(error = value)
    }

    private val _address = mutableStateOf(TextFieldState())
    val address: State<TextFieldState> = _address

    fun setAddress(value: String) {
        _address.value = address.value.copy(text = value)
    }

    fun setAddressError(value: String) {
        _address.value = address.value.copy(error = value)
    }

    fun addUserData() {
        viewModelScope.launch {
            when {
                username.value.text.isEmpty() -> {
                    setUsernameError("Please enter username")
                    return@launch
                }

                email.value.text.isEmpty() -> {
                    setEmailError("Please enter valid email")
                    return@launch
                }

                mobile.value.text.isEmpty() -> {
                    setMobileError("Please enter valid mobile number")
                    return@launch
                }

                address.value.text.isEmpty() -> {
                    setAddressError("Please enter address")
                    return@launch
                }

                else -> {
                    val userData = User(
                        username.value.text,
                        email.value.text,
                        mobile.value.text,
                        address.value.text
                    )
                    userManager.storeValue(
                        stringPreferencesKey("USERDATA"),
                        Gson().toJson(userData)
                    )
                    _eventData.emit(Events.SnackbarEvent("User data added successfully"))
                    delay(1500)
                    _eventData.emit(Events.NavigateEvent(DrawerScreens.SS.route))
                }
            }

        }
    }
}