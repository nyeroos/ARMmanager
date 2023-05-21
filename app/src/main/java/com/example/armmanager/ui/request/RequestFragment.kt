package com.example.armmanager.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.FragmentRequestBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.vo.Status
import javax.inject.Inject

class RequestFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val requestViewModel: RequestViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentRequestBinding? = null
    private lateinit var adapter: RequestAdapter // Объект Adapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRequestBinding.inflate(layoutInflater)
        binding.addbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Navigation.findNavController(v)
                    .navigate(R.id.action_nav_request_to_addRequestFragment)
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
        requestViewModel.log()
        requestViewModel.requests.observe(viewLifecycleOwner, Observer { requestsResponse ->
            if (requestsResponse.status == Status.SUCCESS && requestsResponse.data != null)
                adapter.setData(requestsResponse.data)
        })
        /* viewModel.allRequests.observe(viewLifecycleOwner) { requests ->
             adapter.setData(requests.toMutableList())
         }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}