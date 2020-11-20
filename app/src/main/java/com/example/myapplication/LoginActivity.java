package com.example.myapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public Toolbar actionBarLogin;
    private EditText txtMailLogin, txtPasswordLogin;
    private Button btnLoginApp;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init() {
        actionBarLogin = (Toolbar) findViewById(R.id.action_barLogin);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        txtMailLogin = findViewById(R.id.txtMailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnLoginApp = findViewById(R.id.btnLoginApp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLoginApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginApp();
            }

        });
    }

    public void loginApp() {

        String mail = txtMailLogin.getText().toString();
        String password = txtPasswordLogin.getText().toString();

        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Lütfen bütün alanları doldurunuz", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //Toast.makeText(LoginActivity.this, "Hesap başarıyla oluşturuldu", Toast.LENGTH_SHORT).show();

                        Intent login = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(login);
                        finish();
                    } else
                        Toast.makeText(LoginActivity.this, "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}