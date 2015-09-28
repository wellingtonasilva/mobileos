package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.CorteTipoVO;

public class CorteTipoDAO extends BasicDAO<CorteTipoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDCORTETIPO="idcortetipo";
	public static final String COL_DESCRICAOCORTETIPO="dscortetipo";
	public static final String TABLE_NAME="cortetipo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDCORTETIPO 			+ " INTEGER,"
			+ 	COL_DESCRICAOCORTETIPO 		+ " TEXT"
			+ ");";

	public CorteTipoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(CorteTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(CorteTipoVO vo) 
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
	public List<CorteTipoVO> listar()
	{
		List<CorteTipoVO> data = new ArrayList<CorteTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			CorteTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public CorteTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(CorteTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDCORTETIPO, vo.idCorteTipo);
		values.put(COL_DESCRICAOCORTETIPO, vo.descricaoCorteTipo);
		
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
	public CorteTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		CorteTipoVO vo = new CorteTipoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idCorteTipo = cursor.getInt(cursor.getColumnIndex(COL_IDCORTETIPO));
		vo.descricaoCorteTipo = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOCORTETIPO));
		
		return vo;
	}

	@Override
	public CorteTipoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		CorteTipoVO vo=new CorteTipoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.idCorteTipo = Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricaoCorteTipo = values[1];

		return vo;
	}
}
