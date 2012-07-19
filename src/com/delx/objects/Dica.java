package com.delx.objects;

import android.provider.BaseColumns;


public class Dica {

	public static String[] colunas = new String[] { Dicas._ID, Dicas.TITULO, Dicas.DICA, Dicas.FAVORITO};

	/**
	 * Pacote do Content Provider. Precisa ser �nico.
	 */
	public long id;
	public String titulo;
	public String dica;
	public String favorito;
	
	
	public Dica() {
	}

	public Dica(String titulo ,String dica, String favorito) {
		super();
		this.titulo = titulo;
		this.dica = dica;
		this.favorito = favorito;
	
	}



	public static final class Dicas implements BaseColumns {
		
		public static final String TITULO = "titulo";
		public static final String DICA = "dica";
		public static final String FAVORITO = "favorito";

		// N�o pode instanciar esta Classe
		private Dicas() {
		}

	}

}
