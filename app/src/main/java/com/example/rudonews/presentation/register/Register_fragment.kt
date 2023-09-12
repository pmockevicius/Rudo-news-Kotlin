package com.example.rudonews.presentation.register

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.databinding.RegisterFragmentBinding
import com.example.rudonews.domain.entity.Departament
import com.google.android.material.card.MaterialCardView
import com.google.android.material.radiobutton.MaterialRadioButton


class Register_fragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding
    private lateinit var  passwordText: String
    private var selectedDepartments: List<Departament>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedDepartments = arguments?.getSerializable("selectedDepartments") as? List<Departament>

        onView()
    }

    private fun onView() {
        val activity = activity as? MainActivity
        activity?.setNavBarText("Registro")
        nameFocusListener()
        mailFocusListener()
        initButtonListener()
        passwordTextChangeListener()
        initRadioButtonListener()
        departamentosOnFocusListener()
        setSelectedDepartments()
        removeDepartamentosHint()
    }

    private fun removeDepartamentosHint(){
        if (binding.registerTextInputDepartamentos.length() != 0){
            binding.registerTextInputLayoutDepartamentos.hint = null
        }
    }

    private fun setSelectedDepartments(){
        if (selectedDepartments != null) {
            println("selectedDepartments in frag $selectedDepartments")
            val selectedDepartmentNames = selectedDepartments!!.map { it.deptName }
            val concatenatedNames = selectedDepartmentNames.joinToString("/")

            binding.registerTextInputDepartamentos.setText(concatenatedNames)
            println("selectedDepartmentNames $selectedDepartmentNames")

        } else {
            println("selectedDepartments do not exist")
        }
    }

    private fun initButtonListener() {
        val registerBtn = binding.BtnRegisterInRegister
        registerBtn.setOnClickListener {
            println("clicked Register")

            AlertDialog.Builder(requireContext())
                .setMessage("Dialog`Works")
                .setPositiveButton("CONTINUAR"){_,_ ->
                }
                .show()
        }
    }

    private fun initRadioButtonListener(){
        val radioButton: MaterialRadioButton = binding.radioBtn

        radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                println("button checked")
                radioButton.buttonTintList =
                    context?.let { ContextCompat.getColorStateList(it, com.example.rudonews.R.color.fucia) }
            } else {
                radioButton.buttonTintList =
                    context?.let { ContextCompat.getColorStateList(it, com.example.rudonews.R.color.Error) }
            }
        }


    }

    private fun mailFocusListener() {
        binding.registerTextInputMail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.mailErrorTextView.setText(validateMail())
            }
        }
    }

    private fun nameFocusListener() {
        binding.registerTextInputNombre.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.nombreErrorTextView.setText(validateName())
            }
        }
    }

    private fun validateMail(): String? {
        var emailText = binding.registerTextInputMail.text.toString()
        if (!emailText.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
            binding.registerTextInputLayoutMail.boxStrokeColor =
                context?.getColor(R.color.Error) ?: R.color.fucia
            binding.mailErrorImageView.visibility = View.VISIBLE
            binding.registerTextInputLayoutMail.boxBackgroundColor =
                context?.getColor(R.color.ErrorInputBg) ?: R.color.fucia
            return "Invalid email format"
        } else
            binding.registerTextInputLayoutMail.boxStrokeColor =
                context?.getColor(R.color.fucia) ?: R.color.fucia
        binding.mailErrorImageView.visibility = View.GONE
        binding.registerTextInputLayoutMail.boxBackgroundColor =
            context?.getColor(R.color.white) ?: R.color.fucia

        return null
    }

    private fun validateName(): String? {
        var nameText = binding.registerTextInputNombre.text.toString()
        if (nameText.isEmpty()) {
            binding.registerTextInputLayoutNombre.boxStrokeColor =
                context?.getColor(R.color.Error) ?: R.color.fucia
            binding.nombreErrorImageView.visibility = View.VISIBLE
            binding.registerTextInputLayoutNombre.boxBackgroundColor =
                context?.getColor(R.color.ErrorInputBg) ?: R.color.fucia
            return "Este campo no puede quedar vacío"
        } else binding.registerTextInputLayoutNombre.boxStrokeColor =
            context?.getColor(R.color.fucia) ?: R.color.fucia
        binding.nombreErrorImageView.visibility = View.GONE
        binding.registerTextInputLayoutNombre.boxBackgroundColor =
            context?.getColor(R.color.white) ?: R.color.fucia
        return null
    }

    private fun passwordTextChangeListener(){

        binding.registerTextInputContracena.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                passwordText = binding.registerTextInputContracena.text.toString()

                updateUIForValidation(
                    binding.textViewMayuscula,
                    binding.cardMayuscula,
                    passwordText.matches(Regex(".*[A-Z].*"))
                )

                updateUIForValidation(
                    binding.textViewMinuscula,
                    binding.cardMinuscula,
                    passwordText.matches(Regex(".*[a-z].*"))
                )

                updateUIForValidation(
                    binding.textView8Characters,
                    binding.card8Characters,
                    (passwordText.length >= 8)
                )

                updateUIForValidation(
                    binding.textViewNumeros,
                    binding.cardNumeros,
                    passwordText.matches(Regex(".*[0-9].*"))
                )

                updateUIForValidation(
                    binding.textViewEspecialCharacters,
                    binding.cardEspecialCharacters,
                    passwordText.matches(Regex(".*[@#\$%^&+=!].*"))
                )

            }

            private fun updateUIForValidation(
                textView: TextView,
                cardView: MaterialCardView,
                isValid: Boolean
            ) {
                val textColor = if (isValid) R.color.ValidationBoxText else R.color.Grey
                val backgroundColor = if (isValid) R.color.ValidationBoxTrue else R.color.AppBackground

                textView.setTextColor(resources.getColor(textColor))
                textView.setBackgroundColor(resources.getColor(backgroundColor))
                cardView.strokeColor = (resources.getColor(textColor))

            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

    }

    private fun departamentosOnFocusListener(){
         binding.registerTextInputDepartamentos.setOnFocusChangeListener { _, focused ->
             ( activity as? MainActivity)?.navigateToDepartamentosFragment()

        }
    }


}