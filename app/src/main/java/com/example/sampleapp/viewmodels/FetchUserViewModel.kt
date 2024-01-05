package com.example.sampleapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.common.DrawerScreens
import com.example.sampleapp.common.Events
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
class FetchUserViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    private val _eventData = MutableSharedFlow<Events>()
    val eventData = _eventData.asSharedFlow()

    private val _userData = mutableStateOf(User())
    val userData: State<User> = _userData

    private fun setUserData(value: User) {
        _userData.value = value
    }


    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            userManager.readValue(stringPreferencesKey("USERDATA")) {
                val userData = Gson().fromJson(this, User::class.java)
                setUserData(userData)
            }
            delay(1500)
            _eventData.emit(Events.SnackbarEvent("User data fetched successfully"))
            _eventData.emit(Events.NavigateEvent(DrawerScreens.FS.route))

        }
    }
}