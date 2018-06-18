package br.edu.iff.pooa20181.ondeir.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.iff.pooa20181.ondeir.R;
import br.edu.iff.pooa20181.ondeir.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.ondeir.adapter.EventoAdapter;
import br.edu.iff.pooa20181.ondeir.model.Evento;

public class ListaEvento extends AppCompatActivity implements ClickRecyclerViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvEventos);

        recyclerView.setAdapter(new EventoAdapter(getEventos(),this,this));
       // RecyclerView.LayoutManager mLayoutManager  = ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

     public List<Evento> getEventos(){

        List<Evento> eventos = new ArrayList<Evento>();
         int i =0;
         for(i=0;i<20;i++){
             String iv = String.valueOf(i);
             Evento evento = new Evento(i,"nome -> ".concat(iv),new Date(System.currentTimeMillis()),i);
             eventos.add(evento);
             Log.i("------XXXXXXXXX--", evento.getNome());
         }
         return eventos;

     }

    @Override
    public void onClick(Object object) {
        Evento evento = (Evento) object;
        Intent intent = new Intent(ListaEvento.this,EventoDetalhe.class);
        intent.putExtra("nome",evento.getNome());
        intent.putExtra("data",evento.getData());
        intent.putExtra("capacidade",evento.getCapacidade());
        startActivity(intent);
    }
}
