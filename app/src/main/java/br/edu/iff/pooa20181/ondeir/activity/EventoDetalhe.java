package br.edu.iff.pooa20181.ondeir.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.iff.pooa20181.ondeir.R;
import br.edu.iff.pooa20181.ondeir.model.Evento;
import io.realm.Realm;

public class EventoDetalhe extends AppCompatActivity {

    //evento detalhe: quando clicar no evento vai mostrar essa página ou quando clicar na bolinha de mais
    EditText etNome, etRua, etNumero, etBairro, etCidade, etData, etCapacidade,
            etLatitude, etLongitude;

    Button btsalvar,btalterar, btdeletar;

    int id;
    Evento evento;
    private Realm realm;

    //define formato de data
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhe);

        //pega todos os elementos e botoes com find by id
        etNome = (EditText)findViewById(R.id.etNome);
        etRua = (EditText)findViewById(R.id.etRua);
        etNumero = (EditText)findViewById(R.id.etnumero);
        etBairro =  (EditText)findViewById(R.id.etBairro);
        etCidade = (EditText)findViewById(R.id.etCidade);
        etData = (EditText)findViewById(R.id.etData);
        etCapacidade = (EditText)findViewById(R.id.etCapacidade);
        etLatitude = (EditText)findViewById(R.id.etLatitude);
        etLongitude = (EditText)findViewById(R.id.etLongitude);

        btsalvar = (Button) findViewById(R.id.bt_salvar_evento);
        btalterar = (Button) findViewById(R.id.bt_alterar_evento);
        btdeletar = (Button) findViewById(R.id.bt_deletar_evento);

        //pega a intent
        Intent intent    = getIntent();
        //pega o valor passado por parametro
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        //verifica se foi diferente 0, ou seja, ativa botao de atualiar e deletar, pq o objeto já existe
        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            //pega no banco o elemento com id passado
            evento = realm.where(Evento.class).equalTo("id",id).findFirst();

            //joga os valores de cada coluna do banco, nos componentes da tela eventodetalhe
            etNome.setText(evento.getNome());
            etRua.setText(evento.getRua());
            etNumero.setText(evento.getNumero());
            etBairro.setText(evento.getBairro());
            etCidade.setText(evento.getCidade());
            etCapacidade.setText(String.valueOf (evento.getCapacidade()));
            etData.setText(formato.format((Date) evento.getData()));
            etLongitude.setText(String.valueOf(evento.getLongitude()));
            etLatitude.setText(String.valueOf(evento.getLatitude()));

        }else{
            //se o id for zero, ou seja, tem que criar um obj novo
            //desativa os botoes de alterar e deletar
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }



        //qd clicar no botao salvar, chama o metodo salvar
        btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        //qd clicar no metodo alterar, chama o metodo alterar
        btalterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });


    }

    public void deletar(){
        //inicia transação
        realm.beginTransaction();
        //deleta o evento que foi pego no clique
        evento.deleteFromRealm();
        //comita a transação de deletar
        realm.commitTransaction();
        //fecha o real
        realm.close();

        Toast.makeText(this,"Evento deletado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        //verifica o valor máximo do campo id
        //se for diferente de null, é só somar mais 1
        //se for null, entao quer dizer que vai inserir o primeiro valor
        if(realm.where(Evento.class).max("id") !=null)
            proximoID = realm.where(Evento.class).max("id").intValue()+1;

        //inicia transação
        realm.beginTransaction();
        //cria um novo evento
        Evento evento = new Evento();
        //define o id do elemento
        evento.setId(proximoID);
        //chama o metodo que pega os valores da tela e bota no objeto
        setEGrava(evento);
        //salva o objeto
        realm.copyToRealm(evento);
        //commita a transação
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Evento Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Evento evento){

        //pega todos os valores que estão na tela, dentro do objeto evento
        evento.setNome(etNome.getText().toString());
        evento.setRua(etRua.getText().toString());
        evento.setNumero(etNumero.getText().toString());
        evento.setBairro(etBairro.getText().toString());
        evento.setCidade((etCidade.getText().toString()));
        evento.setCapacidade(Integer.parseInt(etCapacidade.getText().toString()));

        try {
            evento.setData((Date) formato.parse(etData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        evento.setLongitude((Float.parseFloat(etLongitude.getText().toString())));
        evento.setLatitude(Float.parseFloat(etLatitude.getText().toString()));


    }
    public void alterar() {

        realm.beginTransaction();

        setEGrava(evento);

        realm.copyToRealm(evento);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Evento Alterado",Toast.LENGTH_LONG).show();
        this.finish();

    }


}
