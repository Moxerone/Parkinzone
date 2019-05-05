package com.example.leorubio.parkingzoneunedl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public int contEntrada=10;

    private View btnLogin, btnRegister;
    private final String GREETER = "";
    public EditText editUsuario, editContrasena;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editContrasena = (EditText) findViewById(R.id.editContrasena);

        //btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.i("SESION", "sesion iniciada: "+ user.getEmail());
                    /*Intent intent = new Intent(Login.this, ScannerSalida.class);
                    intent.putExtra("greeter", GREETER);
                    startActivity(intent);*/

                }else {
                    Log.i("SESION", "sesion cerrada");
                }
            }
        };

    }

    private void iniciarSesion(String email, String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"INICIO DE SESION",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, EscogerBotones.class);
                    intent.putExtra("greeter", GREETER);
                    startActivity(intent);


                }else{
                    Toast.makeText(getApplicationContext(),"NO EXISTE USUARIO",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                if(editUsuario.getText().toString().isEmpty()||editContrasena.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"ERROR! CAMPOS VACIOS",Toast.LENGTH_SHORT).show();
                }else {
                    String email = editUsuario.getText().toString();
                    String pass = editContrasena.getText().toString();
                    iniciarSesion(email, pass);

                    break;
                }

        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (mAuthListener!=null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }

    }

}
