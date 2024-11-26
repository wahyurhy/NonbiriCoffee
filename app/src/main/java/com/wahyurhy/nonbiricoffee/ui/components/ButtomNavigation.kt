import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahyurhy.nonbiricoffee.R

@Composable
fun BottomNavigationBar() {
    // State to keep track of the selected item
    val selectedItem = remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color(0xFFFFF8E1), // Light cream color
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp) // Height of the bottom navigation bar
    ) {
        // Navigation items
        NavigationBarItem(
            selected = selectedItem.value == 0,
            onClick = { selectedItem.value = 0 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home), // Replace with your home icon
                    contentDescription = "Home",
                    tint = if (selectedItem.value == 0) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            label = {
                Text(
                    text = "Home",
                    fontSize = 10.sp,
                    color = if (selectedItem.value == 0) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            alwaysShowLabel = true
        )

        NavigationBarItem(
            selected = selectedItem.value == 1,
            onClick = { selectedItem.value = 1 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite), // Replace with your favorite icon
                    contentDescription = "Favorites",
                    tint = if (selectedItem.value == 1) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            label = {
                Text(
                    text = "Favorites",
                    fontSize = 10.sp,
                    color = if (selectedItem.value == 1) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            alwaysShowLabel = true
        )

        NavigationBarItem(
            selected = selectedItem.value == 2,
            onClick = { selectedItem.value = 2 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_grid), // Replace with your grid icon
                    contentDescription = "Grid",
                    tint = if (selectedItem.value == 2) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            label = {
                Text(
                    text = "Grid",
                    fontSize = 10.sp,
                    color = if (selectedItem.value == 2) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            alwaysShowLabel = true
        )

        NavigationBarItem(
            selected = selectedItem.value == 3,
            onClick = { selectedItem.value = 3 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile), // Replace with your profile icon
                    contentDescription = "Profile",
                    tint = if (selectedItem.value == 3) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            label = {
                Text(
                    text = "Profile",
                    fontSize = 10.sp,
                    color = if (selectedItem.value == 3) Color(0xFFFFA726) else Color(0xFFBDBDBD)
                )
            },
            alwaysShowLabel = true
        )
    }
}