package br.edu.iff.pooa20181.ondeir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.ondeir.R;
import br.edu.iff.pooa20181.ondeir.model.Evento;

public class EventoAdapter extends RecyclerView.Adapter {

    private List<Evento> eventos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public EventoAdapter(List<Evento> eventos, Context context,ClickRecyclerViewListener clickRecyclerViewListener) {
        this.eventos = eventos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_evento, parent, false);
        EventoViewHolder eventoViewHolder = new EventoViewHolder(view);
        return eventoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        Log.i("--SIZE----XXXXXXXXX--", String.valueOf(this.eventos.size()));

        EventoViewHolder eventoHolder = (EventoViewHolder) viewHolder;

        Evento evento  = this.eventos.get(position) ;

        eventoHolder.nomeEvento.setText(evento.getNome());
        eventoHolder.dataEvento.setText("aaaa");
        eventoHolder.enderecoEvento.setText(evento.getEndereco());

        Log.i("------XXXXXXXXX--", evento.getNome());

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeEvento;
        private final TextView dataEvento;
        private final TextView enderecoEvento;


        public EventoViewHolder(View itemView) {
            super(itemView);
            nomeEvento = (TextView) itemView.findViewById(R.id.tvNomeEvento);
            dataEvento = (TextView) itemView.findViewById(R.id.tvDataEvento);
            enderecoEvento = (TextView) itemView.findViewById(R.id.tvEnderecoEvento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eventos.get(getLayoutPosition()));

                }
            });


        }
    }
}