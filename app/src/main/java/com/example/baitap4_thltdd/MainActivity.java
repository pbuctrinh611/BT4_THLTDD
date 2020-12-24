package com.example.baitap4_thltdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    EditText edt_email, edt_pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        addControls();
    }
    public void addControls(){
        btn_login = findViewById(R.id.btn_login);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn() {
        String email = edt_email.getText().toString();
        String pw = edt_pass.getText().toString();

        if (email.isEmpty()){
            edt_email.setError("Không được để trống");
            edt_email.requestFocus();
            return;
        }
        if (pw.isEmpty()){
            edt_pass.setError("Không được để trống");
            edt_pass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(this, new OnCompleteListener< AuthResult >() {
            @Override
            public void onComplete(@NonNull Task< AuthResult > task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}