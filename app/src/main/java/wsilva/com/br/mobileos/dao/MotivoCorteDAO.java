package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.MotivoCorteVO;

public class MotivoCorteDAO extends BasicDAO<MotivoCorteVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDMOTIVOCORTE="idmotivocorte";
	public static final String COL_DESCRICAOMOTIVOCORTE="dsmotivocorte";
	public static final String TABLE_NAME="motivocorte";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDMOTIVOCORTE + " INTEGER,"
			+ COL_DESCRICAOMOTIVOCORTE + " TEXT"
			+ ");";
	
	
	public MotivoCorteDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(MotivoCorteVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(MotivoCorteVO vo) 
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
	public List<MotivoCorteVO> listar() 
	{
		List<MotivoCorteVO> data = new ArrayList<MotivoCorteVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			MotivoCorteVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public MotivoCorteVO obterPorId(long id) 
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
	public ContentValues obterContentValues(MotivoCorteVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDMOTIVOCORTE, vo.idMotivoCorte);
		values.put(COL_DESCRICAOMOTIVOCORTE, vo.descricaoMotivoCorte);
		
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
	public MotivoCorteVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		MotivoCorteVO vo = new MotivoCorteVO();
		vo.descricaoMotivoCorte = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMOTIVOCORTE));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idMotivoCorte = cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOCORTE));
		
		return vo;
	}
}
