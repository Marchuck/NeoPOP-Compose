package pl.lukaszmarczak.neopop.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.lukaszmarczak.neopop.compose.ui.theme.NeoPOPComposeTheme
import pl.lukaszmarczak.neopop.compose.ui.theme.NeoPopButton

class MainActivity : ComponentActivity() {
    class VM : ViewModel() {
        val state = MutableLiveData(false)
    }

    val viewModel = VM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoPOPComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    color = MaterialTheme.colors.background
                ) {
                    Column(Modifier.fillMaxWidth()) {

                        Greeting("Hello world!")
                        Spacer(Modifier.size(148.dp))

                        Row(modifier = Modifier.padding(24.dp)) {
                            NeoPopButton(
                                buttonColor = Color.Yellow,
                                buttonText = "Button",
                                edgesColors = Color.DarkGray to Color.Gray,
                                modifier = Modifier.size(200.dp, 48.dp),
                            )
                            NeoPopButton(
                                buttonColor = Color.Cyan,
                                buttonText = "Button",
                                edgesColors = Color.Gray to Color.Green,
                                modifier = Modifier.size(200.dp, 48.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NeoPOPComposeTheme {
        Greeting("Android")
    }
}