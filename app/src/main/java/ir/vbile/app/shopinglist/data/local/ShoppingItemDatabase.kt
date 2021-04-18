package ir.vbile.app.shopinglist.data.local
import androidx.room.Database
import androidx.room.RoomDatabase
import ir.vbile.app.shopinglist.data.model.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}