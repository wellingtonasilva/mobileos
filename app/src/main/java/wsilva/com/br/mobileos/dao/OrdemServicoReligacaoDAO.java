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
import wsilva.com.br.mobileos.entity.OrdemServicoReligacaoVO;

public class OrdemServicoReligacaoDAO extends BasicDAO<OrdemServicoReligacaoVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDORDEMSERVICORELIGACAO="idosreligacao";
	public static final String COL_DATARELIGACAO="dtreligacao";
	public static final String COL_HORARELIGACAO="tmreligacao";
	public static final String COL_IDFUNCIONARIO="idfuncionario";
	public static final String COL_NUMEROHIDROMETRO="nnhidrometro";
	public static final String COL_DATAINSTALACAOHIDROMETRO="dtinstalacaohm";
	public static final String COL_IDLOCALINSTALACAOHIDROMETRO="idlocalinstalacaohm";
	public static final String COL_IDPROTECAOHIDROMETRO="idprotecaohm";
	public static final String COL_LEITURAINSTALACAO="nnleitura";
	public static final String COL_NUMEROSELO="nnselo";
	public static final String COL_INDICADORCAVALETE="iccavalete";
	public static final String COL_IDORDEMSERVICO="idordemservico";
	public static final String COL_INDICADORTROCAREGISTRO="ictrocaregistro";
	public static final String COL_INDICADORTROCAPROTECAO="ictrocaprotecao";
	public static final String COL_IDTIPORELIGACAO="idtiporeligacao";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String TABLE_NAME="os_religacao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDORDEMSERVICORELIGACAO		+ " INTEGER,"
			+ COL_DATARELIGACAO					+ " DATE,"
			+ COL_HORARELIGACAO					+ " TEXT,"
			+ COL_IDFUNCIONARIO					+ " TEXT,"
			+ COL_NUMEROHIDROMETRO				+ " TEXT,"
			+ COL_DATAINSTALACAOHIDROMETRO		+ " DATE,"
			+ COL_IDLOCALINSTALACAOHIDROMETRO	+ " INTEGER,"
			+ COL_IDPROTECAOHIDROMETRO			+ " INTEGER,"
			+ COL_LEITURAINSTALACAO				+ " INTEGER,"
			+ COL_NUMEROSELO					+ " TEXT,"
			+ COL_INDICADORCAVALETE				+ " TEXT,"
			+ COL_IDORDEMSERVICO				+ " INTEGER,"
			+ COL_INDICADORTROCAREGISTRO		+ " INTEGER,"
			+ COL_INDICADORTROCAPROTECAO		+ " INTEGER,"
			+ COL_IDTIPORELIGACAO				+ " INTEGER,"
			+ COL_IDEQUIPEEXECUCAO 				+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 		+ " TEXT,"
			+ COL_INDICADORENVIO				+ " INTEGER"
			+ ");";
	
	
	public OrdemServicoReligacaoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(OrdemServicoReligacaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(OrdemServicoReligacaoVO vo) 
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
	public List<OrdemServicoReligacaoVO> listar() 
	{
		List<OrdemServicoReligacaoVO> data = new ArrayList<OrdemServicoReligacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			OrdemServicoReligacaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public OrdemServicoReligacaoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public OrdemServicoReligacaoVO obterPorNumeroOs(int id) 
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
	public ContentValues obterContentValues(OrdemServicoReligacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDORDEMSERVICORELIGACAO, vo.getIdOrdemServicoReligacao());
		if (vo.getDataReligacao()!=null){
			values.put(COL_DATARELIGACAO, Util.dateToString("yyyy-MM-dd", vo.getDataReligacao()));
		}
		values.put(COL_HORARELIGACAO, vo.getHoraReligacao());
		values.put(COL_IDFUNCIONARIO, vo.getIdFuncionario());
		values.put(COL_NUMEROHIDROMETRO, vo.getNumeroHidrometro());
		if (vo.getDataInstalacaoHidrometro()!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRO, Util.dateToString("yyyy-MM-dd", vo.getDataInstalacaoHidrometro()));	
		}
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO, vo.getIdLocalInstalacaoHidrometro());
		values.put(COL_IDPROTECAOHIDROMETRO, vo.getIdProtecaoHidrometro());
		values.put(COL_LEITURAINSTALACAO, vo.getLeituraInstalacao());
		values.put(COL_NUMEROSELO, vo.getNumeroSelo());
		values.put(COL_INDICADORCAVALETE, vo.getIndicadorCavalete());
		values.put(COL_IDORDEMSERVICO, vo.getIdOrdemServico());	
		values.put(COL_INDICADORTROCAPROTECAO, vo.getIndicadorTrocaProtecao());
		values.put(COL_INDICADORTROCAREGISTRO, vo.getIndicadorTrocaRegistro());
		values.put(COL_IDTIPORELIGACAO, vo.getIdTipoReligacao());
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
	public OrdemServicoReligacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoReligacaoVO vo = new OrdemServicoReligacaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdOrdemServicoReligacao(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICORELIGACAO)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATARELIGACAO))!=null) {
			vo.setDataReligacao(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATARELIGACAO))));	
		}
		vo.setHoraReligacao(cursor.getString(cursor.getColumnIndex(COL_HORARELIGACAO)));
		vo.setIdFuncionario(cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO)));
		vo.setNumeroHidrometro(cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))!=null){
			vo.setDataInstalacaoHidrometro(Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))));	
		}
		vo.setIdLocalInstalacaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO)));
		vo.setIdProtecaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO)));
		vo.setLeituraInstalacao(cursor.getInt(cursor.getColumnIndex(COL_LEITURAINSTALACAO)));
		vo.setNumeroSelo(cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO)));
		vo.setIndicadorCavalete(cursor.getString(cursor.getColumnIndex(COL_INDICADORCAVALETE)));
		vo.setIdOrdemServico(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO)));
		vo.setIndicadorTrocaProtecao(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAPROTECAO)));
		vo.setIndicadorTrocaRegistro(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAREGISTRO)));
		vo.setIdTipoReligacao(cursor.getInt(cursor.getColumnIndex(COL_IDTIPORELIGACAO)));
		vo.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		vo.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		vo.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));
		
		return vo;
	}

	@Override
	public OrdemServicoReligacaoVO obterObject(String line) 
	{
		return super.obterObject(line);
	}

	@Override
	public OrdemServicoReligacaoVO obterObject(JSONObject line) 
	{
		return super.obterObject(line);
	}

	@Override
	public String obterLinhaCSV(OrdemServicoReligacaoVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null)
		{
			linha= delimitador
				//Data Religa��o
				+ Util.dateToString("dd/MM/yyyy",vo.getDataReligacao())				+ ";"
				//Hora da Religa��o
				+ vo.getHoraReligacao()												+ ";"
				//Funcion�rio Respons�vel
				+ vo.getIdFuncionario()												+ ";"
				//N�mero do Hidr�metro
				+ vo.getNumeroHidrometro()											+ ";"
				//Data Instala�ao do Hidr�metro
				+ Util.dateToString("dd/MM/yyyy", vo.getDataInstalacaoHidrometro()) + ";"
				//Local da Instala��o do Hidr�metro
				+ Integer.toString(vo.getIdLocalInstalacaoHidrometro())				+ ";"
				//Tipo Prote��o do Hidr�metro
				+ Integer.toString(vo.getIdProtecaoHidrometro())					+ ";"
				//Leitura na Instala��o
				+ Integer.toString(vo.getLeituraInstalacao())						+ ";"
				//N�mero do Selo
				+ vo.getNumeroSelo()												+ ";"
				//Com/Sem cavalete
				+ vo.getIndicadorCavalete()											+ ";"
				//N�mero do Servi�o
				+ Integer.toString(vo.getIdOrdemServico())							+ ";"
				//Indicador de Troca de Prote��o
				+ Integer.toString(vo.getIndicadorTrocaProtecao())					+ ";"
				//Indicador de Troca de Registro
				+ Integer.toString(vo.getIndicadorTrocaRegistro())					+ ";"
				//Tipo de Religacao
				+ Integer.toString(vo.getIdTipoReligacao())							+ ";"
				//Id. Equipe Execu��o
				+ Integer.toString(vo.getIdEquipeExecucao()) 						+ ";"
				//Descrdi��o da Equipe Execu��o
				+ vo.getDescricaoEquipeExecucao()
				+ "\n";
		}
		return linha;
	}
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<OrdemServicoReligacaoVO> lista=listar();
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
					OrdemServicoReligacaoVO vo=lista.get(i);
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
