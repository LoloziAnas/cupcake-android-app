package com.lolozianas.cupecakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lolozianas.cupecakeapp.databinding.FragmentPickupBinding
import com.lolozianas.cupecakeapp.model.OrderViewModel


/**
 * [PickupFragment] allows user to pickup a date for the order
 */
class PickupFragment : Fragment() {

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentPickupBinding? = null
    private val binding get() = _binding!!

    // Initialize a shared view model object by using 'by activityViewModels()' kotlin property
    // delegate from the fragment-ktx library.
    private val sharedViewModel: OrderViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPickupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            // Assign the view model to property binding
            orderViewModel = sharedViewModel
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign fragment
            pickupFragment = this@PickupFragment

        }
    }

    /**
     * Navigate to the next screen to see the order summary
     * */
     fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    /**
     * Cancel order and start over
     * */
     fun cancerOrder() {
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
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