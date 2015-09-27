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
import wsilva.com.br.mobileos.entity.ReligacaoTipoVO;

public class ReligacaoTipoDAO extends BasicDAO<ReligacaoTipoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDTIPORELIGACAO="idtiporeligacao";
	public static final String COL_DESCRICAOTIPORELIGACAO="dstiporeligacao";
	public static final String TABLE_NAME="religacao_tipo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDTIPORELIGACAO 			+ " INTEGER,"
			+ 	COL_DESCRICAOTIPORELIGACAO 		+ " TEXT"
			+ ");";

	public ReligacaoTipoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ReligacaoTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ReligacaoTipoVO vo) 
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
	public List<ReligacaoTipoVO> listar()
	{
		List<ReligacaoTipoVO> data = new ArrayList<ReligacaoTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ReligacaoTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ReligacaoTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ReligacaoTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDTIPORELIGACAO, vo.idTipoReligacao);
		values.put(COL_DESCRICAOTIPORELIGACAO, vo.descricaoTipoReligacao);
		
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
	public ReligacaoTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ReligacaoTipoVO vo = new ReligacaoTipoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idTipoReligacao = cursor.getInt(cursor.getColumnIndex(COL_IDTIPORELIGACAO));
		vo.descricaoTipoReligacao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPORELIGACAO));
		
		return vo;
	}
}
