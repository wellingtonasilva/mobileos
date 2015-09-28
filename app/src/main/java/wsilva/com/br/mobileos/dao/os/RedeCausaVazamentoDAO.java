package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.RedeCausaVazamentoVO;

public class RedeCausaVazamentoDAO extends BasicDAO<RedeCausaVazamentoVO>
{
	
	public static final String COL_ID="_id";
	public static final String COL_IDCAUSAVAZAMENTO="idcausavazamento";
	public static final String COL_DESCRICAOCAUSAVAZAMENTO="dscausavazamento";
	public static final String TABLE_NAME="redecausavazamento";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDCAUSAVAZAMENTO + " INTEGER,"
			+ 	COL_DESCRICAOCAUSAVAZAMENTO + " TEXT"
			+ 	");";

	public RedeCausaVazamentoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(RedeCausaVazamentoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(RedeCausaVazamentoVO vo) 
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
	public List<RedeCausaVazamentoVO> listar() 
	{
		List<RedeCausaVazamentoVO> data = new ArrayList<RedeCausaVazamentoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			RedeCausaVazamentoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public RedeCausaVazamentoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(RedeCausaVazamentoVO vo)
	{
		ContentValues values= new ContentValues();
		values.put(COL_DESCRICAOCAUSAVAZAMENTO, vo.descricaoCausaVazamento);
		values.put(COL_IDCAUSAVAZAMENTO, vo.idCausaVazamento);
		
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
	public RedeCausaVazamentoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		RedeCausaVazamentoVO vo = new RedeCausaVazamentoVO();
		vo.descricaoCausaVazamento = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOCAUSAVAZAMENTO));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idCausaVazamento = cursor.getInt(cursor.getColumnIndex(COL_IDCAUSAVAZAMENTO));
		
		return vo;
	}

	@Override
	public RedeCausaVazamentoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		RedeCausaVazamentoVO vo=new RedeCausaVazamentoVO();

		if (values[0].length() > 0)
		{
			vo.idCausaVazamento = Integer.parseInt(values[0]);
		}
		vo.descricaoCausaVazamento = values[1];

		return vo;
	}
}
