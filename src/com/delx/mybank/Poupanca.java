package com.delx.mybank;
import com.delx.database.RepositorioDica;
import com.delx.objects.Dica;
import com.delx.objects.Sonho.Sonhos;
import com.delx.scripts.RepositorioDicaScript;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Poupanca extends Activity {
	/** Called when the activity is first created. */

	private Integer[] imagem = {
			R.drawable.outro,
			R.drawable.mp3,
			R.drawable.celular,
			R.drawable.viagemnacional,
			R.drawable.viageminternacional,
			R.drawable.roupas,
			R.drawable.carro,
			R.drawable.maquina,
			R.drawable.videogame,
			R.drawable.tablet,
			R.drawable.tvnova,
			R.drawable.notebook,
			R.drawable.wedding,
			R.drawable.businesss,
			R.drawable.another
	};
	private CharSequence[] categoria = {
			"Piggy bank",
			"MP3",
			"Smartphone",
			"Nacional Travel",
			"Internacional Travel",
			"New Clothes",
			"Car",
			"Camera",
			"Videogame",
			"Tablet",
			"TV",
			"Notebook",
			"Wedding",
			"Business",
			"Another"
	};

	int posicao = 0;
	String poupanca;
	String mensal;
	int aux=0;
	RepositorioDica dica;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poupanca);

		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 
		
		dica = new RepositorioDicaScript(this);
		final ImageView logoampliada = (ImageView) findViewById(R.id.logo);
		final TextView categoria1 = (TextView) findViewById(R.id.cat);
		//Pega o atributo posicao da tela anterior
		final Bundle extras = getIntent().getExtras();


		try{
			//		posicao = Integer.parseInt(position);

		}
		catch(NumberFormatException e){

		}
		final EditText valorpoupanca = (EditText) findViewById(R.id.valorpoupanca);
		final EditText valormensal = (EditText) findViewById(R.id.valormensal);
		//		

		//Tips
		Dica dica_objeto = new Dica();
		dica_objeto = dica.passarDica();
		TextView tip = (TextView) findViewById(R.id.tips);
		tip.setText(dica_objeto.dica);
		TextView titulo = (TextView) findViewById(R.id.TextView01);
		titulo.setText("Tip: " + dica_objeto.titulo);

		//Previous
		Button antes = (Button) findViewById(R.id.previous);
		antes.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Poupanca.this, ValorTotal.class));
			}

		});
		//Next
		Button depois = (Button) findViewById(R.id.next);
		depois.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				poupanca = valorpoupanca.getText().toString();
				mensal = valormensal.getText().toString();
				if(mensal.equals("") || poupanca.equals("")){
					Toast.makeText(getBaseContext(), "Please fill in the fields.", aux).show();
				}
				else{
					try{
						double valorpoupanca = Double.parseDouble(poupanca);
						double valortotal = Double.parseDouble(extras.getString(Sonhos.TOTAL));
						if(valortotal>=valorpoupanca){				
							if(poupanca.equals("") || mensal.equals("")){
								Toast.makeText(getBaseContext(), "Please fill in the fields.", aux).show();
							}
							else{
								Intent it = new Intent(Poupanca.this, Resultado.class);
								it.putExtra(Sonhos.POSICAO, extras.getString(Sonhos.POSICAO));
								it.putExtra(Sonhos.TOTAL, extras.getString(Sonhos.TOTAL));
								it.putExtra(Sonhos.FOTO,extras.getString(Sonhos.FOTO));
								it.putExtra(Sonhos.POUPANCA, poupanca);
								it.putExtra(Sonhos.MENSAL, mensal);
								startActivity(it);	
							}

						}
						else{
							Toast.makeText(getBaseContext(), "You already have money to buy this wish!", aux).show();					
						}
					}
					catch(NumberFormatException e){
						Toast.makeText(getBaseContext(), "Please fill with a valid numbers.", aux).show();					

					}
				}
			}

		});


		//Voltar Inicio
		ImageView logo = (ImageView) findViewById(R.id.logo_principal);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				//finish();
			}

		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		dica.fechar();
	}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}

