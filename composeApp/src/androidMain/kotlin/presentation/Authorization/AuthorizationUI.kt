package presentation.Authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import ru.mobileup.template.core.theme.custom.CustomTheme


@Composable
fun AuthorizationUI(component: IAuthorizationScreen) {
    val login by component.login.collectAsState(Dispatchers.Main.immediate)
    val password by component.password.collectAsState(Dispatchers.Main.immediate)
    val inProgress by component.inProgress.collectAsState()

    Surface(
        color = CustomTheme.colors.background.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = "Вход",
                style = CustomTheme.typography.title.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Center
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                elevation = 8.dp,
                backgroundColor = CustomTheme.colors.background.primary
                ){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    TextField(
                        value = login,
                        onValueChange = component::onLoginChanged,
                        textStyle = CustomTheme.typography.body.regular,
                        shape = RoundedCornerShape(
                            20.dp
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    TextField(
                        value = password,
                        onValueChange = component::onPasswordChanged,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        textStyle = CustomTheme.typography.body.regular,
                        shape = RoundedCornerShape(
                            20.dp
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    if(inProgress){
                        CircularProgressIndicator()
                    } else{
                        Button(
                            onClick = component::onSignInClick,
                            shape = RoundedCornerShape(100),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = CustomTheme.colors.button.primary
                            ),
                            elevation = ButtonDefaults.elevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ){
                            Text(
                                text = "Войти",
                                modifier = Modifier
                                    .padding(8.dp),
                                color = CustomTheme.colors.text.invert,
                                style = CustomTheme.typography.button.bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AuthorizationUIPreview() {
    AppTheme() {
        AuthorizationUI(FakeAuthorizationScreen())
    }
}

