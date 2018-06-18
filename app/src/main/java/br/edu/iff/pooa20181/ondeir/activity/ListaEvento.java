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

        //tem que ter o objeto do realm, para poder fazer p select
        realm = Realm.getDefaultInstance();
        //é a classe que representa o botao flutuante de + na tela
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quando clicar nesse botão de +, vai chamar uma nova classe para inserir uma informação.
                //chama da intent da activity eventodetalhe(pag q mostra o detalhe)
                //passa o id 0, que significa que está mandando inserir(essa verificação é feita em eventodetalhe)
                //caso o id seja diferente de 0, ele identifica que é para fazer outra ação(deletar ou atualizar)
                Intent intent = new Intent(ListaEvento.this,EventoDetalhe.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }

        });
    }
//oncreate só é executado quando cria a tela
    //método onresume, que indica que toda vez que volta para uma tela, ele executa o que está no código
    //neste caso, monta o recyclerview com o que está na lista
       protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvEventos);

        recyclerView.setAdapter(new EventoAdapter(getEventos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //método para pegar os eventos do banco
    //retorna um select, que pega todos os elementos da tabela evento
     public List<Evento> getEventos(){

         return (List) realm.where(Evento.class).findAll();

     }

     //no momento que clica no objeto
    //cria a intent, pega o id do objeto clicado(a pk do evento)
    //passa como parametro para a chamada da prox tela
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
