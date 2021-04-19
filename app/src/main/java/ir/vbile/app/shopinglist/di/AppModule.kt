package ir.vbile.app.shopinglist.di

import android.content.*
import androidx.room.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.components.*
import dagger.hilt.android.qualifiers.*
import ir.vbile.app.shopinglist.data.local.*
import ir.vbile.app.shopinglist.data.remote.*
import ir.vbile.app.shopinglist.other.Constants.BASE_URL
import ir.vbile.app.shopinglist.other.Constants.DATABASE_NAME
import retrofit2.*
import retrofit2.converter.gson.*
import javax.inject.*

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            ShoppingItemDatabase::class.java,
            DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideShoppingDao(database: ShoppingItemDatabase)=database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(PixabayAPI::class.java)
    }




}