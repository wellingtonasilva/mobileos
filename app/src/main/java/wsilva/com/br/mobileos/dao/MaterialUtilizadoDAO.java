package wsilva.com.br.mobileos.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.MaterialUtilizadoVO;

public class MaterialUtilizadoDAO extends BasicDAO<MaterialUtilizadoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_NUMEROOS="numeroos";
	public static final String COL_IDMATERIAL="idmaterial";
	public static final String COL_DESCRICAOMATERIAL="dsmaterial";
	public static final String COL_IDUNIDADEMEDIDA="idunidademedida";
	public static final String COL_DESCRICAOUNIDADEMEDIDA="dsunidademedida";
	public static final String COL_QUANTIDADE="quantidade";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String TABLE_NAME="materialutilizado";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_NUMEROOS 					+ " INTEGER,"
			+ COL_IDMATERIAL 				+ " INTEGER,"
			+ COL_DESCRICAOMATERIAL 		+ "  TEXT,"
			+ COL_IDUNIDADEMEDIDA 			+ " INTEGER,"
			+ COL_DESCRICAOUNIDADEMEDIDA 	+ " TEXT,"
			+ COL_QUANTIDADE 				+ " REAL,"
			+ COL_IDEQUIPEEXECUCAO 			+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 	+ " TEXT,"
			+ COL_INDICADORENVIO			+ " INTEGER"
			+ ");";

	public MaterialUtilizadoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(MaterialUtilizadoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(MaterialUtilizadoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo._id)}) > 0;
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
	public List<MaterialUtilizadoVO> listar() 
	{
		List<MaterialUtilizadoVO> data = new ArrayList<MaterialUtilizadoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			MaterialUtilizadoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	public List<MaterialUtilizadoVO> listarPorOrdemServico(int numeroOS) 
	{
		List<MaterialUtilizadoVO> data = new ArrayList<MaterialUtilizadoVO>();
		Cursor cursor = obterCursorPorOrdemServico(numeroOS);
		while (!cursor.isAfterLast())
		{
			MaterialUtilizadoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public MaterialUtilizadoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(MaterialUtilizadoVO vo) 
	{
		ContentValues values = new ContentValues();
		values.put(COL_DESCRICAOMATERIAL, vo.descricaoMateriall);
		values.put(COL_DESCRICAOUNIDADEMEDIDA, vo.descricaoUnidadeMedida);
		values.put(COL_IDMATERIAL, vo.idMaterial);
		values.put(COL_IDUNIDADEMEDIDA, vo.idUnidadeMedida);
		values.put(COL_NUMEROOS, vo.numeroOS);
		values.put(COL_QUANTIDADE, vo.quantidade);
		values.put(COL_IDEQUIPEEXECUCAO, vo.idEquipeExecucao);
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.descricaoEquipeExecucao);
		values.put(COL_INDICADORENVIO,  vo.indicadorEnvio);
		
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
	
	public Cursor obterCursorPorOrdemServico(int numeroOS) 
	{
		Cursor cursor = db.query(TABLE_NAME, null, COL_NUMEROOS+"=?", new String[]{String.valueOf(numeroOS)}, 
				null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}

	@Override
	public MaterialUtilizadoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		MaterialUtilizadoVO vo = new MaterialUtilizadoVO();
		vo.descricaoMateriall = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMATERIAL));
		vo.descricaoUnidadeMedida = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUNIDADEMEDIDA));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idMaterial = cursor.getInt(cursor.getColumnIndex(COL_IDMATERIAL));
		vo.idUnidadeMedida = cursor.getInt(cursor.getColumnIndex(COL_IDUNIDADEMEDIDA));
		vo.numeroOS = cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS));
		vo.quantidade = cursor.getDouble(cursor.getColumnIndex(COL_QUANTIDADE));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		
		return vo;
	}
}
