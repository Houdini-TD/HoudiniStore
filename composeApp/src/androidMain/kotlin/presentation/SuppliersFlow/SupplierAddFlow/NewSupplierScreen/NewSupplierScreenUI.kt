package presentation.SuppliersFlow.SupplierAddFlow.NewSupplierScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import core.utils.dispatchOnBackPressed
import kotlinx.coroutines.Dispatchers
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun ProductListScreenUI(component: INewSupplierScreen) {
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
                    text = "Новый поставщик",
                    style = CustomTheme.typography.title.h2,
                    color = CustomTheme.colors.text.invert
                )
                IconButton(onClick = { component::save }) {
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
            NewSupplierForm(component)
        }
    }
}

@Composable
fun NewSupplierForm(component: INewSupplierScreen){
    val name by component.name.collectAsState(Dispatchers.Main)
    val inn by component.inn.collectAsState(Dispatchers.Main)
    val address by component.address.collectAsState(Dispatchers.Main)
    val phone by component.phone.collectAsState(Dispatchers.Main)

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spaceBetween = 6.dp

        Column(modifier = Modifier.padding(top = spaceBetween)) {
            Text(text = "Название", style = CustomTheme.typography.title.h2)
            TextField(
                value = address,
                onValueChange = component::onNameChanged,
                textStyle = CustomTheme.typography.body.regular,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(top = spaceBetween)) {
            Text(text = "Адрес", style = CustomTheme.typography.title.h2)
            TextField(
                value = address,
                onValueChange = component::onAddressChanged,
                textStyle = CustomTheme.typography.body.regular,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(top = spaceBetween)) {
            Text(text = "ИНН", style = CustomTheme.typography.title.h2)
            TextField(
                value = address,
                onValueChange = component::onInnChanged,
                textStyle = CustomTheme.typography.body.regular,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Column(modifier = Modifier.padding(top = spaceBetween)) {
            Text(text = "Контактный телефон", style = CustomTheme.typography.title.h2)
            TextField(
                value = address,
                onValueChange = component::onPhoneChanged,
                textStyle = CustomTheme.typography.body.regular,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = {component::addSuppliedProducts},
            modifier = Modifier.padding(spaceBetween)) {
            Text(
                text = "Доставляемые товары",
                style = CustomTheme.typography.button.bold,
                color = CustomTheme.colors.text.invert,
                modifier = Modifier
                    .padding(8.dp),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme() {
        ProductListScreenUI(FakeProductListScreen())
    }
}



