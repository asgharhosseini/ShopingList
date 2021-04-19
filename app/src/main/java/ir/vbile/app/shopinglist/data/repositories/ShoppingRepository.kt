package ir.vbile.app.shopinglist.data.repositories

import androidx.lifecycle.LiveData
import ir.vbile.app.shopinglist.data.model.ImageResponse
import ir.vbile.app.shopinglist.data.model.ShoppingItem
import ir.vbile.app.shopinglist.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}