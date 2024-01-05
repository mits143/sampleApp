package com.example.sampleapp.views.customsViews

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonText(
    text: String,
    modifier: Modifier,
    textAlign: TextAlign
) {
    Text(
        text = text,
        modifier = modifier,
        style = typography.labelSmall,
        color = Color.Black,
        fontSize = 12.sp,
        textAlign = textAlign
    )
}