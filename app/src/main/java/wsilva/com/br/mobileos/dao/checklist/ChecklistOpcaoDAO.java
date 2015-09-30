package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistOpcaoVO;

public class ChecklistOpcaoDAO extends BasicDAO<ChecklistOpcaoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDGRUPO="idgrupo";
	public static final String COL_IDITEM="iditem";
	public static final String COL_IDOPCAO="idopcao";
	public static final String COL_DESCRICAOOPCAO="dsopcao";
	public static final String TABLE_NAME="checklist_opcao";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDGRUPO 		+ " INTEGER,"
			+ 	COL_IDITEM 			+ " INTEGER,"
			+ 	COL_IDOPCAO 		+ " INTEGER,"
			+ 	COL_DESCRICAOOPCAO 	+ " TEXT"
			+ ");";
	
	public ChecklistOpcaoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ChecklistOpcaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistOpcaoVO vo) 
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
	public List<ChecklistOpcaoVO> listar()
	{
		List<ChecklistOpcaoVO> data = new ArrayList<ChecklistOpcaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistOpcaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	public List<ChecklistOpcaoVO> listarPorItem(int iditem) 
	{
		List<ChecklistOpcaoVO> data = new ArrayList<ChecklistOpcaoVO>();
		Cursor cursor = obterCursorPorItem(iditem);
		while (!cursor.isAfterLast())
		{
			ChecklistOpcaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistOpcaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ChecklistOpcaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDGRUPO, vo.idGrupo);
		values.put(COL_IDITEM, vo.idItem);
		values.put(COL_IDOPCAO, vo.idOpcao);
		values.put(COL_DESCRICAOOPCAO, vo.descricaoOpcao);
		
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
	
	public Cursor obterCursorPorItem(int iditem)
	{
		Cursor cursor = db.query(TABLE_NAME, null, COL_IDITEM+"=?", new String[]{String.valueOf(iditem)}, 
				null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}


	@Override
	public ChecklistOpcaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistOpcaoVO vo = new ChecklistOpcaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idGrupo=cursor.getInt(cursor.getColumnIndex(COL_IDGRUPO));
		vo.idItem=cursor.getInt(cursor.getColumnIndex(COL_IDITEM));
		vo.idOpcao=cursor.getInt(cursor.getColumnIndex(COL_IDOPCAO));
		vo.descricaoOpcao=cursor.getString(cursor.getColumnIndex(COL_DESCRICAOOPCAO));
		
		return vo;
	}
	
	@Override
	public ChecklistOpcaoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		ChecklistOpcaoVO vo=new ChecklistOpcaoVO();
		//C�digo da Op��o
		if (values[0].length() > 0) {
			vo.idOpcao=Integer.parseInt(values[0]);
		}
		
		//C�digo Item
		if (values[1].length() > 0) {
			vo.idItem=Integer.parseInt(values[1]);
		}
		//Descri��o
		vo.descricaoOpcao=values[2];
		
		return vo;
	}
}
