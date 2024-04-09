package presentation.OrdersFlow.OrderDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import core.utils.dispatchOnBackPressed
import domain.product.Order
import domain.product.OrderStatus
import domain.product.ProductSpecs
import kotlinx.coroutines.Dispatchers
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun OrderDetailsScreenUI(component: IOrderDetailsScreen) {
    val order by component.order.collectAsState(Dispatchers.Main)
    val orderedProducts by component.orderedProducts.collectAsState(Dispatchers.Main)
    val orderLoadState by component.ordersLoadState.collectAsState()

    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = CustomTheme.colors.background.interfacePanel
            ) {
                IconButton(
                    onClick = { dispatchOnBackPressed(context) }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = CustomTheme.colors.icon.invert
                    )
                }
                Text(
                    text = "Подробности заказа",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DetailedOrderCard(order)
                    ProductList(products = orderedProducts)
                }
        }
    }
}

@Composable
fun ProductList(products: List<ProductSpecs>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        items(products.size) { id ->
            ProductCard(product = products[id])
        }
    }
}

@Composable
fun DetailedOrderCard(order: Order) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (order.status){
                OrderStatus.DELIVERED -> CustomTheme.colors.background.green
                OrderStatus.CANCELED -> CustomTheme.colors.background.red
                OrderStatus.EXECUTING -> CustomTheme.colors.background.card
            }
        )
    ){
        Column {
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()){
                Column (
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Заказ №${order.id}",
                        style = CustomTheme.typography.title.h2
                    )
                    Text(
                        text = "От ${order.date}",
                        style = CustomTheme.typography.body.regular
                    )
                }
                Column (
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Поставщик",
                        style = CustomTheme.typography.body.regular
                    )
                    Text(
                        text = "ИНН: ${order.supplierInn.value}",
                        style = CustomTheme.typography.body.regular
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = order.status.toString(),
                    style = CustomTheme.typography.title.h2)
            }
        }
    }
}


@Composable
fun ProductCard(product: ProductSpecs) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colors.background.card
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    style = CustomTheme.typography.title.regular,
                    color = CustomTheme.colors.text.primary
                )
                Text(
                    text = "EAN: " + product.ean.value,
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Срок хранения: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(product.storageTime.toString())
                        }
                        append(" д.")
                    },
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme(false) {
        OrderDetailsScreenUI(component = FakeOrderDetailsScreen())
    }
}

