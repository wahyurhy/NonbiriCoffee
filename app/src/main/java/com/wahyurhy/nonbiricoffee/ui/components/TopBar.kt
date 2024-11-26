package com.wahyurhy.nonbiricoffee.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahyurhy.nonbiricoffee.R


@Composable
fun TopBarComponent(
    profileImage: Int,
    location: String,
    onCartClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile Image
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = profileImage),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
                    .clip(CircleShape)
            )
            Text(
                text = location,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4E342E) // Dark brown
            )
        }

        // Action Icons
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cart), // Replace with cart icon
                contentDescription = "Cart Icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp),
                tint = Color(0xFF4E342E) // Dark brown
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_bell), // Replace with notification icon
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp),
                tint = Color(0xFFFFA726) // Orange
            )
        }
    }
}