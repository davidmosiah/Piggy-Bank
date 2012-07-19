package com.delx.objects;

import android.provider.BaseColumns;


public class Sonho {

	public static String[] colunas = new String[] { Sonhos._ID, Sonhos.POSICAO,
		Sonhos.TOTAL, Sonhos.POUPANCA, Sonhos.MENSAL, Sonhos.FOTO};

	public long id;
	public int posicao;
	public double total;
	public double poupanca;
	public double mensal;
	public String foto;

	public Sonho() {
	}

	public Sonho(int posicao, double total,
			double poupanca, double mensal, int meses, String foto) {
		super();
		this.posicao = posicao;
		this.total = total;
		this.poupanca = poupanca;
		this.mensal = mensal;
		this.foto = foto;
	}



	public static final class Sonhos implements BaseColumns {

		public static final String POSICAO = "posicao";

		public static final String TOTAL = "total";

		public static final String POUPANCA = "poupanca";

		public static final String MENSAL = "mensal";
		
		public static final String FOTO = "foto";

		// N�o pode instanciar esta Classe
		private Sonhos() {
		}

	}

}
