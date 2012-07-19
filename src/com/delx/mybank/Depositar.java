package com.delx.mybank;

import com.delx.database.RepositorioSonho;
import com.delx.objects.Sonho;
import com.delx.objects.Sonho.Sonhos;
import com.delx.scripts.RepositorioSonhoScript;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Depositar extends Activity {
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

	public RepositorioSonho repositorio;
	Long id;
	Sonho c;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.depositar);

		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 

		repositorio = new RepositorioSonhoScript(this);

		//Pega o atributo posicao da tela anterior
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getLong(Sonhos._ID);
		}
		c = repositorio.buscarSonho(id);

		ImageView sonhoimagem = (ImageView) findViewById(R.id.imagemsonho);
		sonhoimagem.setImageResource(imagem[c.posicao]);

		final EditText mensal = (EditText) findViewById(R.id.guardar);
		mensal.setText(String.valueOf(c.mensal));

		//Depositar
		Button salvar = (Button) findViewById(R.id.depositar);
		salvar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Sonho sonho = new Sonho();
				String total = mensal.getText().toString();
				if(total.equals("")){
					int aux=0;
					Toast.makeText(getBaseContext(), "Please fill in the fields.", aux).show();
				}
				try{
					sonho.id = c.id;
					sonho.posicao = c.posicao;
					sonho.total = c.total;
					sonho.poupanca = c.poupanca + Double.parseDouble(total);
					sonho.mensal = c.mensal;
					salvarSonho(sonho);
				}
				catch(NumberFormatException e){
					int aux = 0;
					Toast.makeText(getBaseContext(), "Please fill with a valid number.", aux).show();
				}
			}

		});

		//Voltar Inicio
		ImageView logo = (ImageView) findViewById(R.id.logo_principal);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Depositar.this, ListarSonhos.class));
			}

		});
	}
	// Salvar o Sonho
	protected void salvarSonho(Sonho sonho) {
		repositorio.salvar(sonho);
		Toast.makeText(this, "Your funds are deposited with success!!", Toast.LENGTH_LONG).show();
		Intent it = new Intent(Depositar.this,Detalhes.class);
		it.putExtra(Sonhos._ID, c.id);
		startActivity(it);
	}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
		finish();
	}

}