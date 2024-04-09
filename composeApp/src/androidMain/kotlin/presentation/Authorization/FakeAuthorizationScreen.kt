package presentation.Authorization

import kotlinx.coroutines.flow.MutableStateFlow

class FakeAuthorizationScreen : IAuthorizationScreen  {
    override val login = MutableStateFlow("Login")
    override val password = MutableStateFlow("Password")
    override val inProgress = MutableStateFlow(false)

    override fun onLoginChanged(login: String) = Unit
    override fun onPasswordChanged(password: String) = Unit
    override fun onSignInClick() = Unit
}