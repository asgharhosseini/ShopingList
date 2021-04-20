package ir.vbile.app.shopinglist.ui.fragment

import androidx.navigation.*
import androidx.test.espresso.Espresso.*
import androidx.test.filters.*
import dagger.hilt.android.testing.*
import ir.vbile.app.shopinglist.*
import kotlinx.coroutines.*
import org.junit.*
import org.mockito.Mockito.*

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }

}