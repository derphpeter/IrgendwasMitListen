class Product(val productName : String, val productPrice : Double, val productRating : Int)
{
    override fun toString() = "($productName, $productPrice, $productRating)"
}

    fun productFactory(name : String, rating : Int) : (Double) -> Product =
        { price : Double -> Product(name, price, rating) }
