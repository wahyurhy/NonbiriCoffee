package com.wahyurhy.nonbiricoffee.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarHostState
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
import com.wahyurhy.nonbiricoffee.R

@Composable
fun PromoCard(onShopNowClick: (SnackbarHostState) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF95674D) // Proper background color for the card
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Section: Texts
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Coffee Yard Time: 9:00 am - 5:30 pm",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFBDBDBD) // Light gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Buy one, Get one for Free",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { onShopNowClick(SnackbarHostState()) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA726), // Orange color
                            contentColor = Color(0xFF4E342E) // Dark brown
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(120.dp)
                            .height(36.dp)
                    ) {
                        Text(
                            text = "Shop Now",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Right Section: Image (outside card)
        Image(
            painter = painterResource(id = R.drawable.coffee_splashed),
            contentDescription = "Coffee Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .align(Alignment.TopEnd) // Align the image to the top-end of the box
                .offset(x = 28.dp, y = (-19).dp) // Move the image outside the card
        )
    }
}