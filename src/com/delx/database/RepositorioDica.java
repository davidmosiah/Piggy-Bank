package com.delx.database;

import java.util.ArrayList;
import java.util.List;

import com.delx.objects.Dica;
import com.delx.objects.Dica.Dicas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class RepositorioDica {
	private static final String CATEGORIA = "dica";

	// Nome do banco
	private static final String NOME_BANCO = "delx_dica";
	// Nome da tabela
	public static final String NOME_TABELA = "dica";

	protected SQLiteDatabase db;

	//Abre conex�o com o BD
	public RepositorioDica(Context ctx) {
		// Abre o banco de dados j� existente
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	protected RepositorioDica() {
		// Apenas para criar uma subclasse...
	}


	public long salvar(Dica dica) {
		long id = dica.id;

		if (id != 0) {
			atualizar(dica);
		} 
		return id;
	}

	// Atualiza 
	public int atualizar(Dica dica) {
		ContentValues values = new ContentValues();
		values.put(Dicas.TITULO, dica.titulo);
		values.put(Dicas.DICA, dica.dica);
		values.put(Dicas.FAVORITO, dica.favorito);
	
		String _id = String.valueOf(dica.id);

		String where = Dicas._ID + "=?";
		String[] whereArgs = new String[] { _id };

		int count = atualizar(values, where, whereArgs);

		return count;
	}

	// A cl�usula where � utilizada para identificar o dizimo a ser atualizado
	public int atualizar(ContentValues valores, String where, String[] whereArgs) {
		int count = db.update(NOME_TABELA, valores, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
		return count;
	}



	// Busca a dica pelo id
	public Dica buscarDica(long id) {

		Cursor c = db.query(true, NOME_TABELA, Dica.colunas, Dicas._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {

			// Posicinoa no primeiro elemento do cursor
			c.moveToFirst();

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Dicas._ID);
			int idxTitulo = c.getColumnIndex(Dicas.TITULO);
			int idxDica = c.getColumnIndex(Dicas.DICA);
			int idxFavorito = c.getColumnIndex(Dicas.FAVORITO);

			Dica dica = new Dica();


			// recupera os atributos do sonho
			dica.id = c.getLong(idxId);
			dica.titulo = c.getString(idxTitulo);
			dica.dica = c.getString(idxDica);
			dica.favorito = c.getString(idxFavorito);

			return dica;
		}

		return null;
	}

	public Dica passarDica(){
		long id = (long)(Math.random()*101);

		Dica dica = new Dica();
		dica = buscarDica(id);
		return dica;
	}
	// Retorna um cursor com todos os carros
	public Cursor getCursor() {
		try {
			return db.query(NOME_TABELA, Dica.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os sonhos: " + e.toString());
			return null;
		}
	}

	// Retorna uma lista com todos as dicas
	public List<Dica> listarDicas() {
		Cursor c = getCursor();
		List<Dica> dicas = new ArrayList<Dica>();
		if (c.moveToFirst()) {

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Dicas._ID);
			int idxTitulo = c.getColumnIndex(Dicas.TITULO);
			int idxDica = c.getColumnIndex(Dicas.DICA);
			int idxFavorito = c.getColumnIndex(Dicas.FAVORITO);

			// Loop at� o final
			do {
				Dica dica = new Dica();
				dicas.add(dica);

				// recupera os atributos do sonho
				dica.id = c.getLong(idxId);
				dica.titulo = c.getString(idxTitulo);
				dica.dica = c.getString(idxDica);
				dica.favorito = c.getString(idxFavorito);

			} while (c.moveToNext());
		}

		return dicas;
	}
	
	// Retorna um cursor com todos os carros
		public Cursor getFavoritos() {
			try {
				return db.query(NOME_TABELA, Dica.colunas, Dicas.FAVORITO + "= 's'", null, null, null, null, null);
			} catch (SQLException e) {
				Log.e(CATEGORIA, "Erro ao buscar os sonhos: " + e.toString());
				return null;
			}
		}

		// Retorna uma lista com todos as dicas
		public List<Dica> listarFavoritos() {
			Cursor c = getFavoritos();
			List<Dica> dicas = new ArrayList<Dica>();
			if (c.moveToFirst()) {

				// Recupera os �ndices das colunas
				int idxId = c.getColumnIndex(Dicas._ID);
				int idxTitulo = c.getColumnIndex(Dicas.TITULO);
				int idxDica = c.getColumnIndex(Dicas.DICA);
				int idxFavorito = c.getColumnIndex(Dicas.FAVORITO);

				// Loop at� o final
				do {
					Dica dica = new Dica();
					dicas.add(dica);

					// recupera os atributos do sonho
					dica.id = c.getLong(idxId);
					dica.titulo = c.getString(idxTitulo);
					dica.dica = c.getString(idxDica);
					dica.favorito = c.getString(idxFavorito);

				} while (c.moveToNext());
			}

			return dicas;
		}


	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}
