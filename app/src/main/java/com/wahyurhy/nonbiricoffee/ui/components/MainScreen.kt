package com.wahyurhy.nonbiricoffee.ui.components

import BottomNavigationBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wahyurhy.nonbiricoffee.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val coffeeCategories = listOf(
        CoffeeCategory("Cappuccino", R.drawable.ic_cappucino),
        CoffeeCategory("Espresso", R.drawable.ic_espresso),
        CoffeeCategory("Latte", R.drawable.ic_coffee_latte),
        CoffeeCategory("Mocha", R.drawable.ic_mocha),
        CoffeeCategory("Macchiato", R.drawable.ic_macchiato)
    )

    val menuItems = listOf(
        MenuItem("Cappuccino", "Turkish Coffee", "$25.00", R.drawable.cappuccino),
        MenuItem("Cappuccino", "Brown ceramic mug", "$25.00", R.drawable.cappuccino_brown),
        MenuItem("Latte", "With extra milk", "$30.00", R.drawable.latte),
        MenuItem("Espresso", "Small and strong", "$20.00", R.drawable.espresso)
    )

    // State to track the total cart item count
    var cartItemCount by remember { mutableIntStateOf(0) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopBarComponent(
                profileImage = R.drawable.squareme, // Replace with your profile image drawable
                location = "Lagos, Nigeria",
                cartItemCount = cartItemCount, // Pass the cart item count
                onCartClick = { /* Handle cart click */ },
                onNotificationClick = { /* Handle notification click */ }
            )
        },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            SearchBar()

            // Use a Box to overlap CoffeeCategoryList with PromoCard
            Box(modifier = Modifier.fillMaxWidth()) {
                PromoCard { snackbarState ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Shop Now clicked!")
                    }
                }

                CoffeeCategoryList(
                    categories = coffeeCategories,
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Align CoffeeCategoryList to bottom-center of PromoCard
                        .absoluteOffset(y = (20).dp) // Move CoffeeCategoryList upwards to overlap
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Pass cartItemCount modifier to MenuList
            MenuList(items = menuItems, onItemCountChange = { count ->
                cartItemCount = count // Update total cart item count
            })
        }
    }
}
