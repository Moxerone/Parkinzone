package com.example.leorubio.parkingzoneunedl;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leorubio.parkingzoneunedl.Objetos.Visitantes;
import com.example.leorubio.parkingzoneunedl.Objetos.Referencias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RegistroSalida extends AppCompatActivity {
    ImageView imgimage;
    Button tomarfotox, guardarx,traerPlacas;
 EditText nombre, motivo, placas, horaEntrada, horaSalida;
    static final int REQUEST_IMAGE_CAPTURE =1;
    static final int GALLERY_INTENT =1;
    private final String GREETER = "";

    StorageReference storageReference ;
    private
    ProgressDialog mProgressDialog ;
    FirebaseDatabase databaseAlumnosC = FirebaseDatabase.getInstance();

    FirebaseDatabase databaseVisitantes = FirebaseDatabase.getInstance();
    FirebaseDatabase databaseR = FirebaseDatabase.getInstance();

    final DatabaseReference myrRefR = databaseR.getReference(Referencias.VISITANTES);
    final DatabaseReference myrefR = databaseVisitantes.getReference(Referencias.VISITANTES);


    final DatabaseReference myrefRC = databaseAlumnosC.getReference(Referencias.Contadores);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_salida);

        mProgressDialog = new ProgressDialog(this);



       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myrRef = database.getReference(Referencias.ALUMNOS);*/

        nombre = (EditText) findViewById(R.id.editNombre);
        motivo = (EditText) findViewById(R.id.editMotivo);
        placas = (EditText) findViewById(R.id.editPlacas);
        horaEntrada = (EditText) findViewById(R.id.editEntrada);
        horaSalida = (EditText) findViewById(R.id.editSalida);
        imgimage = (ImageView) findViewById(R.id.imageView2);
        guardarx = (Button) findViewById(R.id.guardar);
        traerPlacas = (Button) findViewById(R.id.btnPlacas);

        traerPlacas.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                nombre.setText("");
                motivo.setText("");

                horaEntrada.setText("");
                horaSalida.setText("");

                System.out.println(placas.getText().toString());
                if(!placas.getText().toString().isEmpty()){
                final DatabaseReference hijo = myrefR.child(placas.getText().toString());
                ValueEventListener valueEventListener = myrefR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Log.i(DatabaseReference,dataSnapshot.child("hijo").getValue(Visitantes.class));
                        GenericTypeIndicator<Visitantes> t = new GenericTypeIndicator<Visitantes>() {
                        };
                        Visitantes visitante = dataSnapshot.child(placas.getText().toString()).getValue(t);
                        if (visitante != null){
                        myrRefR.child(placas.getText().toString());
                        System.out.println(placas.getText().toString());

                        String resultado = (visitante.getNombre().toString());
                        nombre.setText(resultado.toString());

                        String resultado2 = (visitante.getMotivo().toString());
                        motivo.setText(resultado2.toString());

                        String resultado3 = (visitante.getPlacas().toString());
                        placas.setText(resultado3.toString());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        Date date = new Date();
                        final String fecha = dateFormat.format(date);
                        horaSalida.setText(fecha.toString());

                        String resultado5 = (visitante.getHoraEntrada().toString());
                        horaEntrada.setText(resultado5.toString());

                    }else{
                            Toast.makeText(getApplicationContext(), "USUARIO INCORRECTO", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }else {
                    Toast.makeText(getApplicationContext(), "ERROR! CAMPO VACIOS", Toast.LENGTH_SHORT).show();


                }  }
        });


        guardarx.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                ValueEventListener valueEventListener = myrefR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<Visitantes> t = new GenericTypeIndicator<Visitantes>() {
                        };
                        Visitantes visitante = dataSnapshot.child(placas.getText().toString()).getValue(t);
                        if (visitante != null){

                if(     nombre.getText().toString().isEmpty()||horaEntrada.getText().toString().isEmpty()
                        ||  motivo.getText().toString().isEmpty()||placas.getText().toString().isEmpty()
                        ||  horaSalida.getText().toString().isEmpty()

                        ){
                    Toast.makeText(getApplicationContext(),"ERROR! CAMPOS VACIOS",Toast.LENGTH_SHORT).show();
                }




                    if (EscogerBotones.contEntrada>=0 ){

                        Visitantes visitantes = new Visitantes(nombre.getText().toString(), motivo.getText().toString(),placas.getText().toString(),horaSalida.getText().toString(),horaEntrada.getText().toString());
                        myrRefR.child(placas.getText().toString()).child("horaSalida").setValue(visitantes.getHoraSalida().toString());
                        Intent miintent = new Intent(RegistroSalida.this, EscogerBotones.class);
                        EscogerBotones.contEntrada= EscogerBotones.contEntrada-1;

                        myrefRC.child("numAlumno".toString()).child("numAlumno").setValue(String.valueOf(EscogerBotones.contEntrada));
                        startActivity(miintent);
                    }
                              if (EscogerBotones.contEntrada>46 ){
                        Toast.makeText(getApplicationContext(), "Estacionamiento no disponible", Toast.LENGTH_LONG).show();

                    }


                        //myrRefR.child(Referencias.VISITANTES).push().setValue(visitantes);



                        }else{
                            Toast.makeText(getApplicationContext(), "USUARIO INCORRECTO", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                    }


        });

        storageReference = FirebaseStorage.getInstance().getReference();

    }



  private void llamarIntent(){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null){

            startActivityForResult(takePictureIntent, GALLERY_INTENT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data){
             super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgimage.setImageBitmap(imageBitmap);

        }
    }
    private void startActivity1(RegistroSalida registroSalida, Class<EscogerBotones> main2ActivityClass) {
        Intent intent = new Intent(RegistroSalida.this, EscogerBotones.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);


    }
}

