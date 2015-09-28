package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.HidrometroProtecaoVO;

public class HidrometroProtecaoDAO extends BasicDAO<HidrometroProtecaoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDPROTECAOHIDROMETRO="idprotecaohm";
	public static final String COL_DESCRICAOPROTECAOHIDROMETRO="dsprotecaohm";	
	public static final String TABLE_NAME="hm_protecao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDPROTECAOHIDROMETRO 			+ " INTEGER,"
			+ COL_DESCRICAOPROTECAOHIDROMETRO 	+ " TEXT"
			+ ");";
	
	
	public HidrometroProtecaoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(HidrometroProtecaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(HidrometroProtecaoVO vo) 
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
	public List<HidrometroProtecaoVO> listar() 
	{
		List<HidrometroProtecaoVO> data = new ArrayList<HidrometroProtecaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroProtecaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public HidrometroProtecaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroProtecaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDPROTECAOHIDROMETRO, vo.idProtecaoHidrometro);
		values.put(COL_DESCRICAOPROTECAOHIDROMETRO, vo.descricaoProtecaoHidrometro);
		
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
	public HidrometroProtecaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroProtecaoVO vo = new HidrometroProtecaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idProtecaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO));
		vo.descricaoProtecaoHidrometro = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOPROTECAOHIDROMETRO));
		
		return vo;
	}

	@Override
	public HidrometroProtecaoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		HidrometroProtecaoVO vo=new HidrometroProtecaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.idProtecaoHidrometro = Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricaoProtecaoHidrometro = values[1];

		return vo;
	}
}
