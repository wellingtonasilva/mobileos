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
import wsilva.com.br.mobileos.entity.PavimentoTipoVO;

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
		values.put(COL_IDPAVIMENTOTIPO, vo.getIdPavimentoTipo());
		values.put(COL_DESCRICAOPAVIMENTOTIPO, vo.getDescricaoPavimentoTipo());
		
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
	public PavimentoTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		PavimentoTipoVO vo = new PavimentoTipoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdPavimentoTipo(cursor.getInt(cursor.getColumnIndex(COL_IDPAVIMENTOTIPO)));
		vo.setDescricaoPavimentoTipo(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOPAVIMENTOTIPO)));
		
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
		
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdPavimentoTipo(Integer.parseInt(values[0]));	
		}
		//Descri��o
		vo.setDescricaoPavimentoTipo(values[1]);
		
		return vo;
	}
	
	

	@Override
	public PavimentoTipoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		PavimentoTipoVO vo=new PavimentoTipoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idPavimentoTipo").length() > 0) {
				vo.setIdPavimentoTipo(Integer.parseInt(line.getString("idPavimentoTipo")));
			}
			//Descri��o
			vo.setDescricaoPavimentoTipo(line.getString("pavimentoTipo"));
	
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
		List<String> lines=lerDadosFromFile("wb8.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				PavimentoTipoVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/PavimentoTipoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			PavimentoTipoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
