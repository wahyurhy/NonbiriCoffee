package com.wahyurhy.nonbiricoffee.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahyurhy.nonbiricoffee.R

@Composable
fun MenuList(
    items: List<MenuItem>,
    onItemCountChange: (Int) -> Unit, // Callback to update the cart item count
    modifier: Modifier = Modifier
) {
    // State to track the total count of all items
    var totalItemCount by remember { mutableIntStateOf(0) }

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
                onAddClick = {
                    totalItemCount++
                    onItemCountChange(totalItemCount) // Pass updated total count
                },
                onRemoveClick = {
                    if (totalItemCount > 0) {
                        totalItemCount--
                        onItemCountChange(totalItemCount) // Pass updated total count
                    }
                },
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
    onRemoveClick: () -> Unit, // New callback for removing items
    modifier: Modifier = Modifier
) {
    var itemCount by remember { mutableIntStateOf(0) } // State to track item count

    Box(
        modifier = modifier
            .width(150.dp)
            .padding(vertical = 8.dp)
    ) {
        // Card Component
        Card(
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFEDAD4C)),
            modifier = Modifier.fillMaxWidth()
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
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // Animated Visibility
        AnimatedContent(
            targetState = itemCount > 0, // Condition to switch between states
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
            },
            modifier = Modifier.align(Alignment.BottomCenter).offset(y = 20.dp)
        ) { isItemCountVisible ->
            if (isItemCountVisible) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFEDAD4C))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Decrement Button
                    IconButton(
                        onClick = {
                            if (itemCount > 0) {
                                itemCount--
                                onRemoveClick() // Trigger remove callback
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus), // Replace with your drawable resource
                            contentDescription = "Remove",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Item Count
                    Text(
                        text = "$itemCount",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Increment Button
                    IconButton(
                        onClick = {
                            itemCount++
                            onAddClick() // Trigger add callback
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add), // Replace with your drawable resource
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            } else {
                IconButton(
                    onClick = {
                        itemCount++
                        onAddClick() // Trigger add callback
                    },
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFEDAD4C))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add), // Replace with your drawable resource
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
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