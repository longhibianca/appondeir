package br.edu.iff.pooa20181.ondeir.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.edu.iff.pooa20181.ondeir.R;
import br.edu.iff.pooa20181.ondeir.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.ondeir.adapter.EventoAdapter;
import br.edu.iff.pooa20181.ondeir.model.Evento;
import io.realm.Realm;

public class ListaEvento extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaEvento.this,EventoDetalhe.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }

        });
    }


    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvEventos);

        recyclerView.setAdapter(new EventoAdapter(getEventos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

     public List<Evento> getEventos(){

         return (List) realm.where(Evento.class).findAll();

     }

    @Override
    public void onClick(Object object) {
        Evento evento = (Evento) object;
        Intent intent = new Intent(ListaEvento.this,EventoDetalhe.class);
        intent.putExtra("id",evento.getId());
        startActivity(intent);
    }


    public void finish(){
        super.finish();
        realm.close();


    }
}
