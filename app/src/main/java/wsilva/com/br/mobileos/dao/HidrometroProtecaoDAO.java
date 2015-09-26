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
import wsilva.com.br.mobileos.entity.HidrometroProtecaoVO;

public class HidrometroProtecaoDAO extends BasicDAO<HidrometroProtecaoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDPROTECAOHIDROMETRO="idprotecaohm";
	public static final String COL_DESCRICAOPROTECAOHIDROMETRO="dsprotecaohm";	
	public static final String TABLE_NAME="hm_protecao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDPROTECAOHIDROMETRO 			+ " INTEGER,"
			+ COL_DESCRICAOPROTECAOHIDROMETRO 	+ " TEXT"
			+ ");";
	
	
	public HidrometroProtecaoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(HidrometroProtecaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(HidrometroProtecaoVO vo) 
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
	public List<HidrometroProtecaoVO> listar() 
	{
		List<HidrometroProtecaoVO> data = new ArrayList<HidrometroProtecaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroProtecaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public HidrometroProtecaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroProtecaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDPROTECAOHIDROMETRO, vo.getIdProtecaoHidrometro());
		values.put(COL_DESCRICAOPROTECAOHIDROMETRO, vo.getDescricaoProtecaoHidrometro());
		
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
	public HidrometroProtecaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroProtecaoVO vo = new HidrometroProtecaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdProtecaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO)));
		vo.setDescricaoProtecaoHidrometro(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOPROTECAOHIDROMETRO)));
		
		return vo;
	}

	@Override
	public HidrometroProtecaoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		HidrometroProtecaoVO vo=new HidrometroProtecaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdProtecaoHidrometro(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoProtecaoHidrometro(values[1]);
		
		return vo;
	}

	@Override
	public HidrometroProtecaoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		HidrometroProtecaoVO vo=new HidrometroProtecaoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idProtecaoHidrometro").length() > 0) {
				vo.setIdProtecaoHidrometro(Integer.parseInt(line.getString("idProtecaoHidrometro")));
			}
			//Descri��o
			vo.setDescricaoProtecaoHidrometro(line.getString("descricaoProtecaoHidrometro"));
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
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/HidrometroProtecaoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			HidrometroProtecaoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
