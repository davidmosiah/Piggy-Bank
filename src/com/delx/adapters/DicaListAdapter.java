package com.delx.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delx.mybank.R;
import com.delx.objects.Dica;

public class DicaListAdapter extends BaseAdapter {
	private Context context;
	private List<Dica> lista;

	public DicaListAdapter(Context context, List<Dica> lista) {
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	Dica c;
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o Dizimo da posição atual
		c = lista.get(position);


		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dica_linha_tabela, null);

		// Atualiza o valor do TextView
		TextView titulo = (TextView) view.findViewById(R.id.titulo);
		long aux = c.id;		
		titulo.setText(aux + ": " + c.titulo);

		TextView tip = (TextView) view.findViewById(R.id.tip);
		tip.setText(c.dica);
		
		ImageView favorito = (ImageView) view.findViewById(R.id.imagem);
		if(c.favorito.equals("s")){
			favorito.setImageResource(R.drawable.star);
		}

		return view;
	}
}