package presentation.StorageFlow.ProductListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat
import core.theme.AppTheme
import domain.product.ProductEAN
import domain.product.ProductSpecs
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun ProductListScreenUI(component: IProductListScreen) {
    val products by component.products.collectAsState()
    val productsLoadState by component.productLoadState.collectAsState()
    val pendingProductsAmount by component.pendingProductsAmount.collectAsState()

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
                    text = "Товары",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
            }
        },
        floatingActionButton = {pendingProductsFloatingButton(pendingProductsAmount, component::recieveProducts)},
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface(modifier = Modifier.padding(it)) {
            if (products.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(products.size) {
                        ProductCard(product = products[it], component::onProductClick)
                    }
                }
            }
        }
    }
}

@Composable
fun pendingProductsFloatingButton(pendingProductsAmount: Int, onClick: () -> Unit){
    if (pendingProductsAmount > 0){
        ExtendedFloatingActionButton(
            icon = { Icon(
                Icons.Filled.Add,
                contentDescription = "Добавить",
                tint = CustomTheme.colors.icon.invert
            ) },
            text = { Text(
                text = "Ожидают принятия: $pendingProductsAmount",
                color = CustomTheme.colors.text.invert,
                style = CustomTheme.typography.button.bold
            ) },
            onClick = onClick,
            backgroundColor = CustomTheme.colors.background.interfacePanel,
            contentColor = CustomTheme.colors.text.invert
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme() {
        ProductListScreenUI(FakeProductListScreen())
    }
}


@Composable
fun ProductCard(product: ProductSpecs, onClick: (ProductEAN) -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 4.dp),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = CustomTheme.colors.background.card
        ),
        elevation = androidx.compose.material.ButtonDefaults.elevation(8.dp),
        onClick = {
            onClick(product.ean)
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    style = CustomTheme.typography.title.regular,
                    color = CustomTheme.colors.text.primary,
                   // maxLines = 1,
                    //overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "EAN: " + product.ean.value,
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary,
                   // maxLines = 1
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

