package presentation.SuppliersFlow.SupplierAddFlow.SupplierListScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import domain.product.Inn
import domain.product.Supplier
import kotlinx.coroutines.Dispatchers
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun ProductListScreenUI(component: ISupplierListScreen) {
    val suppliers by component.supplierList.collectAsState(Dispatchers.Main)
    val suppliersLoadState by component.supplersLoadState.collectAsState()

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
                    text = "Поставщики",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(
                    Icons.Filled.Add,
                    contentDescription = "Добавить",
                    tint = CustomTheme.colors.icon.invert
                ) },
                text = { Text(
                    text = "Добавить",
                    color = CustomTheme.colors.text.invert,
                    style = CustomTheme.typography.button.bold
                ) },
                onClick = {component.onNewSupplierClick()},
                backgroundColor = CustomTheme.colors.background.interfacePanel,
                contentColor = CustomTheme.colors.text.invert
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface(modifier = Modifier.padding(it)) {
            SupplierList(suppliers, component::onSupplierClick)
        }
    }
}

@Composable
fun SupplierList(suppliers: List<Supplier>, onClick: (Inn) -> Unit){
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ){
        items(suppliers.size){
            SupplierCard(supplier = suppliers[it], onClick)
        }
    }
}

@Composable
fun SupplierCard(supplier: Supplier, onClick: (Inn) -> Unit){
Button (
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = CustomTheme.colors.background.card
    ),
    onClick = {onClick(supplier.Inn)}
){
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "${supplier.Name}",
            style = CustomTheme.typography.title.h2
        )
        Text(
            text = "Адрес: ${supplier.Adress}",
            style = CustomTheme.typography.body.regular
        )
        Text(
            text = "ИНН: ${supplier.Inn.value}",
            style = CustomTheme.typography.body.regular
        )
        Text(
            text = "Телефон: ${supplier.Phone}",
            style = CustomTheme.typography.body.regular
        )
    }
}
}


@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme() {
        ProductListScreenUI(FakeSupplierListScreen())
    }
}



