package ir.vbile.app.shopinglist.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.vbile.app.shopinglist.R
import ir.vbile.app.shopinglist.data.local.ShoppingDao
import ir.vbile.app.shopinglist.data.local.ShoppingItemDatabase
import ir.vbile.app.shopinglist.data.remote.PixabayAPI
import ir.vbile.app.shopinglist.data.repositories.DefaultShoppingRepository
import ir.vbile.app.shopinglist.data.repositories.ShoppingRepository
import ir.vbile.app.shopinglist.other.Constants.BASE_URL
import ir.vbile.app.shopinglist.other.Constants.DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

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


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(dao: ShoppingDao,api: PixabayAPI)=DefaultShoppingRepository(dao,api)as ShoppingRepository



}