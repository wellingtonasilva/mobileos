package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.PavimentoTipoVO;

public class PavimentoTipoDAO extends BasicDAO<PavimentoTipoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDPAVIMENTOTIPO="idpavimentotipo";
	public static final String COL_DESCRICAOPAVIMENTOTIPO="dspavimentotipo";
	public static final String TABLE_NAME="pavimentotipo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 		+ TABLE_NAME + "("
			+ 	COL_ID				 	+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDPAVIMENTOTIPO 	+ " INTEGER,"
			+ 	COL_DESCRICAOPAVIMENTOTIPO + " TEXT"
			+ 	");";
	
	public PavimentoTipoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(PavimentoTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(PavimentoTipoVO vo) 
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
	public List<PavimentoTipoVO> listar() 
	{
		List<PavimentoTipoVO> data = new ArrayList<PavimentoTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			PavimentoTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public PavimentoTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(PavimentoTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDPAVIMENTOTIPO, vo.idPavimentoTipo);
		values.put(COL_DESCRICAOPAVIMENTOTIPO, vo.descricaoPavimentoTipo);
		
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
	public PavimentoTipoVO obterObject(Cursor cursor) {
		if (cursor == null || cursor.getCount() < 1) {
			return null;
		}

		PavimentoTipoVO vo = new PavimentoTipoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idPavimentoTipo = cursor.getInt(cursor.getColumnIndex(COL_IDPAVIMENTOTIPO));
		vo.descricaoPavimentoTipo = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOPAVIMENTOTIPO));

		return vo;
	}

	@Override
	public PavimentoTipoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		PavimentoTipoVO vo= new PavimentoTipoVO();

		if (values[0].length() > 0) {
			vo.idPavimentoTipo = Integer.parseInt(values[0]);
		}
		vo.descricaoPavimentoTipo = values[1];

		return vo;
	}
}
