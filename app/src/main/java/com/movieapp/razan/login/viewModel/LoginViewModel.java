package com.movieapp.razan.login.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.movieapp.razan.home.ui.HomeActivity;
import com.movieapp.razan.login.LoginActivity;
import com.movieapp.razan.login.interfac.LoginResultCallBack;
import com.movieapp.razan.login.model.UsersModel;
import com.movieapp.razan.R;

import es.dmoral.toasty.Toasty;

import static com.movieapp.razan.R.string.loginErrorEmail;

public class LoginViewModel extends ViewModel {
    private UsersModel users;
    private LoginResultCallBack loginResultCallBack;
    Context context;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public LoginViewModel(LoginResultCallBack loginResultCallBack,Context context) {
        this.users = new UsersModel();
        this.loginResultCallBack = loginResultCallBack;
        this.context = context;
    }

    //to getEmail from edatText and set to user
    public TextWatcher getEmail() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                users.setEmail(editable.toString());
            }
        };
    }

    //to getPassword from edatText and set to user
    public TextWatcher getPassword() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                users.setPassword(editable.toString());
            }
        };
    }

    //login
    public void onLogincliked(View view){
        if (users.isValidData())
        {//
            mAuth = FirebaseAuth.getInstance();
            firebaseAuthWithGoogle();
            mAuth.signInWithEmailAndPassword(users.getEmail(),users.getPassword()).addOnSuccessListener((Activity) context, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    loginResultCallBack.onSuccess(R.string.loginSuccess);

                }
            });

            mAuth.signInWithEmailAndPassword(users.getEmail(),users.getPassword()).addOnFailureListener((Activity) context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(context,  R.string.googleFaild, Toast.LENGTH_SHORT, true).show();
                }
            });
            }
    else
        {

            if(users.getEmail()==null){
                loginResultCallBack.onError(loginErrorEmail); }
            else if(users.getPassword()==null)
            {loginResultCallBack.onError(R.string.loginErrorPassword);}
        }
    }

    private void firebaseAuthWithGoogle() {

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                   context.startActivity(new Intent(context, HomeActivity.class));

                } else {
                    // If sign in fails, display a message to the user.
                     Toast.makeText(context, R.string.googleFaild,
                           Toast.LENGTH_SHORT).show();

                }
            }
        };
    }


}
