package com.example.leorubio.parkingzoneunedl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leorubio.parkingzoneunedl.Objetos.Contadores;
import com.example.leorubio.parkingzoneunedl.Objetos.Referencias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


public class EscogerBotones extends AppCompatActivity {
    public Button escEntrada, escSalida, registerEntrada,registerSalida,entradaM,salidaM,mostrarActivos,mostrarMaestros;
    private final String GREETER = "";
    public TextView textCont,textCont2;
    static public int contEntrada,contEntrada2;
    static public  String x1 = "numAlumno";
    static public String x2 = "numMaestro";



 //   private DatabaseReference DatabaseRef;

    public void onStart(){
        super.onStart();

        FirebaseDatabase databaseR = FirebaseDatabase.getInstance();

        final DatabaseReference myrRefR = databaseR.getReference(Referencias.Contadores);
        final DatabaseReference hijo = myrRefR.child(x1.toString());
        final DatabaseReference hijo2 = myrRefR.child(x2.toString());



        ValueEventListener valueEventListener = myrRefR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Contadores> x = new GenericTypeIndicator<Contadores>() {
                };
               /* System.out.println("entro aqui"+dataSnapshot.child(x1).getValue(x).getNumAlumno());
                System.out.println("entro aqui"+dataSnapshot.child(x2).getValue(x).getNumMaestro());*/

                //  Log.i(hijo,dataSnapshot.child("hijo").getValue(Contadores.class));
               //
                // Contadores contadores = dataSnapshot.child(x1).child(x1).getValue(x);

                contEntrada = Integer.parseInt(dataSnapshot.child(x1).getValue(x).getNumAlumno());
                contEntrada2 = Integer.parseInt(dataSnapshot.child(x2).getValue(x).getNumMaestro());
                System.out.println(contEntrada);

                textCont.setText(("Espacios Alumnos: "+ String.valueOf(contEntrada)).toString());
                textCont2.setText(("Espacios Maestros: " + String.valueOf(contEntrada2)).toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("fallo la base de datos");
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escoger_botones);
        System.out.println(x1);


        textCont = (TextView) findViewById(R.id.textCont);
        textCont2 = (TextView) findViewById(R.id.textCont2);
        escEntrada = (Button) findViewById(R.id.escEntrada);
        escSalida = (Button) findViewById(R.id.escSalida);
        registerEntrada = (Button) findViewById(R.id.registrar);
        registerSalida = (Button) findViewById(R.id.regSalida);
        entradaM = (Button) findViewById(R.id.entradaM);
        salidaM = (Button) findViewById(R.id.salidaM);
        mostrarActivos = (Button) findViewById(R.id.mostrarActivos);
        mostrarMaestros = (Button) findViewById(R.id.mostrarActivos2);



        mostrarMaestros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintentMaestros = new Intent(EscogerBotones.this, MostrarActivosMaestros.class);
                startActivity(miintentMaestros);
            }
        });



        mostrarActivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity3(EscogerBotones.this, MostrarActivosAlumnos.class);
            }
        });



        registerSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent = new Intent(EscogerBotones.this, RegistroSalida.class);
                startActivity(miintent);
               /* if (EscogerBotones.contEntrada<=46&&EscogerBotones.contEntrada>=0){
                    Intent miintent = new Intent(EscogerBotones.this, RegistroSalida.class);
                    startActivity(miintent);

                }else {
                    Toast.makeText(getApplicationContext(), "no hay carros por salir", Toast.LENGTH_LONG).show();

                }*/


            }
        });
        salidaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EscogerBotones.contEntrada<100){

                    Intent miintent = new Intent(EscogerBotones.this, ScannerSalidaMaestro.class);
                    startActivity(miintent);

                }else {
                    Toast.makeText(getApplicationContext(), "no hay caros por salir", Toast.LENGTH_LONG).show();

                }




            }
        });



        escSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miintent = new Intent(EscogerBotones.this, ScannerSalida.class);
                startActivity(miintent);
                /*if (EscogerBotones.contEntrada<46&&EscogerBotones.contEntrada>=0){

                    Intent miintent = new Intent(EscogerBotones.this, ScannerSalida.class);
                    startActivity(miintent);

                }else {
                    Toast.makeText(getApplicationContext(), "no hay caros por salir", Toast.LENGTH_LONG).show();

                }*/

            }
        });

        entradaM.setOnClickListener(new View.OnClickListener() {
            //int contEntrada22=0;
            @Override
            public void onClick(View v) {
                if (EscogerBotones.contEntrada2<=0){
                    Toast.makeText(getApplicationContext(), "Estacionamiento no disponible"+contEntrada2, Toast.LENGTH_LONG).show();
                }else {
                    Intent miintent3 = new Intent(EscogerBotones.this, ScannerEntradaMaestro.class);
                    startActivity(miintent3);
                }
            }
        });




        escEntrada.setOnClickListener(new View.OnClickListener() {
            int contEntrada23=0;


            @Override
            public void onClick(View v) {
                if (EscogerBotones.contEntrada<=0){
                    Toast.makeText(getApplicationContext(), "Estacionamiento no disponible"+contEntrada, Toast.LENGTH_LONG).show();
                }else {
                    Intent miintent = new Intent(EscogerBotones.this, ScannerEntrada.class);
                    startActivity(miintent);
                }
            }
        });

        registerEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EscogerBotones.contEntrada<=0){
                    Toast.makeText(getApplicationContext(), "Estacionamiento no disponible", Toast.LENGTH_LONG).show();
                }else {
                    startActivity2(EscogerBotones.this, RegistroEntrada.class);
                }

            }
        });
    }
    private void startActivity1(EscogerBotones botones, Class<ScannerSalida> main2ActivityClass) {
        Intent intent = new Intent(EscogerBotones.this, ScannerSalida.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);


    }

    private void startActivity2(EscogerBotones botones, Class<RegistroEntrada> main3ActivityClass) {
        Intent intent = new Intent(EscogerBotones.this, RegistroEntrada.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);
    }

    private void startActivity3(EscogerBotones botones2, Class<MostrarActivosAlumnos> main3ActivityClass) {
        Intent intent = new Intent(EscogerBotones.this, MostrarActivosAlumnos.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);
    }





}

