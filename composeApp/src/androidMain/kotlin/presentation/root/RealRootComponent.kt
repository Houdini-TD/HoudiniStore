package presentation.Authorization.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import core.Dependencies
import kotlinx.serialization.Serializable
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.PolymorphicSerializer
import presentation.Authorization.RealAuthorizationScreen
import presentation.StorageFlow.RealStorageFlow
import utils.toStateFlow


class RealRootComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext, IRootComponent  {
    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: StateFlow<ChildStack<*, IRootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Authorization,
        serializer = PolymorphicSerializer(ChildConfig::class),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): IRootComponent.Child = when (config){

        is ChildConfig.Authorization -> {
            IRootComponent.Child.Authorization(
                RealAuthorizationScreen(
                    componentContext,
                    onAuthorizationFinished = {
                        navigation.replaceAll(ChildConfig.StorageFlow)
                    }
                )
            )
        }

        is ChildConfig.StorageFlow -> {
            IRootComponent.Child.Storage(
                RealStorageFlow(
                    componentContext,
                    Dependencies.productRepository
                )
            )
        }
    }

    @Serializable
    private sealed interface ChildConfig{

        @Serializable
        object Authorization: ChildConfig

        @Serializable
        object StorageFlow: ChildConfig
    }
}