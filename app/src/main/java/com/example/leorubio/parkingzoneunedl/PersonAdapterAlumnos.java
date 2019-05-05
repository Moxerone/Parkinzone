package com.example.leorubio.parkingzoneunedl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leorubio.parkingzoneunedl.Objetos.Alumnos;

import java.util.List;

public class PersonAdapterAlumnos extends RecyclerView.Adapter<PersonAdapterAlumnos.ArtistViewHolder> {

    private Context mCtx;
    private List<Alumnos> personList;

    public PersonAdapterAlumnos(Context mCtx, List<Alumnos> artistList) {
        this.mCtx = mCtx;
        this.personList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.mostrar_activos_alumnos, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Alumnos alumnos = personList.get(position);
        holder.textViewName.setText(alumnos.getNombre());
        holder.textViewGenre.setText("Activo: " + alumnos.getActivo());
        holder.textViewAge.setText("Hora Entrada: " + alumnos.getEntrada());
        holder.textViewCountry.setText("Carrera: " + alumnos.getCarrera());
        holder.textViewPlacas.setText("Placas: " + alumnos.getPlacas());
        holder.textViewTipo.setText("Tipo: " + alumnos.getTipo());

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
