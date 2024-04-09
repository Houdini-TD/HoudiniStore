package presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import presentation.Authorization.AuthorizationUI
import presentation.Authorization.root.IRootComponent
import presentation.Authorization.root.RealRootComponent
import presentation.StorageFlow.RealStorageFlow
import presentation.StorageFlow.StorageFlowUI

@Composable
fun RootUI(component: IRootComponent) {

   val childStack by component.childStack.collectAsState()

   Children(
       stack = childStack,
       animation = stackAnimation(fade() + scale())
   ) {
       when (val child = it.instance){
           is IRootComponent.Child.Authorization -> AuthorizationUI(child.component)
           is IRootComponent.Child.Storage -> StorageFlowUI(child.component)
       }
   }
}