package com.example.leorubio.parkingzoneunedl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leorubio.parkingzoneunedl.Objetos.Maestros;
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

public class EscaneoSalidaMaestro extends AppCompatActivity {
    private ZXingScannerView myScanner;
    EditText tipo, carrera, placas, hrEntrada, hrSalida, nombre;
    Button registrox, guardarx, buttonDatos;
    private final String GREETER = "", GREETER2 = "";
    Boolean validacion;
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    Date date = new Date();
    String activo;
    String resultado7="1";
    final String fecha = dateFormat.format(date);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escaneo_salida);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        tipo = (EditText) findViewById(R.id.editTextTipo);
        carrera = (EditText) findViewById(R.id.editTextCarrera);
        placas = (EditText) findViewById(R.id.editTextPlacas);
        hrEntrada = (EditText) findViewById(R.id.editTextEntrada);
        hrSalida = (EditText) findViewById(R.id.editTextSalida);

        registrox = (Button) findViewById(R.id.registro);



        FirebaseDatabase databaseAlumnos = FirebaseDatabase.getInstance();






        if (ScannerSalidaMaestro.scaner ==1){
            final DatabaseReference myrefR = databaseAlumnos.getReference(Referencias.MAESTROS);



            final DatabaseReference hijo = myrefR.child(ScannerSalidaMaestro.escaneado.toString());
            ValueEventListener valueEventListener = myrefR.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Log.i(DatabaseReference,dataSnapshot.child("hijo").getValue(Visitantes.class));
                    GenericTypeIndicator<Maestros> t = new GenericTypeIndicator<Maestros>() {
                    };

                    Maestros maestros = dataSnapshot.child(ScannerSalidaMaestro.escaneado.toString()).getValue(t);
                    if (maestros !=null){

                        myrefR.child(ScannerSalida.escaneado.toString());



                    String resultado2 = (maestros.getCarrera().toString());
                    carrera.setText(resultado2.toString());

                    String resultado3 = (maestros.getPlacas().toString());
                    placas.setText(resultado3.toString());

                    String resultado4 = (maestros.getEntrada().toString());
                    hrEntrada.setText(resultado4.toString());

                    String resultado = (maestros.getNombre().toString());
                    nombre.setText(resultado.toString());

                    String resultado5 = (maestros.getSalida().toString());
                    hrSalida.setText(fecha.toString());

                    String resultado6 = (maestros.getTipo().toString());
                    tipo.setText(resultado6.toString());

                    activo = ( maestros.getActivo().toString() );

                        /*String resultado4 =(visitante.getHoraSalida().toString());
                        horaSalida.setText(resultado4.toString());*/
                }else {
                        Toast.makeText(getApplicationContext(), "USUARIO INCORRECTO", Toast.LENGTH_LONG).show();
                        startActivity(EscaneoSalidaMaestro.this, EscogerBotones.class);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        registrox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EscaneoSalidaMaestro.this, EscogerBotones.class);

            }
        });

        guardarx = (Button) findViewById(R.id.button2);
        guardarx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(         tipo.getText().toString().isEmpty()||carrera.getText().toString().isEmpty()
                        ||  placas.getText().toString().isEmpty()||hrEntrada.getText().toString().isEmpty()
                        ||  hrSalida.getText().toString().isEmpty()

                        ){
                    Toast.makeText(getApplicationContext(),"ERROR! CAMPOS VACIOS",Toast.LENGTH_SHORT).show();
                }else {

                    if (EscogerBotones.contEntrada2<0 ){
                        Toast.makeText(getApplicationContext(), "Estacionamiento no disponible", Toast.LENGTH_LONG).show();
                    }
                    if (EscogerBotones.contEntrada2>=37 ){
                        Toast.makeText(getApplicationContext(), "Estacionamiento no disponible", Toast.LENGTH_LONG).show();

                    }
                    if(activo.equals(resultado7)) {
                        FirebaseDatabase databasemaestrosC = FirebaseDatabase.getInstance();
                        FirebaseDatabase databaseAlumnos2 = FirebaseDatabase.getInstance();
                        final DatabaseReference myrefR = databaseAlumnos2.getReference(Referencias.MAESTROS);
                        final DatabaseReference hijo = myrefR.child(ScannerEntrada.escaneado.toString());
                        final DatabaseReference myrefRC = databasemaestrosC.getReference(Referencias.Contadores);
                        Maestros maestros = new Maestros(nombre.getText().toString(), tipo.getText().toString(), carrera.getText().toString(), placas.getText().toString(), hrEntrada.getText().toString(), hrSalida.getText().toString(), activo);
                        myrefR.child(placas.getText().toString()).setValue(maestros);
                        myrefR.child(placas.getText().toString()).child("activo").setValue("0");

                        startActivity2(EscaneoSalidaMaestro.this, EscogerBotones.class);
                        EscogerBotones.contEntrada2 = EscogerBotones.contEntrada2 + 1;


                        myrefRC.child("numMaestro".toString()).child("numMaestro").setValue(String.valueOf(EscogerBotones.contEntrada2));
                        activo="0";

                        //referenceAlumnos.child(alumno.getPlacas()).setValue(alumno);
                        // referenceAlumnos.child(Referencias.ALUMNOS).child(alumno.getPlacas()).setValue(alumno);
                        //referenceAlumnos.child(Referencias.ALUMNOS).push().setValue(alumno);
                    }else{
                        Toast.makeText(getApplicationContext(), "EL MAESTRO NO HA INGRESADO A EL ESTACIONAMIENTO", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

    }

    private void startActivity(EscaneoSalidaMaestro escaneoSalida, Class<EscogerBotones> main3ActivityClass) {
        Intent intent = new Intent(EscaneoSalidaMaestro.this, EscogerBotones.class);

        intent.putExtra("greeter",GREETER2);
        startActivity(intent);


    }

    private void startActivity2(EscaneoSalidaMaestro escaneoSalida, Class<EscogerBotones> main2ActivityClass) {
        Intent intent = new Intent(EscaneoSalidaMaestro.this, EscogerBotones.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);


    }
}

