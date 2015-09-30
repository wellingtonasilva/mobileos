package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistGrupoFinalizadoVO;

public class ChecklistGrupoFinalizadoDAO extends
		BasicDAO<ChecklistGrupoFinalizadoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDGRUPO="idgrupo";
	public static final String COL_IDCHECKLIST="idchecklist";
	public static final String COL_FINALIZOUSAIDA="icfinalizousaida";
	public static final String COL_FINALIZOURETORNO="icfinalizouretorno";
	public static final String TABLE_NAME="checklist_grupo_finalizado";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDGRUPO 		+ " INTEGER,"
			+ 	COL_IDCHECKLIST 	+ " INTEGER,"
			+ 	COL_FINALIZOUSAIDA 	+ " INTEGER,"
			+ 	COL_FINALIZOURETORNO+ " INTEGER"
			+ ");";
	
	
	public ChecklistGrupoFinalizadoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ChecklistGrupoFinalizadoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistGrupoFinalizadoVO vo) 
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
	public List<ChecklistGrupoFinalizadoVO> listar()
	{
		List<ChecklistGrupoFinalizadoVO> data = new ArrayList<ChecklistGrupoFinalizadoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistGrupoFinalizadoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistGrupoFinalizadoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ChecklistGrupoFinalizadoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDGRUPO, vo.idGrupo);
		values.put(COL_IDCHECKLIST, vo.idChecklist);
		values.put(COL_FINALIZOUSAIDA, vo.finalizouSaida);
		values.put(COL_FINALIZOURETORNO, vo.finalizouRetorno);
		
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
	public ChecklistGrupoFinalizadoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistGrupoFinalizadoVO vo = new ChecklistGrupoFinalizadoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idGrupo=cursor.getInt(cursor.getColumnIndex(COL_IDGRUPO));
		vo.idChecklist=cursor.getInt(cursor.getColumnIndex(COL_IDCHECKLIST));
		vo.finalizouSaida=cursor.getInt(cursor.getColumnIndex(COL_FINALIZOUSAIDA));
		vo.finalizouSaida=cursor.getInt(cursor.getColumnIndex(COL_FINALIZOURETORNO));
		
		return vo;
	}
}
