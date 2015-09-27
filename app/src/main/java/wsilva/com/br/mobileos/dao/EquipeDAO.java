package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.EquipeVO;

public class EquipeDAO extends BasicDAO<EquipeVO>
{
	
	public static final String COL_ID="_id";
	public static final String COL_IDEQUIPE="idequipe";
	public static final String COL_NOMEEQUIPE="nomeequipe";
	public static final String COL_NUMEROPLACAVEICULO="nnplacaveiculo";
	public static final String COL_CARGAHORARIOTRABALHODIA="cargahorariotrabalhodia";
	public static final String TABLE_NAME="equipe";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDEQUIPE + " INTEGER,"
			+ 	COL_NOMEEQUIPE + " INTEGER,"
			+ 	COL_NUMEROPLACAVEICULO + " TEXT,"
			+ 	COL_CARGAHORARIOTRABALHODIA + " INTEGER"
			+ ");";
	
	public EquipeDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(EquipeVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(EquipeVO vo) 
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
	public List<EquipeVO> listar()
	{
		List<EquipeVO> data = new ArrayList<EquipeVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			EquipeVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public EquipeVO obterPorId(long id) 
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
	public ContentValues obterContentValues(EquipeVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_CARGAHORARIOTRABALHODIA, vo.cargaHorarioTrabalhoDia);
		values.put(COL_IDEQUIPE, vo.idEquipe);
		values.put(COL_NOMEEQUIPE, vo.nomeEquipe);
		values.put(COL_NUMEROPLACAVEICULO, vo.numeroPlacaVeiculo);
		
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
	public EquipeVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		EquipeVO vo = new EquipeVO();
		vo.cargaHorarioTrabalhoDia = cursor.getInt(cursor.getColumnIndex(COL_CARGAHORARIOTRABALHODIA));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idEquipe = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPE));
		vo.nomeEquipe = cursor.getString(cursor.getColumnIndex(COL_NOMEEQUIPE));
		vo.numeroPlacaVeiculo = cursor.getString(cursor.getColumnIndex(COL_NUMEROPLACAVEICULO));
		
		return vo;
	}
}
