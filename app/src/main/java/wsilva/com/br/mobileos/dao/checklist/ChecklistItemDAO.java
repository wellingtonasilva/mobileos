package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistItemVO;

public class ChecklistItemDAO extends BasicDAO<ChecklistItemVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDGRUPO="idgrupo";
	public static final String COL_IDITEM="iditem";
	public static final String COL_DESCRICAOITEM="dsitem";
	public static final String COL_IDOPCAOCHECKED="idopcaochecked";
	public static final String TABLE_NAME="checklist_item";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDGRUPO 		+ " INTEGER,"
			+ 	COL_IDITEM 			+ " INTEGER,"
			+ 	COL_DESCRICAOITEM 	+ " TEXT,"
			+ 	COL_IDOPCAOCHECKED 	+ " INTEGER"
			+ ");";
	
	public ChecklistItemDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ChecklistItemVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistItemVO vo) 
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
	public List<ChecklistItemVO> listar()
	{
		List<ChecklistItemVO> data = new ArrayList<ChecklistItemVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistItemVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	public List<ChecklistItemVO> listarPorGrupo(int idgrupo) 
	{
		List<ChecklistItemVO> data = new ArrayList<ChecklistItemVO>();
		Cursor cursor = obterCursorPorGrupo(idgrupo);
		while (!cursor.isAfterLast())
		{
			ChecklistItemVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistItemVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ChecklistItemVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDGRUPO, vo.idGrupo);
		values.put(COL_IDITEM, vo.idItem);
		values.put(COL_DESCRICAOITEM, vo.descricaoItem);
		values.put(COL_IDOPCAOCHECKED, vo.idOpcaoChecked);
		
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
	public ChecklistItemVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistItemVO vo = new ChecklistItemVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idGrupo=cursor.getInt(cursor.getColumnIndex(COL_IDGRUPO));
		vo.idItem=cursor.getInt(cursor.getColumnIndex(COL_IDITEM));
		vo.descricaoItem=cursor.getString(cursor.getColumnIndex(COL_DESCRICAOITEM));
		vo.idOpcaoChecked=cursor.getInt(cursor.getColumnIndex(COL_IDOPCAOCHECKED));
		
		return vo;
	}
	
	@Override
	public ChecklistItemVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		ChecklistItemVO vo=new ChecklistItemVO();
		//C�digo do Item
		if (values[0].length() > 0) {
			vo.idItem=Integer.parseInt(values[0]);
		}
		
		//C�digo do Grupo
		if (values[1].length() > 0) {
			vo.idGrupo=Integer.parseInt(values[1]);
		}
		//Descri��o
		vo.descricaoItem=values[2];
		//Op��o que dever� ser checked
		vo.idOpcaoChecked=Integer.parseInt(values[3]);
		
		return vo;
	}
}
