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
import wsilva.com.br.mobileos.entity.UsuarioVO;

public class UsuarioDAO extends BasicDAO<UsuarioVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDUSUARIO="idusuario";
	public static final String COL_LOGIN="login";
	public static final String COL_NOME="nome";
	public static final String COL_SENHA="senha";
	public static final String TABLE_NAME="usuario";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDUSUARIO + " INTEGER,"
			+ COL_LOGIN + " TEXT,"
			+ COL_NOME + " TEXT,"
			+ COL_SENHA + " TEXT"
			+ ");";
	
	public UsuarioDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(UsuarioVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(UsuarioVO vo) 
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
	public List<UsuarioVO> listar() 
	{
		List<UsuarioVO> data = new ArrayList<UsuarioVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			UsuarioVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public UsuarioVO obterPorId(long id) 
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
	public ContentValues obterContentValues(UsuarioVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDUSUARIO, vo.getIdUsuario());
		values.put(COL_LOGIN, vo.getLogin());
		values.put(COL_NOME, vo.getNome());
		values.put(COL_SENHA, vo.getSenha());
		
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
	public UsuarioVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		UsuarioVO vo=new UsuarioVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdUsuario(cursor.getInt(cursor.getColumnIndex(COL_IDUSUARIO)));
		vo.setLogin(cursor.getString(cursor.getColumnIndex(COL_LOGIN)));
		vo.setNome(cursor.getString(cursor.getColumnIndex(COL_NOME)));
		vo.setSenha(cursor.getString(cursor.getColumnIndex(COL_SENHA)));
		
		return vo;
	}

	@Override
	public UsuarioVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		UsuarioVO vo= new UsuarioVO();
		
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdUsuario(Integer.parseInt(values[0]));
		}
		//Login
		vo.setLogin(values[1]);
		//Nome
		vo.setNome(values[2]);
		//Senha
		vo.setSenha(values[3]);
		
		return vo;
	}
	
	@Override
	public UsuarioVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		UsuarioVO vo=new UsuarioVO();
		
		try 
		{
			//C�digo
			if (line.getString("idUsuario").length() > 0) {
				vo.setIdUsuario(Integer.parseInt(line.getString("idUsuario")));
			}
			//Login
			vo.setLogin(line.getString("login"));
			vo.setNome(line.getString("usuario"));
			vo.setSenha(line.getString("senha"));
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
	public int quantidadeRegistros() 
	{
		Cursor cursor=db.query(true, TABLE_NAME, new String[]{COL_ID}, null, null, null, null, null, null);
		if (cursor!=null) {
			return cursor.getCount();
		} else {
			return 0;
		}
	}
	
	public void povoaTabela()
	{
		List<String> lines=lerDadosFromFile("wa1.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				UsuarioVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/UsuarioServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			UsuarioVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
	public UsuarioVO obterPorLogin(String login)
	{
		Cursor cursor=db.query(true, TABLE_NAME, null, COL_LOGIN+"=?", 
				new String[]{login}, null, null, null, null);
		if (cursor!=null) 
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}


	

}
