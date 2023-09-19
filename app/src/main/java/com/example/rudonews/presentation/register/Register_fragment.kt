package com.example.rudonews.presentation.register

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rudonews.activities.MainActivity
import com.example.rudonews.R
import com.example.rudonews.databinding.RegisterFragmentBinding
import com.example.rudonews.domain.entity.Departament
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText


class Register_fragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding
    private lateinit var passwordText: String
    private var selectedDepartments: List<Departament>? = null

    private lateinit var registerButton: MaterialButton
    private lateinit var radioButton: MaterialRadioButton
    private lateinit var departamentsInput: EditText
    private lateinit var nameInput: TextInputEditText
    private lateinit var nameInputErrorText: TextView
    private lateinit var mailInput: TextInputEditText
    private lateinit var mailInputErrorText: TextView
    private lateinit var passwordInput: TextInputEditText


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

        selectedDepartments =
            arguments?.getSerializable("selectedDepartments") as? List<Departament>

        registerButton = binding.BtnRegisterInRegister
        departamentsInput = binding.registerTextInputDepartamentos
        nameInput = binding.registerTextInputNombre
        nameInputErrorText = binding.nombreErrorTextView
        mailInput = binding.registerTextInputMail
        mailInputErrorText = binding.mailErrorTextView
        passwordInput = binding.registerTextInputContracena
        radioButton = binding.radioBtn

        onView()
    }

    private fun onView() {
        disableRegisterButton()
        setNavBarTitle()
        nameFocusListener()
        mailFocusListener()
        initRegisterButtonListener()
        passwordTextChangeListener()
        initRadioButtonListener()
        departamentosOnFocusListener()
        setSelectedDepartments()
        removeDepartamentosHint()
        disableAllInputs()
        enableFormInputs()
        mailTextChangeListener()
        nameTextChangeListener()
    }

    private fun setNavBarTitle() {
        val activity = activity as? MainActivity
        activity?.setNavBarText("Registro")
    }

    private fun removeDepartamentosHint() {
        if (departamentsInput.length() != 0) {
            binding.registerTextInputLayoutDepartamentos.hint = null
        }
    }

    private fun setSelectedDepartments() {
        if (selectedDepartments != null) {
            val selectedDepartmentNames = selectedDepartments!!.map { it.deptName }
            val concatenatedNames = selectedDepartmentNames.joinToString("/")

            departamentsInput.setText(concatenatedNames)
        } else {
            println("selectedDepartments do not exist")
        }
    }

    private fun initRegisterButtonListener() {
        registerButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("Sending details to server")
                .setPositiveButton("CONTINUAR") { _, _ ->
                }
                .show()
        }
    }

    private fun initRadioButtonListener() {
//

        radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                radioButton.buttonTintList =
                    context?.let {
                        ContextCompat.getColorStateList(it, R.color.fucia)
                    }
                enableRegisterButton()
            } else {
                radioButton.buttonTintList =
                    context?.let { ContextCompat.getColorStateList(it, R.color.Error) }
                disableRegisterButton()
            }
        }
    }

    private fun mailFocusListener() {
        mailInput.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                mailInputErrorText.text = validateMail()
            }
        }
    }

    private fun nameFocusListener() {
        nameInput.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                nameInputErrorText.text = validateName()
            }
        }
    }

    private fun validateMail(): String? {
        val emailText = mailInput.text.toString()
        val isValidEmail = emailText.matches(Regex("^.+@.+\\..+\$"))

        with(binding.registerTextInputLayoutMail) {
            boxStrokeColor = if (!isValidEmail) context?.getColor(R.color.Error)
                ?: R.color.fucia else context?.getColor(R.color.fucia) ?: R.color.fucia
            boxBackgroundColor = if (!isValidEmail) context?.getColor(R.color.ErrorInputBg)
                ?: R.color.fucia else context?.getColor(R.color.white) ?: R.color.fucia
        }

        binding.mailErrorImageView.visibility = if (!isValidEmail) View.VISIBLE else View.GONE

        return if (!isValidEmail) "Invalid email format" else null
    }

    private fun validateName(): String? {
        val nameText = nameInput.text.toString()
        val isEmpty = nameText.isEmpty()

        with(binding.registerTextInputLayoutNombre) {
            boxStrokeColor = if (isEmpty) context?.getColor(R.color.Error)
                ?: R.color.fucia else context?.getColor(R.color.fucia) ?: R.color.fucia
            boxBackgroundColor = if (isEmpty) context?.getColor(R.color.ErrorInputBg)
                ?: R.color.fucia else context?.getColor(R.color.white) ?: R.color.fucia
        }

        binding.nombreErrorImageView.visibility = if (isEmpty) View.VISIBLE else View.GONE

        return if (isEmpty) "Este campo no puede quedar vacío" else null
    }

    private fun nameTextChangeListener() {
        nameInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkIfFormValid()
            }
        })

    }

    private fun mailTextChangeListener() {
        mailInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkIfFormValid()
            }
        })
    }

    private fun passwordTextChangeListener() {

        passwordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                passwordText = passwordInput.text.toString()


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

                checkIfFormValid()

            }

            private fun updateUIForValidation(
                textView: TextView,
                cardView: MaterialCardView,
                isValid: Boolean
            ) {
                val textColor = if (isValid) R.color.ValidationBoxText else R.color.Grey
                val backgroundColor =
                    if (isValid) R.color.ValidationBoxTrue else R.color.AppBackground

                textView.setTextColor(resources.getColor(textColor))
                textView.setBackgroundColor(resources.getColor(backgroundColor))
                cardView.strokeColor = (resources.getColor(textColor))

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }

    private fun departamentosOnFocusListener() {
        departamentsInput.setOnFocusChangeListener { _, focused ->
            (activity as? MainActivity)?.navigateToDepartamentosFragment()
        }
    }

    private fun disableRegisterButton() {
        registerButton.isEnabled = false
        registerButton.setBackgroundColor(resources.getColor(R.color.fucia_disabled))
    }

    private fun enableRegisterButton() {
        registerButton.isEnabled = true
        registerButton.setBackgroundColor(resources.getColor(R.color.fucia))
    }

    private fun disableAllInputs() {
        nameInput.isEnabled = false
        nameInput.setBackgroundColor(resources.getColor(R.color.Grey))

        mailInput.isEnabled = false
        mailInput.setBackgroundColor(resources.getColor(R.color.whiteDisabled))

        passwordInput.isEnabled = false
        passwordInput.setBackgroundColor(resources.getColor(R.color.whiteDisabled))

        disableRadioBtn()
    }

    private fun enableFormInputs() {

        if (binding.registerTextInputDepartamentos.text.isNullOrBlank()) {
            nameInput.isEnabled = false
            nameInput.setBackgroundColor(resources.getColor(R.color.soft_grey_disabled))

            mailInput.isEnabled = false
            mailInput.setBackgroundColor(resources.getColor(R.color.soft_grey_disabled))

            passwordInput.isEnabled = false
            passwordInput.setBackgroundColor(resources.getColor(R.color.soft_grey_disabled))
        } else {
            nameInput.isEnabled = true
            nameInput.setBackgroundColor(resources.getColor(R.color.white))

            mailInput.isEnabled = true
            mailInput.setBackgroundColor(resources.getColor(R.color.white))

            passwordInput.isEnabled = true
            passwordInput.setBackgroundColor(resources.getColor(R.color.white))
        }
    }

    private fun checkIfFormValid() {

        val passwordRegex =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!])(?=.*[a-zA-Z]).{8,}$")
        val emailRegex = Regex("^.+@.+\\..+\$")

        if (!departamentsInput.text.isNullOrBlank() && !nameInput.text.isNullOrBlank() && nameInputErrorText.text.isNullOrBlank() && !mailInput.text.isNullOrBlank() &&
            mailInputErrorText.text.isNullOrBlank() && passwordInput.text?.matches(passwordRegex) == true && mailInput.text?.matches(
                emailRegex
            ) == true
        ) {
            enableRadioBtn()
            if (radioButton.isChecked) {
                enableRegisterButton()
            }
        } else {
            disableRadioBtn()
            disableRegisterButton()
        }
    }

    private fun disableRadioBtn() {
        radioButton.isEnabled = false
        radioButton.buttonTintList = context?.let { ContextCompat.getColorStateList(it, R.color.soft_grey) }
    }

    private fun enableRadioBtn() {
        radioButton.isEnabled = true

        if (radioButton.isChecked) {
            radioButton.buttonTintList = context?.let { ContextCompat.getColorStateList(it, R.color.fucia) }
        } else {
            radioButton.buttonTintList = context?.let { ContextCompat.getColorStateList(it, R.color.Grey) }
        }
    }
}
