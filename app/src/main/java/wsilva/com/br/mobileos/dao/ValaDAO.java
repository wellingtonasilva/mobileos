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
import wsilva.com.br.mobileos.entity.ValaVO;

public class ValaDAO extends BasicDAO<ValaVO>
{
	
	public static final String COL_ID="_id";
	public static final String COL_NUMEROOS="numeroos";
	public static final String COL_NUMEROVALA="numerovala";
	public static final String COL_IDLOCALOCORRENCIA="tipolocalocorrencia";
	public static final String COL_DESCRICAOLOCALOCORRENCIA="dslocalocorrencia";
	public static final String COL_IDPAVIMENTO="idpavimento";
	public static final String COL_DESCRICAOPAVIMENTO="dspavimento";
	public static final String COL_COMPRIMENTO="nncomprimento";
	public static final String COL_LARGURA="nnlargura";
	public static final String COL_PROFUNDIDADE="nnprofundidade";
	public static final String COL_INDICADORATERRO="icaterro";
	public static final String COL_INDICADORENTULHO="icentulho";
	public static final String COL_QUANTIDADEBAGS="qtbags";
	public static final String COL_INDICADORATERRADOPELAEQUIPE="icaterradoaequipe";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String COL_INDICADOR_FOTOVALAABERTA="icfotovalaaberta";
	public static final String COL_INDICADOR_FOTOVALAFECHADA="icfotovalafechada";
	public static final String TABLE_NAME="vala";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID 						+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_NUMEROOS 					+ " INTEGER, "
			+ COL_NUMEROVALA 				+ " INTEGER,"
			+ COL_IDLOCALOCORRENCIA 		+ " INTEGER,"
			+ COL_DESCRICAOLOCALOCORRENCIA 	+ " TEXT,"
			+ COL_IDPAVIMENTO 				+ "  INTEGER,"
			+ COL_DESCRICAOPAVIMENTO 		+  " TEXT,"
			+ COL_COMPRIMENTO 				+  " REAL,"
			+ COL_LARGURA					+ " REAL,"
			+ COL_PROFUNDIDADE 				+ "  REAL,"
			+ COL_INDICADORATERRO 			+ " INTEGER,"
			+ COL_INDICADORENTULHO 			+ " INTEGER,"
			+ COL_QUANTIDADEBAGS 				+ " INTEGER,"
			+ COL_INDICADORATERRADOPELAEQUIPE	+ " INTEGER,"
			+ COL_IDEQUIPEEXECUCAO 				+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 		+ " TEXT,"
			+ COL_INDICADORENVIO				+ " INTEGER,"
			+ COL_INDICADOR_FOTOVALAABERTA		+ " INTEGER,"
			+ COL_INDICADOR_FOTOVALAFECHADA		+ " INTEGER"
			+ ");";
			
	public ValaDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(ValaVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(ValaVO vo) 
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
	public List<ValaVO> listar() 
	{
		List<ValaVO> data = new ArrayList<ValaVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ValaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	//Listar somente as Valas referente a uma deteriminada OS
	public List<ValaVO> listarPorOrdemServico(int numeroOS) 
	{
		List<ValaVO> data = new ArrayList<ValaVO>();
		Cursor cursor = obterCursorPorOrdemServico(numeroOS);
		while (!cursor.isAfterLast())
		{
			ValaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ValaVO obterPorId(long id)
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
	public ContentValues obterContentValues(ValaVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_COMPRIMENTO, vo.getComprimento());
		values.put(COL_DESCRICAOLOCALOCORRENCIA, vo.getDescricaoLocalOcorrencia());
		values.put(COL_DESCRICAOPAVIMENTO, vo.getDescricaoPavimento());
		values.put(COL_IDLOCALOCORRENCIA, vo.getIdLocalOcorrencia());
		values.put(COL_IDPAVIMENTO, vo.getIdPavimento());
		values.put(COL_INDICADORATERRO, vo.getIndicadorAterro());
		values.put(COL_INDICADORENTULHO, vo.getIndicadorEntulho());
		values.put(COL_LARGURA, vo.getLargura());
		values.put(COL_NUMEROOS, vo.getNumeroOS());
		values.put(COL_NUMEROVALA, vo.getNumeroVala());
		values.put(COL_PROFUNDIDADE, vo.getProfundidade());
		values.put(COL_QUANTIDADEBAGS, vo.getQuantidadeBags());
		values.put(COL_INDICADORATERRADOPELAEQUIPE, vo.getIndicadorAterradoPelaEquipe());
		values.put(COL_IDEQUIPEEXECUCAO, vo.getIdEquipeExecucao());
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.getDescricaoEquipeExecucao());
		values.put(COL_INDICADORENVIO, vo.getIndicadorEnvio());
		values.put(COL_INDICADOR_FOTOVALAABERTA, vo.getIndicadorFotoValaAberta());
		values.put(COL_INDICADOR_FOTOVALAFECHADA, vo.getIndicadorFotoValaFechada());
		
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
	public ValaVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ValaVO vo= new ValaVO();
		vo.setComprimento(cursor.getDouble(cursor.getColumnIndex(COL_COMPRIMENTO)));
		vo.setDescricaoLocalOcorrencia(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOLOCALOCORRENCIA)));
		vo.setDescricaoPavimento(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOPAVIMENTO)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdLocalOcorrencia(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALOCORRENCIA)));
		vo.setIdPavimento(cursor.getInt(cursor.getColumnIndex(COL_IDPAVIMENTO)));
		vo.setIndicadorAterro(cursor.getInt(cursor.getColumnIndex(COL_INDICADORATERRO)));
		vo.setIndicadorEntulho(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENTULHO)));
		vo.setLargura(cursor.getDouble(cursor.getColumnIndex(COL_LARGURA)));
		vo.setNumeroOS(cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS)));
		vo.setNumeroVala(cursor.getInt(cursor.getColumnIndex(COL_NUMEROVALA)));
		vo.setProfundidade(cursor.getDouble(cursor.getColumnIndex(COL_PROFUNDIDADE)));
		vo.setQuantidadeBags(cursor.getInt(cursor.getColumnIndex(COL_QUANTIDADEBAGS)));
		vo.setIndicadorAterradoPelaEquipe(cursor.getInt(cursor.getColumnIndex(COL_INDICADORATERRADOPELAEQUIPE)));
		vo.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		vo.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		vo.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));
		vo.setIndicadorFotoValaAberta(cursor.getInt(cursor.getColumnIndex(COL_INDICADOR_FOTOVALAABERTA)));
		vo.setIndicadorFotoValaFechada(cursor.getInt(cursor.getColumnIndex(COL_INDICADOR_FOTOVALAFECHADA)));
		
		return vo;
	}
	
	@Override
	public String obterLinhaCSV(ValaVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null) 
		{
			linha=  delimitador
					+ String.valueOf(vo.getNumeroOS()) 					+ ";"
					+ String.valueOf(vo.getNumeroVala()) 				+ ";"
					+ String.valueOf(vo.getIdLocalOcorrencia()) 		+ ";"
					+ vo.getDescricaoLocalOcorrencia() 					+ ";"
					+ String.valueOf(vo.getIdPavimento()) 				+ ";"
					+ vo.getDescricaoPavimento() 						+ ";"
					+ Double.toString(vo.getComprimento()) 				+ ";"
					+ Double.toString(vo.getLargura()) 					+ ";"
					+ Double.toString(vo.getProfundidade()) 			+ ";"
					+ String.valueOf(vo.getIndicadorAterro()) 			+ ";"
					+ String.valueOf(vo.getIndicadorEntulho()) 			+ ";"
					+ String.valueOf(vo.getQuantidadeBags()) 			+ ";"
					+ String.valueOf(vo.getIndicadorAterradoPelaEquipe())+ ";"
					+ Integer.toString(vo.getIdEquipeExecucao()) 		+ ";"
					+ vo.getDescricaoEquipeExecucao()
					+ "\n";
		}
		return linha;
	}

	public int obterUltimoRegistro(int numeroOs)
	{
		int ultimoRegistro=0;
		Cursor cursor= rawQuery("select count(*) from " + TABLE_NAME 
				+ " where " + COL_NUMEROOS + "=" + String.valueOf(numeroOs), null);
		if (cursor!=null && cursor.getCount() > 0) {
			ultimoRegistro= (cursor.getInt(0) == 0 ? 1 : cursor.getInt(0)+1);
		} else {
			ultimoRegistro=1;
		}
		
		return ultimoRegistro;
	}
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<ValaVO> valas=listar();
		int qtd=valas.size();
		String linha="";

		if (qtd > 0) {
			try 
			{
				File sdCard = Environment.getExternalStorageDirectory();
				File directory= new File(sdCard.getAbsolutePath() + directoryname);
				File file = new File(directory, filename);
				FileOutputStream output= new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(output);
				
				for (int i=0; i<qtd; i++)
				{
					ValaVO vo=valas.get(i);
					linha=String.valueOf(vo.getNumeroOS()) 						+ ";"
							+ String.valueOf(vo.getNumeroVala()) 				+ ";"
							+ String.valueOf(vo.getIdLocalOcorrencia()) 		+ ";"
							+ vo.getDescricaoLocalOcorrencia() 					+ ";"
							+ String.valueOf(vo.getIdPavimento()) 				+ ";"
							+ vo.getDescricaoPavimento() 						+ ";"
							+ Double.toString(vo.getComprimento()) 				+ ";"
							+ Double.toString(vo.getLargura()) 					+ ";"
							+ Double.toString(vo.getProfundidade()) 			+ ";"
							+ String.valueOf(vo.getIndicadorAterro()) 			+ ";"
							+ String.valueOf(vo.getIndicadorEntulho()) 			+ ";"
							+ String.valueOf(vo.getQuantidadeBags()) 			+ ";"
							+ String.valueOf(vo.getIndicadorAterradoPelaEquipe()) + ";"
							+ Integer.toString(vo.getIdEquipeExecucao())   		+ ";"
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
