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
import wsilva.com.br.mobileos.entity.HidrometroTipoInstalacaoVO;

public class HidrometroTipoInstalacaoDAO extends
		BasicDAO<HidrometroTipoInstalacaoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDTIPOINSTALACAOHM="idtipoinstalacaohm";
	public static final String COL_DESCRICAOTIPOINSTALACAOHM="dstipoinstalacaohm";
	public static final String TABLE_NAME="hm_tipoinstalacao";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDTIPOINSTALACAOHM 			+ " INTEGER,"
			+ 	COL_DESCRICAOTIPOINSTALACAOHM 		+ " TEXT"
			+ ");";
	
	public HidrometroTipoInstalacaoDAO(Context context) {
		super(context);
	}
	
	
	@Override
	public long inserir(HidrometroTipoInstalacaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(HidrometroTipoInstalacaoVO vo) 
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
	public List<HidrometroTipoInstalacaoVO> listar()
	{
		List<HidrometroTipoInstalacaoVO> data = new ArrayList<HidrometroTipoInstalacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			HidrometroTipoInstalacaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public HidrometroTipoInstalacaoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(HidrometroTipoInstalacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDTIPOINSTALACAOHM, vo.getIdTipoInstalacaoHM());
		values.put(COL_DESCRICAOTIPOINSTALACAOHM, vo.getDescricaoTipoInstalacaoHM());
		
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
	public HidrometroTipoInstalacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		HidrometroTipoInstalacaoVO vo = new HidrometroTipoInstalacaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdTipoInstalacaoHM(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOINSTALACAOHM)));
		vo.setDescricaoTipoInstalacaoHM(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOINSTALACAOHM)));
		
		return vo;
	}
	
	@Override
	public HidrometroTipoInstalacaoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		HidrometroTipoInstalacaoVO vo=new HidrometroTipoInstalacaoVO();
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdTipoInstalacaoHM(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoTipoInstalacaoHM(values[1]);
		
		return vo;
	}
	
	@Override
	public HidrometroTipoInstalacaoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		HidrometroTipoInstalacaoVO vo=new HidrometroTipoInstalacaoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idTipoInstalacaoHM").length() > 0) {
				vo.setIdTipoInstalacaoHM(Integer.parseInt(line.getString("idTipoInstalacaoHM")));
			}
			//Descri��o
			vo.setDescricaoTipoInstalacaoHM(line.getString("descricaoTipoInstalacaoHM"));
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
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/HidrometroTipoInstalacaoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			HidrometroTipoInstalacaoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}

}
