package com.example.armmanager.ui.product


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.BottomSheetAddProductBinding
import com.example.armmanager.databinding.BottomSheetLoadingBinding
import com.example.armmanager.databinding.ProductBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.vo.Status
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val productViewModel: ProductViewModel by viewModels { viewModelFactory }

    private var _binding: ProductBinding? = null

    private lateinit var adapter: ProductAdapter // Объект Adapter
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var addBinding: BottomSheetAddProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = ProductBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetDialog = context?.let { BottomSheetDialog(it) }
        val manager = LinearLayoutManager(context) // LayoutManager
        adapter = ProductAdapter() // Создание объекта
        binding.productRV.layoutManager = manager // Назначение LayoutManager для RecyclerView
        binding.productRV.adapter = adapter // Назначение адаптера для RecyclerView
        // productViewModel.log1()

        binding.fabAdd.setOnClickListener {
//            // Создаем экземпляр диалогового окна
//            val dialog = AddProductDialogFragment()
//
//            // Устанавливаем этот фрагмент в качестве родительского для диалогового окна
//            dialog.setTargetFragment(this, 0)
//
//            // Отображаем диалоговое окно
//            dialog.show(parentFragmentManager, "add_product_dialog")
            showAddBottomSheet()
        }

        productViewModel.product.observe(viewLifecycleOwner, Observer { productsResponse ->
            if (productsResponse.status == Status.SUCCESS && productsResponse.data != null) adapter.setData(
                productsResponse.data
            )
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // Не реализовываем, так как не нужно
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                lifecycleScope.launch {
                    val position = viewHolder.adapterPosition
                    val product = adapter.currentList[position]
                    productViewModel.deleteProduct(product)
                    adapter.removeItem(position)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.productRV)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun createAddView() {
        addBinding = BottomSheetAddProductBinding.inflate(layoutInflater)
        addBinding!!.saveProductBtn.setOnClickListener {
//            var productText = addBinding!!.productNameET.text.toString()
//            if (productText.isNotEmpty()) {
//                productViewModel.createProduct(productText).observe(
//                    viewLifecycleOwner
//                ) { productsResponse ->
//                    when (productsResponse.status) {
//                        Status.SUCCESS -> {
//
//                            bottomSheetDialog?.dismiss()
//                        }
//                        Status.LOADING -> {
//                            //bottomSheet.setContentView(loadBinding.root)
//                            //Показать лоадер вместо кнопки сохранить
//                        }
//                        Status.ERROR -> {
//                            //Показать текст ошибки под текстовым полем
//                            //Активировать кнопку вместо лоадера
//                        }
//
//                    }

//                }
//            }
            bottomSheetDialog?.setContentView(BottomSheetLoadingBinding.inflate(layoutInflater).root)
        }
    }

    private fun showAddBottomSheet() {
        // bottomSheetDialog?.setContentView(R.layout.bottom_sheet_add_product)
        if (addBinding == null)
            createAddView()
        addBinding?.let { bottomSheetDialog?.setContentView(it.root) }

        bottomSheetDialog?.show()
    }

    private fun showRemoveBottomSheet() {
        bottomSheetDialog?.setContentView(R.layout.bottom_sheet_add_product)

        bottomSheetDialog?.show()
    }

    private fun closeBottomSheetDialog() {}

}