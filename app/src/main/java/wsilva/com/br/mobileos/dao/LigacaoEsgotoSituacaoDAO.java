package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.LigacaoEsgotoSituacaoVO;

public class LigacaoEsgotoSituacaoDAO extends BasicDAO<LigacaoEsgotoSituacaoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDSITUACAOLIGACAOESGOTO="idsituacaoesgoto";
	public static final String COL_DESCRICAOSITUACAOLIGACAOESGOTO="dssituacaoesgoto";
	public static final String TABLE_NAME="ligacaoesgotosituacao";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID 								+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDSITUACAOLIGACAOESGOTO 		+ " INTEGER,"
			+ 	COL_DESCRICAOSITUACAOLIGACAOESGOTO  + " TEXT"
			+  ");";
	
	public LigacaoEsgotoSituacaoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(LigacaoEsgotoSituacaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(LigacaoEsgotoSituacaoVO vo) 
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
	public List<LigacaoEsgotoSituacaoVO> listar() 
	{
		List<LigacaoEsgotoSituacaoVO> data = new ArrayList<LigacaoEsgotoSituacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			LigacaoEsgotoSituacaoVO os = obterObject(cursor);
			data.add(os);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public LigacaoEsgotoSituacaoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	
	@Override
	public ContentValues obterContentValues(LigacaoEsgotoSituacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDSITUACAOLIGACAOESGOTO, vo.idSituacaoLigacaoEsgoto);
		values.put(COL_DESCRICAOSITUACAOLIGACAOESGOTO, vo.descricaoSituacaoLigacaoEsgoto);
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
	public LigacaoEsgotoSituacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		LigacaoEsgotoSituacaoVO vo = new LigacaoEsgotoSituacaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idSituacaoLigacaoEsgoto = cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOLIGACAOESGOTO));
		vo.descricaoSituacaoLigacaoEsgoto = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOLIGACAOESGOTO));
		
		return vo;
	}
}
