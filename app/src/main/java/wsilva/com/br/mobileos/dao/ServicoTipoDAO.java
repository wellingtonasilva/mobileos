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
import wsilva.com.br.mobileos.entity.ServicoTipoVO;

public class ServicoTipoDAO extends BasicDAO<ServicoTipoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDSERVICOTIPO="idservicotipo";
	public static final String COL_DESCRICAOSERVICOTIPO="dssevicotipo";
	public static final String COL_IDSUBGRUPO="idsubgrupo";
	public static final String COL_INDICADORATUALIZACAOCOMERCIAL="icatualizacaocomercial";
	public static final String COL_INDICADORPAVIMENTO="icpavimento";
	public static final String COL_INDICADORSERVICOTERCEIRIZADO="icservicoterceirizado";
	public static final String COL_VALORSERVICO="vlservico"; 
	public static final String COL_TEMPOMEDIOEXECUCAO="nntempomedioexecucao";
	public static final String COL_IDDEBITOTIPO="iddebitotipo";
	public static final String COL_IDCREDITOTIPO="idcreditotipo";
	public static final String COL_IDPRIORIDADE="idprioridade";
	public static final String COL_IDPERFILTIPOSERVICO="idperfiltiposervico";
	public static final String COL_IDTIPOSERVICOREFERENCIA="idtiposervicoreferencia";
	public static final String COL_INDICADORVISTORIA="icvistoria";
	public static final String COL_INDICADORFISCALIZACAOINFRACAO="icfiscalizacaoinfracao";
	public static final String COL_VALORREMUNERACAO="vlremuneracao";
	public static final String COL_PERCENTUALREMUNERACAO="percentualremuneracao";
	public static final String COL_PRAZOEXECUCAOSERVICO="prazoexecucaoservico";
	public static final String COL_INDICADORTIPOREMUNERACAO="ictiporemuneracao";
	public static final String COL_INDICADORINFORMARDESLOCAMENTO="icinformardeslocamento";
	public static final String COL_INDICADORINFORMARHORARIOEXECUCAO="icinformarhorarioexecucao";
	public static final String COL_INDICADORINFORMARCAUSAVAZAMENTO="icinformarcausavazamento";
	public static final String COL_INDICADORINFORMARREDERAMAL="icinformarrederamal";
	public static final String COL_INDICADORINFORMARREDERAMALESGOTO="icinformarrederamalesgoto";
	public static final String COL_INDICADORINFORMARMATERIAL="icinformarmaterial";
	public static final String COL_INDICADORINFOMARVALA="icinfomarvala";
	public static final String COL_INDICADORORDEMSELETIVA="icordemseletiva";
	public static final String COL_INDICADORSERVICOCRITICO="icservicocritico";
	public static final String COL_INDICADORATIVIDADEUNICA="icatividadeunica";
	
	
	public static final String TABLE_NAME="servicotipo";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID 								+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDSERVICOTIPO 					+ " INTEGER,"
			+ COL_DESCRICAOSERVICOTIPO 				+ " TEXT,"
			+ COL_IDSUBGRUPO 						+ " INTEGER,"
			+ COL_INDICADORATUALIZACAOCOMERCIAL 	+ " INTEGER,"
			+ COL_INDICADORPAVIMENTO				+ " INTEGER,"
			+ COL_INDICADORSERVICOTERCEIRIZADO		+ " INTEGER,"
			+ COL_VALORSERVICO						+ " FLOAT," 
			+ COL_TEMPOMEDIOEXECUCAO				+ " FLOAT,"
			+ COL_IDDEBITOTIPO						+ " INTEGER,"
			+ COL_IDCREDITOTIPO						+ " INTEGER,"
			+ COL_IDPRIORIDADE						+ " INTEGER,"
			+ COL_IDPERFILTIPOSERVICO				+ " INTEGER,"	
			+ COL_IDTIPOSERVICOREFERENCIA			+ " INTEGER,"
			+ COL_INDICADORVISTORIA					+ " INTEGER,"
			+ COL_INDICADORFISCALIZACAOINFRACAO		+ " INTEGER,"
			+ COL_VALORREMUNERACAO					+ " FLOAT,"
			+ COL_PERCENTUALREMUNERACAO				+ " FLOAT,"
			+ COL_PRAZOEXECUCAOSERVICO				+ " FLOAT,"
			+ COL_INDICADORTIPOREMUNERACAO			+ " INTEGER,"
			+ COL_INDICADORINFORMARDESLOCAMENTO 	+ " INTEGER,"
			+ COL_INDICADORINFORMARHORARIOEXECUCAO	+ " INTEGER,"
			+ COL_INDICADORINFORMARCAUSAVAZAMENTO	+ " INTEGER,"
			+ COL_INDICADORINFORMARREDERAMAL		+ " INTEGER,"
			+ COL_INDICADORINFORMARREDERAMALESGOTO	+ " INTEGER,"
			+ COL_INDICADORINFORMARMATERIAL			+ " INTEGER,"
			+ COL_INDICADORINFOMARVALA				+ " INTEGER,"
			+ COL_INDICADORORDEMSELETIVA			+ " INTEGER,"
			+ COL_INDICADORSERVICOCRITICO			+ " INTEGER,"
			+ COL_INDICADORATIVIDADEUNICA			+ " INTEGER"
			+ ");";
		
	public ServicoTipoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(ServicoTipoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(ServicoTipoVO vo) 
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
	public List<ServicoTipoVO> listar() 
	{
		List<ServicoTipoVO> data = new ArrayList<ServicoTipoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ServicoTipoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ServicoTipoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ServicoTipoVO vo) 
	{
		ContentValues values= new ContentValues();
		values.put(COL_DESCRICAOSERVICOTIPO, vo.getDescricaoServicoTipo());
		values.put(COL_IDSERVICOTIPO, vo.getIdServicoTipo());
		values.put(COL_IDSUBGRUPO, vo.getIdSubgrupo()); 						
		values.put(COL_INDICADORATUALIZACAOCOMERCIAL, vo.getIndicadorAtualizacaoComercial()); 	
		values.put(COL_INDICADORPAVIMENTO, vo.getIndicadorPavimento());				
		values.put(COL_INDICADORSERVICOTERCEIRIZADO, vo.getIndicadorServicoTerceirizado());		
		values.put(COL_VALORSERVICO, vo.getValorServico());						 
		values.put(COL_TEMPOMEDIOEXECUCAO, vo.getTempoMedioExecucao());				
		values.put(COL_IDDEBITOTIPO, vo.getIdDebitoTipo());						
		values.put(COL_IDCREDITOTIPO, vo.getIdCreditoTipo());						
		values.put(COL_IDPRIORIDADE, vo.getIdPrioridade());						
		values.put(COL_IDPERFILTIPOSERVICO,  vo.getIdPerfilTipoServico());					
		values.put(COL_IDTIPOSERVICOREFERENCIA, vo.getIdTipoServicoReferencia());			
		values.put(COL_INDICADORVISTORIA, vo.getIndicadorVistoria());					
		values.put(COL_INDICADORFISCALIZACAOINFRACAO, vo.getIndicadorFiscalizacaoInfracao());		
		values.put(COL_VALORREMUNERACAO, vo.getValorRemuneracao());					
		values.put(COL_PERCENTUALREMUNERACAO, vo.getPercentualRemuneracao());				
		values.put(COL_PRAZOEXECUCAOSERVICO, vo.getPrazoexEcucaoServico());				
		values.put(COL_INDICADORTIPOREMUNERACAO, vo.getIndicadorTipoRemuneracao());			
		values.put(COL_INDICADORINFORMARDESLOCAMENTO, vo.getIndicadorInformarDeslocamento()); 	
		values.put(COL_INDICADORINFORMARHORARIOEXECUCAO, vo.getIndicadorInformarHorarioExecucao());	
		values.put(COL_INDICADORINFORMARCAUSAVAZAMENTO, vo.getIndicadorInformarCausaVazamento());	
		values.put(COL_INDICADORINFORMARREDERAMAL, vo.getIndicadorInformarRedeRamal());		
		values.put(COL_INDICADORINFORMARREDERAMALESGOTO, vo.getIndicadorInformarRedeRamalEsgoto());	
		values.put(COL_INDICADORINFORMARMATERIAL, vo.getIndicadorInformarMaterial());			
		values.put(COL_INDICADORINFOMARVALA, vo.getIndicadorInfomarVala());				
		values.put(COL_INDICADORORDEMSELETIVA, vo.getIndicadorOrdemSeletiva());			
		values.put(COL_INDICADORSERVICOCRITICO, vo.getIndicadorServicoCritico());			
		values.put(COL_INDICADORATIVIDADEUNICA, vo.getIndicadorAtividadeUnica());			

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
	public ServicoTipoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ServicoTipoVO vo =new ServicoTipoVO();
		vo.setDescricaoServicoTipo(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSERVICOTIPO)));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdServicoTipo(cursor.getInt(cursor.getColumnIndex(COL_IDSERVICOTIPO)));
		vo.setIdSubgrupo(cursor.getInt(cursor.getColumnIndex(COL_IDSUBGRUPO)));
		vo.setIndicadorAtualizacaoComercial(cursor.getInt(cursor.getColumnIndex(COL_INDICADORATUALIZACAOCOMERCIAL)));
		vo.setIndicadorPavimento(cursor.getInt(cursor.getColumnIndex(COL_INDICADORPAVIMENTO)));
		vo.setIndicadorServicoTerceirizado(cursor.getInt(cursor.getColumnIndex(COL_INDICADORSERVICOTERCEIRIZADO)));
		vo.setValorServico(cursor.getFloat(cursor.getColumnIndex(COL_VALORSERVICO)));
		vo.setTempoMedioExecucao(cursor.getFloat(cursor.getColumnIndex(COL_TEMPOMEDIOEXECUCAO)));
		vo.setIdDebitoTipo(cursor.getInt(cursor.getColumnIndex(COL_IDDEBITOTIPO)));
		vo.setIdCreditoTipo(cursor.getInt(cursor.getColumnIndex(COL_IDCREDITOTIPO)));
		vo.setIdPrioridade(cursor.getInt(cursor.getColumnIndex(COL_IDPRIORIDADE)));
		vo.setIdPerfilTipoServico(cursor.getInt(cursor.getColumnIndex(COL_IDPERFILTIPOSERVICO)));
		vo.setIdTipoServicoReferencia(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICOREFERENCIA)));
		vo.setIndicadorVistoria(cursor.getInt(cursor.getColumnIndex(COL_INDICADORVISTORIA)));
		vo.setIndicadorFiscalizacaoInfracao(cursor.getInt(cursor.getColumnIndex(COL_INDICADORFISCALIZACAOINFRACAO)));
		vo.setValorRemuneracao(cursor.getFloat(cursor.getColumnIndex(COL_VALORREMUNERACAO)));
		vo.setPercentualRemuneracao(cursor.getFloat(cursor.getColumnIndex(COL_PERCENTUALREMUNERACAO)));
		vo.setPrazoexEcucaoServico(cursor.getFloat(cursor.getColumnIndex(COL_PRAZOEXECUCAOSERVICO)));
		vo.setIndicadorTipoRemuneracao(cursor.getInt(cursor.getColumnIndex(COL_INDICADORTIPOREMUNERACAO)));
		vo.setIndicadorInformarDeslocamento(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARDESLOCAMENTO)));
		vo.setIndicadorInformarHorarioExecucao(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARHORARIOEXECUCAO)));
		vo.setIndicadorInformarCausaVazamento(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARCAUSAVAZAMENTO)));
		vo.setIndicadorInformarRedeRamal(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARREDERAMAL)));
		vo.setIndicadorInformarRedeRamalEsgoto(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARREDERAMALESGOTO)));
		vo.setIndicadorInformarMaterial(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARMATERIAL)));
		vo.setIndicadorInfomarVala(cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFOMARVALA)));
		vo.setIndicadorOrdemSeletiva(cursor.getInt(cursor.getColumnIndex(COL_INDICADORORDEMSELETIVA)));
		vo.setIndicadorServicoCritico(cursor.getInt(cursor.getColumnIndex(COL_INDICADORSERVICOCRITICO)));
		vo.setIndicadorAtividadeUnica(cursor.getInt(cursor.getColumnIndex(COL_INDICADORATIVIDADEUNICA)));

		return vo;
		
	}

	@Override
	public ServicoTipoVO obterObject(String line) 
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		ServicoTipoVO vo= new ServicoTipoVO();
		
		//C�digo
		if (values[0].length() > 0) {
			vo.setIdServicoTipo(Integer.parseInt(values[0]));	
		}
		//Descri��o
		vo.setDescricaoServicoTipo(values[1]);
		
		return vo;
	}
	
	
	
	@Override
	public ServicoTipoVO obterObject(JSONObject line) 
	{

		if (line ==null) {
			return null;
		}
		
		ServicoTipoVO vo=new ServicoTipoVO();
		
		try 
		{
			//C�digo
			if (line.getString("idServicoTipo").length() > 0) {
				vo.setIdServicoTipo(Integer.parseInt(line.getString("idServicoTipo")));
			}
			//Descri��o
			vo.setDescricaoServicoTipo(line.getString("servicoTipo"));
			//Subgrupo
			if (line.getString("idSubgrupo").length() > 0) {
				vo.setIdSubgrupo(Integer.parseInt(line.getString("idSubgrupo")));
			}
			//Atualiza��o Comercial
			if (line.getString("indicadorAtualizacaoComercial").length() > 0) {
				vo.setIndicadorAtualizacaoComercial(Integer.parseInt(line.getString("indicadorAtualizacaoComercial")));
			}
			//Pavimento
			if (line.getString("indicadorPavimento").length() > 0) {
				vo.setIndicadorPavimento(Integer.parseInt(line.getString("indicadorPavimento")));
			}
			//Servi�o Terceirizado
			if (line.getString("indicadorServicoTerceirizado").length() > 0) {
				vo.setIndicadorServicoTerceirizado(Integer.parseInt(line.getString("indicadorServicoTerceirizado")));
			}
			//Valor do Servi�o
			if (line.getString("valorServico").length() > 0) {
				vo.setValorServico(Float.parseFloat(line.getString("valorServico")));
			}
			//Tempo Medio de Execu��o
			if (line.getString("tempoMedioExecucao").length() > 0) {
				vo.setTempoMedioExecucao(Float.parseFloat(line.getString("tempoMedioExecucao")));
			}
			//D�bito Tipo
			if (line.getString("idDebitoTipo").length() > 0) {
				vo.setIdDebitoTipo(Integer.parseInt(line.getString("idDebitoTipo")));
			}
			//Cr�dito Tipo
			if (line.getString("idCreditoTipo").length() > 0) {
				vo.setIdCreditoTipo(Integer.parseInt(line.getString("idCreditoTipo")));
			}
			//Prioridade
			if (line.getString("idPrioridade").length() > 0) {
				vo.setIdPrioridade(Integer.parseInt(line.getString("idPrioridade")));
			}
			//Perfil Tipo Servi�o
			if (line.getString("idPerfilTipoServico").length() > 0) {
				vo.setIdPerfilTipoServico(Integer.parseInt(line.getString("idPerfilTipoServico")));
			}
			//Tipo Servi�o Refer�ncia
			if (line.getString("idTipoServicoReferencia").length() > 0) {
				vo.setIdTipoServicoReferencia(Integer.parseInt(line.getString("idTipoServicoReferencia")));
			}
			//Indicador Vist�ria
			if (line.getString("indicadorVistoria").length() > 0) {
				vo.setIndicadorVistoria(Integer.parseInt(line.getString("indicadorVistoria")));
			}
			//Indicador Fiscaliza��o Infra��o
			if (line.getString("indicadorFiscalizacaoInfracao").length() > 0) {
				vo.setIndicadorFiscalizacaoInfracao(Integer.parseInt(line.getString("indicadorFiscalizacaoInfracao")));
			}
			//Valor Remunera��o
			if (line.getString("valorRemuneracao").length() > 0) {
				vo.setValorRemuneracao(Float.parseFloat(line.getString("valorRemuneracao")));
			}
			//Percentual Remunera��o
			if (line.getString("percentualRemuneracao").length() > 0) {
				vo.setPercentualRemuneracao(Float.parseFloat(line.getString("percentualRemuneracao")));
			}
			//Prazo Execu��o Servi�o
			if (line.getString("prazoexEcucaoServico").length() > 0) {
				vo.setPrazoexEcucaoServico(Float.parseFloat(line.getString("prazoexEcucaoServico")));
			}
			//Tipo Remunera��o
			if (line.getString("indicadorTipoRemuneracao").length() > 0) {
				vo.setIndicadorTipoRemuneracao(Integer.parseInt(line.getString("indicadorTipoRemuneracao")));
			}
			//Informar Descolcamento
			if (line.getString("indicadorInformarDeslocamento").length() > 0) {
				vo.setIndicadorInformarDeslocamento(Integer.parseInt(line.getString("indicadorInformarDeslocamento")));
			}
			//Informar Horario Execu��o
			if (line.getString("indicadorInformarHorarioExecucao").length() > 0) {
				vo.setIndicadorInformarHorarioExecucao(Integer.parseInt(line.getString("indicadorInformarHorarioExecucao")));
			}
			//Informar Causa do Vazamaneto
			if (line.getString("indicadorInformarCausaVazamento").length() > 0) {
				vo.setIndicadorInformarCausaVazamento(Integer.parseInt(line.getString("indicadorInformarCausaVazamento")));
			}
			//Informar Rede Ramal
			if (line.getString("indicadorInformarRedeRamal").length() > 0) {
				vo.setIndicadorInformarRedeRamal(Integer.parseInt(line.getString("indicadorInformarRedeRamal")));
			}
			//Informar Rede/Ramal de Esgoto
			if (line.getString("indicadorInformarRedeRamalEsgoto").length() > 0) {
				vo.setIndicadorInformarRedeRamalEsgoto(Integer.parseInt(line.getString("indicadorInformarRedeRamalEsgoto")));
			}
			//Informar Material
			if (line.getString("indicadorInformarMaterial").length() > 0) {
				vo.setIndicadorInformarMaterial(Integer.parseInt(line.getString("indicadorInformarMaterial")));
			}
			//Informar Vala
			if (line.getString("indicadorInfomarVala").length() > 0) {
				vo.setIndicadorInfomarVala(Integer.parseInt(line.getString("indicadorInfomarVala")));
			}
			//Ordem Seletiva
			if (line.getString("indicadorOrdemSeletiva").length() > 0) {
				vo.setIndicadorInfomarVala(Integer.parseInt(line.getString("indicadorOrdemSeletiva")));
			}
			//Servi�o Cr�tico
			if (line.getString("indicadorServicoCritico").length() > 0) {
				vo.setIndicadorServicoCritico(Integer.parseInt(line.getString("indicadorServicoCritico")));
			}
			//Atividade �nica
			if (line.getString("indicadorAtividadeUnica").length() > 0) {
				vo.setIndicadorAtividadeUnica(Integer.parseInt(line.getString("indicadorAtividadeUnica")));
			}
			
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
		List<String> lines=lerDadosFromFile("wa2.csv", Util.PATH_DOWNLOAD);
		String line;
		int iLinhas=lines.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			line=lines.get(i);
			if (line.length() >0)
			{
				ServicoTipoVO vo=obterObject(line);
				if (vo!=null) inserir(vo);
			}
		}
	}
	
	public void povoaTabelaFromJSON(String url)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/ServicoTipoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			ServicoTipoVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
	
}
