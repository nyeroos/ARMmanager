package com.example.armmanager.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.armmanager.databinding.AccountBinding

class AccountFragment : Fragment() {

    private var _binding: AccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = AccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.changePass.setOnClickListener {
            // Показать поля password, newPassword и confirmPassword
            binding.password.visibility = View.VISIBLE
            binding.newPassword.visibility = View.VISIBLE
            binding.confirmPassword.visibility = View.VISIBLE
            binding.saveBtn.visibility = View.VISIBLE
        }

        binding.saveBtn.setOnClickListener {
            // скрыть поля password, newPassword и confirmPassword
            if (binding.newPasswordET.text.toString().equals(binding.confirmPasswordET.text.toString())){
                Toast.makeText(context, "Успех", Toast.LENGTH_LONG).show()
                binding.password.visibility = View.GONE
                binding.newPassword.visibility = View.GONE
                binding.confirmPassword.visibility = View.GONE
                binding.saveBtn.visibility = View.GONE
            } else {
                Toast.makeText(context, "Пароли не совадают", Toast.LENGTH_LONG).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}