package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.GPSCoordenadasVO;

public class GPSCoordenadasDAO extends BasicDAO<GPSCoordenadasVO>
{
	public static final String COL_ID="_id";
	public static final String COL_DATA="dtcoordenada";
	public static final String COL_HORA="hrcoordenada";
	public static final String COL_LATITUDE="nnlatitude";
	public static final String COL_LONGITUDE="nnlongitude";
	public static final String COL_IDEQUIPE="idequipe";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String TABLE_NAME="gpscoordenadas";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_DATA 			+ " DATE,"
			+ 	COL_HORA 			+ " TEXT,"
			+ 	COL_LATITUDE 		+ " REAL,"
			+ 	COL_LONGITUDE 		+ " REAL,"
			+ 	COL_IDEQUIPE		+ " INTEGER,"
			+ 	COL_IDEQUIPEEXECUCAO 		+ " INTEGER,"
			+ 	COL_DESCRICAOEQUIPEEXECUCAO + " TEXT"
			+ 	");";

	public GPSCoordenadasDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(GPSCoordenadasVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public ContentValues obterContentValues(GPSCoordenadasVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DATA, Util.dateToString("yyyy-MM-dd", vo.data));
		values.put(COL_HORA, vo.hora);
		values.put(COL_LATITUDE, vo.latitude);
		values.put(COL_LONGITUDE, vo.longitude);
		values.put(COL_IDEQUIPE, vo.idEquipe);
		values.put(COL_IDEQUIPEEXECUCAO, vo.idEquipeExecucao);
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.descricaoEquipeExecucao);
		
		return values;
	}

	
	@Override
	public GPSCoordenadasVO obterPorId(long id) 
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
	public List<GPSCoordenadasVO> listar() 
	{
		List<GPSCoordenadasVO> data = new ArrayList<GPSCoordenadasVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			GPSCoordenadasVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	

	@Override
	public GPSCoordenadasVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		GPSCoordenadasVO vo = new GPSCoordenadasVO();
		vo.data = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATA)));
		vo.hora = cursor.getString(cursor.getColumnIndex(COL_HORA));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.latitude = cursor.getDouble(cursor.getColumnIndex(COL_LATITUDE));
		vo.latitude = cursor.getDouble(cursor.getColumnIndex(COL_LONGITUDE));
		vo.idEquipe = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPE));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao= cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		
		return vo;
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

	public long obterUltimoRegistro()
	{
		long ultimoRegistro=0;
		Cursor cursor= rawQuery("select max(_id) from " + TABLE_NAME, null);
		if (cursor!=null && cursor.getCount() > 0) {
			return cursor.getLong(0);
		}
		
		return ultimoRegistro;
	}
	
	public GPSCoordenadasVO obterUltimaCoordenadas(long id)
	{
		return obterPorId(id);
	}
}
