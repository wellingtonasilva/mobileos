package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.LigacaoAguaSituacaoVO;

public class LigacaoAguaSituacaoDAO extends BasicDAO<LigacaoAguaSituacaoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDSITUACAOLIGACAOAGUA="idsituacaoagua";
	public static final String COL_DESCRICAOSITUACAOLIGACAOAGUA="dssituacaoagua";
	public static final String TABLE_NAME="ligacaoaguasituacao";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID 								+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDSITUACAOLIGACAOAGUA 			+ " INTEGER,"
			+ 	COL_DESCRICAOSITUACAOLIGACAOAGUA  	+ " TEXT"
			+  ");";
	
	public LigacaoAguaSituacaoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(LigacaoAguaSituacaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(LigacaoAguaSituacaoVO vo) 
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
	public List<LigacaoAguaSituacaoVO> listar() 
	{
		List<LigacaoAguaSituacaoVO> data = new ArrayList<LigacaoAguaSituacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			LigacaoAguaSituacaoVO os = obterObject(cursor);
			data.add(os);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public LigacaoAguaSituacaoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	
	@Override
	public ContentValues obterContentValues(LigacaoAguaSituacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDSITUACAOLIGACAOAGUA, vo.idSituacaoLigacaoAgua);
		values.put(COL_DESCRICAOSITUACAOLIGACAOAGUA, vo.descricaoSituacaoLigacaoAgua);
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
	public LigacaoAguaSituacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		LigacaoAguaSituacaoVO vo = new LigacaoAguaSituacaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idSituacaoLigacaoAgua = cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOLIGACAOAGUA));
		vo.descricaoSituacaoLigacaoAgua = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOLIGACAOAGUA));
		
		return vo;
	}

	@Override
	public LigacaoAguaSituacaoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		LigacaoAguaSituacaoVO vo=new LigacaoAguaSituacaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.idSituacaoLigacaoAgua = Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricaoSituacaoLigacaoAgua = values[1];

		return vo;
	}
}
