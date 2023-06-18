package com.example.jitbackexample.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by kannanpvm007 on 18-06-2023.
 */


val IconButtonSizeModifier = Modifier.size(40.dp)


@Composable
fun RoundedIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onclick: () -> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
    )
) {
    Card(
        modifier = modifier
            .padding(all = 10.dp)
            .clickable { onclick.invoke() },
//            .then(IconButtonSizeModifier),
        shape = CircleShape,
        elevation = elevation) {
        Icon(imageVector = imageVector, contentDescription = "plus or minis", tint = tint)
    }
}