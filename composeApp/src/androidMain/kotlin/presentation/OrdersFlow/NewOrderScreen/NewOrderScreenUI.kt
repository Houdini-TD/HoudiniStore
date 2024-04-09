package presentation.OrdersFlow.NewOrderScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import core.utils.advancedShadow
import core.utils.dispatchOnBackPressed
import domain.product.Inn
import domain.product.ProductEAN
import domain.product.ProductSpecs
import domain.product.Supplier
import kotlinx.coroutines.Dispatchers
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun NewOrderScreenUI(component: INewOrderScreen) {
    val supplier by component.choosenSupplier.collectAsState(Dispatchers.Main)
    var context = LocalContext.current
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = CustomTheme.colors.background.interfacePanel
            ) {
                IconButton(onClick = { dispatchOnBackPressed(context) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = CustomTheme.colors.icon.invert
                    )
                }
                Text(
                    text = "Создание заказа",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
                IconButton(onClick = { component::createOrder}) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = CustomTheme.colors.icon.invert
                    )
                }
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            SupplierChoose(supplier)
        }
    }
}

@Composable
fun SupplierDropdownMenu(){
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
}

@Composable
fun SupplierChoose(supplier: Supplier){
    DropdownMenu(
        expanded = true,
        onDismissRequest = {}) {
        
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colors.background.card
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
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

@Composable
fun SmallProductCard(
    product: ProductSpecs,
    onProductClick: (ProductEAN) -> Unit,
    selected: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .advancedShadow(
                blur = 3.dp,
                cornersRadius = 12.dp,
                color = Color.Gray
            )
            .clickable(onClick = { onProductClick(product.ean) }),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                CustomTheme.colors.background.card
            else
                CustomTheme.colors.background.selected
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = product.name,
                    style = CustomTheme.typography.title.regular,
                    color = CustomTheme.colors.text.primary,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "EAN: " + product.ean.value,
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary,
                )
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme() {
        NewOrderScreenUI(FakeNewOrderScreen())
    }
}



