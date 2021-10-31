package pt.hventura.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import pt.hventura.shoestore.R
import pt.hventura.shoestore.Utils.hideKeyboard
import pt.hventura.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(inflater, R.layout.fragment_shoe_detail, container, false)

        val viewModel: ShoeViewModel by activityViewModels()

        binding.shoeViewModel = viewModel

        binding.cancelButton.setOnClickListener {
            hideKeyboard()
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
            viewModel.doneNavigating()
        }

        viewModel.errors.observe(this, { error ->
            hideKeyboard()
            if (error.isNotBlank() && error != "noErrors")
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })

        viewModel.doneSavingShoe.observe(this, {
            hideKeyboard()
            if (it) {
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

}