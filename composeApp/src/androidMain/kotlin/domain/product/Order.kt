package domain.product

import java.sql.Date

enum class OrderStatus {
    DELIVERED, EXECUTING, CANCELED;

    override fun toString(): String {
        return when (this){
            DELIVERED -> "Доставлен"
            EXECUTING -> "В обработке"
            CANCELED -> "Отменен"
        }
    }
}

data class Order(
    val id: Int,
    val supplierInn: Inn,
    val status: OrderStatus,
    val date: Date,
)