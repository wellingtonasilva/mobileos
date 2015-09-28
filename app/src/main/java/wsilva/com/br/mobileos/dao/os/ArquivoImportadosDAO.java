package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.ArquivoImportadosVO;

public class ArquivoImportadosDAO extends BasicDAO<ArquivoImportadosVO>
{

	public static final String COL_ID="_id";
	public static final String COL_NOMEARQUIVO="nmarquivo";
	public static final String COL_DATAIMPORTACAO="dtimportacao";
	public static final String TABLE_NAME="arquivoimportado";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_NOMEARQUIVO		+ " TEXT,"
			+ 	COL_DATAIMPORTACAO  + " DATE"
			+  ");";
	
	public ArquivoImportadosDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(ArquivoImportadosVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ArquivoImportadosVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo._id)}) > 0;
	}

	@Override
	public List<ArquivoImportadosVO> listar() 
	{
		List<ArquivoImportadosVO> data = new ArrayList<ArquivoImportadosVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ArquivoImportadosVO os = obterObject(cursor);
			data.add(os);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ArquivoImportadosVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	@Override
	public ContentValues obterContentValues(ArquivoImportadosVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_NOMEARQUIVO, vo.nomeArquivo);
		if (vo.dataImportacao!=null) {
			values.put(COL_DATAIMPORTACAO, vo.dataImportacao.getTime());
		}
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
	public ArquivoImportadosVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ArquivoImportadosVO vo = new ArquivoImportadosVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.nomeArquivo = cursor.getString(cursor.getColumnIndex(COL_NOMEARQUIVO));
		vo.dataImportacao = new Date(cursor.getLong(cursor.getColumnIndex(COL_DATAIMPORTACAO)));
		
		return vo;
	}
	
	public boolean existArquivoImportado(String filename) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_NOMEARQUIVO+ "=?", new String[]{filename}, null, null, null, null);
		if (cursor!=null && cursor.getCount() > 0)
		{
			return true;
		} else {
			return false;
		}
	}
}
