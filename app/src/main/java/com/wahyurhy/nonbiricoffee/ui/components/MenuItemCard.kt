package com.wahyurhy.nonbiricoffee.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuList(items: List<MenuItem>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()), // Enable horizontal scrolling
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Space between items
    ) {
        items.forEachIndexed { index, item ->
            MenuItemCard(
                name = item.name,
                description = item.description,
                price = item.price,
                imageRes = item.imageRes,
                onAddClick = { /* Handle Add Click */ },
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 16.dp else 0.dp, // Add padding only to the first item
                        end = if (index == items.size - 1) 16.dp else 0.dp // Add padding only to the last item
                    )
            )
        }
    }
}

@Composable
fun MenuItemCard(
    name: String,
    description: String,
    price: String,
    imageRes: Int,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .padding(vertical = 8.dp)
    ) {
        // Card Component
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFEDAD4C)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Product Image
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                )
                // Product Name
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                // Product Description
                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
                // Product Price
                Text(
                    text = price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF4E342E),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp) // Adjust space above button
                )
            }
        }
        IconButton(
            onClick = { onAddClick() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 20.dp) // Move the button outside the card
                .size(40.dp) // Adjust button size
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp) // Match the size of the button
                    .background(
                        Color(0xFFEDAD4C),
                        shape = CircleShape
                    ) // Set background color and shape
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // Use Material Design Add icon
                    contentDescription = "Add",
                    tint = Color.White, // Set icon color
                    modifier = Modifier.size(24.dp) // Adjust icon size
                )
            }
        }

    }
}

data class MenuItem(
    val name: String,
    val description: String,
    val price: String,
    val imageRes: Int
)