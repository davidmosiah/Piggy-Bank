package com.delx.mybank;

import java.util.List;

import com.delx.adapters.DicaListAdapter;
import com.delx.database.RepositorioDica;
import com.delx.objects.Dica;
import com.delx.objects.Dica.Dicas;
import com.delx.scripts.RepositorioDicaScript;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class Favoritos extends Activity implements OnItemClickListener {

	public static RepositorioDica repositorio;
	private ListView listview;
	private List<Dica> dicas;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.listarfavoritos);
		
		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 

		repositorio = new RepositorioDicaScript(this);
		atualizarLista();

		//Voltar
		ImageView voltar = (ImageView) findViewById(R.id.logo_principal);
		voltar.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Favoritos.this, ListarSonhos.class));
			}
		});
	}
	protected void atualizarLista() {
		dicas = repositorio.listarFavoritos();
		// Adaptador de lista customizado para cada linha de um carro
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new DicaListAdapter(this, dicas));
		listview.setOnItemClickListener(this);
	}
	public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
		Dica dica = (Dica) listview.getAdapter().getItem(posicao);
		editarDica(posicao);
	}

	// Recupera o id do carro, e abre a tela de edi��o
	protected void editarDica(int posicao) {
		Dica dica = dicas.get(posicao);
		Intent it = new Intent(this, DetalhesDicas.class);
		it.putExtra(Dicas._ID, dica.id);
		startActivity(it);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
	}
	@Override
	protected void onResume() {
		super.onResume();
		atualizarLista();
	}
}