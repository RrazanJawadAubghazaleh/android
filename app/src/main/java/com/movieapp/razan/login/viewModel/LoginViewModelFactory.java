package com.movieapp.razan.login.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.movieapp.razan.login.interfac.LoginResultCallBack;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private LoginResultCallBack loginResultCallBack;
    Context context;

    public LoginViewModelFactory(LoginResultCallBack loginResultCallBack,Context context) {
        this.loginResultCallBack = loginResultCallBack;
        this.context=context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return  (T)new LoginViewModel(loginResultCallBack,context);
    }
}
