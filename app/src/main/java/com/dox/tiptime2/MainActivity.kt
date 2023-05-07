package com.dox.tiptime2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dox.tiptime2.ui.theme.Tiptime2Theme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tiptime2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTime2()
                }
            }
        }
    }
}

@Composable
fun TipTime2(){
    var amountInput by remember {mutableStateOf("")}
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tip = calculateTip(amount)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = stringResource(id = R.string.calculate_tip))
        Spacer(modifier = Modifier.height(23.dp))
        EditFieldText(value = amountInput, onValueChange = {amountInput= it})
        Spacer(modifier = Modifier.height(23.dp))
        Text(text = stringResource(id = R.string.Tip_amount, tip))
    }

}

@Composable
fun EditFieldText(value: String, onValueChange: (String) -> Unit){

        TextField(value = value, onValueChange = onValueChange,
            label = { Text(text = stringResource(id = R.string.bill_amount))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
            )
}
@VisibleForTesting
internal fun calculateTip(amount: Double, tipPercent: Double = 15.0): String{
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tiptime2Theme {
        TipTime2()
    }
}