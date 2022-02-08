package com.lolozianas.cupecakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lolozianas.cupecakeapp.databinding.FragmentStartBinding

/**
 * fragment [StartFragment] is the fist screen of the Cupcake app.
 * The user can choose how many cupcake wants to order.
 */
class StartFragment : Fragment() {

    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // When the user chooses the number of cupcakes
        binding.buttonOneCupcake.setOnClickListener { orderCupcake(1) }
        binding.buttonSixCupcakes.setOnClickListener { orderCupcake(6) }
        binding.buttonTwelveCupcakes.setOnClickListener { orderCupcake(12) }
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen
     * which is flavor fragment
     * */
    private fun orderCupcake(quantity: Int) {
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