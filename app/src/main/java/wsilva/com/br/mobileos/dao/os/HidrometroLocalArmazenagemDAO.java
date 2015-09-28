package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.HidrometroLocalArmazenagemVO;

public class HidrometroLocalArmazenagemDAO extends
		BasicDAO<HidrometroLocalArmazenagemVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDLOCALARMAZENAGEMHIDROMETRO="idlocalarmazenagemhm";
	public static final String COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO="dslocalarmazenagemhm";
	public static final String TABLE_NAME="hm_localarmazenagem";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDLOCALARMAZENAGEMHIDROMETRO 			+ " INTEGER,"
			+ COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO 	+ " TEXT"
			+ ");";
	
	public HidrometroLocalArmazenagemDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(HidrometroLocalArmazenagemVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(HidrometroLocalArmazenagemVO vo) 
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
	public List<HidrometroLocalArmazenagemVO> listar() 
	{
		List<HidrometroLocalArmazenagemVO> data = new ArrayList<HidrometroLocalArmazenagemVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroLocalArmazenagemVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public HidrometroLocalArmazenagemVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroLocalArmazenagemVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDLOCALARMAZENAGEMHIDROMETRO, vo.idLocalArmazenagemHidrometro);
		values.put(COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO, vo.descricaoLocalArmazenagemHidrometro);
		
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
	public HidrometroLocalArmazenagemVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroLocalArmazenagemVO vo = new HidrometroLocalArmazenagemVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idLocalArmazenagemHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDLOCALARMAZENAGEMHIDROMETRO));
		vo.descricaoLocalArmazenagemHidrometro = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO));
		
		return vo;
	}

	@Override
	public HidrometroLocalArmazenagemVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		HidrometroLocalArmazenagemVO vo=new HidrometroLocalArmazenagemVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.idLocalArmazenagemHidrometro = Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricaoLocalArmazenagemHidrometro = values[1];

		return vo;
	}
}
