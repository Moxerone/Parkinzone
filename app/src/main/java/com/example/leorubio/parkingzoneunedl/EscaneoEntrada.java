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

import com.example.leorubio.parkingzoneunedl.Objetos.Alumnos;
import com.example.leorubio.parkingzoneunedl.Objetos.Referencias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class EscaneoEntrada extends AppCompatActivity {
    private ZXingScannerView myScanner;
    EditText tipo;
    EditText carrera;
    EditText placas;
    EditText hrEntrada;
    EditText hrSalida;
    EditText nombre;
    String activo;
    String resultado7="0";
    Button registrox, guardarx, buttonDatos;
    Boolean validacion;
    private final String GREETER = "", GREETER2 = "";
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    Date date = new Date();
    final String fecha = dateFormat.format(date);
    FirebaseDatabase databaseAlumnos = FirebaseDatabase.getInstance();

    FirebaseDatabase databaseAlumnos1 = FirebaseDatabase.getInstance();
    FirebaseDatabase databaseAlumnosC = FirebaseDatabase.getInstance();

    final DatabaseReference myrefR = databaseAlumnos1.getReference(Referencias.ALUMNOS);
    final DatabaseReference myrefRC = databaseAlumnosC.getReference(Referencias.Contadores);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escaner_entrada);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        tipo = (EditText) findViewById(R.id.editTextTipo);
        carrera = (EditText) findViewById(R.id.editTextCarrera);
        placas = (EditText) findViewById(R.id.editTextPlacas);
        hrEntrada = (EditText) findViewById(R.id.editTextEntrada);
        hrSalida = (EditText) findViewById(R.id.editTextSalida);

        registrox = (Button) findViewById(R.id.registro);







        if (ScannerEntrada.scaner ==2){

            final DatabaseReference myrefR = databaseAlumnos.getReference(Referencias.ALUMNOS);
            final DatabaseReference hijo = myrefR.child(ScannerEntrada.escaneado.toString());

            Log.i(String.valueOf((hijo!=null)),"Verificando hijo");



                ValueEventListener valueEventListener = myrefR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Log.i(DatabaseReference,dataSnapshot.child("hijo").getValue(Visitantes.class));
                        GenericTypeIndicator<Alumnos> t = new GenericTypeIndicator<Alumnos>() {
                        };

                        Alumnos alumnos = dataSnapshot.child(ScannerEntrada.escaneado.toString()).getValue(t);
                        if (alumnos != null) {
                            System.out.println(alumnos);
                            myrefR.child(ScannerEntrada.escaneado.toString());


                            String resultado2 = ( alumnos.getCarrera().toString() );
                            carrera.setText(resultado2.toString());

                            String resultado3 = ( alumnos.getPlacas().toString() );
                            placas.setText(resultado3.toString());

                            String resultado4 = ( alumnos.getEntrada().toString() );
                            hrEntrada.setText(fecha.toString());

                            String resultado = ( alumnos.getNombre().toString() );
                            nombre.setText(resultado.toString());

                            String resultado5 = ( alumnos.getSalida().toString() );
                            hrSalida.setText(resultado5.toString());

                            String resultado6 = ( alumnos.getTipo().toString() );
                            tipo.setText(resultado6.toString());
                            activo = ( alumnos.getActivo().toString() );
                            System.out.println(activo);

                        /*String resultado4 =(visitante.getHoraSalida().toString());
                        horaSalida.setText(resultado4.toString());*/

                        }else{
                            Toast.makeText(getApplicationContext(), "USUARIO INCORRECTO", Toast.LENGTH_LONG).show();
                            startActivity(EscaneoEntrada.this, EscogerBotones.class);

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            //el if


        }

        registrox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EscaneoEntrada.this, EscogerBotones.class);

            }
        });

        guardarx = (Button) findViewById(R.id.button2);
        guardarx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(         tipo.getText().toString().isEmpty()||carrera.getText().toString().isEmpty()
                        ||  placas.getText().toString().isEmpty()||hrEntrada.getText().toString().isEmpty()


                        ){
                    Toast.makeText(getApplicationContext(),"ERROR! CAMPOS VACIOS",Toast.LENGTH_SHORT).show();
                }
                    if (EscogerBotones.contEntrada>46||EscogerBotones.contEntrada==0 ) {
                        Toast.makeText(getApplicationContext(), "Estacionamiento no disponible", Toast.LENGTH_LONG).show();
                    }
                        if(activo.equals(resultado7)){
                            final DatabaseReference hijo = myrefR.child(ScannerEntrada.escaneado.toString());

                            Alumnos alumno = new Alumnos(nombre.getText().toString(), tipo.getText().toString(), carrera.getText().toString(), placas.getText().toString(), hrEntrada.getText().toString(), hrSalida.getText().toString(),activo.toString());
                            myrefR.child(placas.getText().toString()).setValue(alumno);
                            myrefR.child(placas.getText().toString()).child("activo").setValue("1");
                            EscogerBotones.contEntrada= EscogerBotones.contEntrada-1;

                            myrefRC.child("numAlumno".toString()).child("numAlumno").setValue(String.valueOf(EscogerBotones.contEntrada));
                            startActivity2(EscaneoEntrada.this, EscogerBotones.class);
                            activo="1";






                        }else{
                            Toast.makeText(getApplicationContext(), "EL ALUMNO ESTA DENTRO DE EL ESTACIONAMIENTO"+activo, Toast.LENGTH_LONG).show();



                        }



                       /* Alumnos alumno = new Alumnos(nombre.getText().toString(), tipo.getText().toString(), carrera.getText().toString(), placas.getText().toString(), hrEntrada.getText().toString(), hrSalida.getText().toString());
                        referenceAlumnos.setValue(alumno);*/

                        //referenceAlumnos.child(alumno.getPlacas()).setValue(alumno);
                        // referenceAlumnos.child(Referencias.ALUMNOS).child(alumno.getPlacas()).setValue(alumno);
                        //referenceAlumnos.child(Referencias.ALUMNOS).push().setValue(alumno);



            }
        });

    }

    private void startActivity(EscaneoEntrada escaneoSalida, Class<EscogerBotones> main3ActivityClass) {
        Intent intent = new Intent(EscaneoEntrada.this, EscogerBotones.class);

        intent.putExtra("greeter",GREETER2);
        startActivity(intent);

    }

    private void startActivity2(EscaneoEntrada escaneoSalida, Class<EscogerBotones> main2ActivityClass) {
        Intent intent = new Intent(EscaneoEntrada.this, EscogerBotones.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);

    }
}

