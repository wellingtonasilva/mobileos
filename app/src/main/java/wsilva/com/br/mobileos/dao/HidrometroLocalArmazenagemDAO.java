package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.HidrometroLocalArmazenagemVO;

public class HidrometroLocalArmazenagemDAO extends
		BasicDAO<HidrometroLocalArmazenagemVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDLOCALARMAZENAGEMHIDROMETRO="idlocalarmazenagemhm";
	public static final String COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO="dslocalarmazenagemhm";
	public static final String TABLE_NAME="hm_localarmazenagem";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDLOCALARMAZENAGEMHIDROMETRO 			+ " INTEGER,"
			+ COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO 	+ " TEXT"
			+ ");";
	
	public HidrometroLocalArmazenagemDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(HidrometroLocalArmazenagemVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(HidrometroLocalArmazenagemVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo.getEntityId())}) > 0;
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
	public List<HidrometroLocalArmazenagemVO> listar() 
	{
		List<HidrometroLocalArmazenagemVO> data = new ArrayList<HidrometroLocalArmazenagemVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroLocalArmazenagemVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public HidrometroLocalArmazenagemVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroLocalArmazenagemVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDLOCALARMAZENAGEMHIDROMETRO, vo.getIdLocalArmazenagemHidrometro());
		values.put(COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO, vo.getDescricaoLocalArmazenagemHidrometro());
		
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
	public HidrometroLocalArmazenagemVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroLocalArmazenagemVO vo = new HidrometroLocalArmazenagemVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdLocalArmazenagemHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALARMAZENAGEMHIDROMETRO)));
		vo.setDescricaoLocalArmazenagemHidrometro(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO)));
		
		return vo;
	}

	@Override
	public HidrometroLocalArmazenagemVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		HidrometroLocalArmazenagemVO vo=new HidrometroLocalArmazenagemVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdLocalArmazenagemHidrometro(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoLocalArmazenagemHidrometro(values[1]);
		
		return vo;
	}
	
	@Override
	public HidrometroLocalArmazenagemVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		HidrometroLocalArmazenagemVO vo=new HidrometroLocalArmazenagemVO();
		
		try 
		{
			//C�digo
			if (line.getString("idLocalArmazenagemHidrometro").length() > 0) {
				vo.setIdLocalArmazenagemHidrometro(Integer.parseInt(line.getString("idLocalArmazenagemHidrometro")));
			}
			//Descri��o
			vo.setDescricaoLocalArmazenagemHidrometro(line.getString("descricaoLocalArmazenagemHidrometro"));
		} catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/HidrometroLocalArmazenagemServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			HidrometroLocalArmazenagemVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
