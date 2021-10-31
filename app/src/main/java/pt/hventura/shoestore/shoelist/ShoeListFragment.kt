package pt.hventura.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import pt.hventura.shoestore.R
import pt.hventura.shoestore.databinding.FragmentShoesListBinding
import pt.hventura.shoestore.databinding.ShoeItemBinding

class ShoeListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentShoesListBinding>(inflater, R.layout.fragment_shoes_list, container, false)

        val viewModel: ShoeViewModel by activityViewModels()

        binding.shoeDetails.setOnClickListener {
            this.findNavController().navigate(ShoeListFragmentDirections.actionShoesFragmentToShoeDetailFragment())
        }

        viewModel.shoeList.observe(this, { shoeList ->
            if (shoeList.size != 0) {
                binding.noContentText.visibility = View.GONE
                binding.svShoeList.visibility = View.VISIBLE

                val rootLayout = binding.shoeItemLayout
                shoeList.forEachIndexed { index, shoe ->
                    val childView = DataBindingUtil.inflate<ShoeItemBinding>(layoutInflater, R.layout.shoe_item, rootLayout, false)
                    childView.shoeNumber.text = (index + 1).toString()
                    childView.newShoe = shoe
                    rootLayout.addView(childView.root)
                }
            } else {
                binding.noContentText.visibility = View.VISIBLE
                binding.svShoeList.visibility = View.GONE
            }
        })

        return binding.root
    }

}