package com.lolozianas.cupecakeapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lolozianas.cupecakeapp.databinding.FragmentStartBinding
import com.lolozianas.cupecakeapp.model.OrderViewModel

/**
 * fragment [StartFragment] is the fist screen of the Cupcake app.
 * The user can choose how many cupcake wants to order.
 */

private const val TAG = "StartFragment"

class StartFragment : Fragment() {

    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    // Initialize a shared view model object by using 'by activityViewModels()' kotlin property
    // delegate from the fragment-ktx library.
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Assign the fragment
        binding.startFragment = this@StartFragment

        //
        binding.slider.addOnChangeListener { _, value, _ ->
            // Responds to when slider's value is changed
            Log.d(TAG, "onViewCreated: $value")
        }

    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen
     * which is flavor fragment
     * */
    fun orderCupcake(quantity: Int) {

        // Update the view model with the quantity
        sharedViewModel.setQuantity(quantity)

        // Set the vanilla as default flavor, if no flavor is set in the view model yet.
        if (sharedViewModel.hasNotFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // Navigate to the next destination to select the flavor of the cupcakes
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
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