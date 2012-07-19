package com.delx.mybank;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Leadbolt.AdController;
import com.airpush.android.Airpush;
import com.delx.database.RepositorioDica;
import com.delx.objects.Dica;
import com.delx.scripts.RepositorioDicaScript;
import com.delx.util.Conexao;


public class DashBoard extends Activity{
	final int SICRONIZAR = 2;
	final int RATE = 3;	
	Handler handler = new Handler();
	Conexao conectado;
	public RepositorioDica dica;
	private static final String NOME = "AngryBanks";
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Recupera o valor do contador, salvo nas preferencias
		SharedPreferences pref = getSharedPreferences(NOME, 0);
		boolean primeira = pref.getBoolean("primeira", false);
		if(!primeira){
			startActivity(new Intent(this, Bemvindo.class));
			finish();
		}
		setContentView(R.layout.dashboard);
		handler.post(new Runnable(){
			public void run() {
				new Airpush(getApplicationContext(),"60890","1337873112100724825",false,true,true);
				AdController myController = new AdController(getApplicationContext(),
						"537618275");
				myController.loadNotification();
			}});
		conectado = new Conexao(this);
		dica = new RepositorioDicaScript(this);

		//Titulo ActionBar
		TextView titulo = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo.setTypeface(font); 
		
		//Manage
		Button sabado = (Button) findViewById(R.id.home_btn_sonho);
		sabado.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(DashBoard.this,ListarSonhos.class));
			}
		}); 

		//Tips
		Button domingo = (Button) findViewById(R.id.home_btn_tips);
		domingo.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(DashBoard.this,ListarDicas.class));
			}
		});

		//Support
		Button palestrantes = (Button) findViewById(R.id.button_support);
		palestrantes.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(DashBoard.this, Contato.class));
			}
		}); 

		//Trofeus
		Button trofeus = (Button) findViewById(R.id.home_btn_trofeu);
		trofeus.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(DashBoard.this, Trofeus.class));
			}
		}); 

		//Rate
		Button rate = (Button) findViewById(R.id.button_rate);
		rate.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://market.android.com/details?id=com.delx.mybank");
				startActivity(new Intent(Intent.ACTION_VIEW, uri));
			}
		}); 


		//Tips
		Dica dica_objeto = new Dica();
		dica_objeto = dica.passarDica();
		TextView tip = (TextView) findViewById(R.id.tips);
		tip.setText(dica_objeto.dica);
		TextView tituloDica = (TextView) findViewById(R.id.TextView01);
		tituloDica.setText("Tip: " + dica_objeto.titulo);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Fecha o banco
		dica.fechar();
	}
}