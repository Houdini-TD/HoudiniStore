package presentation.Authorization.root

import com.arkivanov.decompose.router.stack.ChildStack
import data.dto.Local.AppDatabase
import kotlinx.coroutines.flow.StateFlow
import presentation.Authorization.IAuthorizationScreen
import presentation.StorageFlow.IStorageFlow

interface IRootComponent {
    val childStack : StateFlow<ChildStack<*, Child>>

    sealed interface Child{
        class Authorization(val component: IAuthorizationScreen) : Child
        class Storage(val component: IStorageFlow) : Child
    }
}