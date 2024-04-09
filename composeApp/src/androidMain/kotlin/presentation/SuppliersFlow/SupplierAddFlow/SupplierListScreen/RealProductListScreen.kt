package presentation.SuppliersFlow.SupplierAddFlow.SupplierListScreen

//class RealProductListScreen(
//    componentContext: ComponentContext,
//    private val productRepository: IProductRepository,
//    val onProductChoosen: (ProductEAN) -> Unit
//) : NetworkComponentContext(componentContext), IProductListScreen{
//    override val products = MutableStateFlow(emptyList<ProductSpecs>())
//
//    override val productLoadState = MutableUIStateFlow()
//
//    private val coroutineScope = componentCoroutineScope()
//
//    init {
//        loadProducts()
//    }
//
//    private fun loadProducts(){
//        coroutineScope.launch {
//            productLoadState.value = ComponentState.Loading
//            productRepository.getProductsInStock().collectRequest(productLoadState, products)
//        }
//    }
//
//    override fun onProductClick(choosenEAN: ProductEAN) {
//        onProductChoosen(choosenEAN)
//    }
//
//}