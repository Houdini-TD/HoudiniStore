package data.dto

import core.utils.Either
import core.utils.right
import domain.product.Inn
import domain.product.ProductEAN
import domain.product.Supplier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakeSupplierRepositoryImpl : ISuppliersRepository {
    override suspend fun addSuppliedProducts(products: List<ProductEAN>) {

    }

    override suspend fun getSuppliers(newSupplier: Supplier) : Flow<Either<String, List<Supplier>>> {
        return flowOf(suppliers.right())
    }

    companion object{
        val suppliers = listOf<Supplier>(
            Supplier("Органика ТехноЛаб", Inn("123456789"), "Аллея Космических Открытий 42", "+79818051096"),
            Supplier("НаноУниверс Корпорейшн", Inn("987654321"), "Улица Квантовых Странных Полей 7", "+79818051096"),
            Supplier("МегаФьючер Технохаб", Inn("246813579"), "Бульвар Роботизированных Улиц 23", "+79818051096"),
            Supplier("Гравитационная Зона Инноваций", Inn("864209153"), "Проспект Гиперпространственных Ворот 99", "+79818051096"),
            Supplier("ЭлектроФьюжн Лаборатории", Inn("531246978"), "Улица Параллельных Вселенных 11", "+79818051096")
        )
    }
}