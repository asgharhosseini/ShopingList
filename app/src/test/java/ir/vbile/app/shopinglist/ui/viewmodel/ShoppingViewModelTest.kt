package ir.vbile.app.shopinglist.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat

import ir.vbile.app.shopinglist.MainCoroutineRule
import ir.vbile.app.shopinglist.getOrAwaitValueTest
import ir.vbile.app.shopinglist.other.Constants
import ir.vbile.app.shopinglist.other.Status
import ir.vbile.app.shopinglist.repositories.FakeShoppingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ShoppingViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }


    @Test
    fun `insert shopping item with empty field, returns error`() {
        viewModel.insertShoppingItem("name", "", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name, returns error`() {
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(i)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert shopping item with too long price, returns error`(){
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(i)
            }
        }
        viewModel.insertShoppingItem("name", "5", string)
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert shopping item with too high amount, returns error`(){
        viewModel.insertShoppingItem("name", "9999999999999999999", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }
    @Test
    fun`insert shopping item with valid input, returns success`(){
        viewModel.insertShoppingItem("name", "9", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)

    }

}