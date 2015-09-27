package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.LogradouroTipoVO;

public class LogradouroTipoDAO extends BasicDAO<LogradouroTipoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDLOGRADOURO="idlogradouro";
	public static final String COL_DESCRICAO="dslogradouro";
	public static final String TABLE_NAME="logradouro_tipo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDLOGRADOURO 	+ " INTEGER,"
			+ 	COL_DESCRICAO  		+ " TEXT"
			+  ");";

	public LogradouroTipoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(LogradouroTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(LogradouroTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo._id)}) > 0;
	}

	@Override
	public boolean remover(long id) 
	{
		return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)}) > 0;
	}
	
	@Override
	public boolean removerTodos() 
	{
		if (quantidadeRegistros() <=0) 
		{
			return true;
		} else 
		{
			return db.delete(TABLE_NAME, null, null) > 0;	
		}
	}

	@Override
	public List<LogradouroTipoVO> listar()
	{
		List<LogradouroTipoVO> data = new ArrayList<LogradouroTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			LogradouroTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public LogradouroTipoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	@Override
	public ContentValues obterContentValues(LogradouroTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDLOGRADOURO, vo.idLogradouro);
		values.put(COL_DESCRICAO, vo.descricao);
		
		return values;
	}

	@Override
	public Cursor obterCursor() 
	{
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}

	@Override
	public LogradouroTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		LogradouroTipoVO vo = new LogradouroTipoVO();
		vo._id =  cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idLogradouro= cursor.getInt(cursor.getColumnIndex(COL_IDLOGRADOURO));
		vo.descricao= cursor.getString(cursor.getColumnIndex(COL_DESCRICAO));
		
		return vo;
	}
}
