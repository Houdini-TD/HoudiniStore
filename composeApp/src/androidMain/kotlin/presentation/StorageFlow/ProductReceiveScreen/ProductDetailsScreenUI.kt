package presentation.StorageFlow.ProductReceiveScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import core.utils.dispatchOnBackPressed
import domain.product.ProductSpecs
import domain.product.StoredProduct
import kotlinx.coroutines.Dispatchers
import presentation.MyTextField
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun ProductDetailsScreenUI(component: IProductDetailsScreen) {
    val productState by component.productList.collectAsState(Dispatchers.Main.immediate)
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
                    text = "Просмотр позиции",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            if (productState.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PendingProductList(products = productState, component::saveProduct)
                }
            }
        }
    }
}

@Composable
fun PendingProductList(products: List<StoredProduct>, onProductSaveClick: (id: Int, newShelf: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        items(products.size) { id ->
            ProductCard(product = products[id], onProductSaveClick)
        }
    }
}




@Composable
fun ProductCard(product: StoredProduct, onProductSaveClick: (id: Int, newShelf: Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        elevation = 8.dp,
        backgroundColor = CustomTheme.colors.background.card
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
                    text = product.specs.name,
                    style = CustomTheme.typography.title.regular,
                    color = CustomTheme.colors.text.primary
                )
                Text(
                    text = "EAN: " + product.specs.ean.value,
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("От: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(product.productionDate.toString())
                        }
                    },
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary
                )

                Text(
                    text = buildAnnotatedString {
                        append("Срок хранения: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(product.specs.storageTime.toString())
                        }
                        append(" д.")
                    },
                    style = CustomTheme.typography.body.regular,
                    color = CustomTheme.colors.text.primary
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var chosenShelf = 2

                Text(
                    text = "Полка: ",
                    style = CustomTheme.typography.title.regular
                )
                MyTextField(
                    modifier = Modifier.width(60.dp),
                    placeholderText = "000",
                    textStyle = CustomTheme.typography.title.regular,
                    value = if (chosenShelf != 0) chosenShelf.toString() else "",
                    onValueChange = {
                        if (it.length < 4) {
                            chosenShelf = it.toIntOrNull() ?: 0
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    decorationBox = @Composable { content: @Composable () -> Unit ->
                        Card(backgroundColor = Color.LightGray) {
                            Box(Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                                content()
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))

                if (chosenShelf != 0){
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = CustomTheme.colors.button.primary
                        )
                    ) {
                        Text(
                            text = "Сохранить",
                            color = CustomTheme.colors.text.invert,
                            style = CustomTheme.typography.button.bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme(false) {
        ProductDetailsScreenUI(component = FakeProductDetailsScreen())
    }
}

