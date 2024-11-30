package com.example.fitbites.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeightSelectorWithDropdown(
    weight: String, // Current weight value
    onWeightUpdated: (String) -> Unit // Callback to update the weight
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(weight)) }
    var selectedUnit by remember { mutableStateOf("kg") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp),
        verticalAlignment = Alignment.Bottom
    ) {

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
                    onWeightUpdated(it.text) 
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

        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { isDropdownExpanded = true }
            ) {
                Text(
                    text = selectedUnit,
                    color = Color.Green,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("kg") },
                    onClick = {
                        selectedUnit = "kg"
                        isDropdownExpanded = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = null,
                    trailingIcon = null,
                    enabled = true,
                    colors = MenuDefaults.itemColors(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                )
                DropdownMenuItem(
                    text = { Text("lbs") },
                    onClick = {
                        selectedUnit = "lbs"
                        isDropdownExpanded = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = null,
                    trailingIcon = null,
                    enabled = true,
                    colors = MenuDefaults.itemColors(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewWeightSelectorWithDropdown() {
//    WeightSelectorWithDropdown()
//}


