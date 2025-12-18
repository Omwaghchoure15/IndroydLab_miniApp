package com.example.indroydlab.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indroydlab.cart.CartManager
import com.example.indroydlab.model.CartItem
import com.airbnb.lottie.compose.*
import com.example.indroydlab.R
import kotlinx.coroutines.delay

@Composable
fun CartScreen(cartManager: CartManager) {

    var refresh by remember { mutableStateOf(0) }
    var isOrderPlaced by remember { mutableStateOf(false) }

    val cartItems = remember(refresh) { cartManager.getCartItems() }

    if (isOrderPlaced) {
        CheckoutSuccessScreen(
            onDone = {
                isOrderPlaced = false
            }
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)) {
        Text(
            text = "My Cart",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
            Text(
                text = "Your cart is empty",
                fontSize = 16.sp
            )
        }
    }
        else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = cartItems,
                    key = { it.product.id }
                ) { item ->
                    CartItemCard(
                        item = item,
                        onRemove = {
                            cartManager.removeFromCart(item.product)
                            refresh++
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            PriceSummary(cartManager,refresh)

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (cartManager.finalAmount >= 1000) {
                        cartManager.clearCart()
                        refresh++
                        isOrderPlaced = true
                    }
                },
                enabled = cartManager.finalAmount >= 1000,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Checkout")
            }

        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(text = item.product.name, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Price: ₹${item.itemPrice}")
            Text(text = "Quantity: ${item.quantity}")
            Text(
                text = "Tax: ${item.product.taxPercent}%",
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onRemove) {
                    Text("Remove", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun PriceSummary(
    cartManager: CartManager,
    refresh: Int
) {
    val subTotal = remember(refresh) { cartManager.subTotal }
    val taxTotal = remember(refresh) { cartManager.taxTotal }
    val discount = remember(refresh) { cartManager.couponDiscount }
    val finalAmount = remember(refresh) { cartManager.finalAmount }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = "Minimum Checkout Amount ₹1000",
                fontSize = 18.sp,
                color = Color(0xFFD32F2F))

            SummaryRow("Subtotal", subTotal)
            SummaryRow("Tax", taxTotal)
            SummaryRow("Coupon Discount", -discount)

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp)
            )

            SummaryRow(
                label = "Final Amount",
                value = finalAmount,
                isBold = true
            )
        }
    }
}

@Composable
fun SummaryRow(
    label: String,
    value: Double,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = if (isBold) androidx.compose.ui.text.font.FontWeight.Bold else null
        )
        Text(
            text = "₹${String.format("%.2f", value)}",
            fontWeight = if (isBold) androidx.compose.ui.text.font.FontWeight.Bold else null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    val cartManager = CartManager()
    CartScreen(cartManager)
}
@Composable
fun CheckoutSuccessScreen(onDone: () -> Unit) {
    LaunchedEffect(Unit)
    { delay(3000)
        onDone() }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.confetti)
    )
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF52CC59)) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                iterations = 1
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "🎉",
                    fontSize = 60.sp
                )

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Order Placed Successfully",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = onDone) {
                    Text("Continue Shopping")
                }
            }
        }
    }
}
