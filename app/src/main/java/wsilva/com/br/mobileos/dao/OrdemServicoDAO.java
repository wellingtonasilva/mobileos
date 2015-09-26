package wsilva.com.br.mobileos.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
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
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.OrdemServicoVO;

public class OrdemServicoDAO extends BasicDAO<OrdemServicoVO>
{

	public static final String COL_ID= "_id";
	public static final String COL_NUMEROOS="numeroos";
	public static final String COL_NUMERORA="numerora";
	public static final String COL_SITUACAOOS="situacaoos";
	public static final String COL_SITUACAORA="situacaora";
	public static final String COL_DESRICAOSITUACAORA="desricaosituacaora";
	public static final String COL_DESCRICAOSITUACAOOS="descricaosituacaoos";
	public static final String COL_DATAGERACAOOS="datageracaoos";
	public static final String COL_DATAGERACAORA="datageracaora";
	public static final String COL_IDTIPOSERVICO="idtiposervico";
	public static final String COL_DESCRICAOTIPOSERVICO="descricaotiposervico";
	public static final String COL_OBSERVACAOOS="observacaoos";
	public static final String COL_OBSERVACAORA="observacaora";
	public static final String COL_IDUNIDADEGERACAO="idunidadegeracao";
	public static final String COL_DESCRICAOUNIDADEGERACAO="descricaounidadegeracao";
	public static final String COL_IDTIPOSERVICOEXECUTADO="idtiposervicoexecutado";
	public static final String COL_DESCRICAOTIPOSERVICOEXECUTADO="descricaotiposervicoexecutado";
	public static final String COL_DATAENCERRAMENTOOS="dataencerramentoos";
	public static final String COL_HORAENCERRAMENTOOS="horaencerramentoos";
	public static final String COL_IDMOTIVOENCERRAMENTO="idmotivoencerramento";
	public static final String COL_DESCRICAOMOTIVOENCERRAMENTO="descricaomotivoencerramento";
	public static final String COL_PARECERENCERRAMENTO="parecerencerramento";
	public static final String COL_DATAEXECUCAO="dataexecucao";
	public static final String COL_HORAINICIALEXECUCAO="horainicialexecucao";
	public static final String COL_HORAFINALEXECUCAO="horafinalexecucao";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_TIPOREDE="tiporede";
	public static final String COL_DESCRICAOTIPOREDE="descricaotiporede";
	public static final String COL_IDDIAMETROREDE="iddiametrorede";
	public static final String COL_DESCRICAODIAMETROREDE="descricaodiametrorede";
	public static final String COL_IDMATERIALREDE="idmaterialrede";
	public static final String COL_DESCRICAOMATERIALREDE="descricaomaterialrede";
	public static final String COL_PROFUNDIDADEREDE="profundidaderede";
	public static final String COL_PRESSAOREDE="pressaorede";
	public static final String COL_IDAGENTEEXTERNO="idagenteexterno";
	public static final String COL_AGENTEEXTERNO="agenteexterno";
	public static final String COL_IDUSUARIO="idusuario";
	public static final String COL_DESCRICAOUSUARIO="descricaousuario";
	public static final String COL_LOGRADOURO="dslogradouro";
	public static final String COL_BAIRRO="nmbairro";
	public static final String COL_NUMEROIMOVEL="nnimovel";
	public static final String COL_IDCLIENTE="idcliente";
	public static final String COL_IDIMOVEL="idimovel";
	public static final String COL_NOMECLIENTE="nmcliente";
	public static final String COL_IDCAUSAREDE="idcausarede";
	public static final String COL_DESCRICAOCAUSAREDE="dscausarede";
	public static final String COL_DATACANCELAMENTO="dtcancelamento";
	public static final String COL_HORACANCELAMENTO="hrcancelamento";
	public static final String COL_IDTIPOSERVICOGERAR="idtiposervicogerar";
	public static final String COL_DESCRICAOTIPOSERVICOGERAR="dstiposervicogerar";
	public static final String COL_IDMOVIMENTORECEBITO="idmovimentorecebido";
	public static final String COL_IDCOLETORENVIOITEM="idcoletorenvioitem";
	public static final String COL_IDCOLETORENVIO="idcoletorenvio";
	public static final String COL_KMINICIAL="kminicial";
	public static final String COL_KMFINAL="kmfinal";
	public static final String COL_IDORDEMSERVICO="idordemservico";
	public static final String COL_NUMEROLACREANTERIOR="nnlacreanterior";
	public static final String COL_NUMEROLOCRENOVO="nnlacrenovo";
	public static final String COL_NUMEROHIDROMETRO="nnhidromero";
	public static final String COL_LEITURA="nnleitura";
	public static final String COL_OBSERVACAOCAMPO="dsobservacaocampo";
	public static final String COL_IDSETORCOMERCIAL="setorcomercial";
	public static final String COL_NUMEROLOTE="nnlote";
	public static final String COL_NUMEROSUBLOTE="nnsublote";
	public static final String COL_SEQUENCIAROTA="sequenciarota";
	public static final String COL_NUMEROQUADRA="nnquadra";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String COL_TIPOLOGRADOURO="tipologradouro";
	public static final String COL_INDICADORORDEMSERVICOAVULSA="icosavulsa";
	public static final String COL_LATITUDE="latitude";
	public static final String COL_LONGITUDE= "longitude";
	
	//Nome da tabela
	public static final String TABLE_NAME="ordem_servico";
	
	//Script de cria��o da tabela
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID 			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_NUMEROOS 						+ " INTEGER,"
			+ 	COL_NUMERORA						+ " INTEGER,"
			+ 	COL_SITUACAOOS						+ " INTEGER,"
			+ 	COL_SITUACAORA						+ " INTEGER,"
			+	COL_DESRICAOSITUACAORA 				+ " TEXT,"
			+ 	COL_DESCRICAOSITUACAOOS 			+ " TEXT,"
			+ 	COL_DATAGERACAOOS 					+ " TEXT,"
			+ 	COL_DATAGERACAORA 					+ " TEXT,"
			+ 	COL_IDTIPOSERVICO 					+ " TEXT,"
			+ 	COL_DESCRICAOTIPOSERVICO 			+ " TEXT,"
			+ 	COL_OBSERVACAOOS					+ " TEXT,"
			+ 	COL_OBSERVACAORA					+ " TEXT,"
			+ 	COL_IDUNIDADEGERACAO				+ " INTEGER,"
			+ 	COL_DESCRICAOUNIDADEGERACAO			+ " TEXT,"
			+ 	COL_IDTIPOSERVICOEXECUTADO			+ " INTEGER,"
			+	COL_DESCRICAOTIPOSERVICOEXECUTADO	+ " TEXT,"
			+ 	COL_DATAENCERRAMENTOOS				+ " DATE,"
			+ 	COL_HORAENCERRAMENTOOS				+ " TEXT,"
			+ 	COL_IDMOTIVOENCERRAMENTO			+ " INTEGER,"
			+ 	COL_DESCRICAOMOTIVOENCERRAMENTO		+ " TEXT,"
			+ 	COL_PARECERENCERRAMENTO				+ " TEXT,"
			+ 	COL_DATAEXECUCAO					+ " DATE,"
			+ 	COL_HORAINICIALEXECUCAO				+ " TEXT,"
			+	COL_HORAFINALEXECUCAO				+ " TEXT,"
			+	COL_IDEQUIPEEXECUCAO				+ " INTEGER,"
			+	COL_DESCRICAOEQUIPEEXECUCAO			+ " TEXT,"
			+ 	COL_TIPOREDE						+ " INTEGER,"
			+ 	COL_DESCRICAOTIPOREDE				+ " TEXT,"
			+ 	COL_IDDIAMETROREDE					+ " INTEGER,"
			+	COL_DESCRICAODIAMETROREDE			+ " TEXT,"
			+	COL_IDMATERIALREDE					+ " INTEGER,"
			+ 	COL_DESCRICAOMATERIALREDE			+ " TEXT,"
			+ 	COL_PROFUNDIDADEREDE				+ " TEXT,"
			+	COL_PRESSAOREDE						+ " TEXT,"
			+	COL_IDAGENTEEXTERNO					+ " INTEGER,"
			+	COL_AGENTEEXTERNO					+ " TEXT,"
			+	COL_IDUSUARIO						+ " INTEGER,"
			+	COL_DESCRICAOUSUARIO				+ " TEXT,"
			+	COL_LOGRADOURO						+ " TEXT,"
			+   COL_BAIRRO							+ " TEXT,"
			+	COL_NUMEROIMOVEL					+ " TEXT,"
			+	COL_IDCLIENTE						+ " INTEGER,"
			+ 	COL_IDIMOVEL						+ " INTEGER,"
			+ 	COL_NOMECLIENTE						+ " TEXT,"
			+ 	COL_IDCAUSAREDE						+ " INTEGER,"
			+	COL_DESCRICAOCAUSAREDE				+ " TEXT,"
			+ 	COL_DATACANCELAMENTO				+ " DATE,"
			+ 	COL_HORACANCELAMENTO				+ " TEXT,"
			+ 	COL_IDTIPOSERVICOGERAR				+ " INTEGER,"
			+	COL_DESCRICAOTIPOSERVICOGERAR		+ " TEXT,"
			+ 	COL_IDMOVIMENTORECEBITO				+ " INTEGER,"
			+ 	COL_IDCOLETORENVIOITEM				+ " INTEGER, "
			+ 	COL_IDCOLETORENVIO					+ " INTEGER,"
			+	COL_KMINICIAL						+ " INTEGER,"
			+	COL_KMFINAL							+ " INTEGER,"
			+	COL_IDORDEMSERVICO					+ " INTEGER,"
			+ 	COL_NUMEROLACREANTERIOR				+ "	INTEGER,"
			+	COL_NUMEROLOCRENOVO					+ " INTEGER,"
			+	COL_NUMEROHIDROMETRO				+ " TEXT,"
			+	COL_LEITURA							+ " INTEGER,"
			+ 	COL_OBSERVACAOCAMPO					+ " TEXT,"
			+ 	COL_IDSETORCOMERCIAL				+ " INTEGER,"
			+ 	COL_NUMEROLOTE						+ " INTEGER,"
			+ 	COL_NUMEROSUBLOTE					+ " INTEGER,"
			+ 	COL_SEQUENCIAROTA					+ " INTEGER,"
			+ 	COL_NUMEROQUADRA					+ " TEXT,"
			+ 	COL_INDICADORENVIO					+ " INTEGER,"
			+ 	COL_TIPOLOGRADOURO					+ " TEXT,"
			+ 	COL_INDICADORORDEMSERVICOAVULSA		+ " INTEGER,"
			+ 	COL_LATITUDE						+ " TEXT,"
			+ 	COL_LONGITUDE						+ " TEXT"
			+ " );";
			
	public OrdemServicoDAO(Context ctx) 
	{
		super(ctx);
	}

	@Override
	public long inserir(OrdemServicoVO vo) 
	{
		long lonReturn=0;
		try {
			ContentValues values= obterContentValues(vo);
			lonReturn= db.insert(TABLE_NAME, null, values);	
		} catch (Exception e) {
			lonReturn=0;
		}
		return lonReturn;
	}

	@Override
	public boolean atualizar(OrdemServicoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo.getEntityId())}) > 0;
	}

	@Override
	public boolean remover(long id) 
	{
		return db.delete(TABLE_NAME, COL_ID +"=?", new String[]{String.valueOf(id)}) > 0;
	}

	@Override
	public List<OrdemServicoVO> listar() 
	{
		List<OrdemServicoVO> data = new ArrayList<OrdemServicoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			OrdemServicoVO os = obterObject(cursor);
			data.add(os);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}
	
	public List<OrdemServicoVO> listar(String selection, String[] selectionArgs)
	{
		List<OrdemServicoVO> data = new ArrayList<OrdemServicoVO>();
		Cursor cursor = obterCursor(selection,selectionArgs);
		while (!cursor.isAfterLast())
		{
			OrdemServicoVO os = obterObject(cursor);
			data.add(os);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public OrdemServicoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public OrdemServicoVO obterPorNumeroOs(int id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_NUMEROOS+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public long obterIdOrdemServicoAvulsa()
	{
		int ultimoRegistro=0;
		Cursor cursor= rawQuery("select count(1) from " 
				+ TABLE_NAME 
				+  " where " + COL_INDICADORORDEMSERVICOAVULSA + "=1", null);
		if (cursor!=null && cursor.getCount() > 0) 
		{	
			ultimoRegistro= (cursor.getInt(0) == 0 ? 1 : cursor.getInt(0)+1);
		} else {
			ultimoRegistro=1;
		}
		
		return ultimoRegistro;
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
	public ContentValues obterContentValues(OrdemServicoVO vo) 
	{
		ContentValues values=new ContentValues();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		values.put(COL_NUMEROOS,vo.getNumeroOS());
		values.put(COL_NUMERORA, vo.getNumeroRA());
		values.put(COL_SITUACAOOS, vo.getSituacaoOS());
		values.put(COL_SITUACAORA, vo.getSituacaoRA());
		values.put(COL_DESRICAOSITUACAORA, vo.getDesricaoSituacaoRA());
		values.put(COL_DESCRICAOSITUACAOOS, vo.getDescricaoSituacaoOS());
		
		if (vo.getDataGeracaoOS() !=null) 
		{
			values.put(COL_DATAGERACAOOS, sdf.format(vo.getDataGeracaoOS()));
		}
			
		if (vo.getDataGeracaoRA() !=null)
		{
			values.put(COL_DATAGERACAORA, sdf.format(vo.getDataGeracaoRA()));	
		}
		
		values.put(COL_IDTIPOSERVICO, vo.getIdTipoServico());
		values.put(COL_DESCRICAOTIPOSERVICO, vo.getDescricaoTipoServico());
		values.put(COL_OBSERVACAOOS, vo.getObservacaoOS());
		values.put(COL_OBSERVACAORA, vo.getObservacaoRA());
		values.put(COL_IDUNIDADEGERACAO, vo.getIdUnidadeGeracao());
		values.put(COL_DESCRICAOUNIDADEGERACAO, vo.getDescricaoUnidadeGeracao());
		values.put(COL_IDTIPOSERVICOEXECUTADO, vo.getIdTipoServicoExecutado());
		values.put(COL_DESCRICAOTIPOSERVICOEXECUTADO, vo.getDescricaoTipoServicoExecutado());
		
		if (vo.getDataEncerramentoOS() !=null)
		{
			values.put(COL_DATAENCERRAMENTOOS, sdf.format(vo.getDataEncerramentoOS()));	
		}
		values.put(COL_HORAENCERRAMENTOOS, vo.getHoraEncerramentoOS());
		values.put(COL_IDMOTIVOENCERRAMENTO, vo.getIdMotivoEncerramento());
		values.put(COL_DESCRICAOMOTIVOENCERRAMENTO, vo.getDescricaoMotivoEncerramento());
		values.put(COL_PARECERENCERRAMENTO, vo.getParecerEncerramento());
		
		if (vo.getDataExecucao() !=null)
		{
			values.put(COL_DATAEXECUCAO, sdf.format(vo.getDataExecucao()));	
		}
		values.put(COL_HORAINICIALEXECUCAO, vo.getHoraInicialExecucao());
		values.put(COL_HORAFINALEXECUCAO, vo.getHoraFinalExecucao());
		values.put(COL_IDEQUIPEEXECUCAO, vo.getIdEquipeExecucao());
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.getDescricaoEquipeExecucao());
		values.put(COL_TIPOREDE, vo.getTipoRede());
		values.put(COL_DESCRICAOTIPOREDE, vo.getDescricaoTipoRede());
		values.put(COL_IDDIAMETROREDE, vo.getIdDiametroRede());
		values.put(COL_DESCRICAODIAMETROREDE, vo.getDescricaoDiametroRede());
		values.put(COL_IDMATERIALREDE, vo.getIdMaterialRede());
		values.put(COL_DESCRICAOMATERIALREDE, vo.getDescricaoMaterialRede());
		values.put(COL_PROFUNDIDADEREDE, vo.getProfundidadeRede());
		values.put(COL_PRESSAOREDE, vo.getPressaoRede());
		values.put(COL_IDAGENTEEXTERNO, vo.getIdAgenteExterno());
		values.put(COL_AGENTEEXTERNO, vo.getAgenteExterno());
		values.put(COL_IDUSUARIO, vo.getIdUsuario());
		values.put(COL_DESCRICAOUSUARIO, vo.getDescricaoUsuario());
		values.put(COL_LOGRADOURO, vo.getLogradouro());
		values.put(COL_BAIRRO, vo.getBairro());
		values.put(COL_NUMEROIMOVEL, vo.getNumeroImovel());
		values.put(COL_IDCLIENTE, vo.getIdCliente());
		values.put(COL_IDIMOVEL, vo.getIdImovel());
		values.put(COL_NOMECLIENTE, vo.getNomeCliente());
		values.put(COL_IDCAUSAREDE, vo.getIdCausaRede());
		values.put(COL_DESCRICAOCAUSAREDE, vo.getDescricaoCausaRede());
		
		if (vo.getDataCancelamento() !=null) {
			values.put(COL_DATACANCELAMENTO, sdf.format(vo.getDataCancelamento()));
		}
		values.put(COL_HORACANCELAMENTO, vo.getHoraCancelamento());
		values.put(COL_IDTIPOSERVICOGERAR, vo.getIdTipoServicoGerar());
		values.put(COL_DESCRICAOTIPOSERVICOGERAR, vo.getDescricaoTipoServicoGerar());
		values.put(COL_IDMOVIMENTORECEBITO, vo.getIdMovimentoRecebito());
		values.put(COL_IDCOLETORENVIOITEM, vo.getIdColetorEnvioItem());
		values.put(COL_IDCOLETORENVIO, vo.getIdColetorEnvio());
		values.put(COL_KMINICIAL, vo.getKmInicial());
		values.put(COL_KMFINAL, vo.getKmFinal());
		values.put(COL_IDORDEMSERVICO, vo.getIdOrdemServico());
		values.put(COL_NUMEROLACREANTERIOR, vo.getNumeroLacreAnterior());
		values.put(COL_NUMEROLOCRENOVO, vo.getNumeroLocreNovo());
		values.put(COL_NUMEROHIDROMETRO, vo.getNumeroHidrometro());
		values.put(COL_LEITURA, vo.getLeitura());
		values.put(COL_OBSERVACAOCAMPO, vo.getObservacaoCampo());
		
		/**
		 * Adicionado: 2013-03-15
		 */
		values.put(COL_IDSETORCOMERCIAL, vo.getIdSetorComercial());
		values.put(COL_NUMEROLOTE, vo.getNumeroLote());
		values.put(COL_NUMEROSUBLOTE, vo.getNumeroSublote());
		values.put(COL_SEQUENCIAROTA, vo.getSequenciaRota());
		values.put(COL_NUMEROQUADRA, vo.getNumeroQuadra());
		values.put(COL_INDICADORENVIO, vo.getIndicadorEnvio());
		values.put(COL_TIPOLOGRADOURO, vo.getTipoLogradouro());
		values.put(COL_INDICADORORDEMSERVICOAVULSA, vo.getIndicadorOrdemServicoAvulsa());
		values.put(COL_LATITUDE, vo.getLatitude());
		values.put(COL_LONGITUDE, vo.getLongitude());
			
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
	
	public Cursor obterCursor(String selection, String[] selectionArgs) 
	{
		Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}

	@Override
	public OrdemServicoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoVO os = new OrdemServicoVO();
		os.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		os.setNumeroOS(cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS)));
		os.setNumeroRA(cursor.getInt(cursor.getColumnIndex(COL_NUMERORA)));
		os.setSituacaoOS(cursor.getInt(cursor.getColumnIndex(COL_SITUACAOOS)));
		os.setSituacaoRA(cursor.getInt(cursor.getColumnIndex(COL_SITUACAORA)));
		os.setDescricaoSituacaoOS(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOOS)));
		os.setDesricaoSituacaoRA(cursor.getString(cursor.getColumnIndex(COL_DESRICAOSITUACAORA)));
		//Data da Gera��o da OS
		if (cursor.getString(cursor.getColumnIndex(COL_DATAGERACAOOS))!=null) {
			os.setDataGeracaoOS(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAGERACAOOS))));	
		}
		//Data da Gera��o RA
		if (cursor.getString(cursor.getColumnIndex(COL_DATAGERACAORA))!=null) {
			os.setDataGeracaoRA(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAGERACAORA))));	
		}
		os.setIdTipoServico(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICO)));
		os.setDescricaoTipoServico(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSERVICO)));
		os.setObservacaoOS(cursor.getString(cursor.getColumnIndex(COL_OBSERVACAOOS)));
		os.setObservacaoRA(cursor.getString(cursor.getColumnIndex(COL_OBSERVACAORA)));
		os.setIdUnidadeGeracao(cursor.getInt(cursor.getColumnIndex(COL_IDUNIDADEGERACAO)));
		os.setDescricaoUnidadeGeracao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUNIDADEGERACAO)));
		os.setIdTipoServicoExecutado(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICOEXECUTADO)));
		os.setDescricaoTipoServicoExecutado(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSERVICOEXECUTADO)));
		//Data do Encerramento
		if (cursor.getString(cursor.getColumnIndex(COL_DATAENCERRAMENTOOS))!=null) {
			os.setDataEncerramentoOS(Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATAENCERRAMENTOOS))));	
		}
		os.setHoraEncerramentoOS(cursor.getString(cursor.getColumnIndex(COL_HORAENCERRAMENTOOS)));
		os.setIdMotivoEncerramento(cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOENCERRAMENTO)));
		os.setDescricaoMotivoEncerramento(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMOTIVOENCERRAMENTO)));
		os.setParecerEncerramento(cursor.getString(cursor.getColumnIndex(COL_PARECERENCERRAMENTO)));
		//Data da Execu��o
		if (cursor.getString(cursor.getColumnIndex(COL_DATAEXECUCAO))!=null) {
			os.setDataExecucao(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAEXECUCAO))));
		}
		os.setHoraInicialExecucao(cursor.getString(cursor.getColumnIndex(COL_HORAINICIALEXECUCAO)));
		os.setHoraFinalExecucao(cursor.getString(cursor.getColumnIndex(COL_HORAFINALEXECUCAO)));
		os.setIdEquipeExecucao(cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO)));
		os.setDescricaoEquipeExecucao(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO)));
		os.setTipoRede(cursor.getInt(cursor.getColumnIndex(COL_TIPOREDE)));
		os.setDescricaoTipoRede(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOREDE)));
		os.setIdDiametroRede(cursor.getInt(cursor.getColumnIndex(COL_IDDIAMETROREDE)));
		os.setDescricaoDiametroRede(cursor.getString(cursor.getColumnIndex(COL_DESCRICAODIAMETROREDE)));
		os.setIdMaterialRede(cursor.getInt(cursor.getColumnIndex(COL_IDMATERIALREDE)));
		os.setDescricaoMaterialRede(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMATERIALREDE)));
		os.setProfundidadeRede(cursor.getString(cursor.getColumnIndex(COL_PROFUNDIDADEREDE)));
		os.setPressaoRede(cursor.getString(cursor.getColumnIndex(COL_PRESSAOREDE)));
		os.setIdAgenteExterno(cursor.getInt(cursor.getColumnIndex(COL_IDAGENTEEXTERNO)));
		os.setAgenteExterno(cursor.getString(cursor.getColumnIndex(COL_AGENTEEXTERNO)));
		os.setIdUsuario(cursor.getInt(cursor.getColumnIndex(COL_IDUSUARIO)));
		os.setDescricaoUsuario(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUSUARIO)));
		os.setLogradouro(cursor.getString(cursor.getColumnIndex(COL_LOGRADOURO)));
		os.setBairro(cursor.getString(cursor.getColumnIndex(COL_BAIRRO)));
		os.setNumeroImovel(cursor.getString(cursor.getColumnIndex(COL_NUMEROIMOVEL)));
		os.setIdCliente(cursor.getInt(cursor.getColumnIndex(COL_IDCLIENTE)));
		os.setIdImovel(cursor.getInt(cursor.getColumnIndex(COL_IDIMOVEL)));
		os.setNomeCliente(cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTE)));
		os.setIdCausaRede(cursor.getInt(cursor.getColumnIndex(COL_IDCAUSAREDE)));
		os.setDescricaoCausaRede(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOCAUSAREDE)));
		//Data do Cancelamento
		if (cursor.getString(cursor.getColumnIndex(COL_DATACANCELAMENTO))!=null) {
			os.setDataCancelamento(Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATACANCELAMENTO))));	
		}
		os.setHoraCancelamento(cursor.getString(cursor.getColumnIndex(COL_HORACANCELAMENTO)));
		os.setIdTipoServicoGerar(cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICOGERAR)));
		os.setDescricaoTipoServicoGerar(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSERVICOGERAR)));
		os.setIdMovimentoRecebito(cursor.getInt(cursor.getColumnIndex(COL_IDMOVIMENTORECEBITO)));
		os.setIdColetorEnvioItem(cursor.getInt(cursor.getColumnIndex(COL_IDCOLETORENVIOITEM)));
		os.setIdColetorEnvio(cursor.getInt(cursor.getColumnIndex(COL_IDCOLETORENVIO)));
		os.setKmInicial(cursor.getInt(cursor.getColumnIndex(COL_KMINICIAL)));
		os.setKmFinal(cursor.getInt(cursor.getColumnIndex(COL_KMFINAL)));
		os.setIdOrdemServico(cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO)));
		
		os.setNumeroLacreAnterior(cursor.getInt(cursor.getColumnIndex(COL_NUMEROLACREANTERIOR)));
		os.setNumeroLocreNovo(cursor.getInt(cursor.getColumnIndex(COL_NUMEROLOCRENOVO)));
		os.setNumeroHidrometro(cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO)));
		os.setLeitura(cursor.getInt(cursor.getColumnIndex(COL_LEITURA)));
		os.setObservacaoCampo(cursor.getString(cursor.getColumnIndex(COL_OBSERVACAOCAMPO)));
		
		/**
		 * Adicionado: 2013-03-15
		 */
		os.setIdSetorComercial(cursor.getInt(cursor.getColumnIndex(COL_IDSETORCOMERCIAL)));
		os.setNumeroLote(cursor.getInt(cursor.getColumnIndex(COL_NUMEROLOTE)));
		os.setNumeroSublote(cursor.getInt(cursor.getColumnIndex(COL_NUMEROSUBLOTE)));
		os.setSequenciaRota(cursor.getInt(cursor.getColumnIndex(COL_SEQUENCIAROTA)));
		os.setNumeroQuadra(cursor.getString(cursor.getColumnIndex(COL_NUMEROQUADRA)));
		os.setIndicadorEnvio(cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO)));
		os.setTipoLogradouro(cursor.getString(cursor.getColumnIndex(COL_TIPOLOGRADOURO)));
		os.setIndicadorOrdemServicoAvulsa(cursor.getInt(cursor.getColumnIndex(COL_INDICADORORDEMSERVICOAVULSA)));
		os.setLatitude(cursor.getString(cursor.getColumnIndex(COL_LATITUDE)));
		os.setLongitude(cursor.getString(cursor.getColumnIndex(COL_LONGITUDE)));
		
		return os;
	}

	@Override
	public OrdemServicoVO obterObject(String line) 
	{

		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		OrdemServicoVO vo= new OrdemServicoVO();
		
		try 
		{
			//N�mero da OS
			if (values[0].length() > 0) {
				vo.setNumeroOS(Integer.parseInt(values[0]));
			}
			//N�mero da RA
			if (values[1].length() > 0) {
				vo.setNumeroRA(Integer.parseInt(values[1]));
			}
			//Situa��o da OS
			if (values[2].length() > 0) {
				vo.setSituacaoOS(Integer.parseInt(values[2]));
			}
			//Situa��o da RA
			if (values[3].length() > 0) {
				vo.setSituacaoRA(Integer.parseInt(values[3]));
			}
			//Descri��o Situa��o da RA
			vo.setDesricaoSituacaoRA(values[4]);
			//Descri��o Situa��o da OS
			vo.setDescricaoSituacaoOS(values[5]);
			//Data Gera��o Os
			if (values[6].length() > 0) {
				vo.setDataGeracaoOS(Util.stringToDate("dd/MM/yyyy",values[6].replace("\"", "").toString()));
			}
			//Data Gera��o RA
			if (values[7].length() > 0) {
				vo.setDataGeracaoRA(Util.stringToDate("dd/MM/yyyy",values[7].replace("\"", "").toString()));
			}
			//Tipo Servi�o
			if (values[8].length() > 0) {
				vo.setIdTipoServico(Integer.parseInt(values[8]));
			}
			//Descri��o do Servi�o
			vo.setDescricaoTipoServico(values[9]);
			//Observa��o OS
			vo.setObservacaoOS(values[10]);
			//Observa��o RA
			vo.setObservacaoRA(values[11]);
			//Id Unidade gera��o RA
			if (values[12].length() > 0) {
				vo.setIdUnidadeGeracao(Integer.parseInt(values[12]));
			}
			//Descri��o Unidade Gera��o RA
			vo.setDescricaoUnidadeGeracao(values[13]);	
			//Logradouro
			vo.setLogradouro(values[14]);
			//Bairro
			vo.setBairro(values[15]);
			//Numero do Im�vel
			vo.setNumeroImovel(values[16]);
			//Id do Cliente
			if (values[17].length() > 0) {
				vo.setIdCliente(Integer.parseInt(values[17]));	
			}
			//Id do Im�vel
			if (values[18].length() > 0) {
				vo.setIdImovel(Integer.parseInt(values[18]));
			}
			//Nome do Cliente
			vo.setNomeCliente(values[19]);
			
			//IdMovimento Recebido
			if (values[20].length() > 0) {
				vo.setIdMovimentoRecebito(Integer.parseInt(values[20]));
			}
			//Id. Coletor Envio Item
			if (values[21].length() > 0) {
				vo.setIdColetorEnvioItem(Integer.parseInt(values[21]));
			}
			//Id. Equipe
			if (values[22].length() > 0) {
				vo.setIdEquipeExecucao(Integer.parseInt(values[22]));
			}
			//Descri��o Equipe Execu��o
			vo.setDescricaoEquipeExecucao(values[23]);
			//Id. Coletor Envio
			if (values[24].length() > 0) {
				vo.setIdColetorEnvio(Integer.parseInt(values[24]));
			}
			//Id. do Coletor (25)
			//Mobile ID (26)
			//Descri��o do Coletor (27)
			//Data do Movimento (28)
			//Nome do Arquivo (29)
			//Hora do Movimento (30)
			//Id. Ordem de Servi�o (31) *** Diferen�a do n�mero da OS
			if (values[31].length() > 0) {
				vo.setIdOrdemServico(Integer.parseInt(values[31]));
			}
			//Data do Encerramento da OS (32)
			if (values[32].length() > 0) {
				vo.setDataEncerramentoOS(Util.stringToDate("dd/MM/yyyy",values[32].replace("\"", "").toString()));
			}
			
			/**
			 * Adicionado: 2013-03-15
			 */
			//N�mero do Hidrometro
			if (values[33].length() > 0) {
				vo.setNumeroHidrometro(values[33]);
			}
			//N�mero do Selo
			if (values[34].length() > 0) {
			}
			//Ano de Fabricacao
			if (values[35].length() > 0) {
			}
			//Complemento do Endere�o
			if (values[36].length() > 0) {
			}
			//Quadra
			if (values[37].length() > 0) {
				vo.setNumeroQuadra(values[37]);
			}
			//Lote
			if (values[38].length() > 0) {
				vo.setNumeroLote(Integer.parseInt(values[38]));
			}
			//Sublote
			if (values[39].length() > 0) {
				vo.setNumeroSublote(Integer.parseInt(values[39]));
			}
			//Sequencial lote
			if (values[40].length() > 0) {
				vo.setSequenciaRota(Integer.parseInt(values[40]));
			}
			//Setor Comercial
			if (values[41].length() > 0) {
				vo.setIdSetorComercial(Integer.parseInt(values[41]));
			}
			
			//Latitude
			if (values[42].length() > 0) {
				vo.setLatitude(values[42]);
			}
			//Longitude
			if (values[43].length() > 0) {
				vo.setLongitude(values[43]);
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			Log.w("ordem_servico", line);
		}
		
		return vo;
	}
	
	@Override
	public OrdemServicoVO obterObject(JSONObject line)
	{
		if (line ==null) {
			return null;
		}
		
		OrdemServicoVO vo=new OrdemServicoVO();
		
		try 
		{
			//Id. Coletor Envio
			if (line.getString("idColetorEnvio").length() > 0) {
				vo.setIdColetorEnvio(Integer.parseInt(line.getString("idColetorEnvio")));
			}
			//N�mero da OS
			if (line.getString("numeroOS").length() > 0) {
				vo.setNumeroOS(Integer.parseInt(line.getString("numeroOS")));	
			}
			//N�mero da RA
			if (line.getString("numeroRA").length() > 0) {
				vo.setNumeroRA(Integer.parseInt(line.getString("numeroRA")));
			}
			//Id. Situa��o da OS
			if (line.getString("idSituacaoOS").length() > 0) {
				vo.setSituacaoOS(Integer.parseInt(line.getString("idSituacaoOS")));	
			}
			//Id. Situa��o RA
			if (line.getString("idSituacaoRA").length() > 0) {
				vo.setSituacaoRA(Integer.parseInt(line.getString("idSituacaoRA")));
			}
			//Situa��o OS
			vo.setDescricaoSituacaoOS(line.getString("situacaoOS"));
			//Situa��o RA
			vo.setDesricaoSituacaoRA(line.getString("situacaoRA"));
			//Data Gera��o OS
			if (line.getString("dataGeracaoOS").length() > 0) {
				vo.setDataGeracaoOS(Util.stringToDate("dd/MM/yyyy", line.getString("dataGeracaoOS")));
			}
			//Data Gera��o RA
			if (line.getString("dataGeracaoRA").length() > 0) {
				vo.setDataGeracaoRA(Util.stringToDate("dd/MM/yyyy", line.getString("dataGeracaoRA")));
			}
			//Id. Servico Tipo
			if (line.getString("idServicoTipo").length() > 0) {
				vo.setIdTipoServico(Integer.parseInt(line.getString("idServicoTipo")));
			}
			//Servico Tipo
			vo.setDescricaoTipoServico(line.getString("servicoTipo"));
			//Observa��o OS
			vo.setObservacaoOS(line.getString("observacaoOS"));
			//Observa��o RA
			vo.setObservacaoRA(line.getString("observacaoRA"));
			//Id. Unidade Gera��o
			if (line.getString("idUnidadeGeracao").length() > 0) {
				vo.setIdUnidadeGeracao(Integer.parseInt(line.getString("idUnidadeGeracao")));
			}
			//Unidade Gera��o
			vo.setDescricaoUnidadeGeracao(line.getString("uniadeGeracao"));
			//Logradouro
			vo.setLogradouro(line.getString("logradouro"));
			//Bairro
			vo.setBairro(line.getString("bairro"));
			//N�mero Im�vel
			vo.setNumeroImovel(line.getString("numeroImovel"));
			//Id. Cliente
			if (line.getString("idCliente").length() > 0) {
				vo.setIdCliente(Integer.parseInt(line.getString("idCliente")));
			}
			//Id. Im�vel
			vo.setNumeroImovel(line.getString("idImovel"));
			//Nome do Cliente
			vo.setNomeCliente(line.getString("nomeCliente"));
			//Id. Movimento Recebido
			if (line.getString("idMovimentoRecebido").length() > 0) {
				vo.setIdMovimentoRecebito(Integer.parseInt(line.getString("idMovimentoRecebido")));
			}
			//Id. Coletor Envio Item
			if (line.getString("idColetorEnvioItem").length() > 0) {
				vo.setIdColetorEnvioItem(Integer.parseInt(line.getString("idColetorEnvioItem")));
			}
			//Id. Equipe
			if (line.getString("idEquipe").length() > 0) {
				vo.setIdEquipeExecucao(Integer.parseInt(line.getString("idEquipe")));
			}
			//Equipe
			vo.setDescricaoEquipeExecucao(line.getString("equipe"));
			//Mobile ID
			if (line.getString("idMobile").length() > 0) {
			}
			//Descri��o do Coletor
			if (line.getString("descricaoColetor").length() > 0) {
			}
			//Data do Movimento
			if (line.getString("dataMovimento").length() > 0) {
			}
			//Nome do Arquivo
			if (line.getString("nomeArquivo").length() > 0) {
			}
			//Hora do Movimento
			if (line.getString("horaMovimento").length() > 0) {
			}	
			//Id. Ordem Servi�o
			if (line.getString("idOrdemServico").length() > 0) {
				vo.setIdOrdemServico(Integer.parseInt(line.getString("idOrdemServico")));
			}
			//Data de Encerramento
			if (line.getString("dataEncerramento").length() > 0) {
				vo.setDataEncerramentoOS(Util.stringToDate("dd/MM/yyyy", line.getString("dataEncerramento")));
			}
			//N�mero do Hidrometro
			if (line.getString("numeroHidrometro").length() > 0) {
				vo.setNumeroHidrometro(line.getString("numeroHidrometro"));
			}
			//Numero do Selo
			if (line.getString("numeroSelo").length() > 0) {
			}
			//Ano de Fabrica�ao
			if (line.getString("anoFabricacao").length() > 0) {
			}
			//Complemento do Endere�o
			if (line.getString("complementoEndereco").length() > 0) {
			}
			//Numero da Quadra
			if (line.getString("numeroQuadra").length() > 0) {
				vo.setNumeroQuadra(line.getString("numeroQuadra"));
			}
			//Numero do Lote
			if (line.getString("numeroLote").length() > 0) {
				vo.setNumeroLote(Integer.parseInt(line.getString("numeroLote")));
			}
			//Numero do Sublote
			if (line.getString("numeroSublote").length() > 0) {
				vo.setNumeroSublote(Integer.parseInt(line.getString("numeroSublote")));
			}
			//SEquencial da Rota
			if (line.getString("sequencialRota").length() > 0) {
				vo.setSequenciaRota(Integer.parseInt(line.getString("sequencialRota")));
			}
			//Setor Comercial
			if (line.getString("setorComerial").length() > 0) {
				vo.setIdSetorComercial(Integer.parseInt(line.getString("setorComerial")));
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
	

	@Override
	public String obterLinhaCSV(OrdemServicoVO vo, String delimitador) 
	{
		String linha="";
		
		if (vo!=null && vo.getSituacaoOS()!=Util.OS_ID_STATUS_PENDENTE) 
		{
			linha=  delimitador
					+ String.valueOf(vo.getNumeroOS()) + ";"
					+ String.valueOf(vo.getIdTipoServicoExecutado()) + ";"
					+ vo.getDescricaoTipoServicoExecutado() + ";"
					+ Util.dateToString("dd/MM/yyyy", vo.getDataEncerramentoOS()) +  ";"
					+ vo.getHoraEncerramentoOS() + ";"
					+ String.valueOf(vo.getIdMotivoEncerramento()) + ";"
					+ vo.getDescricaoMotivoEncerramento() + ";"
					+ vo.getParecerEncerramento() + ";"
					+ Util.dateToString("dd/MM/yyyy", vo.getDataExecucao()) + ";"
					+ vo.getHoraInicialExecucao() + ";"
					+ vo.getHoraFinalExecucao() + ";"
					+ String.valueOf(vo.getIdEquipeExecucao()) + ";"
					+ vo.getDescricaoEquipeExecucao() + ";"
					+ String.valueOf(vo.getTipoRede()) + ";"
					+ vo.getDescricaoTipoRede() + ";"
					+ String.valueOf(vo.getIdDiametroRede()) + ";"
					+ vo.getDescricaoDiametroRede() + ";"
					+ String.valueOf(vo.getIdMaterialRede()) + ";"
					+ vo.getDescricaoMaterialRede() + ";"
					+ vo.getProfundidadeRede() + ";"
					+ vo.getPressaoRede() + ";"
					+ String.valueOf(vo.getIdAgenteExterno()) + ";"
					+ vo.getAgenteExterno() + ";"
					+ String.valueOf(vo.getIdCausaRede()) + ";"
					+ vo.getDescricaoCausaRede() + ";"
					+ Util.dateToString("dd/MM/yyyy", vo.getDataCancelamento()) + ";"
					+ vo.getHoraCancelamento()													+ ";"
					+ String.valueOf(vo.getIdTipoServicoGerar()) 								+ ";"
					+ vo.getDescricaoTipoServicoGerar() 										+ ";"
					+ String.valueOf(vo.getIdMovimentoRecebito()) 								+ ";"
					+ String.valueOf(vo.getIdColetorEnvioItem()) 								+ ";"
					+ String.valueOf(vo.getIdColetorEnvio())   									+ ";"
					+ String.valueOf(vo.getKmInicial()) 										+ ";"
					+ String.valueOf(vo.getKmFinal())  											+ ";"	
					+ String.valueOf(vo.getSituacaoOS())										+ ";"
					+ (vo.getDescricaoSituacaoOS()==null ? " " : vo.getDescricaoSituacaoOS()) 	+ ";"
					+ String.valueOf(vo.getNumeroLacreAnterior()) 								+ ";"
					+ String.valueOf(vo.getNumeroLocreNovo())									+ ";"
					+ (vo.getNumeroHidrometro()==null ? " " : vo.getNumeroHidrometro()) 		+ ";"
					+ String.valueOf(vo.getLeitura()) 											+ ";"
					+ (vo.getObservacaoCampo()==null ? "." : vo.getObservacaoCampo())			+ ";"
					+ (vo.getLogradouro()==null ? "." : vo.getLogradouro())						+ ";"
					+ (vo.getBairro()==null ? "." : vo.getBairro())								+ ";"
					+ (vo.getNumeroImovel()==null ? "0" : vo.getNumeroImovel())					+ ";"
					+ (vo.getObservacaoOS()==null ? "." : vo.getObservacaoOS())					+ ";"
					+ (vo.getTipoLogradouro()==null ? "." : vo.getTipoLogradouro())				+ ";"
					+ "\n";	
		}
		return linha;
	}

	public String obterOrdemServicoExecucao() 
	{
		String numeroOS="";
		Cursor cursor= rawQuery("select " + COL_NUMEROOS + " from " + TABLE_NAME 
				+ " where " + COL_SITUACAOOS + "=" + String.valueOf(Util.OS_ID_STATUS_EM_EXECUCAO), null);
		if (cursor!=null && cursor.getCount() > 0) {
			numeroOS= cursor.getString(0);
		} 
		return numeroOS;
	}
	
	public boolean isOrdemServicoExecucao() 
	{
		boolean exist=false;
		Cursor cursor= rawQuery("select count(*) from " + TABLE_NAME 
				+ " where " + COL_SITUACAOOS + "=" + String.valueOf(Util.OS_ID_STATUS_EM_EXECUCAO), null);
		if (cursor!=null && cursor.getCount() > 0) {
			exist= (cursor.getInt(0) == 0 ? false : true);
		} 
		return exist;
	}	
	
	public void povoaTabela()
	{
		//Lista de arquivos dispon�veis
		List<String> files=getListFiles();
		int qtd=files.size();
		
		for (int x=0; x<qtd; x++) 
		{
			List<String> lines=lerDadosFromFile(files.get(x), Util.PATH_DOWNLOAD);
			String line;
			int iLinhas=lines.size();
			
			for (int i=0; i<iLinhas; i++)
			{
				line=lines.get(i);
				if (line.length() >0)
				{
					OrdemServicoVO vo=obterObject(line);
					if (vo!=null) inserir(vo);
				}
			}	
		}
	}
	
	public List<String> getListFiles()
	{
		List<String> list=new ArrayList<String>();
		try 
		{
			String sdCard = Environment.getExternalStorageDirectory().toString();
			File file=new File(sdCard + Util.PATH_DOWNLOAD);
			FileFilter fileFilter=new FileFilter() 
			{
				@Override
				public boolean accept(File pathname) 
				{
					return pathname.getName().contains("wb3" + Util.dateToString("yyyyMMdd", new Date()));
				}
			};
			
			File files[]=file.listFiles(fileFilter);
			int qtd=files.length;
			
			for (int i=0; i<qtd; i++)
			{
				list.add(files[i].getName());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<OrdemServicoVO > oss=listar();
		int qtd=oss.size();
		String linha="";

		if (qtd > 0) 
		{
			try 
			{
				File sdCard = Environment.getExternalStorageDirectory();
				File directory= new File(sdCard.getAbsolutePath() + directoryname);
				File file = new File(directory, filename);
				FileOutputStream output= new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(output);
				
				for (int i=0; i<qtd; i++)
				{
					OrdemServicoVO vo=oss.get(i);
					if (vo.getDataEncerramentoOS() !=null || vo.getDataCancelamento() !=null) 
					{
						linha= obterLinhaCSV(vo, "");
						osw.write(linha);
					}
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
	
	public void povoaTabelaFromJSON(String url,  String idMobile, Date dataMovimento)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("idMobile", idMobile));
		postParameters.add(new BasicNameValuePair("dataMovimento", Util.dateToString("MM/dd/yyyy", dataMovimento)));
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/OrdemServicoServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			OrdemServicoVO vo=obterObject(jsonObject);
			if (vo!=null) {
				if (obterPorNumeroOs(vo.getNumeroOS())==null) {
					inserir(vo);	
				}
			}
		}
	}
 }
