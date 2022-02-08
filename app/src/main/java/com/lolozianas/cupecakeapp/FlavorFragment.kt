package com.lolozianas.cupecakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lolozianas.cupecakeapp.databinding.FragmentFlavorBinding
import com.lolozianas.cupecakeapp.model.OrderViewModel


/**
 * [FlavorFragment] allows user to choose a cupcake flavor for the order.
 */
class FlavorFragment : Fragment() {

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentFlavorBinding? = null
    private val binding get() = _binding!!

    // Initialize a shared view model object by using 'by activityViewModels()' kotlin property
    // delegate from the fragment-ktx library.
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFlavorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to the property in binding class
            orderViewModel = sharedViewModel

            // Assign the fragment
            flavorFragment = this@FlavorFragment
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     * */
    fun goToNextScreen() {
        // navigate to the [PickupFragment]
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    /**
     * Cancel the order and start over.
     * */
    fun cancelOrder() {
        // Reset the order values
        sharedViewModel.resetOrder()
        // Navigate to [StartFragment]
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}