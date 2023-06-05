package com.example.armmanager.ui.request.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.FragmentWorkRequestBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.ui.request.RequestAdapter
import com.example.armmanager.ui.request.RequestViewModel
import com.example.armmanager.vo.Request
import com.example.armmanager.vo.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkRequestFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val requestViewModel: RequestViewModel by viewModels { viewModelFactory }
    //private val addRequestViewModel: AddRequestViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentWorkRequestBinding? = null
    private lateinit var adapter: RequestAdapter // Объект Adapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkRequestBinding.inflate(layoutInflater)
        binding.fabAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Navigation.findNavController(v)
                    .navigate(R.id.action_workRequestFragment_to_addRequestFragment)
            }
        })

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(context) // LayoutManager
        adapter = RequestAdapter() // Создание объекта
        binding.currentRequestRV.layoutManager =
            manager // Назначение LayoutManager для RecyclerView
        binding.currentRequestRV.adapter = adapter // Назначение адаптера для RecyclerView
        //requestViewModel.log1()
        requestViewModel.requests.observe(viewLifecycleOwner, Observer { requestsResponse ->
            if (requestsResponse.status == Status.SUCCESS && requestsResponse.data != null)
                adapter.submitList(null)
                adapter.submitList(requestsResponse.data)
        })

        binding.currentRequestRV.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0){
                    if (binding.fabAdd.isExtended)
                        binding.fabAdd.shrink()
                }
                else if (dy < 0) {
                    if (!binding.fabAdd.isExtended)
                        binding.fabAdd.extend()
                }
            }
        })

        adapter.setOnItemClickListener(object : RequestAdapter.OnItemClickListener {
            override fun onItemClick(request: Request) {
                var bundle = bundleOf("value" to request)
                Navigation.findNavController(view)
                    .navigate(R.id.action_workRequestFragment_to_editRequestFragment, bundle)
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
        itemTouchHelper.attachToRecyclerView(binding.currentRequestRV)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}