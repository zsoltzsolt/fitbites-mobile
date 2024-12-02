package com.example.fitbites.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YearsInputField(
    initialYear: String,
    onYearChanged: (String) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(initialYear)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        // Text Field with Underline
        Column(
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    onYearChanged(it.text)
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Green)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "years",
            color = Color.Green,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}

//@Preview
//@Composable
//fun PreviewYearsInputField() {
//    YearsInputField()
//}
