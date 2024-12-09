package com.example.fitbites.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.fitbites.R

@Composable
fun SocialMediaSection(
    onGoogleAuth: () -> Unit,
    onFacebookAuth: () -> Unit,
    onAppleAuth: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        ) {
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            SocialMediaIcon(
                iconRes = R.drawable.google,
                contentDescription = stringResource(R.string.google),
                onClick = onGoogleAuth
            )

            SocialMediaIcon(
                iconRes = R.drawable.facebook,
                contentDescription = stringResource(R.string.facebook),
                onClick = onFacebookAuth
            )

            SocialMediaIcon(
                iconRes = R.drawable.apple,
                contentDescription = stringResource(R.string.apple),
                onClick = onAppleAuth
            )
        }
    }
}

@Composable
fun SocialMediaIcon(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = contentDescription,
        modifier = modifier
            .size(35.dp)
            .clickable { onClick() },
        tint = Color.Unspecified
    )
}