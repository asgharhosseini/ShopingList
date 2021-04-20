package ir.vbile.app.shopinglist.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

import com.bumptech.glide.RequestManager
import ir.vbile.app.shopinglist.data.repositoreis.FakeShoppingRepositoryAndroidTest
import ir.vbile.app.shopinglist.ui.adapter.ImageAdapter
import ir.vbile.app.shopinglist.ui.adapter.ShoppingItemAdapter
import ir.vbile.app.shopinglist.ui.viewmodel.ShoppingViewModel
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingItemAdapter: ShoppingItemAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name -> ShoppingFragment(
                shoppingItemAdapter,
                ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}