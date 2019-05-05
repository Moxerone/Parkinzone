package com.example.leorubio.parkingzoneunedl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.leorubio.parkingzoneunedl.Objetos.Maestros;

import java.util.List;

public class PersonAdapterMaestros extends RecyclerView.Adapter<PersonAdapterMaestros.ArtistViewHolder> {
    private Context mCtx;
    private List<Maestros> personList;

    public PersonAdapterMaestros(Context mCtx, List<Maestros> artistList) {
        this.mCtx = mCtx;
        this.personList = artistList;
    }
    @NonNull
    @Override
    public PersonAdapterMaestros.ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.mostrar_activos_maestros, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Maestros maestros = personList.get(position);
        holder.textViewName.setText(maestros.getNombre());
        holder.textViewGenre.setText("Activo: " + maestros.getActivo());
        holder.textViewAge.setText("Hora Entrada: " + maestros.getEntrada());
        holder.textViewCountry.setText("Carrera: " + maestros.getCarrera());
        holder.textViewPlacas.setText("Placas: " + maestros.getPlacas());
        holder.textViewTipo.setText("Tipo: " + maestros.getTipo());
    }



    @Override
    public int getItemCount() {
        return personList.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewGenre, textViewAge, textViewCountry,textViewPlacas,textViewTipo;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewGenre = itemView.findViewById(R.id.text_view_genre);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewCountry = itemView.findViewById(R.id.text_view_country);
            textViewPlacas = itemView.findViewById(R.id.text_view_placas);
            textViewTipo = itemView.findViewById(R.id.text_view_tipo);
        }
    }






}
