package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistRespostaGrupoVO;


public class ChecklistRespostaGrupoDAO extends BasicDAO<ChecklistRespostaGrupoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDGRUPO="idgrupo";
	public static final String COL_DATAMOVIMENTO="dtmovimento";
	public static final String COL_SALVOU_SAIDA="icsalvousaida";
	public static final String COL_SALVOU_RETORNO="icsalvouretorno";
	public static final String TABLE_NAME="checklist_resposta_grupo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDGRUPO 		+ " INTEGER,"
			+ 	COL_DATAMOVIMENTO 	+ " TEXT,"
			+ 	COL_SALVOU_SAIDA 	+ " INTEGER,"
			+ 	COL_SALVOU_RETORNO 	+ " INTEGER"
			+ ");";
	
	public ChecklistRespostaGrupoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(ChecklistRespostaGrupoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistRespostaGrupoVO vo) 
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
	public List<ChecklistRespostaGrupoVO> listar()
	{
		List<ChecklistRespostaGrupoVO> data = new ArrayList<ChecklistRespostaGrupoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistRespostaGrupoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	public List<ChecklistRespostaGrupoVO> listarPorGrupo(int idgrupo) 
	{
		List<ChecklistRespostaGrupoVO> data = new ArrayList<ChecklistRespostaGrupoVO>();
		Cursor cursor = obterCursorPorGrupo(idgrupo);
		while (!cursor.isAfterLast())
		{
			ChecklistRespostaGrupoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistRespostaGrupoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public ChecklistRespostaGrupoVO obterPorIdGrupo(long idgrupo) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_IDGRUPO+ "=?", 
				new String[]{String.valueOf(idgrupo)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	@Override
	public ContentValues obterContentValues(ChecklistRespostaGrupoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDGRUPO, vo.idGrupo);
		values.put(COL_DATAMOVIMENTO, vo.dataMovimento);
		values.put(COL_SALVOU_SAIDA, vo.salvouSaida);
		values.put(COL_SALVOU_RETORNO, vo.salvouRetorno);
		
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
	
	public Cursor obterCursorPorGrupo(int idgrupo)
	{
		Cursor cursor = db.query(TABLE_NAME, null, COL_IDGRUPO+"=?", new String[]{String.valueOf(idgrupo)}, 
				null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}

	@Override
	public ChecklistRespostaGrupoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistRespostaGrupoVO vo = new ChecklistRespostaGrupoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idGrupo=cursor.getInt(cursor.getColumnIndex(COL_IDGRUPO));
		vo.dataMovimento=cursor.getString(cursor.getColumnIndex(COL_DATAMOVIMENTO));
		vo.salvouSaida=cursor.getInt(cursor.getColumnIndex(COL_SALVOU_SAIDA));
		vo.salvouRetorno=cursor.getInt(cursor.getColumnIndex(COL_SALVOU_RETORNO));
		
		return vo;
	}
}
