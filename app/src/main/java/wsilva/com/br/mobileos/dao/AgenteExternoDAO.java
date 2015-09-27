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
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.AgenteExternoVO;

public class AgenteExternoDAO extends BasicDAO<AgenteExternoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDAGENTEEXTERNO="idagenteexterno";
	public static final String COL_AGENTEEXTERNO="agenteexterno";
	public static final String TABLE_NAME="agenteexterno";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDAGENTEEXTERNO + " INTEGER,"
			+ 	COL_AGENTEEXTERNO  + " TEXT"
			+  ");";

	public AgenteExternoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(AgenteExternoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(AgenteExternoVO vo) 
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
	public List<AgenteExternoVO> listar() 
	{
		List<AgenteExternoVO> data = new ArrayList<AgenteExternoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			AgenteExternoVO os = obterObject(cursor);
			data.add(os);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public AgenteExternoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	
	@Override
	public ContentValues obterContentValues(AgenteExternoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDAGENTEEXTERNO, vo.idAgenteExterno);
		values.put(COL_AGENTEEXTERNO, vo.agenteExterno);
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
	public AgenteExternoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		AgenteExternoVO vo = new AgenteExternoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idAgenteExterno = cursor.getInt(cursor.getColumnIndex(COL_IDAGENTEEXTERNO));
		vo.agenteExterno = cursor.getString(cursor.getColumnIndex(COL_AGENTEEXTERNO));
		
		return vo;
	}
}
