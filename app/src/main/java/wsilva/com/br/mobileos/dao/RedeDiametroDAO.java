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
import wsilva.com.br.mobileos.entity.RedeDiametroVO;

public class RedeDiametroDAO extends BasicDAO<RedeDiametroVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDREDEDIAMETRO="idredediamentro";
	public static final String COL_DESRICAOREDEDIAMETRO="dsredediametro";
	public static final String TABLE_NAME="redediametro";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDREDEDIAMETRO + " INTEGER,"
			+ COL_DESRICAOREDEDIAMETRO + " TEXT"
			+ ");";
	
	public RedeDiametroDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(RedeDiametroVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(RedeDiametroVO vo) 
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
	public List<RedeDiametroVO> listar() 
	{
		List<RedeDiametroVO> data = new ArrayList<RedeDiametroVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			RedeDiametroVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public RedeDiametroVO obterPorId(long id) 
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
	public ContentValues obterContentValues(RedeDiametroVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DESRICAOREDEDIAMETRO, vo.descricaoRedeDiametro);
		values.put(COL_IDREDEDIAMETRO, vo.idRedeDiametro);
		
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
	public RedeDiametroVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		RedeDiametroVO vo = new RedeDiametroVO();
		vo.descricaoRedeDiametro = cursor.getString(cursor.getColumnIndex(COL_DESRICAOREDEDIAMETRO));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idRedeDiametro = cursor.getInt(cursor.getColumnIndex(COL_IDREDEDIAMETRO));
		
		return vo;
	}
}
