package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistGrupoVO;

public class ChecklistGrupoDAO extends BasicDAO<ChecklistGrupoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDGRUPO="idgrupo";
	public static final String COL_DESCRICAOGRUPO="dsgrupo";
	public static final String TABLE_NAME="checklist_grupo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDGRUPO 		+ " INTEGER,"
			+ 	COL_DESCRICAOGRUPO 	+ " TEXT"
			+ ");";
	
	public ChecklistGrupoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ChecklistGrupoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistGrupoVO vo) 
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
	public List<ChecklistGrupoVO> listar()
	{
		List<ChecklistGrupoVO> data = new ArrayList<ChecklistGrupoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistGrupoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistGrupoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ChecklistGrupoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDGRUPO, vo.idGrupo);
		values.put(COL_DESCRICAOGRUPO, vo.descricaoGrupo);
		
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
	public ChecklistGrupoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistGrupoVO vo = new ChecklistGrupoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idGrupo=cursor.getInt(cursor.getColumnIndex(COL_IDGRUPO));
		vo.descricaoGrupo=cursor.getString(cursor.getColumnIndex(COL_DESCRICAOGRUPO));
		
		return vo;
	}

	@Override
	public ChecklistGrupoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		ChecklistGrupoVO vo=new ChecklistGrupoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.idGrupo=Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricaoGrupo=values[1];
		
		return vo;
	}
}
