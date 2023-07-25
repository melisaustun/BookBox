package com.example.bookbox.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookbox.*
import com.example.bookbox.ui.adapter.BestSellingAdapter
import com.example.bookbox.ui.adapter.RentalAdapter
import com.example.bookbox.ui.adapter.CategoryAdapter
import com.example.bookbox.listener.OnClickItemAndAdd
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.ui.activity.FourActivity
import com.example.bookbox.ui.activity.OneActivity
import com.example.bookbox.ui.activity.ThreeActivity
import com.example.bookbox.ui.activity.TwoActivity
import com.example.bookbox.ui.activity.MainActivity
import com.example.bookbox.ui.bsproduct.BSProductActivity
import com.example.bookbox.ui.detailproduct.DetailProductActivity
import com.example.bookbox.ui.product.ProductActivity
import com.example.bookbox.ui.rtproduct.RTProductActivity
import com.example.bookbox.utill.Constant
import com.example.bookbox.utill.ProductSavedType
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {


    private val rentalAdapter: RentalAdapter by lazy {
        RentalAdapter()
    }

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter()
    }

    private val bestSellingAdapter: BestSellingAdapter by lazy {
        BestSellingAdapter()
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentSearch()
        showBanner()

        setListRental()
        observeRental()

        setListBestSelling()
        observeBestSelling()

        setListCategory()
        observeCategory()

        viewModel.showDataRental()
        viewModel.showDataBestSelling()
        viewModel.showDataCategory()

        all1.setOnClickListener {
            startActivity(Intent(activity, ProductActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        all2.setOnClickListener {
            startActivity(Intent(activity,  RTProductActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        all3.setOnClickListener {
            startActivity(Intent(activity, BSProductActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        offerone.setOnClickListener {
            startActivity(Intent(activity, OneActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }
        offertwo.setOnClickListener {
            startActivity(Intent(activity, TwoActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        offerthree.setOnClickListener {
            startActivity(Intent(activity, ThreeActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        offerfour.setOnClickListener {
            startActivity(Intent(activity, FourActivity::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun intentSearch() {
        tv_search.setOnClickListener {
            (activity as MainActivity).navigateExplore()
        }
    }

    private fun showBanner() {
        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.k1))
        imageList.add(SlideModel(R.drawable.k2))
        imageList.add(SlideModel(R.drawable.k3))

        image_slider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    private fun observeRental() {
        viewModel.rental.observe(viewLifecycleOwner) {
            rentalAdapter.setDataAdapter(it)
        }
    }

    private fun setListRental() {
        rv_rental.setHasFixedSize(true)
        rv_rental.adapter = rentalAdapter
        rentalAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                toDetailRentals(productEntity)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity, ProductSavedType.CART)
            }
        }
    }

    private fun observeCategory() {
        viewModel.category.observe(viewLifecycleOwner) {
            categoryAdapter.setDataAdapter(it)
        }
    }

    private fun setListCategory() {
        rv_category.setHasFixedSize(true)
        rv_category.adapter = categoryAdapter
        categoryAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                toDetailCategory(productEntity)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity, ProductSavedType.CART)
            }
        }
    }

    private fun observeBestSelling() {
        viewModel.bestsSelling.observe(viewLifecycleOwner) {
            bestSellingAdapter.setDataAdapter(it)
        }
    }

    private fun setListBestSelling() {
        rv_best_selling.setHasFixedSize(true)
        rv_best_selling.adapter = bestSellingAdapter
        bestSellingAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                toDetailBestSelling(productEntity)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity, ProductSavedType.CART)
            }
        }
    }


    private fun toDetailCategory(productEntity: ProductEntity) {
        val intent = Intent(activity, DetailProductActivity::class.java)
        intent.putExtra(Constant.DATA, productEntity)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    private fun toDetailRentals(productEntity: ProductEntity) {
        val intent = Intent(activity, DetailProductActivity::class.java)
        intent.putExtra(Constant.DATA, productEntity)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun toDetailBestSelling(productEntity: ProductEntity) {
        val intent = Intent(activity, DetailProductActivity::class.java)
        intent.putExtra(Constant.DATA, productEntity)
        startActivity(intent)
        requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    private fun addProductToCart(productEntity: ProductEntity, cart: Int) {
        viewModel.addToChar(productEntity, ProductSavedType.CART)
        Toast.makeText(activity, "Sepete eklendi", Toast.LENGTH_SHORT).show()
    }


}