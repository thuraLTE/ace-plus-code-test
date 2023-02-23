package com.example.codetest.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codetest.R
import com.example.codetest.adapter.ProductAdapter
import com.example.codetest.model.Product
import com.example.codetest.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    private lateinit var productListRv: RecyclerView
    private var scrollState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productListRv = findViewById(R.id.rvPhotoList)

        productViewModel.getProductListFromNetwork()
        observeProductList()
    }

    private fun observeProductList() {
        productViewModel.productList.observe(this) { list ->
            Log.d("ProductActivity", "Number of products: ${list.size}")
            configureProductListAdapter(list)
        }
    }

    private fun configureProductListAdapter(productList: List<Product>) {
        val productAdapter = ProductAdapter(productList)
        Log.d("ProductActivity", "Adapter count: ${productAdapter.itemCount}")
        scrollState = productListRv.layoutManager?.onSaveInstanceState()
        productListRv.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        productListRv.layoutManager?.onRestoreInstanceState(scrollState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        return super.onKeyDown(keyCode, event)
    }

}