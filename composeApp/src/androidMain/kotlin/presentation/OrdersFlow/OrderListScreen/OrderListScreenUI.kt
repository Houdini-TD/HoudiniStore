package presentation.SuppliersFlow.SupplierAddFlow.SupplierListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import domain.product.Order
import domain.product.OrderStatus
import kotlinx.coroutines.Dispatchers
import presentation.StorageFlow.ProductDetailsScreen.FakeOrderListScreen
import presentation.StorageFlow.ProductDetailsScreen.IOrderListScreen
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun OrderListScreenUI(component: IOrderListScreen) {
    val suppliers by component.orderList.collectAsState(Dispatchers.Main)
    val suppliersLoadState by component.ordersLoadState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = CustomTheme.colors.background.interfacePanel
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = CustomTheme.colors.icon.invert
                    )
                }
                Text(
                    text = "Заказы",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(
                    Icons.Filled.Add,
                    contentDescription = "Новый заказ",
                    tint = CustomTheme.colors.icon.invert
                ) },
                text = { Text(
                    text = "Новый заказ",
                    color = CustomTheme.colors.text.invert,
                    style = CustomTheme.typography.button.bold
                ) },
                onClick = {component.onNewOrderClick()},
                backgroundColor = CustomTheme.colors.background.interfacePanel,
                contentColor = CustomTheme.colors.text.invert
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface(modifier = Modifier.padding(it)) {
            OrderList(suppliers, component::onOrderClick)
        }
    }
}

@Composable
fun OrderList(orders: List<Order>, onClick: (Int) -> Unit){
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ){
        items(orders.size){
            OrderCard(order = orders[it], onClick)
        }
    }
}

@Composable
fun OrderCard(order: Order, onClick: (Int) -> Unit){
    Button (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (order.status){
                OrderStatus.DELIVERED -> CustomTheme.colors.background.green
                OrderStatus.CANCELED -> CustomTheme.colors.background.red
                OrderStatus.EXECUTING -> CustomTheme.colors.background.card
            }
        ),
        onClick = {onClick(order.id)}
    ){
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
                    text = "Поставщик:",
                    style = CustomTheme.typography.body.regular
                )
                Text(
                    text = "ИНН: ${order.supplierInn.value}",
                    style = CustomTheme.typography.body.regular
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun OrderListScreenUIPreview() {
    AppTheme() {
        OrderListScreenUI(FakeOrderListScreen())
    }
}



