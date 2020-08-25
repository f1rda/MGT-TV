package com.example.contoh2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contoh2.network.ServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    private TextView etBranch, etZoneCode;
    private Button btnLogin;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameApi = BuildConfig.Username;
                String api_key = BuildConfig.ApiKey;
                String user = etUser.getText().toString();
                String password = etPassword.getText().toString();

                if (user.isEmpty()) {
                    etUser.setError("User Empty");
                    return;
                }

                if (password.isEmpty()) {
                    etPassword.setError("Password Empty");
                    return;
                }

                doLogin(usernameApi, api_key,user, password);
            }
        });


    }
    private void doLogin(String usernameApi, String api_key, String user, String password) {
        etUser.setEnabled(false);
        etPassword.setEnabled(false);
        btnLogin.setEnabled(false);

        ServiceClient
                .buildServiceClient()
                .auth(usernameApi,api_key,user,password)
                .enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if (response.isSuccessful()) {
                            LoginResponse auth = response.body();
                            if (auth.isStatus() == true) {

                                finish();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                                startActivity(intent);

                            } else {
                                //alert.showAlertDialog(LoginActivity.this, "Login gagal.. ", "User atau password salah",false);
                                //Toast.makeText(LoginActivity.this, auth.getErrorReason(), Toast.LENGTH_LONG).show();
                                Toast.makeText(LoginActivity.this, "Username atau password salah, silahkan login kembali", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > "+ t.getMessage());
                        Toast.makeText(LoginActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
