package ir.vbile.app.shopinglist.ui.fragment
import android.os.*
import android.view.*
import androidx.activity.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.navigation.fragment.*
import ir.vbile.app.shopinglist.R
import ir.vbile.app.shopinglist.ui.viewmodel.*
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*


class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                    AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }
} 