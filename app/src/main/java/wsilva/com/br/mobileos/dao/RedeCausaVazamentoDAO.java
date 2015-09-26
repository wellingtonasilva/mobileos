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
import wsilva.com.br.mobileos.entity.RedeCausaVazamentoVO;

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
		values.put(COL_DESCRICAOCAUSAVAZAMENTO, vo.getDescricaoCausaVazamento());
		values.put(COL_IDCAUSAVAZAMENTO, vo.getIdCausaVazamento());
		
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
		vo.setDescricaoCausaVazamento(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOCAUSAVAZAMENTO)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdCausaVazamento(cursor.getInt(cursor.getColumnIndex(COL_IDCAUSAVAZAMENTO)));
		
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
		
		//C�digo
		if (values[0].length() > 0)
		{
			vo.setIdCausaVazamento(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoCausaVazamento(values[1]);
		
		return vo;
	}
	
	@Override
	public RedeCausaVazamentoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		RedeCausaVazamentoVO vo=new RedeCausaVazamentoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idRedeCausaVazamento").length() > 0) {
				vo.setIdCausaVazamento(Integer.parseInt(line.getString("idRedeCausaVazamento")));
			}
			//Descri��o
			vo.setDescricaoCausaVazamento(line.getString("redeCausaVazamento"));
			
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
		List<String> lines=lerDadosFromFile("wb4.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				RedeCausaVazamentoVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}

	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/RedeCausaVazamentoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			RedeCausaVazamentoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
}
