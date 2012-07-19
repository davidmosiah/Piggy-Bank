package com.delx.mybank;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.delx.adapters.SonhoListAdapter;
import com.delx.database.RepositorioSonho;
import com.delx.objects.Sonho;
import com.delx.objects.Sonho.Sonhos;
import com.delx.scripts.RepositorioSonhoScript;


public class Trofeus extends Activity implements OnItemClickListener {
	protected static final int INSERIR_EDITAR = 1;
	protected static final int CONTATO = 2;

	public static RepositorioSonho repositorio;
	private ListView listview;
	private List<Sonho> sonhos;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.trofeus);

		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 
		
		repositorio = new RepositorioSonhoScript(this);
		atualizarLista();
		//Dashboard
		ImageView logo = (ImageView) findViewById(R.id.logo_principal);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Trofeus.this, DashBoard.class));
			}
		});

	}
	protected void atualizarLista() {
		// Pega a lista de carros e exibe na tela
		sonhos = repositorio.listarConquistas();
		// Adaptador de lista customizado para cada linha de um carro
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new SonhoListAdapter(this, sonhos));
		listview.setOnItemClickListener(this);
		if(sonhos.size()==0){
			int aux=0;
			Toast.makeText(this, "There is no dreams owned.", aux).show();
		}
	}
	public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {		
		editarSonho(posicao);
	}
	// Recupera o id do carro, e abre a tela de edi��o
	protected void editarSonho(int posicao) {
		Sonho sonho = sonhos.get(posicao);
		Intent it = new Intent(this, Detalhes.class);
		it.putExtra(Sonhos._ID, sonho.id);
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