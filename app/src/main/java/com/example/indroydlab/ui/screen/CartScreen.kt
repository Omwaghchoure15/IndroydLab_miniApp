package com.example.indroydlab.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.*
import com.example.indroydlab.R
import com.example.indroydlab.model.CartItem
import com.example.indroydlab.ui.shared.card.CartItemCard
import com.example.indroydlab.ui.theme.IndroydLabTheme
import com.example.indroydlab.ui.viewmodel.CartViewModel
import kotlinx.coroutines.delay

@Composable
fun CartScreen(viewModel: CartViewModel) {

    val cartItems = viewModel.cartItem
    var isOrderPlaced by remember { mutableStateOf(false) }

    if (isOrderPlaced) {
        CheckoutSuccessScreen(
            onDone = { isOrderPlaced = false }
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
                        onIncrease = { viewModel.increaseQuantity(item.product) },
                        onDecrease = { viewModel.decreaseQuantity(item.product) },
                        onRemove = { viewModel.removeFromCart(item.product) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            PriceSummary(viewModel)

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (viewModel.getFinalAmount() >= 1000) {
                        viewModel.clearCart()
                        isOrderPlaced = true
                    }
                },
                enabled = viewModel.getFinalAmount() >= 1000,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Checkout")
            }
        }
    }
}


@Composable
fun PriceSummary(cartViewModel: CartViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = "Minimum Checkout Amount ₹1000",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary)
            SummaryRow("Subtotal", cartViewModel.getSubTotal())
            SummaryRow("Tax", cartViewModel.getTaxTotal())
            SummaryRow("Coupon Discount", cartViewModel.getCouponDiscount())
            HorizontalDivider ( modifier = Modifier.padding(vertical = 6.dp) )

            SummaryRow(
                label = "Final Amount",
                value = cartViewModel.getFinalAmount(),
                isBold = true
            )
        }
    }
}
@SuppressLint("DefaultLocale")
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
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = "₹${String.format("%.2f", value)}",
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun CheckoutSuccessScreen(onDone: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000)
        onDone()
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.confetti)
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Box(contentAlignment = Alignment.Center
        ) {
            LottieAnimation(composition = composition, iterations = 1)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🎉", fontSize = 60.sp)

                Spacer(modifier = Modifier.height(22.dp))

                Text(text = "Order Placed Successfully", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(32.dp))

                Button(onClick = onDone) { Text("Continue Shopping") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(viewModel = viewModel() )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckoutPreview(){
    IndroydLabTheme{
        CheckoutSuccessScreen(onDone = {})
    }
}