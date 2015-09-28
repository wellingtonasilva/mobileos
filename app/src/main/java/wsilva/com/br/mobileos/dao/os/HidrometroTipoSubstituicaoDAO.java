package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.HidrometroTipoSubstituicaoVO;

public class HidrometroTipoSubstituicaoDAO extends
		BasicDAO<HidrometroTipoSubstituicaoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDTIPOSUBSTITUICAOHM="idtiposubstituicaohm";
	public static final String COL_DESCRICAOTIPOSUBSTITUICAOHM="dstiposubstituicaohm";
	public static final String TABLE_NAME="hm_tipo_substituicao";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDTIPOSUBSTITUICAOHM 			+ " INTEGER,"
			+ 	COL_DESCRICAOTIPOSUBSTITUICAOHM 		+ " TEXT"
			+ ");";
	
	public HidrometroTipoSubstituicaoDAO(Context context) {
		super(context);
	}
	
	
	@Override
	public long inserir(HidrometroTipoSubstituicaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(HidrometroTipoSubstituicaoVO vo) 
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
	public List<HidrometroTipoSubstituicaoVO> listar()
	{
		List<HidrometroTipoSubstituicaoVO> data = new ArrayList<HidrometroTipoSubstituicaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroTipoSubstituicaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public HidrometroTipoSubstituicaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroTipoSubstituicaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDTIPOSUBSTITUICAOHM, vo.idTipoSubstituicaoHM);
		values.put(COL_DESCRICAOTIPOSUBSTITUICAOHM, vo.descricaoTipoSubstituicaoHM);
		
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
	public HidrometroTipoSubstituicaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroTipoSubstituicaoVO vo = new HidrometroTipoSubstituicaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idTipoSubstituicaoHM = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSUBSTITUICAOHM));
		vo.descricaoTipoSubstituicaoHM = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSUBSTITUICAOHM));
		
		return vo;
	}

	@Override
	public HidrometroTipoSubstituicaoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		HidrometroTipoSubstituicaoVO vo=new HidrometroTipoSubstituicaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.idTipoSubstituicaoHM = Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricaoTipoSubstituicaoHM = values[1];

		return vo;
	}
}
