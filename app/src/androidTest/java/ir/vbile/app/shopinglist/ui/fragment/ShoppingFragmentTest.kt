package ir.vbile.app.shopinglist.ui.fragment

import androidx.navigation.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.*
import dagger.hilt.android.testing.*
import ir.vbile.app.shopinglist.*
import ir.vbile.app.shopinglist.R
import kotlinx.coroutines.*
import org.junit.*
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ShoppingFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickAddShoppingItemButton_navigateToAddShoppingItemFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<ShoppingFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.fabAddShoppingItem)).perform(click())

        verify(navController).navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
        )
    }
}