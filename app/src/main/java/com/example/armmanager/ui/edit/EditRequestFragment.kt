package com.example.armmanager.ui.edit

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.armmanager.AppExecutors
import com.example.armmanager.R
import com.example.armmanager.databinding.AddRequestBinding
import com.example.armmanager.di.Injectable
import com.example.armmanager.ui.add.AddRequestViewModel
import com.example.armmanager.vo.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import java.util.*
import javax.inject.Inject

class EditRequestFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val viewModel: AddRequestViewModel by viewModels { viewModelFactory }

    private lateinit var binding: AddRequestBinding
    private lateinit var currentRequest: Request

    private lateinit var statuses:Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = AddRequestBinding.inflate(inflater)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.creationDateET.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val date = "${dayOfMonth}.${monthOfYear+1}.${year}"
                binding.creationDateET.setText(date)
            }, year, month, day)

            dpd.show()
        }

        binding.planDateET.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val date = "${dayOfMonth}.${monthOfYear+1}.${year}"
                binding.planDateET.setText(date)
            }, year, month, day)

            dpd.show()
        }

        binding.factDateET.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val date = "${dayOfMonth}.${monthOfYear+1}.${year}"
                binding.factDateET.setText(date)
            }, year, month, day)

            dpd.show()
        }

        statuses = resources.getStringArray(R.array.statuses_array)

        val spinner = binding.spinner
        if (spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, statuses
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val status = statuses[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        arguments?.let {
            currentRequest = it.getParcelable("value")
                ?: throw IllegalArgumentException("Request object is null")
            currentRequest?.let {
                binding.numberRequestET.setText(currentRequest.number.toString())
                binding.nameRequestET.setText(currentRequest.name)
                binding.customerET.setText(currentRequest.customer)
                binding.creationDateET.setText(currentRequest.creationDate)
                binding.planDateET.setText(currentRequest.expectedDate)
                binding.factDateET.setText(currentRequest.actualDate)
                binding.spinner.setSelection(statuses.indexOf(currentRequest.status))
            }
        }


        return binding.root
    }

    private fun onSave(){
        val requestId = 0
        val requestNumber = binding.numberRequestET.text.toString().toIntOrNull() ?: 0
        val requestName = binding.nameRequestET.text.toString()
        val requestDate = binding.creationDateET.text.toString()
        val customer = binding.customerET.text.toString()
        val planDate = binding.planDateET.text.toString()
        val status = statuses[binding.spinner.selectedItemPosition]
        val factDate = binding.factDateET.text.toString()
        val user = 1
        Log.d("QQQ", "requestName: $requestName, requestDate: $requestDate")
        if (requestName.isNotEmpty()) {
            var request = Request(
                requestId,
                requestNumber,
                requestName,
                customer,
                planDate,
                requestDate,
                factDate,
                user,
                status
            )
            val coroutineScope = CoroutineScope(Dispatchers.Main)
            coroutineScope.launch {
                viewModel.insertRequest(request)
            }
        }
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_request_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.saveBtn ->{
                        onSave()
                        true}
                    else ->false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}