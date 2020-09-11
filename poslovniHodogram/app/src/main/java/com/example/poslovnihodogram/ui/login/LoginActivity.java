package com.example.poslovnihodogram.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poslovnihodogram.MainMenuActivity;
import com.example.poslovnihodogram.R;
import com.example.poslovnihodogram.SessionManager;
import com.example.poslovnihodogram.retrofit.api.ApiUtil;
import com.example.poslovnihodogram.retrofit.services.UserRegisterService;
import com.example.poslovnihodogram.ui.login.LoginViewModel;
import com.example.poslovnihodogram.ui.login.LoginViewModelFactory;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        if (SessionManager.getFirstTimeInApp(getApplicationContext()).equals("yes")) {
            SessionManager.setFirstTimeInApp(getApplicationContext(), "no");
        }else{

            String user = SessionManager.getuserName(getApplicationContext()) + " " + SessionManager.getLastName(getApplicationContext());
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            intent.putExtra("name", user);
            startActivity(intent);

        }

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText nameEditText = findViewById(R.id.name);
        final EditText lastNameEditText = findViewById(R.id.lastname);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });



        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("") || lastNameEditText.getText().toString().equals("")
                        || nameEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Još jednom provjerite unesene podatke", Toast.LENGTH_SHORT).show();
                }else {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    registerUser(usernameEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(),
                            lastNameEditText.getText().toString());
                }

            }
        });
    }

    private void registerUser(String userName, String password, String name, String lastName) {

        HashMap<String, String> userData = new HashMap<>();

        userData.put("Email", userName);
        userData.put("Password", password);
        userData.put("FirstName", name);
        userData.put("LastName", lastName);

        UserRegisterService userRegisterService = ApiUtil.getUserRegisterService();

        userRegisterService.getPost(userData).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        handleResponse(response.body().string(), userName, password, name, lastName);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Nešto nije u redu, probajte još jednom", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.i("error", "error");
                    Toast.makeText(getApplicationContext(), "Nešto nije u redu, probajte još jednom", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Nešto nije u redu, probajte još jednom", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleResponse(String body, String userName, String password, String name, String lastName) {

        String welcome = "Dobrodošao/la " + name + " " + lastName;
        String user = name + " " + lastName;

        SessionManager.setUserName(getApplicationContext(), name);
        SessionManager.setLastName(getApplicationContext(), lastName);

        // TODO : initiate successful logged in experience
        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        intent.putExtra("name", user);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();


    }


    private void updateUiWithUser(LoggedInUserView model) {
        final EditText nameEditText = findViewById(R.id.name);
        final EditText lastNameEditText = findViewById(R.id.lastname);
        String welcome = "Dobrodošao/la " + nameEditText.getText().toString() + " " + lastNameEditText.getText().toString();
        String user = nameEditText.getText().toString() + " " + lastNameEditText.getText().toString();
        // TODO : initiate successful logged in experience
        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        intent.putExtra("name", user);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
