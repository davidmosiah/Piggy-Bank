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


public class ValorTotal extends Activity {
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

	String total;
	RepositorioDica dica;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total);
		
		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 
		
		dica = new RepositorioDicaScript(this);
		//Pega o atributo posicao da tela anterior
		final Bundle extras = getIntent().getExtras();
		final EditText valortotal = (EditText) findViewById(R.id.valortotal);

		//Tips
		Dica dica_objeto = new Dica();
		dica_objeto = dica.passarDica();
		TextView tip = (TextView) findViewById(R.id.tips);
		tip.setText(dica_objeto.dica);
		TextView titulo = (TextView) findViewById(R.id.TextView01);
		titulo.setText("Tip: " + dica_objeto.titulo);

		//Previous
		Button antes = (Button) findViewById(R.id.previous);

		antes.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ValorTotal.this, EscolherSonho.class));
			}

		});
		//Next
		Button depois = (Button) findViewById(R.id.next);
		depois.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				total = valortotal.getText().toString();
				if(total.equals("")){
					int aux=0;
					Toast.makeText(getBaseContext(), "Please fill in the fields.", aux).show();
				}
				else{
					try{
						Double.parseDouble(total);						
						Intent it = new Intent(ValorTotal.this, Poupanca.class);
						it.putExtra(Sonhos.POSICAO,extras.getString(Sonhos.POSICAO));
						it.putExtra(Sonhos.TOTAL, total);
						it.putExtra(Sonhos.FOTO,extras.getString(Sonhos.FOTO));
						startActivity(it);
					}
					catch(NumberFormatException e){
						Toast.makeText(getBaseContext(), "Please fill with a valid number.", Toast.LENGTH_SHORT).show();
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
