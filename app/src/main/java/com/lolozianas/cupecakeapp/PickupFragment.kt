package com.lolozianas.cupecakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lolozianas.cupecakeapp.databinding.FragmentPickupBinding


/**
 * [PickupFragment] allows user to pickup a date for the order
 */
class PickupFragment : Fragment() {

    private var _binding: FragmentPickupBinding? = null
    private val binding get() = _binding!!
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
        binding.buttonCancel.setOnClickListener { cancerOrder() }
        binding.buttonNext.setOnClickListener { goToNextScreen() }
    }

    /**
     * Navigate to the next screen to see the order summary
     * */
    private fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    /**
     * Cancel order and start over
     * */
    private fun cancerOrder() {
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