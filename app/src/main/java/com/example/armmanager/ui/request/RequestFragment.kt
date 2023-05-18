package com.example.armmanager.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.armmanager.R
import com.example.armmanager.databinding.FragmentRequestBinding

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val requestViewModel =
            ViewModelProvider(this).get(RequestViewModel::class.java)
        _binding = FragmentRequestBinding.inflate(layoutInflater)
        binding.addbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Navigation.findNavController(v).navigate(R.id.action_nav_request_to_addRequestFragment)
            }
        })

        //binding = FragmentRequestBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

//        val textView: TextView = binding.textView4 //????
//        requestViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}