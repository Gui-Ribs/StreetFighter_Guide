package com.example.streetfighterguide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    TextView textForm;
    EditText name, password, repassword;
    Button btnRegister;
    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        textForm = findViewById(R.id.textForms);
        textForm.setText("Registrar");

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        btnRegister = findViewById(R.id.btnRegister);
        dbHelper = new DBHelper(this);
    }

    public void register(View view) {

        String user = name.getText().toString();
        String word = password.getText().toString();
        String repass = repassword.getText().toString();

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(word) || TextUtils.isEmpty(repass)) {
            Toast.makeText(RegisterActivity.this, "Todos os campos devem ser registrados", Toast.LENGTH_SHORT).show();
        }
        else {
            if(word.equals(repass)) {
                Boolean viewUser = dbHelper.viewName(user);
                if(viewUser == false) {
                    Boolean insert = dbHelper.insertDb(user, word);
                    if (insert == true) {
                        Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                        MainActivity.gotoActivity(RegisterActivity.this, HallActivity.class);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "O cadastro falhou", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "O usuário já existe", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(RegisterActivity.this, "A senha não corresponde", Toast.LENGTH_SHORT).show();
            }
        }


    }
}