package com.example.fitbites.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(Color(0xFF32CD32), Color(0xFF228B22)),
    textColor: Color = Color.White,
    textSize: Int = 18
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = Brush.horizontalGradient(colors),
                shape = RoundedCornerShape(50)
            )
    ) {
        Text(text = text, color = textColor, fontSize = textSize.sp)
    }
}
