package wsilva.com.br.mobileos.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.OrdemServicoCorteVO;

public class OrdemServicoCorteDAO extends BasicDAO<OrdemServicoCorteVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDORDEMSERVICOCORTE="idoscorte";
	public static final String COL_IDORDEMSERVICO="idordemservico";
	public static final String COL_DATACORTE="dtcorte";
	public static final String COL_HORACORTE="tmcorte";
	public static final String COL_LEITURACORTE="nnleituracorte";
	public static final String COL_NUMEROSELO="nnselo";
	public static final String COL_IDFUNCIONARIO="idfuncionario";
	public static final String COL_NUMEROHIDROMETRO="nnhidrometro";
	public static final String COL_IDMOTIVOCORTE="idmotivocorte";
	public static final String COL_IDCORTETIPO="idcortetipo";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String TABLE_NAME="os_corte";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDORDEMSERVICOCORTE 		+ " INTEGER,"
			+ COL_IDORDEMSERVICO 			+ " INTEGER,"
			+ COL_DATACORTE 				+ " DATE,"
			+ COL_HORACORTE 				+ " TEXT,"
			+ COL_LEITURACORTE 				+ " INTEGER,"
			+ COL_NUMEROSELO 				+ " TEXT,"
			+ COL_IDFUNCIONARIO 			+ " TEXT,"
			+ COL_NUMEROHIDROMETRO 			+ " TEXT,"
			+ COL_IDMOTIVOCORTE 			+ " INTEGER,"
			+ COL_IDCORTETIPO				+ " INTEGER,"
			+ COL_IDEQUIPEEXECUCAO 			+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 	+ " TEXT,"
			+ COL_INDICADORENVIO			+ " INTEGER"
			+ ");";
	
	public OrdemServicoCorteDAO(Context context) 
	{
		super(context);
	}
	
	@Override
	public long inserir(OrdemServicoCorteVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(OrdemServicoCorteVO vo) 
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
	public List<OrdemServicoCorteVO> listar() 
	{
		List<OrdemServicoCorteVO> data = new ArrayList<OrdemServicoCorteVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			OrdemServicoCorteVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public OrdemServicoCorteVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public OrdemServicoCorteVO obterPorNumeroOs(int id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_IDORDEMSERVICO+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	@Override
	public ContentValues obterContentValues(OrdemServicoCorteVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDORDEMSERVICOCORTE, vo.getIdOrdemServicoCorte());
		values.put(COL_IDORDEMSERVICO, vo.getIdOrdemServico());
		if (vo.getDataCorte()!=null) {
			values.put(COL_DATACORTE, Util.dateToString("yyyy-MM-dd", vo.getDataCorte()));	
		}
		values.put(COL_HORACORTE, vo.getHoraCorte());
		values.put(COL_LEITURACORTE, vo.getLeituraCorte());
		values.put(COL_NUMEROSELO, vo.getNumeroSelo());
		values.put(COL_IDFUNCIONARIO, vo.getIdFuncionario());
		values.put(COL_NUMEROHIDROMETRO, vo.getNumeroHidrometro());
		values.put(COL_IDMOTIVOCORTE, vo.getIdMotivoCorte());
		values.put(COL_IDCORTETIPO, vo.getIdCorteTipo());
		values.put(COL_IDEQUIPEEXECUCAO, vo.getIdEquipeExecucao());
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.getDescricaoEquipeExecucao());
		values.put(COL_INDICADORENVIO, vo.getIndicadorEnvio());
		
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
	public OrdemServicoCorteVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoCorteVO vo = new OrdemServicoCorteVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdOrdemServicoCorte(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICOCORTE)));
		vo.setIdOrdemServico(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATACORTE)) !=null) {
			vo.setDataCorte(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATACORTE))));
		}
		vo.setHoraCorte(cursor.getString(cursor.getColumnIndex(COL_HORACORTE)));
		vo.setLeituraCorte(cursor.getInt(cursor.getColumnIndex(COL_LEITURACORTE)));
		vo.setNumeroSelo(cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO)));
		vo.setIdFuncionario(cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO)));
		vo.setNumeroHidrometro(cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO)));
		vo.setIdMotivoCorte(cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOCORTE)));
		vo.setIdCorteTipo(cursor.getInt(cursor.getColumnIndex(COL_IDCORTETIPO)));
		vo.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		vo.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		vo.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));
		
		return vo;
	}

	@Override
	public OrdemServicoCorteVO obterObject(String line) 
	{
		return super.obterObject(line);
	}

	@Override
	public OrdemServicoCorteVO obterObject(JSONObject line) 
	{
		return super.obterObject(line);
	}

	@Override
	public String obterLinhaCSV(OrdemServicoCorteVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null)
		{
			linha=  delimitador												
					+ Integer.toString(vo.getIdOrdemServicoCorte()) 		+ ";"
					+ Integer.toString(vo.getIdOrdemServico()) 				+ ";"
					+ Util.dateToString("dd/MM/yyyy", vo.getDataCorte()) 	+ ";"
					+ vo.getHoraCorte()										+ ";"
					+ Integer.toString(vo.getLeituraCorte()) 				+ ";"
					+ vo.getNumeroSelo()									+ ";"
					+ vo.getIdFuncionario() 								+ ";"
					+ vo.getNumeroHidrometro()								+ ";"
					+ Integer.toString(vo.getIdMotivoCorte()) 				+ ";"
					+ Integer.toString(vo.getIdCorteTipo())					+ ";"
					+ Integer.toString(vo.getIdEquipeExecucao()) 			+ ";"
					+ vo.getDescricaoEquipeExecucao()
					+ "\n";
		}
		return linha;
	}
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<OrdemServicoCorteVO> lista=listar();
		int qtd=lista.size();
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
					OrdemServicoCorteVO vo=lista.get(i);
					linha= obterLinhaCSV(vo, "");
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
