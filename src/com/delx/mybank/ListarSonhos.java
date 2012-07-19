package com.delx.mybank;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.delx.adapters.SonhoListAdapter;
import com.delx.database.RepositorioSonho;
import com.delx.objects.Sonho;
import com.delx.objects.Sonho.Sonhos;
import com.delx.scripts.RepositorioSonhoScript;


public class ListarSonhos extends Activity implements OnItemClickListener {

	public static RepositorioSonho repositorio;
	private ListView listview;
	private List<Sonho> sonhos;
	private static final String NOME = "AngryBanks";
	int visitas;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.listarsonhos);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Toast.makeText(getBaseContext(), "Can't connect with servidor" ,Toast.LENGTH_LONG).show();
		}	
		// Recupera o valor do contador, salvo nas preferencias
		SharedPreferences pref = getSharedPreferences(NOME, 0);
		visitas = pref.getInt("visitas", 0 );
		repositorio = new RepositorioSonhoScript(this);
		salvarVisitas();

		TextView titulo = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo.setTypeface(font); 

		atualizarLista();

		//Home
		Button voltar = (Button) findViewById(R.id.actionbar_home);
		voltar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();				
			}
		}); 


		//Novo sonho
		Button add = (Button) findViewById(R.id.novo);
		add.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ListarSonhos.this, EscolherSonho.class));
			}
		});
	}
	protected void atualizarLista() {
		// Pega a lista de carros e exibe na tela
		sonhos = repositorio.listarSonhos();
		// Adaptador de lista customizado para cada linha de um carro
		listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(new SonhoListAdapter(this, sonhos));
		listview.setOnItemClickListener(this);
		if(sonhos.size()==0 && visitas<5){
			Toast.makeText(this, "There is no wishes registered. Please add a new wish pressing the green plus button!", Toast.LENGTH_SHORT).show();
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

	public void salvarVisitas(){
		SharedPreferences pref = getSharedPreferences(NOME, 0);
		int visita = pref.getInt("visitas",0);
		visita++;
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt("visitas", visita);
		editor.commit();
	}
}