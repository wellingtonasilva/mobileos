package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.RedeTipoVO;

public class RedeTipoDAO extends BasicDAO<RedeTipoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDREDETIPO="idredetipo";
	public static final String COL_DESCRICAOREDETIPO="dsredetipo";
	public static final String TABLE_NAME="redetipo";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDREDETIPO + " INTEGER,"
			+ COL_DESCRICAOREDETIPO + " TEXT"
			+ ");";
	
	public RedeTipoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(RedeTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(RedeTipoVO vo) 
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
	public List<RedeTipoVO> listar() 
	{
		List<RedeTipoVO> data = new ArrayList<RedeTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			RedeTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public RedeTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(RedeTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DESCRICAOREDETIPO, vo.descricaoRedeTipo);
		values.put(COL_IDREDETIPO, vo.idRedeTipo);
		
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
	public RedeTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		RedeTipoVO vo=new RedeTipoVO();
		vo.descricaoRedeTipo = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOREDETIPO));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idRedeTipo = cursor.getInt(cursor.getColumnIndex(COL_IDREDETIPO));
		
		return vo;
	}

	@Override
	public RedeTipoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		RedeTipoVO vo= new RedeTipoVO();

		if (values[0].length() > 0) {
			vo.idRedeTipo = Integer.parseInt(values[0]);
		}
		vo.descricaoRedeTipo = values[1];

		return vo;
	}
}
