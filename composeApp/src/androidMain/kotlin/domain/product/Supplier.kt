package domain.product

@JvmInline
value class Inn(val value: String)

data class Supplier(
    val Name: String,
    val Inn: Inn,
    val Adress: String,
    val Phone: String
)
