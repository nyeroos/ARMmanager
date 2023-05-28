package com.example.armmanager.ui.request.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.FragmentCompleteRequestBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.ui.request.RequestAdapter
import com.example.armmanager.ui.request.RequestViewModel
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompleteRequestFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val requestViewModel: RequestViewModel by viewModels { viewModelFactory }
    //private val addRequestViewModel: AddRequestViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentCompleteRequestBinding? = null
    private lateinit var adapter: RequestAdapter // Объект Adapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCompleteRequestBinding.inflate(layoutInflater)


        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(context) // LayoutManager
        adapter = RequestAdapter() // Создание объекта
        binding.completeRequestRV.layoutManager =
            manager // Назначение LayoutManager для RecyclerView
        binding.completeRequestRV.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.completeRequestRV.adapter = adapter // Назначение адаптера для RecyclerView
        //addRequestViewModel.log()
        requestViewModel.log1()
        //requestViewModel.log2()
        requestViewModel.completerequests.observe(viewLifecycleOwner, Observer { requestsResponse ->
            if (requestsResponse.status == Status.SUCCESS && requestsResponse.data != null)
                adapter.setData(requestsResponse.data)
        })

        adapter.setOnItemClickListener(object : RequestAdapter.OnItemClickListener {
            override fun onItemClick(request: Request) {

            }
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
                    val request = adapter.currentList[position]
                    requestViewModel.deleteRequest(request)
                    adapter.removeItem(position)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.completeRequestRV)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}