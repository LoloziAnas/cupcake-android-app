package com.lolozianas.cupecakeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lolozianas.cupecakeapp.model.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class OrderViewModelUnitTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var orderViewModel: OrderViewModel

    @Before
    fun setup() {
        orderViewModel = OrderViewModel()
    }

    @Test
    fun quantity_twelve_cupcakes() {
        orderViewModel.setQuantity(12)
        assertEquals(12, orderViewModel.quantity.value)
    }

    @Test
    fun price_twelve_cupcakes() {
        orderViewModel.setQuantity(12)
        orderViewModel.price.observeForever {}
        assertEquals("27,00 €", orderViewModel.price.value)
    }
}