fun main()
{
    val productList = List(
        Product("Kaese", 4.5, 5),
        Product("Schokolade", 2.5, 7),
        Product("Veganes Schnitzel", 6.5, 2),
        Product("Superwasser", 1.0, 9),
        Product("Abo Luft Lifetime", 90000.0, 11)
    )

    val productFunctions = ListFunctions()

    val priceFilteredProductList = productFunctions.filter(productList) { it.productPrice > 10}
    println("Nach Preis gefilterte Liste: $priceFilteredProductList")

    val mappedPriceFilteredProductList = productFunctions.map(priceFilteredProductList) { it.productName }
    println("Gemappte nach Preis gefilterte Liste: $mappedPriceFilteredProductList")

    val taxedProductList = productFunctions.map(productList) { it.productPrice * 1.19}
    println("Gemappt auf Bruttopreise: $taxedProductList")

    val ratingFilteredProductList = productFunctions.filter(productList) { it.productRating > 3}
    println("Gefiltert nach Rating: $ratingFilteredProductList")

    val replacedProductList = productFunctions.replaceIf(
        productList,
        { Product("Angebot: " + it.productName, it.productPrice, it.productRating)},
        { it.productName.startsWith('A') || it.productName.startsWith('B') }
    )
    println("A und B-Ersetzte Liste: $replacedProductList")

    val isThereSomethingMoreExpensiveThan100 = productFunctions.any(productList) { p : Product -> p.productPrice > 100 }
    println(isThereSomethingMoreExpensiveThan100)

    val sumOfAllProductPrices1 = productFunctions.recursiveFold(
        productList,
        0.0,
        { acc : Double, it -> acc + it.productPrice }
    )
    val sumOfAllProductPrices2 = productFunctions.iterativeFold(
        productList,
        0.0,
        { acc : Double, it -> acc + it.productPrice }
    )

    val sumOfAllProductPrices3 = productFunctions.foldedFold(
        productList,
        0.0,
        { acc : Double, it -> acc + it.productPrice }
    )

    println("Summe aller Preise: $sumOfAllProductPrices1")
    println("Summe aller Preise: $sumOfAllProductPrices2")
    println("Summe aller Preise: $sumOfAllProductPrices3")

    val maxOfAllProductPrices = productFunctions.recursiveFold(
        productList,
        0.0,
        { acc : Double, it -> if(acc < it.productPrice) it.productPrice else acc }
    )
    println("Hoechster Preis: $maxOfAllProductPrices")

    val shoeFactory = productFactory("Schuh", 6)

    val expensiveShoe = shoeFactory(250.0)
    val cheapShoe = shoeFactory(50.0)
    println(expensiveShoe)
    println(cheapShoe)
}
