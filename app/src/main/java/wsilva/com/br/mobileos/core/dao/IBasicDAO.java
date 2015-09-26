package wsilva.com.br.mobileos.core.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public interface IBasicDAO<T>
{

	/* Inserir registro na tabela */
	public long inserir(T vo);
	
	/* Atualizar registro na tabela */
	public boolean atualizar(T vo);
	
	/* Remover registro na tabela */
	public boolean remover(long id);
	
	/* Listar todos os registros */
	public List<T> listar();
	
	/* Obter por Id */
	public T obterPorId(long id);
	
	/* Obter por Id da tabela */
	public T obterPorIdTabela(int id);
	
	/* Converter para ContentValues */
	public ContentValues obterContentValues(T vo);
	
	/* Obter cursor */
	public Cursor obterCursor();
	
	/* Converter de Cursor para Object */
	public T obterObject(Cursor cursor);
	
	/* Obter object de uma linha */
	public T obterObject(String line);
	
	/* Obter object de JSONObject */
	public T obterObject(JSONObject line);
	
	/* Obter quantidade de registros */
	public int quantidadeRegistros();
	
	/* Obter cursor de uma Query */
	public Cursor rawQuery(String sql, String[] whereArgs);
	
	/* Excluir todos os registros */
	public boolean removerTodos();
	
	/* Verifica se a tabela existe na base de dados */
	public boolean existTabela(String tablename);
	
	/* Converte um VO em uma linha delimitada */
	public String obterLinhaCSV(T vo, String delimitador);
	
	public JSONObject obterJSONObject(T vo);
	
	public JSONArray obterJSONObject(List<T> list);
}
