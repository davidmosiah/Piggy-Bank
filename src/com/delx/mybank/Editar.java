package com.delx.mybank;

import com.delx.database.RepositorioSonho;
import com.delx.objects.Sonho;
import com.delx.objects.Sonho.Sonhos;
import com.delx.scripts.RepositorioSonhoScript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Editar extends Activity {
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
			R.drawable.notebook
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
			"Notebook"
	};

	public RepositorioSonho repositorio;
	Long id;
	Sonho c;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar);
		repositorio = new RepositorioSonhoScript(this);

		//Pega o atributo posicao da tela anterior
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getLong(Sonhos._ID);
		}
		c = repositorio.buscarSonho(id);

		ImageView sonhoimagem = (ImageView) findViewById(R.id.imagemsonho);
		sonhoimagem.setImageResource(imagem[c.posicao]);

		final TextView categoria1 = (TextView) findViewById(R.id.categoria);
		categoria1.setText(categoria[c.posicao]);

		final EditText total = (EditText) findViewById(R.id.total);
		total.setText("$"+c.total);

		final EditText poupanca = (EditText) findViewById(R.id.poupanca);
		poupanca.setText("$"+c.poupanca);

		final EditText mensal = (EditText) findViewById(R.id.mensal);
		mensal.setText("$"+c.mensal);

		//Voltar
		ImageView voltar = (ImageView) findViewById(R.id.voltar);
		voltar.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//Salvar
		ImageView salvar = (ImageView) findViewById(R.id.salvar	);
		salvar.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				Double total1;
				Double poupanca1;
				Double mensal1;
				Sonho sonho = new Sonho();
				String valortotal = total.getText().toString();
				String valorpoupanca = poupanca.getText().toString();
				String valormensal = mensal.getText().toString();
				if(valortotal.substring(0, 1).equals("$")){
					total1=  Double.parseDouble(valortotal.substring(1, total.length())); 
				}
				else{
					total1=  Double.parseDouble(valortotal);
				}
				
				if(valorpoupanca.substring(0, 1).equals("$")){
					poupanca1= Double.parseDouble(valorpoupanca.substring(1, total.length())); 
				}
				else{
					poupanca1 =  Double.parseDouble(valorpoupanca);
				}
				if(valormensal.substring(0, 1).equals("$")){
					mensal1= Double.parseDouble(valormensal.substring(1, total.length())); 
				}
				else{
					mensal1 =  Double.parseDouble(valormensal);
				}
					
				sonho.id = c.id;
				sonho.posicao = c.posicao;
				sonho.total = total1;
				sonho.poupanca = poupanca1;
				sonho.mensal = mensal1;
				salvarSonho(sonho);
			}

		});
		
		//Voltar Inicio
		ImageView logo = (ImageView) findViewById(R.id.logoinspiration);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Editar.this, ListarSonhos.class));
			}

		});
	}
		// Salvar o Sonho
	protected void salvarSonho(Sonho sonho) {
		repositorio.salvar(sonho);
		Toast.makeText(this, "Your wish was successfully edited!", Toast.LENGTH_LONG).show();
		Intent it = new Intent(Editar.this,Detalhes.class);
		it.putExtra(Sonhos._ID, c.id);
		startActivity(it);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
		finish();
	}

}