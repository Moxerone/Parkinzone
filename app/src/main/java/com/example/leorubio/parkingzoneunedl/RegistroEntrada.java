package com.example.leorubio.parkingzoneunedl;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leorubio.parkingzoneunedl.Objetos.Visitantes;
import com.example.leorubio.parkingzoneunedl.Objetos.Referencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RegistroEntrada extends AppCompatActivity {
    ImageView imgimage;
    Button tomarfotox, guardarx,traerPlacas;
    EditText nombre, motivo, placas, horaEntrada, horaSalida;
    static final int REQUEST_IMAGE_CAPTURE =1;
    static final int GALLERY_INTENT =1;
    private final String GREETER = "";
    StorageReference storageReference ;
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    Date date = new Date();
    final String fecha = dateFormat.format(date);
    private
    ProgressDialog mProgressDialog ;
    FirebaseDatabase databaseAlumnosC = FirebaseDatabase.getInstance();


    final DatabaseReference myrefRC = databaseAlumnosC.getReference(Referencias.Contadores);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_entrada);

        FirebaseDatabase databaseR = FirebaseDatabase.getInstance();
        final DatabaseReference myrRefR = databaseR.getReference(Referencias.VISITANTES);
        final DatabaseReference hijo = myrRefR.child("visitantillos");

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

        horaEntrada.setText(fecha);

        guardarx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(     nombre.getText().toString().isEmpty()||horaEntrada.getText().toString().isEmpty()
                        ||  motivo.getText().toString().isEmpty()||placas.getText().toString().isEmpty()


                        ){
                    Toast.makeText(getApplicationContext(),"ERROR! CAMPOS VACIOS",Toast.LENGTH_SHORT).show();
                }else {

                    if (EscogerBotones.contEntrada<47 ){
                        Visitantes visitante = new Visitantes(nombre.getText().toString(), motivo.getText().toString(),placas.getText().toString(),horaSalida.getText().toString(),fecha.toString());
                        myrRefR.child(placas.getText().toString()).setValue(visitante);
                        EscogerBotones.contEntrada= EscogerBotones.contEntrada-1;
                        myrefRC.child("numAlumno".toString()).child("numAlumno").setValue(String.valueOf(EscogerBotones.contEntrada));
                        Intent miintent = new Intent(RegistroEntrada.this, EscogerBotones.class);
                        startActivity(miintent);
                        }if (EscogerBotones.contEntrada==0){
                        Toast.makeText(getApplicationContext(), "Estacionamiento no disponible", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });

        storageReference = FirebaseStorage.getInstance().getReference(placas.toString()).child(placas.toString());

    }

    private boolean validarPermisos() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if ((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)){

            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA))|| (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }
        return false;
    }



    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(RegistroEntrada.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage(" debes aceptar los permisos para el correcto funcionamiento de la app");
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
                }
            }
        });
        dialogo.show();
    }

 private void llamarIntent(){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null){

            //takePictureIntent.setType("image/*");




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
    private void startActivity1(RegistroEntrada main3Activity, Class<EscogerBotones> main2ActivityClass) {
        Intent intent = new Intent(RegistroEntrada.this, EscogerBotones.class);

        intent.putExtra("greeter",GREETER);
        startActivity(intent);


    }
}


