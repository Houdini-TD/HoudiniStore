package presentation.OrdersFlow.OrderDetailsScreen

//class RealProductDetailsScreen(
//    componentContext: ComponentContext,
//    private val productRepository: IProductRepository,
//    private val chosenEan: ProductEAN
//) : NetworkComponentContext(componentContext), IProductDetailsScreen {
//
//    override val productLoadState = MutableUIStateFlow()
//
//    override val productList = MutableStateFlow(
//        emptyList<StoredProduct>().toMutableList()
//    )
//    private val coroutineScope = componentCoroutineScope()
//
//    init {
//        loadProducts(chosenEan)
//    }
//
//    override fun onRefresh() {
//        loadProducts(chosenEan)
//    }
//
//    override fun loadProducts(productEan: ProductEAN) {
//        val productList = MutableStateFlow<List<StoredProduct>>(emptyList())
//        coroutineScope.launch {
//            productRepository.getProductsByEan(chosenEan).collectRequest(productLoadState, productList)
//            if (productLoadState.value == ComponentState.Success)
//                this@RealProductDetailsScreen.productList.value = productList.value.toMutableList()
//        }
//    }
//
//    override fun saveProductChanged(id: Int, newAmount: Int, newShelf: Int) {
//        val index = productList.value.indexOfFirst { it.id == id }
//        if (index != -1){
//            val oldProduct = productList.value[index]
//            coroutineScope.launch {
//                productRepository.changeProductById(id, oldProduct.copy(amount = newAmount, shelf = newShelf))
//            }
//        }
//    }
//
//    override fun onProductDelete(id: Int) {
//        val index = productList.value.indexOfFirst { it.id == id }
//        if (index != -1){
//            productList.value.removeAt(index)
//            coroutineScope.launch{
//                productRepository.deleteProductById(id)
//            }
//        }
//    }
//}