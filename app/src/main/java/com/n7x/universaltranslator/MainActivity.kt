
package com.n7x.universaltranslator

import android.os.Bundle
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            N7XTranslatorApp()
        }
    }
}

@Composable
fun N7XTranslatorApp() {

    var selectedFile by remember {
        mutableStateOf("No book selected")
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            selectedFile = it.lastPathSegment ?: "Book selected"
        }
    }

    val background = Color(0xFF10131C)
    val cardColor = Color(0xFF1C2230)
    val accent = Color(0xFF6C8CFF)

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Column {
                    Text(
                        text = "N7X",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = accent
                    )

                    Text(
                        text = "UNIVERSAL TRANSLATOR",
                        fontSize = 13.sp,
                        color = Color.LightGray
                    )
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Read Without Language Barriers",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(
                            text = "Translate English books into Bangla.",
                            color = Color.LightGray
                        )

                        Button(
                            onClick = {
                                launcher.launch(
                                    arrayOf(
                                        "application/pdf",
                                        "application/epub+zip",
                                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                                        "text/plain"
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accent
                            )
                        ) {
                            Text("＋ Import PDF / eBook")
                        }

                        Text(
                            text = selectedFile,
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }

                Text(
                    text = "TRANSLATION TOOLS",
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )

                FeatureCard("📋", "Copy & Translate", "Translate selected text")
                FeatureCard("📤", "Share to Translate", "Receive text from other apps")
                FeatureCard("📷", "Screen OCR", "Translate text from images")

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "English  →  বাংলা",
                    color = accent,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "N7X Translator • Version 1.0.0",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun FeatureCard(
    emoji: String,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1C2230)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(emoji, fontSize = 25.sp)

            Column {
                Text(
                    title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    description,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}
