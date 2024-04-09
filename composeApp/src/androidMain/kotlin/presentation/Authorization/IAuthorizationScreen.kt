
package presentation.Authorization

import kotlinx.coroutines.flow.StateFlow

interface IAuthorizationScreen{
    val login: StateFlow<String>

    val password: StateFlow<String>

    val inProgress: StateFlow<Boolean>

    fun onLoginChanged(login: String)

    fun onPasswordChanged(password: String)

    fun onSignInClick()
}