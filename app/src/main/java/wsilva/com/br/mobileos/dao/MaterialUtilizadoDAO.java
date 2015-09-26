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
		values.put(COL_DESCRICAOMATERIAL, vo.getDescricaoMateriall());
		values.put(COL_DESCRICAOUNIDADEMEDIDA, vo.getDescricaoUnidadeMedida());
		values.put(COL_IDMATERIAL, vo.getIdMaterial());
		values.put(COL_IDUNIDADEMEDIDA, vo.getIdUnidadeMedida());
		values.put(COL_NUMEROOS, vo.getNumeroOS());
		values.put(COL_QUANTIDADE, vo.getQuantidade());
		values.put(COL_IDEQUIPEEXECUCAO, vo.getIdEquipeExecucao());
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.getDescricaoEquipeExecucao());
		values.put(COL_INDICADORENVIO,  vo.getIndicadorEnvio());
		
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
		vo.setDescricaoMateriall(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMATERIAL)));
		vo.setDescricaoUnidadeMedida(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUNIDADEMEDIDA)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdMaterial(cursor.getInt(cursor.getColumnIndex(COL_IDMATERIAL)));
		vo.setIdUnidadeMedida(cursor.getInt(cursor.getColumnIndex(COL_IDUNIDADEMEDIDA)));
		vo.setNumeroOS(cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS)));
		vo.setQuantidade(cursor.getDouble(cursor.getColumnIndex(COL_QUANTIDADE)));
		vo.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		vo.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		vo.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));
		
		return vo;
	}
	
	@Override
	public String obterLinhaCSV(MaterialUtilizadoVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null)
		{
			linha=	delimitador									+ ";"
					+ String.valueOf(vo.getNumeroOS()) 			+ ";"
					+ vo.getDescricaoMateriall() 				+ ";"
					+ String.valueOf(vo.getIdUnidadeMedida()) 	+ ";"
					+ vo.getDescricaoUnidadeMedida() 			+ ";"
					+ Double.toString(vo.getQuantidade()) 		+ ";"
					+ String.valueOf(vo.getIdMaterial()) 		+ ";"
					+ Integer.toString(vo.getIdEquipeExecucao())+ ";"
					+ vo.getDescricaoEquipeExecucao()
					+ "\n";
		}
		return linha;
	}

	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<MaterialUtilizadoVO> materiais=listar();
		int qtd=materiais.size();
		String linha="";

		if (qtd>0) {
			try 
			{
				File sdCard = Environment.getExternalStorageDirectory();
				File directory= new File(sdCard.getAbsolutePath() + directoryname);
				File file = new File(directory, filename);
				FileOutputStream output= new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(output);
				
				for (int i=0; i<qtd; i++)
				{
					MaterialUtilizadoVO vo=materiais.get(i);
					linha=String.valueOf(vo.getNumeroOS())				+ ";"
							+ vo.getDescricaoMateriall() 				+  ";"
							+ String.valueOf(vo.getIdUnidadeMedida()) 	+ ";"
							+ vo.getDescricaoUnidadeMedida() 			+ ";"
							+ Double.toString(vo.getQuantidade()) 		+ ";"
							+ String.valueOf(vo.getIdMaterial()) 		+ ";"
							+ Integer.toString(vo.getIdEquipeExecucao())+ ";"
							+ vo.getDescricaoEquipeExecucao()
							+ "\n";
					osw.write(linha);
				}
				osw.flush();
				osw.close();
				bolReturn=true;
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}
		return bolReturn;
	}
}
