package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.EquipeComponentesVO;

public class EquipeComponentesDAO extends BasicDAO<EquipeComponentesVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDEQUIPE="idequipe";
	public static final String COL_IDEQUIPECOMPONENTE="idequipecomponente";
	public static final String COL_INDICADORRESPONSAVEL="icresponsavel";
	public static final String COL_NOMECOMPONENTE="nomecomponente";
	public static final String COL_IDFUNCIONARIO="idfuncionario";
	public static final String TABLE_NAME="equipe_componentes";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDEQUIPE + " INTEGER,"
			+ 	COL_IDEQUIPECOMPONENTE + " INTEGER,"
			+ 	COL_INDICADORRESPONSAVEL + " INTEGER,"
			+ 	COL_NOMECOMPONENTE + " TEXT,"
			+	COL_IDFUNCIONARIO + " TEXT"
			+ 	");";
	
	public EquipeComponentesDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(EquipeComponentesVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(EquipeComponentesVO vo) 
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
	public List<EquipeComponentesVO> listar() 
	{
		List<EquipeComponentesVO> data = new ArrayList<EquipeComponentesVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			EquipeComponentesVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public EquipeComponentesVO obterPorId(long id) 
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
	public ContentValues obterContentValues(EquipeComponentesVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDEQUIPE, vo.idEquipe);
		values.put(COL_IDEQUIPECOMPONENTE, vo.idEquipeComponente);
		values.put(COL_IDFUNCIONARIO, vo.idFuncionario);
		values.put(COL_INDICADORRESPONSAVEL, vo.indicadorResponsavel);
		values.put(COL_NOMECOMPONENTE, vo.nomeComponente);
		
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
	public EquipeComponentesVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		EquipeComponentesVO vo=new EquipeComponentesVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idEquipe = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPE));
		vo.idEquipeComponente = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPECOMPONENTE));
		vo.idFuncionario = cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO));
		vo.indicadorResponsavel = cursor.getInt(cursor.getColumnIndex(COL_INDICADORRESPONSAVEL));
		vo.nomeComponente = cursor.getString(cursor.getColumnIndex(COL_NOMECOMPONENTE));
		
		return vo;
	}
}
