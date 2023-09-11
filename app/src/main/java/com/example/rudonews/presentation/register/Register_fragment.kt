package com.example.rudonews.presentation.register

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.databinding.ActivityMainBinding
import com.example.rudonews.databinding.RegisterFragmentBinding
import com.google.android.material.card.MaterialCardView


class Register_fragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding
    private lateinit var  passwordText: String

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
        onView()
    }

    private fun onView() {
        val activity = activity as? MainActivity
        activity?.setNavBarText("Registro")
        nameFocusListener()
        mailFocusListener()
//        passwordFocusListener()
        registerButtonListener()
        passwordTextChangeListener()
    }

    private fun registerButtonListener() {
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

    private fun passwordFocusListener() {
        binding.registerTextInputContracena.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordErrorTextView.setText(validatePassword())
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

    private fun validatePassword(): String? {

        val containsUppercase = passwordText.matches(Regex(".*[A-Z].*"))
        val containsLowercase = passwordText.matches(Regex(".*[a-z].*"))
        val containsSpecialCharacter = passwordText.matches(Regex(".*[@#\$%^&+=!].*"))
        val atLeast8Characters = passwordText.length >= 8

        if (!containsUppercase || !containsLowercase || !containsSpecialCharacter || !atLeast8Characters) {
            addPasswordValidationErrorStyle()

            val errorMessage = when {
                !containsUppercase && !containsLowercase ->
                    "Password must contain at least 1 Upper-case and 1 Lower-case Character"

                !containsUppercase ->
                    "Password must contain at least 1 Upper-case Character"

                !containsLowercase ->
                    "Password must contain at least 1 Lower-case Character"

                !containsSpecialCharacter ->
                    "Password must contain at least 1 Special Character"

                !atLeast8Characters ->
                    "Password must be at least 8 Characters long"

                else ->
                    "Invalid password"
            }
            return errorMessage
        } else {
            binding.registerTextInputLayoutContracena.boxStrokeColor =
                context?.getColor(R.color.fucia) ?: R.color.fucia
            binding.passwordErrorImageView.visibility = View.GONE
            binding.registerTextInputLayoutContracena.boxBackgroundColor =
                context?.getColor(R.color.white) ?: R.color.fucia

            return null
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

    private fun addPasswordValidationErrorStyle() {
        binding.registerTextInputLayoutContracena.boxStrokeColor =
            context?.getColor(R.color.Error) ?: R.color.fucia
        binding.passwordErrorImageView.visibility = View.VISIBLE
        binding.registerTextInputLayoutContracena.boxBackgroundColor =
            context?.getColor(R.color.ErrorInputBg) ?: R.color.fucia
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
}