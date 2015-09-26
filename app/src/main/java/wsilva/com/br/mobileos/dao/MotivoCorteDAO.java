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
import wsilva.com.br.mobileos.entity.MotivoCorteVO;

public class MotivoCorteDAO extends BasicDAO<MotivoCorteVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDMOTIVOCORTE="idmotivocorte";
	public static final String COL_DESCRICAOMOTIVOCORTE="dsmotivocorte";
	public static final String TABLE_NAME="motivocorte";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDMOTIVOCORTE + " INTEGER,"
			+ COL_DESCRICAOMOTIVOCORTE + " TEXT"
			+ ");";
	
	
	public MotivoCorteDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(MotivoCorteVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(MotivoCorteVO vo) 
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
	public List<MotivoCorteVO> listar() 
	{
		List<MotivoCorteVO> data = new ArrayList<MotivoCorteVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			MotivoCorteVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public MotivoCorteVO obterPorId(long id) 
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
	public ContentValues obterContentValues(MotivoCorteVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDMOTIVOCORTE, vo.getIdMotivoCorte());
		values.put(COL_DESCRICAOMOTIVOCORTE, vo.getDescricaoMotivoCorte());
		
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
	public MotivoCorteVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		MotivoCorteVO vo = new MotivoCorteVO();
		vo.setDescricaoMotivoCorte(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMOTIVOCORTE)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdMotivoCorte(cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOCORTE)));
		
		return vo;
	}

	@Override
	public MotivoCorteVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		MotivoCorteVO vo=new MotivoCorteVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdMotivoCorte(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoMotivoCorte(values[1]);
		
		return vo;
	}

	@Override
	public MotivoCorteVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		MotivoCorteVO vo=new MotivoCorteVO();
		
		try 
		{
			//C�digo
			if (line.getString("idMotivoCorte").length() > 0) {
				vo.setIdMotivoCorte(Integer.parseInt(line.getString("idMotivoCorte")));
			}
			//Descri��o
			vo.setDescricaoMotivoCorte(line.getString("descricaoMotivoCorte"));
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
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/MotivoCorteServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			MotivoCorteVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
}
