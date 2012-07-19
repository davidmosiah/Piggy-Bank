package com.delx.mybank;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delx.objects.Sonho.Sonhos;


public class EscolherSonho extends Activity {
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
			"Piggy Bank",
			"MP3",
			"Smartphone",
			"National Travel",
			"International Travel",
			"New Clothes",
			"Car",
			"Camera",
			"Video Game",
			"Tablet",
			"TV",
			"Notebook",
			"Wedding",
			"Business",
			"Another"
	};

	int posicao = 0;
	String foto = null;
	private static final int TIRAR_FOTO = 1020394857;
	private Uri imageUri;
	Bitmap bitmap;
	ImageView sonho_imagem;
	boolean batendoFoto = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.escolhersonhos);
		sonho_imagem = (ImageView) findViewById(R.id.logo);
		Toast.makeText(this,"You can take a picture of your wish touching in the image!", Toast.LENGTH_SHORT);
		final TextView categoria1 = (TextView) findViewById(R.id.cat);
		Button antes = (Button) findViewById(R.id.previous);
		Button depois = (Button) findViewById(R.id.next);

		TextView titulo = (TextView) findViewById(R.id.titulo);
		Typeface font = Typeface.createFromAsset(getAssets(), "font/Rumpelstiltskin.ttf");  
		titulo.setTypeface(font); 

		//Get Extras
		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			foto = extras.getString("FOTO");
			posicao = Integer.parseInt(extras.getString(Sonhos.POSICAO));
			FileInputStream in;
			BufferedInputStream buf;
			try {
				in = new FileInputStream(foto);
				buf = new BufferedInputStream(in);
				Bitmap bMap = BitmapFactory.decodeStream(buf);
				sonho_imagem.setImageBitmap(bMap);
				if (in != null) {
					in.close();
				}
				if (buf != null) {
					buf.close();
				}
			} catch (Exception e) {
				Log.e("Error reading file", e.toString());
			}
			categoria1.setText(categoria[posicao]);
			antes.setVisibility(View.INVISIBLE);
			depois.setVisibility(View.INVISIBLE);
		}
		else{
			sonho_imagem.setImageResource(imagem[posicao]);
			categoria1.setText(categoria[posicao]);
		}

		//Fotografar
		sonho_imagem.setOnClickListener(new ImageView.OnClickListener() {
			public void onClick(View v) {
				batendoFoto = true;
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, TIRAR_FOTO);
			}
		});

		//Previous
		antes.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(posicao==0){
					posicao = 15;
				}
				posicao-= 1;
				sonho_imagem.setImageResource(imagem[posicao]);
				sonho_imagem.setTag(posicao);
				categoria1.setText(categoria[posicao]);
			}

		});
		//Next
		depois.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(posicao == 14)
				{
					posicao = -1;
				}
				posicao+= 1;
				sonho_imagem.setImageResource(imagem[posicao]);
				categoria1.setText(categoria[posicao]);
				sonho_imagem.setTag(posicao);

			}
		});
		Object a = sonho_imagem.getTag();
		//Escolher
		Button escolher = (Button) findViewById(R.id.escolher);
		escolher.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(sonho_imagem.getTag()==null){
					Intent it = new Intent(EscolherSonho.this, ValorTotal.class);
					it.putExtra(Sonhos.POSICAO, "0");
					it.putExtra(Sonhos.FOTO,foto);
					startActivity(it);
				}
				else{
					int aux = (Integer) sonho_imagem.getTag();
					String aux1 = String.valueOf(aux);
					Intent it = new Intent(EscolherSonho.this, ValorTotal.class);
					it.putExtra(Sonhos.POSICAO, aux1);
					it.putExtra(Sonhos.FOTO,foto);
					startActivity(it);
				}
			}
		});

		//Home
		Button voltar = (Button) findViewById(R.id.actionbar_home);
		voltar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();				
			}
		}); 
	}
	@Override
	protected void onPause() {
		super.onPause();
		if(batendoFoto==false){
			finish();
		}

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TIRAR_FOTO) {
			if (resultCode == RESULT_OK) {
				bitmap = (Bitmap) data.getExtras().get("data");
				sonho_imagem.setImageBitmap(bitmap);
				salvarSD(bitmap);
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT);
			} else {
				Toast.makeText(this, "Exit", Toast.LENGTH_SHORT);
			}
		}
	}
	FileOutputStream outStream;
	public void salvarSD(Bitmap bitmap){
		foto = String.format("/sdcard/AngryBanks/%d.PNG",System.currentTimeMillis());
		File sdCard = Environment.getExternalStorageDirectory(); 
		File dir = new File (sdCard.getAbsolutePath() + "/AngryBanks"); 
		dir.mkdirs(); 
		OutputStream outStream = null;
		File file = new File(foto);
		try {
			outStream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			outStream.flush();
			outStream.close();
			batendoFoto = false;
		}
		catch(Exception e)
		{

		}
	}

}
