package wsilva.com.br.mobileos.core.dao;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import wsilva.com.br.mobileos.core.util.Conexao;

public class BasicDAO<T> implements IBasicDAO<T>
{

	protected static SQLiteDatabase db;
	protected static Context context;
	private static Conexao conexao;
	
	public BasicDAO(Context context)
	{
		BasicDAO.context = context;
		open();
	}
	
	public synchronized void open()
	{
		if (db==null || (db !=null && !db.isOpen()))
		{
			conexao=new Conexao(context);
			db=conexao.open();
		}
	}
	
	public synchronized void close()
	{
		if (db!=null && db.isOpen())
		{
			conexao.close();
			db.close();
		}
	}

	@Override
	public long inserir(T vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean atualizar(T vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T obterPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T obterPorIdTabela(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentValues obterContentValues(T vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor obterCursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T obterObject(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public T obterObject(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> lerDadosFromFile(String filename, String directoryname)
	{
		List<String> arrReturn=new ArrayList<String>();
		try 
		{
			File sdCard = Environment.getExternalStorageDirectory();
			File directory= new File(sdCard.getAbsolutePath() + directoryname);
			File file = new File(directory, filename);
			FileInputStream input= new FileInputStream(file);
			
			
			InputStreamReader reader=new InputStreamReader(input);
			BufferedReader br=new BufferedReader(reader);
			String line;
			
			while ((line = br.readLine()) != null)
			{
				arrReturn.add(line);
			}
			br.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return arrReturn;
	}

	@Override
	public int quantidadeRegistros() 
	{
		int result = 0;
		Cursor cursor = obterCursor();
		if (cursor!=null) {
			result = cursor.getCount();
			cursor.close();	
		}
		
		return result;
	}
	
	@Override
	public Cursor rawQuery(String sql, String[] whereArgs) 
	{
		Cursor cursor= db.rawQuery(sql, whereArgs);
		if (cursor!=null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	@Override
	public boolean removerTodos() 
	{
		return true;
	}

	@Override
	public boolean existTabela(String tablename) 
	{
		boolean bolReturn=false;
		Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tablename +"'", null);
		
	    if(cursor != null) 
	    {
	        if(cursor.getCount()>0) 
	        {
	        	cursor.close();
	        	bolReturn=true;
	        }
	        cursor.close();
	    }
	    return bolReturn;
	}
	
	public List<JSONObject> lerDadosFromFile(String url, Object postParameters)
	{
		List<JSONObject> lstReturn=new ArrayList<JSONObject>();

		return lstReturn;
	}

	@Override
	public T obterObject(JSONObject line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obterLinhaCSV(T vo, String delimitador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject obterJSONObject(T vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray obterJSONObject(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
