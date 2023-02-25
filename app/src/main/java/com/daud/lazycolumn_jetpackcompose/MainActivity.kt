package com.daud.lazycolumn_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daud.lazycolumn_jetpackcompose.ui.theme.LazyColumnJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnJetpackComposeTheme {
                val textInput = remember { mutableStateOf("") }
                val names = remember { mutableStateOf(listOf<String>()) }

                Column(modifier = Modifier.fillMaxSize()) {
                    Card(
                        backgroundColor = Color.LightGray,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(70.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxSize(),
                                textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                                singleLine = true,
                                shape = RoundedCornerShape(10.dp),
                                value = textInput.value,
                                onValueChange = { text ->
                                    textInput.value = text
                                })

                            Button(modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .padding(start = 10.dp),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    when {
                                        textInput.value.isNotBlank() -> {
                                            names.value = names.value + textInput.value
                                            textInput.value = ""
                                        }
                                        else -> {
                                            names.value = emptyList()
                                        }
                                    }
                                }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                Text(text = "Add")
                            }

                        }
                    }
                    if (names.value.isNotEmpty()) {
                        Card(
                            backgroundColor = Color.LightGray,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            LazyColumn {
                                items(names.value) { currentName ->
                                    Card(
                                        backgroundColor = Color.White,
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 5.dp)
                                    ) {
                                        Text(text = currentName, modifier = Modifier.padding(10.dp))
                                    }
                                }
                            }
                        }
                    } else {
                        Card(
                            backgroundColor = Color.LightGray,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Nothing to show here")
                            }
                        }
                    }
                }
            }
        }
    }
}