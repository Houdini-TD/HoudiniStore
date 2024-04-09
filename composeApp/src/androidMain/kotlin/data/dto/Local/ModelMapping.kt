package data.dto.Local

import data.dto.Local.Entities.ProductsDbEntity
import domain.product.ProductSpecs

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