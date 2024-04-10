package data.dto

import core.utils.Either
import core.utils.right
import domain.product.ProductEAN
import domain.product.ProductSpecs
import domain.product.StoredProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

import java.sql.Date

class FakeProductRepositoryImpl: IProductRepository {
    override suspend fun getProductsInStock(): Flow<Either<String, List<ProductSpecs>>> {
        return flowOf(products.right())
    }

    override suspend fun getProducts(): Flow<Either<String, List<ProductSpecs>>> {
        return flowOf(products.right())
    }

    override suspend fun getProductsByEan(ean: ProductEAN): Flow<Either<String, List<StoredProduct>>> {
        val filteredList = detailedProducts.filter {
            it.specs.ean == ean
        }
        return flowOf(filteredList.right())
    }

    override suspend fun updateStoredProduct(newProduct: StoredProduct) {
        TODO("Not yet implemented")
    }
    

    override suspend fun deleteProductById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getPendingProductsAmount() : Flow<Either<String, Int>> {
        return flowOf(5.right())
    }


    companion object{
        val products = listOf(
            ProductSpecs(ProductEAN("342421"), "Картошка", 5),
            ProductSpecs(ProductEAN("111112"), "Яблоки", 7),
            ProductSpecs(ProductEAN("342443"), "Бананы", 3),
            ProductSpecs(ProductEAN("3213124"), "Свекла", 2),
            ProductSpecs(ProductEAN("1231231235"), "Орехи", 10),
            ProductSpecs(ProductEAN("3424216"), "Морковь", 6),
            ProductSpecs(ProductEAN("111117"), "Груши", 4),
            ProductSpecs(ProductEAN("342448"), "Апельсины", 8),
            ProductSpecs(ProductEAN("3213129"), "Лук", 3),
            ProductSpecs(ProductEAN("1231231230"), "Грецкие орехи", 12),
            ProductSpecs(ProductEAN("3424211"), "Брокколи", 4),
            ProductSpecs(ProductEAN("111118"), "Мандарины", 9),
            ProductSpecs(ProductEAN("342442"), "Персики", 6),
            ProductSpecs(ProductEAN("3213123"), "Чеснок", 2),
            ProductSpecs(ProductEAN("1231231234"), "Арахис", 8)
        )

        val detailedProducts = mutableListOf<StoredProduct>().apply {
            for(product in products){
                add(StoredProduct(1, product, 20, 12, Date(2024, 9, 23)))
                add(StoredProduct(2, product, 9, 12, Date(2020, 9, 23)))
                add(StoredProduct(232, product, 202, 23, Date(2024, 2, 23)))
            }
        }
    }
}