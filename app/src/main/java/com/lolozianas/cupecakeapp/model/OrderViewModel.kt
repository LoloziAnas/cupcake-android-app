package com.lolozianas.cupecakeapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * [OrderViewModel] holds information about a cupcakes order in terms of quantity, flavor and pickup
 * date. It also knows how to calculate the total price based on these order details.
 * */

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    // Quantity of the cupcakes in this order
    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    // Flavor of the cupcakes in this order
    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor

    // Pickup date of the cupcakes in this order
    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    // Price of the order so far
    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Possible date options
    val dateOptions: List<String> = getPickupOptions()

    init {
        // Set initial values for the order
        resetOrder()
    }

    /** Set the quantity of cupcakes for this order
     * @param numberCupCakes int represent the number of cupcakes
     * */
    fun setQuantity(numberCupCakes: Int) {
        _quantity.value = numberCupCakes
        // Update price after the user choose the quantity.
        updatePrice()
    }

    /** Set the flavor of cupcakes for this order.
     * PS: Only one flavor can be selected for the whole cupcakes
     * @param desiredFlavor is the cupcake flavor as a String
     * */
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor

    }

    /** Set the pickup date for this order
     * @param pickupDate is the pickup date as String
     * */
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        // Update price after the user pickup a date
        updatePrice()
    }

    /**
     * Returns true if a flavor has not been selected for the order yet. Returns false otherwise.
     * */
    fun hasNotFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    /**
     * Returns a list of date options starting with the current date and the following next 3 days.
     * */
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create a list of dates starting with the current date and the following 3 dates
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    /** Update the order price*/
    private fun updatePrice() {
        var calculatedPrice = (_quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (_date.value.equals(dateOptions[0])) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    /**
     * Reset this order
     * */
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0

    }

}