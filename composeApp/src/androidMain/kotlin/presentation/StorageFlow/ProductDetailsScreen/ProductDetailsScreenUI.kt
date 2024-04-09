package presentation.StorageFlow.ProductDetailsScreen

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
                    ProductCard(productState[0].specs)
                    DetailedProductList(products = productState)
                }
            }
        }
    }
}

@Composable
fun DetailedProductList(products: List<StoredProduct>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(products.size) { id ->
            DetailedProductCard(product = products[id])
        }
    }
}

@Composable
fun DetailedProductCard(product: StoredProduct) {
    Surface (
        border = BorderStroke(width = 2.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "От " + product.productionDate.toString(),
                    style = CustomTheme.typography.title.regular
                )

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        tint = CustomTheme.colors.icon.primary,
                        contentDescription = "Delete",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                var amountChangedState by remember { mutableStateOf(false) }
                var shelfChangedState by remember { mutableStateOf(false) }

                val decorationBox = @Composable { content: @Composable () -> Unit ->
                    Card(backgroundColor = Color.LightGray) {
                        Box(Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                            content()
                        }
                    }
                }

                Column {

                    var amountState by remember { mutableStateOf(product.amount) }
                    Text(
                        text = "Количество: "
                    )
                    MyTextField(
                        placeholderText = "000",
                        modifier = Modifier.width(48.dp),
                        value = if (amountState != 0) amountState.toString() else "",
                        onValueChange = {
                            if (it.length < 4) {
                                amountState = it.toIntOrNull() ?: 0
                                amountChangedState = it != product.amount.toString()
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        decorationBox = decorationBox
                    )
                }

                Column (modifier = Modifier.padding(horizontal = 8.dp)){
                    Text(
                        text = " Полка: "
                    )

                    var shelfState by remember { mutableStateOf(product.shelf) }
                    MyTextField(
                        placeholderText = "000",
                        modifier = Modifier.width(48.dp),
                        value = if (shelfState != 0) shelfState.toString() else "",
                        onValueChange = {
                            if (it.length < 4) {
                                shelfState = it.toIntOrNull() ?: 0
                                shelfChangedState = it != product.shelf.toString()
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        decorationBox = decorationBox
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                if ((shelfChangedState || amountChangedState)){
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


@Composable
fun ProductCard(product: ProductSpecs) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
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
        ProductDetailsScreenUI(component = FakeProductDetailsScreen())
    }
}

