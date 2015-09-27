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
import wsilva.com.br.mobileos.entity.MaterialVO;

public class MaterialDAO extends BasicDAO<MaterialVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDMATERIAL="idmaterial";
	public static final String COL_DESCRICAOMATERIAL="dsmaterial";
	public static final String COL_IDUNIDADEMEDIDA="idunidademedida";
	public static final String COL_DESCRICAOUNIDADEMEDIDA="dsunidademedida";
	public static final String TABLE_NAME="material";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDMATERIAL +  " INTEGER,"
			+ 	COL_DESCRICAOMATERIAL + " TEXT,"
			+ 	COL_IDUNIDADEMEDIDA + " INTEGER,"
			+ 	COL_DESCRICAOUNIDADEMEDIDA + " TEXT"
			+ ");";
	
	public MaterialDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(MaterialVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(MaterialVO vo) 
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
	public List<MaterialVO> listar() 
	{
		List<MaterialVO> data = new ArrayList<MaterialVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			MaterialVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public MaterialVO obterPorId(long id) 
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
	public ContentValues obterContentValues(MaterialVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DESCRICAOMATERIAL, vo.descricaoMaterial);
		values.put(COL_DESCRICAOUNIDADEMEDIDA, vo.descricaoUnidadeMedida);
		values.put(COL_IDMATERIAL, vo.idMaterial);
		values.put(COL_IDUNIDADEMEDIDA, vo.idUnidadeMedida);
		
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
	public MaterialVO obterObject(Cursor cursor) 
	{

		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		MaterialVO vo = new MaterialVO();
		vo.descricaoMaterial = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMATERIAL));
		vo.descricaoUnidadeMedida = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUNIDADEMEDIDA));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idMaterial = cursor.getInt(cursor.getColumnIndex(COL_IDMATERIAL));
		vo.idUnidadeMedida = cursor.getInt(cursor.getColumnIndex(COL_IDUNIDADEMEDIDA));
		
		return vo;
		
	}
}
