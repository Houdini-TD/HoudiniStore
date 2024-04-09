package presentation.Authorization

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import utils.componentCoroutineScope

class RealAuthorizationScreen(
    componentContext: ComponentContext,
    val onAuthorizationFinished: () -> Unit
) : ComponentContext by componentContext, IAuthorizationScreen {

    override val login = MutableStateFlow("")
    override val password = MutableStateFlow("")
    override val inProgress = MutableStateFlow(false)

    private val coroutineScope = componentCoroutineScope()

    override fun onLoginChanged(login: String) {
        this.login.value = login
    }

    override fun onPasswordChanged(password: String) {
        this.password.value = password
    }

    override fun onSignInClick() {
        coroutineScope.launch {
            inProgress.value = true
            //TODO: Авторизация, сервер, вся хуйня
            inProgress.value = false
            onAuthorizationFinished()
        }
    }
}