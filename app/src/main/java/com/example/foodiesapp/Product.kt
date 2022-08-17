import android.graphics.Bitmap

data class Product (
    val id: Int,
    val name: String,
    val price: Double,
    val image: Bitmap,
    val description: String,
    var bought_items_count: Int )