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
import wsilva.com.br.mobileos.entity.CorteTipoVO;

public class CorteTipoDAO extends BasicDAO<CorteTipoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDCORTETIPO="idcortetipo";
	public static final String COL_DESCRICAOCORTETIPO="dscortetipo";
	public static final String TABLE_NAME="cortetipo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDCORTETIPO 			+ " INTEGER,"
			+ 	COL_DESCRICAOCORTETIPO 		+ " TEXT"
			+ ");";

	public CorteTipoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(CorteTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(CorteTipoVO vo) 
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
	public List<CorteTipoVO> listar()
	{
		List<CorteTipoVO> data = new ArrayList<CorteTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			CorteTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public CorteTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(CorteTipoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDCORTETIPO, vo.getIdCorteTipo());
		values.put(COL_DESCRICAOCORTETIPO, vo.getDescricaoCorteTipo());
		
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
	public CorteTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		CorteTipoVO vo = new CorteTipoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdCorteTipo(cursor.getInt(cursor.getColumnIndex(COL_IDCORTETIPO)));
		vo.setDescricaoCorteTipo(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOCORTETIPO)));
		
		return vo;
	}
	
	@Override
	public CorteTipoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		CorteTipoVO vo=new CorteTipoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idCorteTipo").length() > 0) {
				vo.setIdCorteTipo(Integer.parseInt(line.getString("idCorteTipo")));
			}
			//Descri��o
			vo.setDescricaoCorteTipo(line.getString("descricaoCorteTipo"));
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
	public CorteTipoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		CorteTipoVO vo=new CorteTipoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdCorteTipo(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoCorteTipo(values[1]);
		
		return vo;
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/CorteTipoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			CorteTipoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
