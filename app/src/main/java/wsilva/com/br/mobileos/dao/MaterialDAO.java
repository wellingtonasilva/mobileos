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
import wsilva.com.br.mobileos.entity.MaterialVO;

public class MaterialDAO extends BasicDAO<MaterialVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDMATERIAL="idmaterial";
	public static final String COL_DESCRICAOMATERIAL="dsmaterial";
	public static final String COL_IDUNIDADEMEDIDA="idunidademedida";
	public static final String COL_DESCRICAOUNIDADEMEDIDA="dsunidademedida";
	public static final String TABLE_NAME="material";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDMATERIAL +  " INTEGER,"
			+ 	COL_DESCRICAOMATERIAL + " TEXT,"
			+ 	COL_IDUNIDADEMEDIDA + " INTEGER,"
			+ 	COL_DESCRICAOUNIDADEMEDIDA + " TEXT"
			+ ");";
	
	public MaterialDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(MaterialVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(MaterialVO vo) 
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
	public List<MaterialVO> listar() 
	{
		List<MaterialVO> data = new ArrayList<MaterialVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			MaterialVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public MaterialVO obterPorId(long id) 
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
	public ContentValues obterContentValues(MaterialVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DESCRICAOMATERIAL, vo.getDescricaoMaterial());
		values.put(COL_DESCRICAOUNIDADEMEDIDA, vo.getDescricaoUnidadeMedida());
		values.put(COL_IDMATERIAL, vo.getIdMaterial());
		values.put(COL_IDUNIDADEMEDIDA, vo.getIdUnidadeMedida());
		
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
	public MaterialVO obterObject(Cursor cursor) 
	{

		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		MaterialVO vo = new MaterialVO();
		vo.setDescricaoMaterial(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMATERIAL)));
		vo.setDescricaoUnidadeMedida(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUNIDADEMEDIDA)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdMaterial(cursor.getInt(cursor.getColumnIndex(COL_IDMATERIAL)));
		vo.setIdUnidadeMedida(cursor.getInt(cursor.getColumnIndex(COL_IDUNIDADEMEDIDA)));
		
		return vo;
		
	}

	@Override
	public MaterialVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		MaterialVO vo=new MaterialVO();
		//C�digo do Material
		if (values[0].length() > 0) {
			vo.setIdMaterial(Integer.parseInt(values[0]));
		}
		//Descri��o do Material
		vo.setDescricaoMaterial(values[1]);
		//C�digo da Unidade
		if (values[2].length()>0)
		{
			vo.setIdUnidadeMedida(Integer.parseInt(values[2]));
		}
		//Descri��o da Unidade
		vo.setDescricaoUnidadeMedida(values[3]);
		
		return vo;
	}
	
	
	@Override
	public MaterialVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		MaterialVO vo=new MaterialVO();
		
		try 
		{
			//C�digo Material
			if (line.getString("idMaterial").length() > 0) {
				vo.setIdMaterial(Integer.parseInt(line.getString("idMaterial")));
			}
			//Descri��o Material
			vo.setDescricaoMaterial(line.getString("material"));
			
			//C�digo Unidade
			if (line.getString("idUnidadeMedida").length() > 0) {
				vo.setIdUnidadeMedida(Integer.parseInt(line.getString("idUnidadeMedida")));
			}
			
			//Descri��o da Unidade
			vo.setDescricaoUnidadeMedida(line.getString("unidadeMedida"));
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
		List<String> lines=lerDadosFromFile("wa7.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				MaterialVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/MaterialServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			MaterialVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
	
	
}
