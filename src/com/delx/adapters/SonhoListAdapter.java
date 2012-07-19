package com.delx.adapters;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delx.mybank.R;
import com.delx.objects.Sonho;

public class SonhoListAdapter extends BaseAdapter {
	private Context context;
	private List<Sonho> lista;

	public SonhoListAdapter(Context context, List<Sonho> lista) {
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
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
	Sonho c;
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o Dizimo da posi��o atual
		c = lista.get(position);


		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.sonho_linha_tabela, null);

		ImageView sonhosimagem = (ImageView) view.findViewById(R.id.sonhoimagem);
		if(c.foto == null){
			sonhosimagem.setImageResource(imagem[c.posicao]);
		}
		else{
			FileInputStream in;
			BufferedInputStream buf;
			try {
				in = new FileInputStream(c.foto);
				buf = new BufferedInputStream(in);
				Bitmap bMap = BitmapFactory.decodeStream(buf);
				sonhosimagem.setImageBitmap(bMap);
				if (in != null) {
					in.close();
				}
				if (buf != null) {
					buf.close();
				}
			} catch (Exception e) {
				Log.e("Error reading file", e.toString());
				sonhosimagem.setImageResource(imagem[c.posicao]);
			}
		}
		// Atualiza o valor do TextView
		TextView data = (TextView) view.findViewById(R.id.sonho);
		data.setText(categoria[c.posicao]);

		TextView descricao = (TextView) view.findViewById(R.id.data);
		descricao.setText(calcularData());
		if(c.poupanca>=c.total){
			descricao.setText("OWNED");
		}

		TextView saved = (TextView) view.findViewById(R.id.salvo);
		saved.setText(calcularPorcentagem()+"%");

		return view;
	}
	public int calcularResultado(){
		double aux = c.total- c.poupanca;
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
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov","Dec" };

	public int calcularPorcentagem(){
		double aux = c.poupanca/c.total;
		aux = aux*100;
		int aux2 = (int)aux;
		if(aux2>100){
			aux2=100;
		}
		return aux2;
	}
}