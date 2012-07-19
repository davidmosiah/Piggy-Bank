package com.delx.mybank;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.delx.database.RepositorioDica;
import com.delx.database.RepositorioSonho;
import com.delx.objects.Dica;
import com.delx.objects.Sonho;
import com.delx.objects.Sonho.Sonhos;
import com.delx.scripts.RepositorioDicaScript;
import com.delx.scripts.RepositorioSonhoScript;

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


public class Resultado extends Activity {
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
	int posicaotexto;
	Double valortotal;
	Double valorpoupanca;
	Double valormensal;
	public RepositorioSonho repositorio;
	public RepositorioDica dica;
	String foto;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultado);

		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 
				
		repositorio = new RepositorioSonhoScript(this);
		dica = new RepositorioDicaScript(this);
		//Pega o atributo posicao da tela anterior
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String posicaotexto1 = extras.getString(Sonhos.POSICAO);
			String total1 = extras.getString(Sonhos.TOTAL);
			String poupanca1 = extras.getString(Sonhos.POUPANCA);
			String mensal1 = extras.getString(Sonhos.MENSAL);
			foto = extras.getString(Sonhos.FOTO);
			try{

				posicaotexto = Integer.parseInt(posicaotexto1);
				valortotal = Double.parseDouble(total1);
				valorpoupanca = Double.parseDouble(poupanca1);
				valormensal = Double.parseDouble(mensal1);

			}
			catch(NumberFormatException e){

			}

			//Tips
			Dica dica_objeto = new Dica();
			dica_objeto = dica.passarDica();
			TextView tip = (TextView) findViewById(R.id.tips);
			tip.setText(dica_objeto.dica);
			TextView titulo = (TextView) findViewById(R.id.TextView01);
			titulo.setText("Tip: " + dica_objeto.titulo);

			final TextView resultado = (TextView) findViewById(R.id.resultado);
			resultado.setText(tempoTotal());
			final TextView data = (TextView) findViewById(R.id.data);
			data.setText(calcularData());
			final TextView categoria1 = (TextView) findViewById(R.id.categoria);
			categoria1.setText(categoria[posicaotexto]);
			final TextView total = (TextView) findViewById(R.id.total);
			total.setText("$"+total1);
			final TextView poupanca = (TextView) findViewById(R.id.poupanca);
			poupanca.setText("$"+poupanca1);
			final TextView mensal = (TextView) findViewById(R.id.mensal);
			mensal.setText("$"+mensal1);
			//		
			//Previous
			Button antes = (Button) findViewById(R.id.previous);

			antes.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					startActivity(new Intent(Resultado.this, Poupanca.class));
				}

			});
			//Salvar
			Button salvar = (Button) findViewById(R.id.salvar);
			salvar.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					Sonho sonho= new Sonho();
					sonho.posicao = posicaotexto;
					sonho.total= valortotal;
					sonho.poupanca= valorpoupanca;
					sonho.mensal= valormensal;
					sonho.foto = foto;

					// Salvar
					salvarSonho(sonho);
				}

			});
			//Share
			Button share = (Button) findViewById(R.id.share);
			share.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					String msg = "I already saved " + calcularPorcentagem() + "% of the money I need for "+ categoria[posicaotexto] + ".\n You can find out also the way to achieve what you want.\n https://market.android.com/details?id=com.delx.angrybanks";
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_TEXT, msg);
					startActivity(Intent.createChooser(intent, "Share with"));
				}

			});
			Log.e("Calculo",""+calcularPorcentagem());


			//Voltar Inicio
			ImageView logo = (ImageView) findViewById(R.id.logo_principal);
			logo.setOnClickListener(new ImageView.OnClickListener() {
				public void onClick(View v) {
					//finish();
				}

			});
		}
	}
	public int calcularResultado(){
		double aux = valortotal-valorpoupanca;
		aux = aux/valormensal;

		return (int)aux+1;
	}
	public String tempoTotal(){
		int ano = (int)calcularResultado()/12;
		int mes = calcularResultado()%12;
		String tempo;
		if(ano>0){
			if(mes>0){
				if(ano==1){
					if(mes==1){
						tempo = ano + " year and " + mes + " month";
					}
					else{
						tempo = ano + " year and " + mes + " months";
					}
				}
				else{
					if(mes==1){
						tempo = ano + " years and " + mes + " month";
					}
					else{
						tempo = ano + " years and " + mes + " months";
					}
				}
			}
			else{
				if(ano==1)
				{
					tempo = ano + " year";
				}
				else{
					tempo = ano + " years";					
				}
			}

		}
		else{
			if(mes==1){
				tempo = calcularResultado() + " month";
			}
			else{
				tempo = calcularResultado() + " months";
			}
		}
		return tempo;
	}
	public int calcularPorcentagem(){
		double aux = valorpoupanca/valortotal;
		aux = aux*100;
		return (int)aux;
	}

	// Salvar o Sonho
	protected void salvarSonho(Sonho sonho) {
		repositorio.salvar(sonho);
		Toast.makeText(this, "Your wish registered successfully", Toast.LENGTH_LONG).show();
		startActivity(new Intent(Resultado.this,ListarSonhos.class));
	}

	public String calcularData(){
		int ano = (int)calcularResultado()/12;
		int mes = calcularResultado()%12;
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("MM/yyyy");
		String dataformatar = formatador.format( data );
		String[] aux = dataformatar.split("/");
		int anofinal;
		int  mesfinal;
		int aux2 = mes + Integer.parseInt(aux[0]);
		String datafinal;
		if(aux2>11){
			ano++;
			anofinal = ano + Integer.parseInt(aux[1]);
			mesfinal = aux2 - 12;
			datafinal = mesfinal+"/"+anofinal;
		}
		else{
			mesfinal = aux2;
			anofinal= ano + Integer.parseInt(aux[1]);
		}
		String dataoficial = meses[mesfinal] + " " + anofinal;
		return dataoficial;

	}

	static String meses[] = {
		"January", "February", "March", "April", "May", "June", "July", "Agost", "September", "October", "November","December" };

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
		dica.fechar();
	}
	@Override
	protected void onPause() {
		super.onPause();
		
		// Fecha o banco
		repositorio.fechar();
		dica.fechar();
		finish();
	}

}