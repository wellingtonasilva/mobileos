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
import wsilva.com.br.mobileos.entity.MotivoEncerramentoVO;

public class MotivoEncerramentoDAO extends BasicDAO<MotivoEncerramentoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDMOTIVOENCERRAMENTO="idmotivoencerramento";
	public static final String COL_DESCRICAOMOTIVOENCERRAMENTO="dsmotivoencerramento";
	public static final String TABLE_NAME="motivoencerramento";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDMOTIVOENCERRAMENTO + " INTEGER,"
			+ 	COL_DESCRICAOMOTIVOENCERRAMENTO + " TEXT"
			+ " );";
	
	public MotivoEncerramentoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(MotivoEncerramentoVO vo)
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(MotivoEncerramentoVO vo) 
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
	public List<MotivoEncerramentoVO> listar() 
	{
		List<MotivoEncerramentoVO> data = new ArrayList<MotivoEncerramentoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			MotivoEncerramentoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public MotivoEncerramentoVO obterPorId(long id)
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
	public ContentValues obterContentValues(MotivoEncerramentoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DESCRICAOMOTIVOENCERRAMENTO, vo.getDescricaoMotivoEncerramento());
		values.put(COL_IDMOTIVOENCERRAMENTO, vo.getIdMotivoEncerramento());
		
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
	public MotivoEncerramentoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		MotivoEncerramentoVO vo = new MotivoEncerramentoVO();
		vo.setDescricaoMotivoEncerramento(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMOTIVOENCERRAMENTO)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdMotivoEncerramento(cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOENCERRAMENTO)));
		
		return vo;
	}

	@Override
	public MotivoEncerramentoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		MotivoEncerramentoVO vo=new MotivoEncerramentoVO();
		//C�digo
		if (values[0].length()>0){
			vo.setIdMotivoEncerramento(Integer.parseInt(values[0]));
		}
		//Descri��o
		vo.setDescricaoMotivoEncerramento(values[1]);
		
		return vo;
	}
	
	@Override
	public MotivoEncerramentoVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		MotivoEncerramentoVO vo=new MotivoEncerramentoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idMotivoEncerramento").length() > 0) {
				vo.setIdMotivoEncerramento(Integer.parseInt(line.getString("idMotivoEncerramento")));
			}
			//Descri��o
			vo.setDescricaoMotivoEncerramento(line.getString("motivoEncerramento"));
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
		List<String> lines=lerDadosFromFile("wa6.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				MotivoEncerramentoVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/MotivoEncerramentoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			MotivoEncerramentoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
}
