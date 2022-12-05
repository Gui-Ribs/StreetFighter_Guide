package com.example.streetfighterguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {

    TextView textForm;
    EditText username, password;
    CheckBox chk_one, chk_two;
    DBHelper DB;
    SharedPreferences preferences;

    private static final String SHARED_MAIN = "Kmain";
    private static final String SHARED_NAME = "Kname";
    private static final String NAME_FILE = "Dados_Usuário.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textForm = findViewById(R.id.textForms);
        textForm.setText("Login");

        username = findViewById(R.id.name);
        password = findViewById(R.id.password);
        chk_one = findViewById(R.id.chk_one);
        chk_two = findViewById(R.id.chk_two);
        DB = new DBHelper(this);


    }

    public void logar(View view) {

        String name = username.getText().toString();
        String pass = password.getText().toString();

        preferences = getSharedPreferences(SHARED_MAIN , MODE_PRIVATE);
        String check = preferences.getString(SHARED_NAME, null);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SHARED_NAME, name);
        editor.apply();

        if (check != null) {

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                Toast.makeText(LoginActivity.this, "Os campos não podem ficar nulos", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean viewMoast = DB.viewAll(name, pass);
                if(viewMoast == true) {
                    Toast.makeText(LoginActivity.this, "Login efetuado", Toast.LENGTH_SHORT).show();
                    MainActivity.gotoActivity(LoginActivity.this, MainActivity.class);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else {
            Toast.makeText(LoginActivity.this, "O campo nome está nulo", Toast.LENGTH_SHORT).show();
        }
    }

    public void external(View view) {

        String nome = username.getText().toString();
        String senha = password.getText().toString();

        String state = Environment.getExternalStorageState();

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File arq = new File(dir,nome + "_" + NAME_FILE);

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(senha)) {
            Toast.makeText(LoginActivity.this, "Os campos não podem ser nulos", Toast.LENGTH_SHORT).show();
        }
        else {
            if(Environment.MEDIA_MOUNTED.equals(state)) {
                try{
                    FileOutputStream fos = new FileOutputStream(arq);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject("Password :" + password.getText().toString());
                    fos.close();
                    oos.close();
                    Toast.makeText(LoginActivity.this, "Dados salvos com sucesso no armazenamento exteno", Toast.LENGTH_SHORT).show();

                }
                catch (IOException io) {
                    io.printStackTrace();
                }
            }
            else {
                Toast.makeText(LoginActivity.this, "Não foi possível armazenar, devido a falta de permissões", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void internal(View view) {

        String nome = username.getText().toString();
        String senha = password.getText().toString();

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File arq = new File(dir,nome + "_" + NAME_FILE);

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(senha)) {
            Toast.makeText(LoginActivity.this, "Os campos não podem ser nulos", Toast.LENGTH_SHORT).show();
        }
        else {

            try {
                FileOutputStream fos = new FileOutputStream(arq);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject("Nome :" + username.getText().toString());
                oos.writeObject("Password :" + password.getText().toString());
                oos.close();
                fos.close();
                Toast.makeText(LoginActivity.this, "Dados salvos com sucesso no armazenamento Interno", Toast.LENGTH_SHORT).show();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

    }

}