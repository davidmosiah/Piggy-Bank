package com.delx.database;

import java.util.ArrayList;
import java.util.List;

import com.delx.objects.Sonho;
import com.delx.objects.Sonho.Sonhos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;


public class RepositorioSonho {
	private static final String CATEGORIA = "sonho";

	// Nome do banco
	private static final String NOME_BANCO = "delx_sonho";
	// Nome da tabela
	public static final String NOME_TABELA = "sonho";

	protected SQLiteDatabase db;

	//Abre conex�o com o BD
	public RepositorioSonho(Context ctx) {
		// Abre o banco de dados j� existente
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	protected RepositorioSonho() {
		// Apenas para criar uma subclasse...
	}

	// Salva o carro, insere um novo ou atualiza
	public long salvar(Sonho sonho) {
		long id = sonho.id;

		if (id != 0) {
			atualizar(sonho);
		} else {
			// Insere novo
			id = inserir(sonho);
		}

		return id;
	}

	// Insere um novo sonho
	public long inserir(Sonho sonho) {
		ContentValues values = new ContentValues();
		values.put(Sonhos.POSICAO, sonho.posicao);
		values.put(Sonhos.TOTAL, sonho.total);
		values.put(Sonhos.POUPANCA, sonho.poupanca);
		values.put(Sonhos.MENSAL, sonho.mensal);
		values.put(Sonhos.FOTO, sonho.foto);

		long id = inserir(values);
		return id;
	}

	// Insere um novo dizimo
	public long inserir(ContentValues valores) {

		long id = db.insert(NOME_TABELA, "", valores);
		return id;
	}

	// Atualiza 
	public int atualizar(Sonho sonho) {
		ContentValues values = new ContentValues();
		values.put(Sonhos.POSICAO, sonho.posicao);
		values.put(Sonhos.TOTAL, sonho.total);
		values.put(Sonhos.POUPANCA, sonho.poupanca);
		values.put(Sonhos.MENSAL, sonho.mensal);

		String _id = String.valueOf(sonho.id);

		String where = Sonhos._ID + "=?";
		String[] whereArgs = new String[] { _id };

		int count = atualizar(values, where, whereArgs);

		return count;
	}

	// Atualiza o dizimo com os valores abaixo
	// A cl�usula where � utilizada para identificar o dizimo a ser atualizado
	public int atualizar(ContentValues valores, String where, String[] whereArgs) {
		int count = db.update(NOME_TABELA, valores, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
		return count;
	}

	// Deleta o dizimo com o id fornecido
	public int deletar(long id) {
		String where = Sonhos._ID + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = deletar(where, whereArgs);

		return count;
	}

	// Deleta o dizimo com os argumentos fornecidos
	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		Log.i(CATEGORIA, "Deletou [" + count + "] registros");
		return count;
	}

	// Busca o dizimo pelo id
	public Sonho buscarSonho(long id) {

		Cursor c = db.query(true, NOME_TABELA, Sonho.colunas, Sonhos._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {



			// Posicinoa no primeiro elemento do cursor
			c.moveToFirst();

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Sonhos._ID);
			int idxPosicao = c.getColumnIndex(Sonhos.POSICAO);
			int idxTotal = c.getColumnIndex(Sonhos.TOTAL);
			int idxPoupanca = c.getColumnIndex(Sonhos.POUPANCA);
			int idxMensal = c.getColumnIndex(Sonhos.MENSAL);
			int idxFoto = c.getColumnIndex(Sonhos.FOTO);
			
			Sonho sonho = new Sonho();


			// recupera os atributos do sonho
			sonho.id = c.getLong(idxId);
			sonho.posicao = c.getInt(idxPosicao);
			sonho.total = c.getDouble(idxTotal);
			sonho.poupanca = c.getDouble(idxPoupanca);
			sonho.mensal = c.getDouble(idxMensal);
			sonho.foto = c.getString(idxFoto);

			return sonho;
		}

		return null;
	}

	// Retorna um cursor com todos os carros
	public Cursor getCursor() {
		try {
			// select * from carros
			return db.query(NOME_TABELA, Sonho.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			return null;
		}
	}

	// Retorna uma lista com todos os sonhos
	public List<Sonho> listarSonhos() {
		Cursor c = getCursor();
		List<Sonho> sonhos = new ArrayList<Sonho>();
		if (c.moveToFirst()) {

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Sonhos._ID);
			int idxPosicao = c.getColumnIndex(Sonhos.POSICAO);
			int idxTotal = c.getColumnIndex(Sonhos.TOTAL);
			int idxPoupanca = c.getColumnIndex(Sonhos.POUPANCA);
			int idxMensal = c.getColumnIndex(Sonhos.MENSAL);
			int idxFoto = c.getColumnIndex(Sonhos.FOTO);



			// Loop at� o final
			do {
				Sonho sonho = new Sonho();
				// recupera os atributos do sonho
				sonho.id = c.getLong(idxId);
				sonho.posicao = c.getInt(idxPosicao);
				sonho.total = c.getDouble(idxTotal);
				sonho.poupanca = c.getDouble(idxPoupanca);
				sonho.mensal = c.getDouble(idxMensal);
				sonho.foto = c.getString(idxFoto);

				if(sonho.poupanca<sonho.total){
					sonhos.add(sonho);	
				}
				
			} while (c.moveToNext());
		}

		return sonhos;
	}
	
	// Retorna uma lista com sonhos conquistadors
	public List<Sonho> listarConquistas() {
		Cursor c = getCursor();

		List<Sonho> sonhos = new ArrayList<Sonho>();

		if (c.moveToFirst()) {

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Sonhos._ID);
			int idxPosicao = c.getColumnIndex(Sonhos.POSICAO);
			int idxTotal = c.getColumnIndex(Sonhos.TOTAL);
			int idxPoupanca = c.getColumnIndex(Sonhos.POUPANCA);
			int idxMensal = c.getColumnIndex(Sonhos.MENSAL);



			// Loop at� o final
			do {
				Sonho sonho = new Sonho();
				// recupera os atributos do sonho
				sonho.id = c.getLong(idxId);
				sonho.posicao = c.getInt(idxPosicao);
				sonho.total = c.getDouble(idxTotal);
				sonho.poupanca = c.getDouble(idxPoupanca);
				sonho.mensal = c.getDouble(idxMensal);
				
				if(sonho.poupanca>=sonho.total){
					sonhos.add(sonho);	
				}

			} while (c.moveToNext());
		}

		return sonhos;
	}
	
	public int contarSonhos(){
		String sql_contar = "SELECT COUNT(*) FROM SONHO";
		Cursor c = db.rawQuery(sql_contar, null);
		c.moveToFirst();
		return c.getInt(0);
	}


	// Busca um carro utilizando as configura��es definidas no
	// SQLiteQueryBuilder
	// Utilizado pelo Content Provider de carro
	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		Cursor c = queryBuilder.query(this.db, projection, selection, selectionArgs, groupBy, having, orderBy);
		return c;
	}

	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}
