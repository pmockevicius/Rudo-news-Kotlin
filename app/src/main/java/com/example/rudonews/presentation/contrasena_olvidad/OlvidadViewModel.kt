package com.example.rudonews.presentation.contrasena_olvidad

import com.example.rudonews.domain.usecase.AuthUsecase


interface OlvidadViewModelInterface {
    suspend fun resetPassword(mail: String):String
}

class OlvidadViewModel(val usecase: AuthUsecase) : OlvidadViewModelInterface{
    override suspend fun resetPassword(mail: String):String {
        val response = usecase.resetPassword(mail)

        return if (response) {
            "Vas a recibir un mail de recuperación, revisa tu bandeja de entrada y en Spam."
        } else "No hay ninguna cuenta registrada con este mail, revisa que has introducido el mail correctamente."

    }
}