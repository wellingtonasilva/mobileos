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
import wsilva.com.br.mobileos.entity.LocalOcorrenciaVO;

public class LocalOcorrenciaDAO extends BasicDAO<LocalOcorrenciaVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDLOCALOCORRENCIA="idlocalocorrencia";
	public static final String COL_DESCRICAOLOCALOCORRENCIA="dslocalocorrencia";
	public static final String TABLE_NAME="localocorrencia";
	public static final String CREATE_TABLE=
				"CREATE TABLE " +  TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDLOCALOCORRENCIA + " INTEGER,"
			+ 	COL_DESCRICAOLOCALOCORRENCIA + " TEXT"
			+ ");";
	
	
	public LocalOcorrenciaDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(LocalOcorrenciaVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(LocalOcorrenciaVO vo) 
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
	public List<LocalOcorrenciaVO> listar() 
	{

		List<LocalOcorrenciaVO> data = new ArrayList<LocalOcorrenciaVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			LocalOcorrenciaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public LocalOcorrenciaVO obterPorId(long id) 
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
	public ContentValues obterContentValues(LocalOcorrenciaVO vo) 
	{
		ContentValues values= new ContentValues();
		values.put(COL_DESCRICAOLOCALOCORRENCIA, vo.getDescricaoLocalOcorrencia());
		values.put(COL_IDLOCALOCORRENCIA, vo.getIdLocalOcorrencia());
		
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
	public LocalOcorrenciaVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		LocalOcorrenciaVO vo = new LocalOcorrenciaVO();
		vo.setDescricaoLocalOcorrencia(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOLOCALOCORRENCIA)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdLocalOcorrencia(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALOCORRENCIA)));
		
		return vo;
	}

	@Override
	public LocalOcorrenciaVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		LocalOcorrenciaVO vo=new LocalOcorrenciaVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdLocalOcorrencia(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoLocalOcorrencia(values[1]);
		
		return vo;
	}
	
	
	
	@Override
	public LocalOcorrenciaVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		LocalOcorrenciaVO vo=new LocalOcorrenciaVO();
		
		try 
		{
			//C�digo
			if (line.getString("idLocalOcorrencia").length() > 0) {
				vo.setIdLocalOcorrencia(Integer.parseInt(line.getString("idLocalOcorrencia")));
			}
			//Descri��o
			vo.setDescricaoLocalOcorrencia(line.getString("localOcorrencia"));
		} catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return vo;
	}

	public void povoaTabela()
	{
		List<String> lines=lerDadosFromFile("wa8.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				LocalOcorrenciaVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/LocalOcorrenciaServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			LocalOcorrenciaVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
