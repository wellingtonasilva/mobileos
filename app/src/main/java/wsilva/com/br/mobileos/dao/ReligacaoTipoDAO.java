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
import wsilva.com.br.mobileos.entity.ReligacaoTipoVO;

public class ReligacaoTipoDAO extends BasicDAO<ReligacaoTipoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDTIPORELIGACAO="idtiporeligacao";
	public static final String COL_DESCRICAOTIPORELIGACAO="dstiporeligacao";
	public static final String TABLE_NAME="religacao_tipo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDTIPORELIGACAO 			+ " INTEGER,"
			+ 	COL_DESCRICAOTIPORELIGACAO 		+ " TEXT"
			+ ");";

	public ReligacaoTipoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ReligacaoTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ReligacaoTipoVO vo) 
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
	public List<ReligacaoTipoVO> listar()
	{
		List<ReligacaoTipoVO> data = new ArrayList<ReligacaoTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ReligacaoTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ReligacaoTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ReligacaoTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDTIPORELIGACAO, vo.getIdTipoReligacao());
		values.put(COL_DESCRICAOTIPORELIGACAO, vo.getDescricaoTipoReligacao());
		
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
	public ReligacaoTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ReligacaoTipoVO vo = new ReligacaoTipoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdTipoReligacao(cursor.getInt(cursor.getColumnIndex(COL_IDTIPORELIGACAO)));
		vo.setDescricaoTipoReligacao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPORELIGACAO)));
		
		return vo;
	}
	
	@Override
	public ReligacaoTipoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		ReligacaoTipoVO vo=new ReligacaoTipoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdTipoReligacao(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoTipoReligacao(values[1]);
		
		return vo;
	}
	
	@Override
	public ReligacaoTipoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		ReligacaoTipoVO vo=new ReligacaoTipoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idTipoReligacao").length() > 0) {
				vo.setIdTipoReligacao(Integer.parseInt(line.getString("idTipoReligacao")));
			}
			//Descri��o
			vo.setDescricaoTipoReligacao(line.getString("descricaoTipoReligacao"));
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
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/ReligacaoTipoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			ReligacaoTipoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
