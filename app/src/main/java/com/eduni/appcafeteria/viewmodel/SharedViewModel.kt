package com.eduni.appcafeteria.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduni.appcafeteria.model.Product

class SharedViewModel : ViewModel() {
    private val _selectedProducts = MutableLiveData<Map<String, Product>>(emptyMap())
    val selectedProducts: LiveData<Map<String, Product>> get() = _selectedProducts

    fun toggleProductSelection(product: Product) {
        val updatedProducts = _selectedProducts.value?.toMutableMap() ?: mutableMapOf()

        if (updatedProducts.containsKey(product.category)) {
            updatedProducts.remove(product.category)
        } else {
            updatedProducts[product.category] = product
        }

        _selectedProducts.value = updatedProducts
    }

    fun clearSelectedProducts() {
        _selectedProducts.value = emptyMap()
    }

    fun getTotalPrice(): Double {
        return _selectedProducts.value?.values?.sumOf { it.price } ?: 0.0
    }
}
