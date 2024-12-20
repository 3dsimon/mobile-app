package com.example.bigproject.account

import androidx.annotation.MainThread
import com.example.bigproject.databinding.AccountLoginBinding

@MainThread
class AccountView(val binding: AccountLoginBinding, private val model: AccountModel) {
    var name=binding.editName
    var account=binding.editAccount
    var password=binding.editPassword
    var password2=binding.editPassword2

    @MainThread
    fun AccountModeLoad() {
        name.visibility =  model.visibility
        password2.visibility = model.visibility
        binding.accountName.visibility = model.visibility
        binding.passwordText2.visibility = model.visibility
        binding.loginButton.text=model.login_button_text
        binding.registerButton.setBackgroundResource(model.background)
    }
}