package com.example.erick.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;



    public Myadapter(List<String> names, int layout, OnItemClickListener listener){
        this.names = names;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //Inflamos el layout y se lo pasamos al constructor del ViewHolder, donde manejaremos
        //toda la lógica como extraer los datos, referencias....
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        //Se crea nuestro ViewHolder
        ViewHolder vh = new ViewHolder(v);//Se le pasa la vista inflada
        return  vh;
    }

    //Va hacer el volcado de datos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(names.get(position),itemClickListener);

    }

    @Override
    public int getItemCount() {
        //Número de lista a obtener
        return names.size(); //tamaño de la lista
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname;

        public ViewHolder(View itemView){
            //Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            //con nuestras propiedades ViewHolder declarados justo arriba
            super(itemView);
            this.textViewname = (TextView) itemView.findViewById(R.id.textView);
        }

        public void bind(final String name, final OnItemClickListener listener ){
            //Procesamos los datos a renderizar
            this.textViewname.setText(name);

            //Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
            //que se comporta de la siguiente manera..
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(name,getAdapterPosition());//Se pasa el nombre y la posición actual
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(String name, int position);
    }

}
