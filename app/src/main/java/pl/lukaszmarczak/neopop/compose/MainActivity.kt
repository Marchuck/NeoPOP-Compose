@file:OptIn(ExperimentalUnitApi::class)

package pl.lukaszmarczak.neopop.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import pl.lukaszmarczak.neopop.compose.ui.theme.NeoPOPComposeTheme
import pl.lukaszmarczak.neopop.compose.ui.theme.NeoPopButton

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoPOPComposeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.Black
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("NEOPOP")
                        Spacer(Modifier.size(100.dp))
                        NeoPopButton(
                            buttonColor = Color.Yellow,
                            edgesColors = Color.Gray to Color.LightGray,
                            modifier = Modifier.size(150.dp, 50.dp),
                            buttonContent = {
                                Text(text = "Accept", textAlign = TextAlign.Center)
                            }
                        )
                        NeoPopButton(
                            buttonColor = Color.Magenta,
                            edgesColors = Color.LightGray to Color.Gray,
                            modifier = Modifier.size(150.dp, 50.dp),
                            buttonContent = {
                                Text(
                                    color = Color.White,
                                    text = "Reject",
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                        NeoPopButton(
                            buttonColor = Color.Cyan,
                            edgesColors = Color.Gray to Color.LightGray,
                            modifier = Modifier.size(150.dp, 50.dp),
                            buttonContent = {
                                Text(text = "Cancel", textAlign = TextAlign.Center)
                            }
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.Greeting(name: String) {
    Spacer(modifier = Modifier.height(100.dp))
    Text(
        text = "Hello $name!",
        color = Color.White,
        fontSize = TextUnit(24f, TextUnitType.Sp)
    )
}
