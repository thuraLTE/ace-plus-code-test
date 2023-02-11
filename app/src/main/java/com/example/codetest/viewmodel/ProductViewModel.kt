package com.example.codetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codetest.model.Product
import com.example.codetest.network.RetrofitInstance
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    fun getProductListFromNetwork() {
        viewModelScope.launch {
            _productList.value = RetrofitInstance.retrofitService.fetchAllProducts().productList
        }
    }
}