package core.network

sealed class ComponentState{
    data object Idle : ComponentState()
    data object Loading : ComponentState()
    class Error(val error: String) : ComponentState()
    data object Success : ComponentState()
}