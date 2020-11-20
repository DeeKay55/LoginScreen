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

public class SignUpActivity extends AppCompatActivity {

    public Toolbar actionBarSignUp;
    private EditText txtUserNameSignUp, txtMailSignUp, txtPasswordSignUp, txtPasswordAgain;
    private Button btnCreateAccount;

    private FirebaseAuth auth;

    public void init() {
        actionBarSignUp = findViewById(R.id.action_barSignUp);
        setSupportActionBar(actionBarSignUp);
        getSupportActionBar().setTitle("Kayıt Ol");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        txtUserNameSignUp = findViewById(R.id.txtUserNameSignUp);
        txtMailSignUp = findViewById(R.id.txtMailSignUp);
        txtPasswordSignUp = findViewById(R.id.txtPasswordSignUp);
        txtPasswordAgain = findViewById(R.id.txtPasswordAgain);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }

        });
    }

    private void createNewAccount() {

        String userName = txtUserNameSignUp.getText().toString();
        String mail = txtMailSignUp.getText().toString();
        String password = txtPasswordSignUp.getText().toString();
        String passwordAgain = txtPasswordAgain.getText().toString();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordAgain)) {
            Toast.makeText(this, "Lütfen bütün alanları doldurunuz", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.equals(password, passwordAgain)) {
            Toast.makeText(this, "Şifreler uyuşmamakta", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Hesap başarıyla oluşturuldu", Toast.LENGTH_SHORT).show();

                        Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                    } else
                        Toast.makeText(SignUpActivity.this, "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}