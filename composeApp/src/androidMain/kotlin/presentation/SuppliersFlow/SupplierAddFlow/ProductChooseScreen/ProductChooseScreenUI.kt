package presentation.SuppliersFlow.SupplierAddFlow.ProductChooseScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import core.utils.advancedShadow
import core.utils.dispatchOnBackPressed
import domain.product.ProductEAN
import domain.product.ProductSpecs
import ru.mobileup.template.core.theme.custom.CustomTheme

@Composable
fun ProductChooseScreenUI(
    component: IProductChooseScreen
) {
    val productList by component.productList.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
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
                    text = "Выбор продуктов",
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
                onClick = {},
                backgroundColor = CustomTheme.colors.background.interfacePanel,
                contentColor = CustomTheme.colors.text.invert
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface(
            color = CustomTheme.colors.background.primary,
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                SmallProductCardList(products = productList, component::onProductChosen)
            }
        }
    }
}

@Composable
fun SmallProductCardList(products: List<ProductSpecs>, onProductClick: (ProductEAN) -> Unit) {
    LazyColumn() {
        items(products.size) {
            SmallProductCard(products[it], onProductClick, true)
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

@Composable
@Preview(showSystemUi = true)
fun ProductChooseScreenPreview() {
    AppTheme {
        ProductChooseScreenUI(component = FakeProductChooseScreen())
    }
}

