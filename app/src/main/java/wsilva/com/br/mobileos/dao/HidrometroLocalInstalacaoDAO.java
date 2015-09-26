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
import wsilva.com.br.mobileos.entity.HidrometroLocalInstalacaoVO;

public class HidrometroLocalInstalacaoDAO extends
		BasicDAO<HidrometroLocalInstalacaoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDLOCALINSTALACAOHIDROMETRO="idlocalinstalacaohm";
	public static final String COL_DESCRICAOLOCALINSTALACAOHIDROMETRO="dslocalinstalacaohm";
	public static final String TABLE_NAME="hm_localinstalacao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDLOCALINSTALACAOHIDROMETRO 			+ " INTEGER,"
			+ COL_DESCRICAOLOCALINSTALACAOHIDROMETRO	+ " TEXT"
			+ ");";
	
	public HidrometroLocalInstalacaoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(HidrometroLocalInstalacaoVO vo)
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(HidrometroLocalInstalacaoVO vo) 
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
	public List<HidrometroLocalInstalacaoVO> listar() 
	{
		List<HidrometroLocalInstalacaoVO> data = new ArrayList<HidrometroLocalInstalacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroLocalInstalacaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public HidrometroLocalInstalacaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroLocalInstalacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO, vo.getIdLocalInstalacaoHidrometro());
		values.put(COL_DESCRICAOLOCALINSTALACAOHIDROMETRO, vo.getDescricaoLocalInstalacaoHidrometro());
		
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
	public HidrometroLocalInstalacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroLocalInstalacaoVO vo = new HidrometroLocalInstalacaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setDescricaoLocalInstalacaoHidrometro(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOLOCALINSTALACAOHIDROMETRO)));
		vo.setIdLocalInstalacaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO)));
		
		return vo;
	}

	@Override
	public HidrometroLocalInstalacaoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		HidrometroLocalInstalacaoVO vo=new HidrometroLocalInstalacaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdLocalInstalacaoHidrometro(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoLocalInstalacaoHidrometro(values[1]);
		
		return vo;
	}

	@Override
	public HidrometroLocalInstalacaoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		HidrometroLocalInstalacaoVO vo=new HidrometroLocalInstalacaoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idLocalInstalacaoHidrometro").length() > 0) {
				vo.setIdLocalInstalacaoHidrometro(Integer.parseInt(line.getString("idLocalInstalacaoHidrometro")));
			}
			//Descri��o
			vo.setDescricaoLocalInstalacaoHidrometro(line.getString("descricaoLocalInstalacaoHidrometro"));
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
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/HidrometroLocalInstalacaoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			HidrometroLocalInstalacaoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
}
