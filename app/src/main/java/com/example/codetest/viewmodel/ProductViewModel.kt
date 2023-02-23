package com.example.codetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codetest.model.Product
import com.example.codetest.network.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val networkService: NetworkService
): ViewModel() {

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    fun getProductListFromNetwork() {
        viewModelScope.launch {
            _productList.value = networkService.fetchAllProducts().productList
        }
    }
}