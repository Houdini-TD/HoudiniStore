package data.dto.Local

import data.dto.Local.Entities.ProductsDbEntity
import data.dto.Local.Entities.ProductsInStockDbEntity
import data.dto.Local.Tuples.StoredProductWithSpecs
import domain.product.ProductSpecs
import domain.product.StoredProduct

fun ProductsDbEntity.toProductSpecs(): ProductSpecs{
    return ProductSpecs(
        ean = ean,
        name = name,
        storageTime = storageTime
    )
}

fun List<ProductsDbEntity>.toProductSpecs(): List<ProductSpecs>{
    return map {
        it.toProductSpecs()
    }
}

fun StoredProductWithSpecs.toStoredProduct(): StoredProduct {
    return StoredProduct(
        id = id,
        amount = amount,
        specs = ProductSpecs(
            ean = specs.ean,
            name = specs.name,
            storageTime = specs.storageTime
        ),
        shelf = shelfId,
        productionDate = productionDate
    )
}

fun List<StoredProductWithSpecs>.toStoredProduct(): List<StoredProduct>{
    return map {
        it.toStoredProduct()
    }
}

fun StoredProduct.toProductInStock(): ProductsInStockDbEntity{
    return ProductsInStockDbEntity(
        orderedProductId = id,
        shelfId = shelf,
        amount = amount
    )
}