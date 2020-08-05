package com.movieapp.razan.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.movieapp.razan.R;
import com.movieapp.razan.databinding.ActivityLoginBinding;
import com.movieapp.razan.home.ui.HomeActivity;
import com.movieapp.razan.login.interfac.LoginResultCallBack;
import com.movieapp.razan.login.viewModel.LoginViewModel;
import com.movieapp.razan.login.viewModel.LoginViewModelFactory;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity  implements LoginResultCallBack {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(ViewModelProviders.of(this,
                new LoginViewModelFactory(this, this))
                .get(LoginViewModel.class));
        mAuth = FirebaseAuth.getInstance();
        firebaseAuthWithGoogle();
//
        binding.edEnterUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edEnterUserName.setCompoundDrawablesWithIntrinsicBounds
                        (ContextCompat.getDrawable(LoginActivity.this, R.drawable.ic_user)
                                , null, null, null);
            }
        });
//
        binding.edEnterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edEnterPassword.setCompoundDrawablesWithIntrinsicBounds
                        (ContextCompat.getDrawable(LoginActivity.this, R.drawable.ic_lock)
                                , null, null, null);
            }
        });

    }


    private void firebaseAuthWithGoogle() {

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    // If sign in fails, display a message to the user.
                   // Toast.makeText(LoginActivity.this, R.string.googleFaild,
                     //       Toast.LENGTH_SHORT).show();

                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
       if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }

    }

    @Override
    public void onSuccess(int massage) {
        Toasty.success(this, R.string.loginSuccess, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onError(int massage) {
        Toasty.error(this,  R.string.loginError, Toast.LENGTH_SHORT, true).show();
    }
}