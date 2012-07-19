package com.delx.mybank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Contato extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contato);

		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 

		final EditText nome = (EditText) findViewById(R.id.nome);
		final EditText mensagem = (EditText) findViewById(R.id.mensagem);

		//Home
		Button voltar = (Button) findViewById(R.id.actionbar_home);
		voltar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();				
			}
		}); 


		Button enviar = (Button) findViewById(R.id.button_enviar);
		enviar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(!nome.getText().toString().equals("") && !mensagem.getText().toString().equals("")){
					email(nome.getText().toString(),mensagem.getText().toString());
				}
				else{
					Toast.makeText(getBaseContext(), "Please fill in all fields." ,Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	//Metodo E-mail
	public void email(String nome, String mensagem){
		String emailtext = new String();
		emailtext = "Name: "+ nome+ "\n Message: "+mensagem;
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"support@delxmobile.com"});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Contact from My Piggy Bank");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext);
		startActivity(Intent.createChooser(emailIntent, "Send mail...")); 
	}
}