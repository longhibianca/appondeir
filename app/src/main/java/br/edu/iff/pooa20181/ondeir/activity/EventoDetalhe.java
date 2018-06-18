package br.edu.iff.pooa20181.ondeir.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.iff.pooa20181.ondeir.R;

public class EventoDetalhe extends AppCompatActivity {

    EditText etNome, etRua, etNumero, etBairro, etCidade, etData, etCapacidade, etLatitude, etLongitude;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhe);

        etNome = (EditText)findViewById(R.id.etNome);
        etRua = (EditText)findViewById(R.id.etRua);
        etNumero = (EditText)findViewById(R.id.etnumero);
        etBairro =  (EditText)findViewById(R.id.etBairro);
        etCidade = (EditText)findViewById(R.id.etCidade);
        etData = (EditText)findViewById(R.id.etData);
        etCapacidade = (EditText)findViewById(R.id.etCapacidade);
        etLatitude = (EditText)findViewById(R.id.etLatitude);
        etLongitude = (EditText)findViewById(R.id.etLongitude);

        Intent intent    = getIntent();
        etNome.setText((String) intent.getSerializableExtra("nome"));
        etCapacidade.setText(String.valueOf ((Integer) intent.getSerializableExtra("capacidade")));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        etData.setText(formato.format((Date) intent.getSerializableExtra("data")));

    }


}
