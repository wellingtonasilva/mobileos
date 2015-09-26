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
import wsilva.com.br.mobileos.entity.OrdemServicoHidrometroInstalacaoVO;

public class OrdemServicoHidrometroInstalacaoDAO extends
		BasicDAO<OrdemServicoHidrometroInstalacaoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDORDEMSERVICOINSTALACAOHM="idosinstalacaohm";
	public static final String COL_NUMEROHIDROMETRO="nnhidrometro";
	public static final String COL_DATAINSTALACAOHIDROMETRO="dtinstalacaohm";
	public static final String COL_IDTIPOMEDICAO="idtipomedicao";
	public static final String COL_IDLOCALINSTALACAOHIDROMETRO="idlocalinstalacaohm";
	public static final String COL_IDPROTECAOHIDROMETRO="idprotecaohm";
	public static final String COL_IDORDEMSERVICO="idordemservico";
	public static final String COL_INDICADORTROCAPROTECAO="ictrocaprotecao";
	public static final String COL_INDICADORTROCAREGISTRO="ictrocaregistro";
	public static final String COL_LEITURAINSTALACAO="nnleiturainstalacao";
	public static final String COL_NUMEROSELO="nnselo";
	public static final String COL_INDCADORCAVALETE="iccavalete";
	public static final String COL_IDFUNCIONARIO="idfuncionario";
	public static final String COL_IDTIPOINSTALACAOHM="idtipoinstalacaohm";
	public static final String COL_HORAINSTALACAOHIDROMETRO="horainstalacaohm";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String TABLE_NAME="os_hm_instalacao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDORDEMSERVICOINSTALACAOHM		+ " INTEGER,"
			+ COL_NUMEROHIDROMETRO					+ " TEXT,"
			+ COL_DATAINSTALACAOHIDROMETRO			+ " DATE,"
			+ COL_IDTIPOMEDICAO						+ " TEXT,"
			+ COL_IDLOCALINSTALACAOHIDROMETRO		+ " INTEGER,"
			+ COL_IDPROTECAOHIDROMETRO				+ " INTEGER,"
			+ COL_IDORDEMSERVICO					+ " INTEGER,"
			+ COL_INDICADORTROCAPROTECAO			+ " INTEGER,"
			+ COL_INDICADORTROCAREGISTRO			+ " INTEGER,"
			+ COL_LEITURAINSTALACAO					+ " INTEGER,"
			+ COL_NUMEROSELO						+ " TEXT,"
			+ COL_INDCADORCAVALETE					+ " TEXT,"
			+ COL_IDFUNCIONARIO						+ " TEXT,"	
			+ COL_IDTIPOINSTALACAOHM				+ " INTEGER,"
			+ COL_HORAINSTALACAOHIDROMETRO			+ " TEXT,"
			+ COL_IDEQUIPEEXECUCAO 					+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 			+ " TEXT,"
			+ COL_INDICADORENVIO					+ " INTEGER"
			+ ");";
	
	
	public OrdemServicoHidrometroInstalacaoDAO(Context context) {
		super(context);
	}
	

	@Override
	public long inserir(OrdemServicoHidrometroInstalacaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(OrdemServicoHidrometroInstalacaoVO vo) 
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
	public List<OrdemServicoHidrometroInstalacaoVO> listar() 
	{
		List<OrdemServicoHidrometroInstalacaoVO> data = new ArrayList<OrdemServicoHidrometroInstalacaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			OrdemServicoHidrometroInstalacaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public OrdemServicoHidrometroInstalacaoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public OrdemServicoHidrometroInstalacaoVO obterPorNumeroOs(int id) 
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
	public ContentValues obterContentValues(OrdemServicoHidrometroInstalacaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDORDEMSERVICOINSTALACAOHM, vo.getIdOrdemServicoInstalacaoHM());
		values.put(COL_NUMEROHIDROMETRO, vo.getNumeroHidrometro());
		if (vo.getDataInstalacaoHidrometro()!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRO,  Util.dateToString("yyyy-MM-dd", vo.getDataInstalacaoHidrometro()));
		}
		values.put(COL_IDTIPOMEDICAO, vo.getIdTipoMedicao());
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO, vo.getIdLocalInstalacaoHidrometro());
		values.put(COL_IDPROTECAOHIDROMETRO, vo.getIdProtecaoHidrometro());
		values.put(COL_IDORDEMSERVICO, vo.getIdOrdemServico());
		values.put(COL_INDICADORTROCAPROTECAO, vo.getIndicadorTrocaProtecao());
		values.put(COL_INDICADORTROCAREGISTRO, vo.getIndicadorTrocaRegistro());
		values.put(COL_LEITURAINSTALACAO, vo.getLeituraInstalacao());
		values.put(COL_NUMEROSELO, vo.getNumeroSelo());
		values.put(COL_INDCADORCAVALETE, vo.getIndcadorCavalete());
		values.put(COL_IDFUNCIONARIO, vo.getIdFuncionario());
		values.put(COL_IDTIPOINSTALACAOHM, vo.getIdTipoInstalacaoHM());
		values.put(COL_HORAINSTALACAOHIDROMETRO, vo.getHoraInstalacaoHidrometro());
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
	public OrdemServicoHidrometroInstalacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoHidrometroInstalacaoVO vo = new OrdemServicoHidrometroInstalacaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdOrdemServicoInstalacaoHM(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICOINSTALACAOHM)));
		vo.setNumeroHidrometro(cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))!=null) {
			vo.setDataInstalacaoHidrometro(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))));
		}
		vo.setIdTipoMedicao(cursor.getString(cursor.getColumnIndex(COL_IDTIPOMEDICAO)));
		vo.setIdLocalInstalacaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO)));
		vo.setIdProtecaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO)));
		vo.setIdOrdemServico(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO)));
		vo.setIndicadorTrocaProtecao(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAPROTECAO)));
		vo.setIndicadorTrocaRegistro(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAREGISTRO)));
		vo.setLeituraInstalacao(cursor.getInt(cursor.getColumnIndex(COL_LEITURAINSTALACAO)));
		vo.setNumeroSelo(cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO)));
		vo.setIndcadorCavalete(cursor.getString(cursor.getColumnIndex(COL_INDCADORCAVALETE)));
		vo.setIdFuncionario(cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO)));
		vo.setIdTipoInstalacaoHM(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOINSTALACAOHM)));
		vo.setHoraInstalacaoHidrometro(cursor.getString(cursor.getColumnIndex(COL_HORAINSTALACAOHIDROMETRO)));
		vo.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		vo.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		vo.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));
		
		return vo;
	}

	@Override
	public OrdemServicoHidrometroInstalacaoVO obterObject(String line) 
	{
		return super.obterObject(line);
	}

	@Override
	public OrdemServicoHidrometroInstalacaoVO obterObject(JSONObject line) 
	{
		return super.obterObject(line);
	}


	@Override
	public String obterLinhaCSV(OrdemServicoHidrometroInstalacaoVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null) 
		{
			linha= delimitador 
				//N�mero do Hidr�metro
				+ vo.getNumeroHidrometro()								 				+ ";"
				//Data da Instala��o
				+ Util.dateToString("dd/MM/yyyy",vo.getDataInstalacaoHidrometro()) 		+ ";"
				//Tipo de Medi��o (�gua ou Po�o)
				+ vo.getIdTipoMedicao()													+ ";"
				//Local da Instala��o do Hidr�metro
				+ Integer.toString(vo.getIdLocalInstalacaoHidrometro())					+ ";"
				//Tipo de Prote��o
				+ Integer.toString(vo.getIdProtecaoHidrometro())						+ ";"
				//N�mero da Ordem de Servi�o
				+ Integer.toString(vo.getIdOrdemServico())								+ ";"
				//Indicador de Troca de Protec��o
				+ Integer.toString(vo.getIndicadorTrocaProtecao())						+ ";"
				//Indicador de Troca de Registro
				+ Integer.toString(vo.getIndicadorTrocaRegistro())						+ ";"
				//Leitura
				+ Integer.toString(vo.getLeituraInstalacao())							+ ";"
				//Selo
				+ vo.getNumeroSelo()													+ ";"
				//Indicador Com ou Sem Cavalete
				+ vo.getIndcadorCavalete()												+ ";"
				//Funcion�rio Respons�vel
				+ vo.getIdFuncionario()													+ ";"
				//Tipo de Instala��o do Hidr�metro
				+ Integer.toString(vo.getIdTipoInstalacaoHM())							+ ";"
				//Hora da Instala��o do Hidr�metro
				+ vo.getHoraInstalacaoHidrometro()										+ ";"
				//Id. Equipe Execuc�o
				+ Integer.toString(vo.getIdEquipeExecucao())							+ ";"
				//Nome da Equipe
				+ vo.getDescricaoEquipeExecucao()								
				+ "\n";
		}
		return linha;
	}
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<OrdemServicoHidrometroInstalacaoVO> lista=listar();
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
					OrdemServicoHidrometroInstalacaoVO vo=lista.get(i);
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
