package com.example.leorubio.parkingzoneunedl;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.leorubio.parkingzoneunedl.Objetos.Referencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerEntrada extends AppCompatActivity  implements ZXingScannerView.ResultHandler{

    private View btn, btn2;
    private final String GREETER = "", GREETER2 ="";
    private ZXingScannerView myScanner;
    private EditText leidox;
    static public int scaner =0 ;
    static public String escaneado="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.escaner_entrada);
        bottoenScanner(myScanner);

    }

    public void bottoenScanner(View v){
        myScanner = new ZXingScannerView(this);
        setContentView(myScanner);
        myScanner.startCamera();
        myScanner.setResultHandler(this);

    }

    @Override
    public void handleResult(Result result) {
        FirebaseDatabase databaseR = FirebaseDatabase.getInstance();
        final DatabaseReference myrRefR = databaseR.getReference(Referencias.ALUMNOS);
        final DatabaseReference hijo = myrRefR.child(result.getText().toString());



         escaneado=result.getText().toString();
        scaner=2;

        startActivity(ScannerEntrada.this, EscaneoEntrada.class);

    }

    private void startActivity(ScannerEntrada scannerSalida, Class<EscaneoEntrada> main4ActivityClass) {
        Intent intent = new Intent(ScannerEntrada.this, EscaneoEntrada.class);

        intent.putExtra("greeter",GREETER2);
        startActivity(intent);
        bottoenScanner(myScanner);

    }


}