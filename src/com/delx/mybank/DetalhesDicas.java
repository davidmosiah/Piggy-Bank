package com.delx.mybank;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.delx.database.RepositorioDica;
import com.delx.objects.Dica;
import com.delx.objects.Dica.Dicas;
import com.delx.scripts.RepositorioDicaScript;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DetalhesDicas extends Activity {
	/** Called when the activity is first created. */

	public RepositorioDica repositorio;
	Long id;
	Dica c;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalhesdicas);
		repositorio = new RepositorioDicaScript(this);
		
		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 

		//Pega o atributo posicao da tela anterior
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getLong(Dicas._ID);
		}
		c = repositorio.buscarDica(id);

		// Atualiza o valor do TextView
		titulo_principal.setText(c.titulo);

		TextView tip = (TextView) findViewById(R.id.tip);
		tip.setText(c.dica);

		//Voltar
		ImageView voltar = (ImageView) findViewById(R.id.previous);
		voltar.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//Get
		ImageView logo = (ImageView) findViewById(R.id.logo_principal);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				finish();
			}

		});

		//Favorito
		ImageView favorito = (ImageView) findViewById(R.id.favorito);
		if(c.favorito.equals("s")){
			favorito.setImageResource(R.drawable.naofavorito);
		}
		favorito.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				Dica dica = new Dica();
				dica.id = c.id;
				dica.titulo = c.titulo;
				dica.dica = c.dica;
				if(c.favorito.equals("n")){
					dica.favorito = "s";
				}
				else{
					dica.favorito = "n";
				}

				salvar(dica);
			}
		});
	}

	// por como favorito
	protected void salvar(Dica dica) {
		repositorio.salvar(dica);
		if(c.favorito.equals("n")){
			Toast.makeText(this, "This tip was saved as favorite!", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(this, "This tip isn't favorite anymore!", Toast.LENGTH_LONG).show();
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
	}

}