package com.apd.composestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apd.composestate.ui.theme.ComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen(mainScreenVM: MainScreenVM = viewModel()) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            val text by mainScreenVM.name.observeAsState("")
            val showOutput by mainScreenVM.showOutput.observeAsState(false)
            NameInput(text, onNameChange = { mainScreenVM.onNameChange(it) })
            Spacer(modifier = Modifier.height(10.dp))
            if (mainScreenVM.showOutput.value == true) {
                NameOutput(name = text)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                mainScreenVM.enableShowOutput(!mainScreenVM.showOutput.value!!)
            }) {
                Text(text = "Enable Output")
            }
        }

    }
}

@Composable
fun NameInput(name: String, onNameChange: (value: String) -> Unit) {
    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = "Enter name") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NameOutput(name: String) {
    Text(text = name)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStateTheme {
        MainScreen()
    }
}