package com.example.cube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText email;

    Button loginButton;
    TextView registation;
    TextView title;
    boolean registrationMode = false;
    static DataBaseHelper DBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registation = findViewById(R.id.signupText);
        title = findViewById(R.id.loginText);
        email = findViewById(R.id.email);
        DBHelper = new DataBaseHelper(this);
        registation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AUtorisation", "called");
                if(registrationMode){
                    Log.i("AUtorisation", "called auted");
                    registrationMode = false;
                    title.setText("Авторизация");
                    loginButton.setText("Войти");
                    email.setVisibility(View.GONE);
                    registation.setText("Не зарегестрированны? Зарегестрироваться");
                }
                else{
                    registrationMode = true;
                    title.setText("Регистрация");
                    username.setText("");
                    password.setText("");
                    loginButton.setText("Сохранить");
                    email.setVisibility(View.VISIBLE);
                    registation.setText("Уже есть аккаунт? Войти");
                }

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registrationMode) {
                    if (DBHelper.findUser(username.getText().toString(), password.getText().toString()) != null || username.getText().toString().length() == 0) {
                        Toast.makeText(LoginActivity.this, "Выберите другое имя", Toast.LENGTH_SHORT).show();
                    }
                    else if (password.getText().toString().length() < 6) {
                        Toast.makeText(LoginActivity.this, "Слишком короткий пароль", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(DBHelper.addUser(new User(-1, username.getText().toString(), password.getText().toString(), 0))){
                            Toast.makeText(LoginActivity.this, "Вы зарегистрированы", Toast.LENGTH_SHORT).show();
                            //Log.i("AUtorisation", "Button added");
                            registation.performClick();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Не получилось зарегистрироваться", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else{
                    if (DBHelper.findUser(username.getText().toString(), password.getText().toString()) != null) {
                        Toast.makeText(LoginActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("userStatus", DBHelper.findUser(username.getText().toString(), password.getText().toString()).getIs_admin());
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Ваш аккаунт не найден", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}