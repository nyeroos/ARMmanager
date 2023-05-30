package com.example.armmanager.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.armmanager.databinding.BottomSheetAddProductBinding

import com.example.armmanager.vo.Product

class AddProductDialogFragment : DialogFragment() {
    // Переменные для хранения ссылок на View-компоненты
    private var _binding: BottomSheetAddProductBinding? = null
    private val binding get() = _binding!!

    // Интерфейс для обработки события добавления нового продукта
    interface OnAddProductListener {
        fun onProductAdded(product: Product)
    }

    // Ссылка на экземпляр интерфейса OnAddProductListener для обработки события добавления нового продукта
    private var onAddProductListener: OnAddProductListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Получаем ссылку на экземпляр OnAddProductListener из родительского фрагмента
        onAddProductListener = parentFragment as? OnAddProductListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Создаем View-компоненты из макета диалогового окна
        _binding = BottomSheetAddProductBinding.inflate(inflater, container, false)

        // Обработчик для кнопки "Добавить"
        binding.saveProductBtn.setOnClickListener {
            // Получаем данные о новом продукте из полей ввода
            val id = 0
            val name = binding.productNameET.text.toString()

            // Проверяем, что все поля были заполнены
            if (name.isNotEmpty()) {
                // Создаем объект Product с полученными данными
                val product = Product(id, name)

                // Отправляем событие OnAddProductListener в родительский фрагмент, передавая новый продукт
                onAddProductListener?.onProductAdded(product)

                // Закрываем диалоговое окно
                dismiss()
            } else {
                Toast.makeText(context, "Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Освобождаем ссылку на View-компоненты
        _binding = null
    }
}