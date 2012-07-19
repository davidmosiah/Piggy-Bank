package com.delx.scripts;

import android.content.Context;

import com.delx.database.RepositorioSonho;
import com.delx.util.SQLiteHelper;

public class RepositorioSonhoScript extends RepositorioSonho {

	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS sonho";

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
	"create table sonho ( _id integer primary key autoincrement, posicao number not null, total number not null, poupanca number not null, mensal number not null, foto text);"
	};
	

	// Nome do banco
	private static final String NOME_BANCO = "livro_android";

	// Controle de vers�o
	private static final int VERSAO_BANCO = 2;

	// Nome da tabela
	public static final String TABELA_CARRO = "carro";

	// Classe utilit�ria para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;

	// Cria o banco de dados com um script SQL
	public RepositorioSonhoScript(Context ctx) {
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, RepositorioSonhoScript.NOME_BANCO, RepositorioSonhoScript.VERSAO_BANCO,
				RepositorioSonhoScript.SCRIPT_DATABASE_CREATE, RepositorioSonhoScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar tamb�m
		db = dbHelper.getWritableDatabase();
	}

	// Fecha o banco
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
