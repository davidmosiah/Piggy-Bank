package com.delx.mybank;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.delx.adapters.DicaListAdapter;
import com.delx.database.RepositorioDica;
import com.delx.objects.Dica;
import com.delx.objects.Dica.Dicas;
import com.delx.scripts.RepositorioDicaScript;


public class ListarDicas extends Activity implements OnItemClickListener {
	public static RepositorioDica repositorio;
	private ListView listview;
	private List<Dica> dicas;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.listardicas);
		
		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 

		repositorio = new RepositorioDicaScript(this);
		atualizarLista();


		//Dashboard
		ImageView logo = (ImageView) findViewById(R.id.logo_principal);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ListarDicas.this, DashBoard.class));
			}
		});
	}
	protected void atualizarLista() {
		// Pega a lista de carros e exibe na tela
		dicas = repositorio.listarDicas();


		// Adaptador de lista customizado para cada linha de um carro
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new DicaListAdapter(this, dicas));
		listview.setOnItemClickListener(this);
	}
	public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
		editarDica(posicao);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Show favorite tips").setIcon(R.drawable.star);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Clicou no menu
		switch (item.getItemId()) {
		case 0:
			// Abre a tela com o formul�rio para adicionar
			startActivity(new Intent(this, Favoritos.class));
			break;
		}
		return true;
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