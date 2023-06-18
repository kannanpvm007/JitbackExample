package com.example.jitbackexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jitbackexample.components.InputField
import com.example.jitbackexample.ui.theme.JitbackExampleTheme
import com.example.jitbackexample.widgets.RoundedIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JitbackExampleTheme {
                // A surface container using the 'background' color from the theme

                MyApp {
//                TopHeader()
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JitbackExampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(150.dp)
//            .clip(shape = CircleShape.copy(all = CornerSize(12.dp)))
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))) // 1 WAY
        , color = Color(0x9FDFD3D0)


    ) { // 2 WAy
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val total = "%.2f".format(totalPerPerson)
            Text(text = "Total per person", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "$$total",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )

        }

    }
}

@Preview
@Composable
fun MainContent() {
    Column() {
    BillForm {
        Log.d("TAG", "MainContent------>: $it")
    }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    changeValue: (String) -> Unit
) {
    val totalPersonSate = remember {
        mutableStateOf("")
    }
    val ingressDegreesValue = remember {
        mutableStateOf(1)
    }
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipValue = remember {
        mutableStateOf("0.0")
    }
    val totalPerPerson = remember {
        mutableStateOf(0.0)
    }
    val validState = remember(totalPersonSate.value) {
        totalPersonSate.value.trim().isNotEmpty()

    }
    val tipPertage = (sliderPositionState.value*100).toInt()
    val keyboradControll = LocalSoftwareKeyboardController.current

    TopHeader(totalPerPerson.value)

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)

    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            InputField(valueState = totalPersonSate,
                labelId = "Enter Bill",
                onAction = KeyboardActions {
                    Log.d("TAG", "MainContent:--------> " + totalPersonSate.value.trim())
                    if (!validState) return@KeyboardActions
                    changeValue(totalPersonSate.value.trim())

                    keyboradControll?.hide()
                }

            )
            if (validState) {
                Row(
                    modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Split", modifier = Modifier.align(
                            alignment = Alignment.CenterVertically
                        )
                    )
                    Spacer(modifier = Modifier.width(120.dp))

                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundedIconButton(
                            imageVector = Icons.Default.Remove,
                            onclick = {
                                Log.d("TAG", "BillForm: minis")
                                ingressDegreesValue.value =
                                    if (ingressDegreesValue.value > 1)
                                    ingressDegreesValue.value-1
                                    else 1
                                
                            }
                        )
                        Text(text = "${ingressDegreesValue.value}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp))

                        RoundedIconButton(
                            imageVector = Icons.Default.Add,
                            onclick = {
                                Log.d("TAG", "BillForm: add")


                                ingressDegreesValue.value = ingressDegreesValue.value+1

                            }
                        )

                    }


                }
                Row(modifier=Modifier.padding(horizontal = 3.dp, vertical = 12.dp)) {
                    Text(
                        text = "Tip", modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(200.dp))

                    Text(text = "${tipValue.value}$", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                }
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    modifier=Modifier.padding(horizontal = 3.dp, vertical = 12.dp)) {
                    Text(text = "$tipPertage %")

                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = {
                            Log.d("TAG", "Slider: $it")
                            sliderPositionState.value = it
                            val total = "%.2f".format(calculateTipValue(totalPersonSate.value.toDouble(),sliderPositionState.value))
                            tipValue.value=total
                            val value = calculateTotalPerson(totalPersonSate.value.toDouble(),ingressDegreesValue.value,sliderPositionState.value)
                            Log.e("TAG", "BillForm----->: $value", )
                            totalPerPerson.value=value

                        },
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        steps = 5,
                        onValueChangeFinished = {
                            Log.d("TAG", "BillForm:onValueChangeFinished ")
                        }
                    )
                }
            } else {
                Box() {

                }
            }



        }

    }

}

fun calculateTipValue(value: Double, tipPertage: Float): Double {
    Log.e("TAG", "calculateTipValue: total bill=$value; tippertage=$tipPertage ", )
    return if (value.toString().isNotEmpty() && tipPertage.toString().isNotEmpty())


        (value * tipPertage) /// 100
    else 0.0
}

fun calculateTotalPerson(
    totalBill:Double,split:Int,tipPertage: Float
): Double {
    Log.e("TAG", "calculateTotalPerson: totalBill:$totalBill")
    Log.e("TAG", "calculateTotalPerson: split:$split")
    Log.e("TAG", "calculateTotalPerson: tipPertage:$tipPertage")
    val bill = calculateTipValue(totalBill, tipPertage)+totalBill
    Log.e("TAG", "calculateTotalPerson: bill:$bill")
    return (bill/split)
}


//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JitbackExampleTheme {
        MyApp {
        }
    }
}