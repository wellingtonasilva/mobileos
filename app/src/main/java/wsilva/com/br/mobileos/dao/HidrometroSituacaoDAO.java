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
import wsilva.com.br.mobileos.entity.HidrometroSituacaoVO;

public class HidrometroSituacaoDAO extends BasicDAO<HidrometroSituacaoVO> {

	
	public static final String COL_ID="_id";
	public static final String COL_IDSITUACAOHIDROMETRO="idsituacaohm";
	public static final String COL_DESCRICAOSITUACAOHIDROMETRO="dssituacaohm";
	public static final String TABLE_NAME="hm_situacao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDSITUACAOHIDROMETRO 			+ " INTEGER,"
			+ COL_DESCRICAOSITUACAOHIDROMETRO 	+ " TEXT"
			+ ");";
	
	public HidrometroSituacaoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(HidrometroSituacaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(HidrometroSituacaoVO vo) 
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
	public List<HidrometroSituacaoVO> listar() 
	{
		List<HidrometroSituacaoVO> data = new ArrayList<HidrometroSituacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroSituacaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public HidrometroSituacaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroSituacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDSITUACAOHIDROMETRO, vo.getIdSituacaoHidrometro());
		values.put(COL_DESCRICAOSITUACAOHIDROMETRO, vo.getDescricaoSituacaoHidrometro());
		
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
	public HidrometroSituacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroSituacaoVO vo = new HidrometroSituacaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdSituacaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOHIDROMETRO)));
		vo.setDescricaoSituacaoHidrometro(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOHIDROMETRO)));
		
		return vo;
	}

	@Override
	public HidrometroSituacaoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		HidrometroSituacaoVO vo=new HidrometroSituacaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdSituacaoHidrometro(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoSituacaoHidrometro(values[1]);
		
		return vo;
	}

	@Override
	public HidrometroSituacaoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		HidrometroSituacaoVO vo=new HidrometroSituacaoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idSituacaoHidrometro").length() > 0) {
				vo.setIdSituacaoHidrometro(Integer.parseInt(line.getString("idSituacaoHidrometro")));
			}
			//Descri��o
			vo.setDescricaoSituacaoHidrometro(line.getString("descricaoSituacaoHidrometro"));
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
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/HidrometroSituacaoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			HidrometroSituacaoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
