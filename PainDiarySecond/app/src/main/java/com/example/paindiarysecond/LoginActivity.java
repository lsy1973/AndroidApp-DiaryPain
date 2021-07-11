package com.example.paindiarysecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Toast;


import com.example.paindiarysecond.database.CustomerDatabase;
import com.example.paindiarysecond.databinding.ActivityLoginBinding;
import com.example.paindiarysecond.databinding.ActivityMainBinding;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private boolean hide = true;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getWindow().setStatusBarColor(0xff039BE5);

        binding.iv3.setImageResource(R.drawable.eye_close);
        binding.iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv3:
                        if (hide == true){
                            binding.iv3.setImageResource(R.drawable.eye_open);  //可见样貌
                            HideReturnsTransformationMethod method = HideReturnsTransformationMethod.getInstance(); //可见
                            binding.etPassword.setTransformationMethod(method);
                            hide = false;
                        }else{
                            binding.iv3.setImageResource(R.drawable.eye_close);  //不可见样貌
                            TransformationMethod method = PasswordTransformationMethod.getInstance();  //隐藏
                            binding.etPassword.setTransformationMethod(method);
                            hide = true;
                        }
                        int index = binding.etPassword.getText().toString().length();
                        binding.etPassword.setSelection(index);
                        break;
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        //Register Button -> RegisterActivity
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = null ;
                String password = null;
                email = binding.etUseremail.getText().toString();
                password = binding.etPassword.getText().toString();
                if ((!email.isEmpty() && email != null) && (!password.isEmpty() && password != null)) {
                    signIn();
                } else {
                    Toast.makeText(LoginActivity.this,"you input is Null! ",Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        binding.directEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void signIn() {
//        EditText etMail = findViewById(R.id.et_useremail);
//        EditText etPassword = findViewById(R.id.et_password);
        String email = binding.etUseremail.getText().toString();
        String password = binding.etPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login succeed! ", Toast.LENGTH_SHORT).show();
                } else {
                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Login failed. Please identify your email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }

    private void reload() { }
    private void updateUI(FirebaseUser user) {

    }
}