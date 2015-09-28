package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.RedeMaterialVO;

public class RedeMaterialDAO extends BasicDAO<RedeMaterialVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDREDEMATERIAL="idredematerial";
	public static final String COL_DESCRICAOREDEMATERIAL="dsredematerial";
	public static final String TABLE_NAME="redematerial";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDREDEMATERIAL + " INTEGER,"
			+ COL_DESCRICAOREDEMATERIAL + " TEXT"
			+ ");";

	public RedeMaterialDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(RedeMaterialVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(RedeMaterialVO vo) 
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
	public List<RedeMaterialVO> listar() 
	{
		List<RedeMaterialVO> data = new ArrayList<RedeMaterialVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			RedeMaterialVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public RedeMaterialVO obterPorId(long id) 
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
	public ContentValues obterContentValues(RedeMaterialVO vo) 
	{
		ContentValues values= new ContentValues();
		values.put(COL_DESCRICAOREDEMATERIAL, vo.descricaoRedeMaterial);
		values.put(COL_IDREDEMATERIAL, vo.idRedeMaterial);
		
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
	public RedeMaterialVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		RedeMaterialVO vo = new RedeMaterialVO();
		vo.descricaoRedeMaterial = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOREDEMATERIAL));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idRedeMaterial = cursor.getInt(cursor.getColumnIndex(COL_IDREDEMATERIAL));
		
		return vo;
	}

	@Override
	public RedeMaterialVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		RedeMaterialVO vo=new RedeMaterialVO();

		if (values[0].length() > 0)
		{
			vo.idRedeMaterial = Integer.parseInt(values[0]);
		}
		vo.descricaoRedeMaterial = values[1];

		return vo;
	}
}
