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
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.RedeTipoVO;

public class RedeTipoDAO extends BasicDAO<RedeTipoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDREDETIPO="idredetipo";
	public static final String COL_DESCRICAOREDETIPO="dsredetipo";
	public static final String TABLE_NAME="redetipo";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDREDETIPO + " INTEGER,"
			+ COL_DESCRICAOREDETIPO + " TEXT"
			+ ");";
	
	public RedeTipoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(RedeTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(RedeTipoVO vo) 
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
	public List<RedeTipoVO> listar() 
	{
		List<RedeTipoVO> data = new ArrayList<RedeTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			RedeTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public RedeTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(RedeTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DESCRICAOREDETIPO, vo.getDescricaoRedeTipo());
		values.put(COL_IDREDETIPO, vo.getIdRedeTipo());
		
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
	public RedeTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		RedeTipoVO vo=new RedeTipoVO();
		vo.setDescricaoRedeTipo(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOREDETIPO)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdRedeTipo(cursor.getInt(cursor.getColumnIndex(COL_IDREDETIPO)));
		
		return vo;
	}
	

	@Override
	public RedeTipoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		RedeTipoVO vo=new RedeTipoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idRedeTipo").length() > 0) {
				vo.setIdRedeTipo(Integer.parseInt(line.getString("idRedeTipo")));
			}
			//Descri��o
			vo.setDescricaoRedeTipo(line.getString("redeTipo"));
		} catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public RedeTipoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		RedeTipoVO vo= new RedeTipoVO();
		
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdRedeTipo(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoRedeTipo(values[1]);
		
		return vo;
	}

	public void povoaTabela()
	{
		List<String> lines=lerDadosFromFile("wa3.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				RedeTipoVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/RedeTipoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			RedeTipoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
	
	
}
