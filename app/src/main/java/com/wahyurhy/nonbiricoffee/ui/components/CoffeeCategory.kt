package com.wahyurhy.nonbiricoffee.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoffeeCategoryList(categories: List<CoffeeCategory>, modifier: Modifier = Modifier) {
    // State to keep track of the selected category
    var selectedCategory by remember { mutableStateOf(categories.first().name) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState(), reverseScrolling = false) // Allow horizontal scrolling
            .padding(horizontal = 16.dp), // Maintain padding on the edges
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
    ) {
        categories.forEach { category ->
            CoffeeCategoryItem(
                category = category,
                isSelected = selectedCategory == category.name, // Check if this category is selected
                onClick = { selectedCategory = category.name } // Update the selected category on click
            )
        }
    }
}

@Composable
fun CoffeeCategoryItem(
    category: CoffeeCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() } // Tracks interactions like clicks or presses

    Card(
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFFA726) else Color(0xFFF5F5F5) // Active vs inactive color
        ),
        modifier = Modifier
            .height(70.dp) // Adjusted height to 70.dp
            .padding(vertical = 8.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = true, // Ripple stays within the component
                    color = Color.White // Ripple color
                ),
                onClick = onClick // Handle click events
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight() // Ensures the Row takes the full height of the card
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically // Centers content vertically
        ) {
            // Icon inside a circle
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp) // Circle size adjusted for 70.dp card height
                    .background(
                        if (isSelected) Color.White else Color(0xFFE0E0E0), // Active vs inactive circle background
                        shape = RoundedCornerShape(50)
                    )
            ) {
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = category.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(24.dp) // Icon size adjusted for better proportions
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.name,
                fontSize = 16.sp, // Slightly larger font for better readability
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Color.White else Color.Black // Active vs inactive text color
            )
        }
    }
}

data class CoffeeCategory(val name: String, val icon: Int)