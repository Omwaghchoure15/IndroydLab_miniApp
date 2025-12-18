Author/Developer 
Om Rajendra Waghchoure
Android Developer (Java/Kotlin/Jetpack Compose/MVVM)
Mumbai, India

Indroyd Lab – Mini E-Commerce App 

A mini e-commerce Android application built using Jetpack Compose (UI) and Java (business logic) as part of the Indroyd Lab Android assignment.
This project demonstrates cart management, pricing rules, checkout validation, and modern Material 3 UI.

Features:
 Product Listing
 Displays multiple products
 Each product contains: 
   Name,
   Original price,
   Discounted price (if applicable),
   Tax percentage (5% / 18%),
   Discounted prices shown in green,
   Tax shown in red.

Cart Management:
 Add products to cart
 Remove individual products from the cart
 Cart updates instantly using Compose recomposition
 The empty cart state is shown at the center
 Price Calculation
 Subtotal calculation
 Tax calculation
 Coupon discount calculation
 Final amount calculation.

All calculations handled in Java (CartManager)
Coupon Rules
Coupon applicable only if:
 Cart value ≥ ₹1000
 
Discount:
20% discount
Maximum discount ₹300
Coupon not applied to already discounted products

Checkout Validation: The checkout button is enabled only when the final amount ≥ ₹1000. Prevents invalid checkout. Clean UX with disabled button state.

Checkout Success Screen
Confetti animation using Lottie,
Auto dismisses after 3 seconds,
The cart is cleared after checkout.

Architecture
UI Layer → Jetpack Compose (Kotlin)
Business Logic → Java

Clean separation of concerns:
UI handles state & recomposition
Java handles pricing & cart logic

Tech Stack :
Kotlin (UI)/
Java (Cart & Pricing Logic)/
Jetpack Compose/
Material 3/
Lottie Animations/
In-memory data (no database).

How to Run:
Clone the repository Open in Android Studio Sync Gradle, and run on an emulator or Physical Device (Android 8+)

Screens Included :
Product List Screen,
Cart Screen,
Checkout Success Screen.

Notes :
No backend/database used. Focused on UI, business rules, and clean architecture
Designed as a mini assignment project.

Thanks you...
