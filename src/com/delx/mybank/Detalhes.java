package com.delx.mybank;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Detalhes extends Activity {
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
	RepositorioDica dica;
	Long id;
	Sonho c;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalhes);
		
		TextView titulo_principal = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo_principal.setTypeface(font); 
		
		repositorio = new RepositorioSonhoScript(this);
		dica = new RepositorioDicaScript(this);

		//Pega o atributo posicao da tela anterior
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getLong(Sonhos._ID);
		}
		c = repositorio.buscarSonho(id);

		ImageView sonhoimagem = (ImageView) findViewById(R.id.imagemsonho);
		if(c.foto == null){
			sonhoimagem.setImageResource(imagem[c.posicao]);
		}
		else{
			FileInputStream in;
			BufferedInputStream buf;
			try {
				in = new FileInputStream(c.foto);
				buf = new BufferedInputStream(in);
				Bitmap bMap = BitmapFactory.decodeStream(buf);
				sonhoimagem.setImageBitmap(bMap);
				if (in != null) {
					in.close();
				}
				if (buf != null) {
					buf.close();
				}
			} catch (Exception e) {
				Log.e("Error reading file", e.toString());
				sonhoimagem.setImageResource(imagem[c.posicao]);
			}
		}
		
		final TextView resultado = (TextView) findViewById(R.id.resultado);
		resultado.setText(calcularData());
		if(c.poupanca>=c.total){
			resultado.setText("OWNED");
		}

		//Tips
		Dica dica_objeto = new Dica();
		dica_objeto = dica.passarDica();
		TextView tip = (TextView) findViewById(R.id.tips);
		tip.setText(dica_objeto.dica);
		TextView titulo = (TextView) findViewById(R.id.TextView01);
		titulo.setText("Tip: " + dica_objeto.titulo);

		titulo_principal.setText("My " + categoria[c.posicao]);

		final TextView total = (TextView) findViewById(R.id.total);
		total.setText("$"+c.total);

		final TextView poupanca = (TextView) findViewById(R.id.poupanca);
		poupanca.setText("$"+c.poupanca);
		if(c.poupanca>=c.total){
			poupanca.setText("$"+c.total);
		}

		final TextView mensal = (TextView) findViewById(R.id.mensal);
		mensal.setText("$"+c.mensal);

		//Deletar
		Button deletar = (Button) findViewById(R.id.deletar);
		deletar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				/** AlertDialog com Sim e N�o **/
				AlertDialog.Builder alerta = new AlertDialog.Builder(Detalhes.this);
				alerta.setMessage("Do you really want to delete this wish?");
				// M�todo executado se escolher Sim
				alerta.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						repositorio.deletar(c.id);
						Toast.makeText(Detalhes.this, "Your wish was deleted successfully", Toast.LENGTH_LONG).show();
						finish();
						startActivity(new Intent(Detalhes.this,ListarSonhos.class));
					}
				});
				// M�todo executado se escolher N�o
				alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						}
				});
				// Exibe o alerta de confirma��o
				alerta.show();
			}
		});
		
		//Depositar
		Button depositar = (Button) findViewById(R.id.depositar);
		depositar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(c.poupanca>=c.total){
					Toast.makeText(Detalhes.this, "Congratulation! You already have money to buy your wish!", Toast.LENGTH_LONG).show();
				}
				else{
					Intent it = new Intent(Detalhes.this,Depositar.class);
					it.putExtra(Sonhos._ID, c.id);
					startActivity(it);
				}
			}


		});

		//Compartilhar
		Button share = (Button) findViewById(R.id.share);
		share.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String msg = "I already saved " + calcularPorcentagem() + "% of the money I need for "+ categoria[c.posicao] + ".\n You can find out also the way to achieve what you want.\n https://market.android.com/details?id=com.delx.angrybanks";
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, msg);
				startActivity(Intent.createChooser(intent, "Share with"));
			}

		});
		//Voltar Inicio
		ImageView logo = (ImageView) findViewById(R.id.logo_principal);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Detalhes.this,ListarSonhos.class));
			}

		});
	}
	public int calcularResultado(){
		double aux = c.total-c.poupanca;
		aux = aux/c.mensal;

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
		double aux = c.poupanca/c.total;
		aux = aux*100;
		int aux2 = (int)aux;
		if(aux2>100){
			aux2=100;
		}
		return aux2;
	}

	// Salvar o Sonho
	protected void salvarSonho(Sonho sonho) {
		repositorio.salvar(sonho);
		Toast.makeText(this, "Your wish registered successfully", Toast.LENGTH_LONG).show();
		startActivity(new Intent(Detalhes.this,ListarSonhos.class));
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

}