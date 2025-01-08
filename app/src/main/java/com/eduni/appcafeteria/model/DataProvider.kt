package com.eduni.appcafeteria.model

object DataProvider {

    val products = listOf(
        Product(1, "Sandwich", "Menjars", 5.50),
        Product(2, "Hamburguesa", "Menjars", 7.99),
        Product(3, "Ensalada", "Menjars", 4.75),
        Product(4, "Coca-Cola", "Begudes", 1.99),
        Product(5, "Café", "Begudes", 2.50),
        Product(6, "Té", "Begudes", 2.00),
        Product(7, "Tarta de Chocolate", "Postres", 3.99),
        Product(8, "Helado", "Postres", 2.75)
    )

    var users = listOf(
        User(1, "admin", "admin123", "admin@cafeteria.com"),
        User(2, "user1", "password1", "user1@cafeteria.com"),
        User(3, "user2", "password2", "user2@cafeteria.com")
    )
}
