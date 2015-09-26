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
import wsilva.com.br.mobileos.entity.OrdemServicoHidrometroSubstituicaoVO;

public class OrdemServicoHidrometroSubstituicaoDAO extends
		BasicDAO<OrdemServicoHidrometroSubstituicaoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDORDEMSERVICOSUBSTITUICAOHM="idossubsthm";
	public static final String COL_NUMERHIDROMETROATUAL="nnhidrometroatual";
	public static final String COL_INDICADORTIPOMEDICAOATUAL="ictipomedicaoatual";
	public static final String COL_DATARETIRADA="dtretirada";
	public static final String COL_LEITURARETIRADA="nnleituraretirada";
	public static final String COL_IDSITUACAOHIDROMETRO="idsituacaohm";
	public static final String COL_IDLOCALARMAZENAGEMHIDROMETRO="idlocalarmazenagem";
	public static final String COL_NUMEROHIDROMETRONOVO="nnhidrometronovo";
	public static final String COL_DATAINSTALACAOHIDROMETRONOVO="dtinstalacaohmnovo";
	public static final String COL_INDICADORTIPOMEDICAO="ictipomedicao";
	public static final String COL_IDLOCALINSTALACAOHIDROMETRO="idlocalinstalacaohm";
	public static final String COL_IDPROTECAOHIDROMETRO="idprotecaohm";
	public static final String COL_INDICADORTROCAPROTECAO="ictrocaprotecao";
	public static final String COL_INDICADORTROCAREGISTRO="ictrocaregistro";
	public static final String COL_LEITURAINSTALACAO="nnleiturainstalacao";
	public static final String COL_NUMEROSELO="nnselo";
	public static final String COL_INDICADORCAVALETE="iccavalete";
	public static final String COL_IDFUNCIONARIO="idfuncionario";
	public static final String COL_IDORDEMSERVICO="idordemservico";
	public static final String COL_IDTIPOSUBSTITUICAOHM="idtiposubstituicaohm";
	public static final String COL_HORAINSTALACAOHIDROMETRONOVO="horainstalacaohidrometronovo";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String TABLE_NAME="os_hm_substituicao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDORDEMSERVICOSUBSTITUICAOHM	+ " INTEGER,"
			+ COL_NUMERHIDROMETROATUAL			+ " TEXT,"
			+ COL_INDICADORTIPOMEDICAOATUAL		+ " TEXT,"
			+ COL_DATARETIRADA					+ " DATE,"
			+ COL_LEITURARETIRADA				+ " INTEGER,"
			+ COL_IDSITUACAOHIDROMETRO			+ " INTEGER,"
			+ COL_IDLOCALARMAZENAGEMHIDROMETRO	+ " INTEGER,"
			+ COL_NUMEROHIDROMETRONOVO			+ " TEXT,"
			+ COL_DATAINSTALACAOHIDROMETRONOVO	+ " DATE,"
			+ COL_INDICADORTIPOMEDICAO			+ " TEXT,"
			+ COL_IDLOCALINSTALACAOHIDROMETRO	+ " INTEGER,"
			+ COL_IDPROTECAOHIDROMETRO			+ " INTEGER,"
			+ COL_INDICADORTROCAPROTECAO		+ " INTEGER,"
			+ COL_INDICADORTROCAREGISTRO		+ " INTEGER,"
			+ COL_LEITURAINSTALACAO				+ " INTEGER,"
			+ COL_NUMEROSELO					+ " TEXT,"
			+ COL_INDICADORCAVALETE				+ " TEXT,"
			+ COL_IDFUNCIONARIO					+ " TEXT,"
			+ COL_IDORDEMSERVICO				+ " INTEGER,"
			+ COL_IDTIPOSUBSTITUICAOHM			+ " INTEGER,"
			+ COL_HORAINSTALACAOHIDROMETRONOVO	+ " TEXT,"
			+ COL_IDEQUIPEEXECUCAO 				+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 		+ " TEXT,"
			+ COL_INDICADORENVIO				+ " INTEGER"
			+ ");";
	
	
	public OrdemServicoHidrometroSubstituicaoDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(OrdemServicoHidrometroSubstituicaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(OrdemServicoHidrometroSubstituicaoVO vo) 
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
	public List<OrdemServicoHidrometroSubstituicaoVO> listar() 
	{
		List<OrdemServicoHidrometroSubstituicaoVO> data = new ArrayList<OrdemServicoHidrometroSubstituicaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			OrdemServicoHidrometroSubstituicaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public OrdemServicoHidrometroSubstituicaoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public OrdemServicoHidrometroSubstituicaoVO obterPorNumeroOs(int id) 
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
	public ContentValues obterContentValues(OrdemServicoHidrometroSubstituicaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDORDEMSERVICOSUBSTITUICAOHM,vo.getIdOrdemServicoSubstituicaoHM());
		values.put(COL_NUMERHIDROMETROATUAL,vo.getNumerHidrometroAtual());
		values.put(COL_INDICADORTIPOMEDICAOATUAL,vo.getIndicadorTipoMedicaoAtual());
		if (vo.getDataRetirada()!=null){
			values.put(COL_DATARETIRADA, Util.dateToString("yyyy-MM-dd", vo.getDataRetirada()));
		}
		values.put(COL_LEITURARETIRADA, vo.getLeituraRetirada());
		values.put(COL_IDSITUACAOHIDROMETRO,vo.getIdSituacaoHidrometro());
		values.put(COL_IDLOCALARMAZENAGEMHIDROMETRO,vo.getIdLocalArmazenagemHidrometro());
		values.put(COL_NUMEROHIDROMETRONOVO,vo.getNumeroHidrometroNovo());
		if (vo.getDataInstalacaoHidrometroNovo()!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRONOVO, Util.dateToString("yyyy-MM-dd", vo.getDataInstalacaoHidrometroNovo()));	
		}
		values.put(COL_INDICADORTIPOMEDICAO,vo.getIndicadorTipoMedicao());
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO,vo.getIdLocalInstalacaoHidrometro());
		values.put(COL_IDPROTECAOHIDROMETRO,vo.getIdProtecaoHidrometro());
		values.put(COL_INDICADORTROCAPROTECAO,vo.getIndicadorTrocaProtecao());
		values.put(COL_INDICADORTROCAREGISTRO,vo.getIndicadorTrocaRegistro());
		values.put(COL_LEITURAINSTALACAO,vo.getLeituraInstalacao());
		values.put(COL_NUMEROSELO,vo.getNumeroSelo());
		values.put(COL_INDICADORCAVALETE,vo.getIndicadorCavalete());
		values.put(COL_IDFUNCIONARIO,vo.getIdFuncionario());
		values.put(COL_IDORDEMSERVICO,vo.getIdOrdemServico());
		values.put(COL_IDTIPOSUBSTITUICAOHM,vo.getIdTipoSubstituicaoHM());
		values.put(COL_HORAINSTALACAOHIDROMETRONOVO,vo.getHoraInstalacaoHidrometroNovo());
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
	public OrdemServicoHidrometroSubstituicaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoHidrometroSubstituicaoVO vo = new OrdemServicoHidrometroSubstituicaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdOrdemServicoSubstituicaoHM(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICOSUBSTITUICAOHM)));
		vo.setNumerHidrometroAtual(cursor.getString(cursor.getColumnIndex(COL_NUMERHIDROMETROATUAL)));
		vo.setIndicadorTipoMedicaoAtual(cursor.getString(cursor.getColumnIndex(COL_INDICADORTIPOMEDICAOATUAL)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATARETIRADA))!=null) {
			vo.setDataRetirada(Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATARETIRADA))));	
		}
		vo.setLeituraRetirada(cursor.getInt(cursor.getColumnIndex(COL_LEITURARETIRADA)));
		vo.setIdSituacaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOHIDROMETRO)));
		vo.setIdLocalArmazenagemHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALARMAZENAGEMHIDROMETRO)));
		vo.setNumeroHidrometroNovo(cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRONOVO)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRONOVO))!=null){
			vo.setDataInstalacaoHidrometroNovo(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRONOVO))));
		}
		vo.setIndicadorTipoMedicao(cursor.getString(cursor.getColumnIndex(COL_INDICADORTIPOMEDICAO)));
		vo.setIdLocalInstalacaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO)));
		vo.setIdProtecaoHidrometro(cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO)));
		vo.setIndicadorTrocaProtecao(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAPROTECAO)));
		vo.setIndicadorTrocaRegistro(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAREGISTRO)));
		vo.setLeituraInstalacao(cursor.getInt(cursor.getColumnIndex(COL_LEITURAINSTALACAO)));
		vo.setNumeroSelo(cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO)));
		vo.setIndicadorCavalete(cursor.getString(cursor.getColumnIndex(COL_INDICADORCAVALETE)));
		vo.setIdFuncionario(cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO)));
		vo.setIdOrdemServico(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO)));
		vo.setIdTipoSubstituicaoHM(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSUBSTITUICAOHM)));
		vo.setHoraInstalacaoHidrometroNovo(cursor.getString(cursor.getColumnIndex(COL_HORAINSTALACAOHIDROMETRONOVO)));
		vo.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		vo.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		vo.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));

		return vo;
	}

	@Override
	public OrdemServicoHidrometroSubstituicaoVO obterObject(String line) 
	{
		return super.obterObject(line);
	}

	@Override
	public OrdemServicoHidrometroSubstituicaoVO obterObject(JSONObject line) 
	{
		return super.obterObject(line);
	}

	@Override
	public String obterLinhaCSV(OrdemServicoHidrometroSubstituicaoVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null) 
		{
			linha= delimitador
				//N�mero do Hidr�metro Atual
				+ vo.getNumerHidrometroAtual()											+ ";"
				//Tipo de Medi��o Atual (�Gua/Po�o)
				+ vo.getIndicadorTipoMedicaoAtual()										+ ";"
				//Data da Retirada do Hidr�metro
				+ Util.dateToString("dd/MM/yyyy", vo.getDataRetirada())					+ ";"
				//N�mero da Leitura na REtirada
				+ Integer.toString(vo.getLeituraRetirada())								+ ";"
				//Situa��o do Hidr�metro Retirado
				+ Integer.toString(vo.getIdSituacaoHidrometro())						+ ";"
				//Local da Armazenagem
				+ Integer.toString(vo.getIdLocalArmazenagemHidrometro())				+ ";"
				//N�mero do Hidr�metro Novo
				+ vo.getNumeroHidrometroNovo()											+ ";"
				//Data da Instala��o
				+ Util.dateToString("dd/MM/yyyy", vo.getDataInstalacaoHidrometroNovo())	+ ";"
				//Tipo Medi��o novo (�gua/Po�o)
				+ vo.getIndicadorTipoMedicao()											+ ";"
				//Local da Instala��o do Hidr�metro
				+ Integer.toString(vo.getIdLocalInstalacaoHidrometro())					+ ";"
				//Tipo de Prote��o do Hidr�metro
				+ Integer.toString(vo.getIdProtecaoHidrometro())						+ ";"
				//Indicador de Troca de Prote��o
				+ Integer.toString(vo.getIndicadorTrocaProtecao())						+ ";"
				//Indicador de Troca de Registro
				+ Integer.toString(vo.getIndicadorTrocaRegistro())						+ ";"
				//N�mero da Leitura na Instala��o
				+ Integer.toString(vo.getLeituraInstalacao())							+ ";"
				//N�mero do Selo
				+ vo.getNumeroSelo()													+ ";"
				//Indicador Com ou Sem Cavalete
				+ vo.getIndicadorCavalete()												+ ";"
				//Funcion�rio Respons�vel
				+ vo.getIdFuncionario()													+ ";"
				//N�mero da OS
				+ Integer.toString(vo.getIdOrdemServico())								+ ";"
				//Tipo de Substitui��o
				+ Integer.toString(vo.getIdTipoSubstituicaoHM())						+ ";"
				//Hora da Substitui��o
				+ vo.getHoraInstalacaoHidrometroNovo()									+ ";"
				//Id. Equipe Execu��o
				+ Integer.toString(vo.getIdEquipeExecucao())							+ ";"
				//Descri��o da Equipe Execu��o
				+ vo.getDescricaoEquipeExecucao()
				+ "\n";
		}
		return linha;
	}
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<OrdemServicoHidrometroSubstituicaoVO> lista=listar();
		int qtd=lista.size();
		String linha="";

		if (qtd>0){
			try 
			{
				File sdCard = Environment.getExternalStorageDirectory();
				File directory= new File(sdCard.getAbsolutePath() + directoryname);
				File file = new File(directory, filename);
				FileOutputStream output= new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(output);
				
				for (int i=0; i<qtd; i++)
				{
					OrdemServicoHidrometroSubstituicaoVO vo=lista.get(i);
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
