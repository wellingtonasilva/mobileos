package wsilva.com.br.mobileos.dao.os;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;

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
	public long inserir(OrdemServicoVO vo) {
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
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo._id)}) > 0;
	}

	@Override
	public boolean remover(long id) 
	{
		return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)}) > 0;
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
				+ " where " + COL_INDICADORORDEMSERVICOAVULSA + "=1", null);
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
		values.put(COL_NUMEROOS,vo.numeroOS);
		values.put(COL_NUMERORA, vo.numeroRA);
		values.put(COL_SITUACAOOS, vo.situacaoOS);
		values.put(COL_SITUACAORA, vo.situacaoRA);
		values.put(COL_DESRICAOSITUACAORA, vo.desricaoSituacaoRA);
		values.put(COL_DESCRICAOSITUACAOOS, vo.descricaoSituacaoOS);
		
		if (vo.dataGeracaoOS !=null)
		{
			values.put(COL_DATAGERACAOOS, sdf.format(vo.dataGeracaoOS));
		}
			
		if (vo.dataGeracaoRA !=null)
		{
			values.put(COL_DATAGERACAORA, sdf.format(vo.dataGeracaoRA));
		}
		
		values.put(COL_IDTIPOSERVICO, vo.idTipoServico);
		values.put(COL_DESCRICAOTIPOSERVICO, vo.descricaoTipoServico);
		values.put(COL_OBSERVACAOOS, vo.observacaoOS);
		values.put(COL_OBSERVACAORA, vo.observacaoRA);
		values.put(COL_IDUNIDADEGERACAO, vo.idUnidadeGeracao);
		values.put(COL_DESCRICAOUNIDADEGERACAO, vo.descricaoUnidadeGeracao);
		values.put(COL_IDTIPOSERVICOEXECUTADO, vo.idTipoServicoExecutado);
		values.put(COL_DESCRICAOTIPOSERVICOEXECUTADO, vo.descricaoTipoServicoExecutado);
		
		if (vo.dataEncerramentoOS !=null)
		{
			values.put(COL_DATAENCERRAMENTOOS, sdf.format(vo.dataEncerramentoOS));
		}
		values.put(COL_HORAENCERRAMENTOOS, vo.horaEncerramentoOS);
		values.put(COL_IDMOTIVOENCERRAMENTO, vo.idMotivoEncerramento);
		values.put(COL_DESCRICAOMOTIVOENCERRAMENTO, vo.descricaoMotivoEncerramento);
		values.put(COL_PARECERENCERRAMENTO, vo.parecerEncerramento);
		
		if (vo.dataExecucao !=null)
		{
			values.put(COL_DATAEXECUCAO, sdf.format(vo.dataExecucao));
		}
		values.put(COL_HORAINICIALEXECUCAO, vo.horaInicialExecucao);
		values.put(COL_HORAFINALEXECUCAO, vo.horaFinalExecucao);
		values.put(COL_IDEQUIPEEXECUCAO, vo.idEquipeExecucao);
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.descricaoEquipeExecucao);
		values.put(COL_TIPOREDE, vo.tipoRede);
		values.put(COL_DESCRICAOTIPOREDE, vo.descricaoTipoRede);
		values.put(COL_IDDIAMETROREDE, vo.idDiametroRede);
		values.put(COL_DESCRICAODIAMETROREDE, vo.descricaoDiametroRede);
		values.put(COL_IDMATERIALREDE, vo.idMaterialRede);
		values.put(COL_DESCRICAOMATERIALREDE, vo.descricaoMaterialRede);
		values.put(COL_PROFUNDIDADEREDE, vo.profundidadeRede);
		values.put(COL_PRESSAOREDE, vo.pressaoRede);
		values.put(COL_IDAGENTEEXTERNO, vo.idAgenteExterno);
		values.put(COL_AGENTEEXTERNO, vo.agenteExterno);
		values.put(COL_IDUSUARIO, vo.idUsuario);
		values.put(COL_DESCRICAOUSUARIO, vo.descricaoUsuario);
		values.put(COL_LOGRADOURO, vo.logradouro);
		values.put(COL_BAIRRO, vo.bairro);
		values.put(COL_NUMEROIMOVEL, vo.numeroImovel);
		values.put(COL_IDCLIENTE, vo.idCliente);
		values.put(COL_IDIMOVEL, vo.idImovel);
		values.put(COL_NOMECLIENTE, vo.nomeCliente);
		values.put(COL_IDCAUSAREDE, vo.idCausaRede);
		values.put(COL_DESCRICAOCAUSAREDE, vo.descricaoCausaRede);
		
		if (vo.dataCancelamento !=null) {
			values.put(COL_DATACANCELAMENTO, sdf.format(vo.dataCancelamento));
		}
		values.put(COL_HORACANCELAMENTO, vo.horaCancelamento);
		values.put(COL_IDTIPOSERVICOGERAR, vo.idTipoServicoGerar);
		values.put(COL_DESCRICAOTIPOSERVICOGERAR, vo.descricaoTipoServicoGerar);
		values.put(COL_IDMOVIMENTORECEBITO, vo.idMovimentoRecebito);
		values.put(COL_IDCOLETORENVIOITEM, vo.idColetorEnvioItem);
		values.put(COL_IDCOLETORENVIO, vo.idColetorEnvio);
		values.put(COL_KMINICIAL, vo.kmInicial);
		values.put(COL_KMFINAL, vo.kmFinal);
		values.put(COL_IDORDEMSERVICO, vo.idOrdemServico);
		values.put(COL_NUMEROLACREANTERIOR, vo.numeroLacreAnterior);
		values.put(COL_NUMEROLOCRENOVO, vo.numeroLocreNovo);
		values.put(COL_NUMEROHIDROMETRO, vo.numeroHidrometro);
		values.put(COL_LEITURA, vo.leitura);
		values.put(COL_OBSERVACAOCAMPO, vo.observacaoCampo);
		values.put(COL_IDSETORCOMERCIAL, vo.idSetorComercial);
		values.put(COL_NUMEROLOTE, vo.numeroLote);
		values.put(COL_NUMEROSUBLOTE, vo.numeroSublote);
		values.put(COL_SEQUENCIAROTA, vo.sequenciaRota);
		values.put(COL_NUMEROQUADRA, vo.numeroQuadra);
		values.put(COL_INDICADORENVIO, vo.indicadorEnvio);
		values.put(COL_TIPOLOGRADOURO, vo.tipoLogradouro);
		values.put(COL_INDICADORORDEMSERVICOAVULSA, vo.indicadorOrdemServicoAvulsa);
		values.put(COL_LATITUDE, vo.latitude);
		values.put(COL_LONGITUDE, vo.longitude);
			
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
		os._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		os.numeroOS = cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS));
		os.numeroRA = cursor.getInt(cursor.getColumnIndex(COL_NUMERORA));
		os.situacaoOS = cursor.getInt(cursor.getColumnIndex(COL_SITUACAOOS));
		os.situacaoRA = cursor.getInt(cursor.getColumnIndex(COL_SITUACAORA));
		os.descricaoSituacaoOS = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOOS));
		os.desricaoSituacaoRA = cursor.getString(cursor.getColumnIndex(COL_DESRICAOSITUACAORA));
		//Data da Gera��o da OS
		if (cursor.getString(cursor.getColumnIndex(COL_DATAGERACAOOS))!=null) {
			os.dataGeracaoOS = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAGERACAOOS)));
		}
		//Data da Gera��o RA
		if (cursor.getString(cursor.getColumnIndex(COL_DATAGERACAORA))!=null) {
			os.dataGeracaoRA = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAGERACAORA)));
		}
		os.idTipoServico = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICO));
		os.descricaoTipoServico = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSERVICO));
		os.observacaoOS = cursor.getString(cursor.getColumnIndex(COL_OBSERVACAOOS));
		os.observacaoRA = cursor.getString(cursor.getColumnIndex(COL_OBSERVACAORA));
		os.idUnidadeGeracao = cursor.getInt(cursor.getColumnIndex(COL_IDUNIDADEGERACAO));
		os.descricaoUnidadeGeracao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUNIDADEGERACAO));
		os.idTipoServicoExecutado = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICOEXECUTADO));
		os.descricaoTipoServicoExecutado = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSERVICOEXECUTADO));
		//Data do Encerramento
		if (cursor.getString(cursor.getColumnIndex(COL_DATAENCERRAMENTOOS))!=null) {
			os.dataEncerramentoOS = Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATAENCERRAMENTOOS)));
		}
		os.horaEncerramentoOS = cursor.getString(cursor.getColumnIndex(COL_HORAENCERRAMENTOOS));
		os.idMotivoEncerramento = cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOENCERRAMENTO));
		os.descricaoMotivoEncerramento = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMOTIVOENCERRAMENTO));
		os.parecerEncerramento = cursor.getString(cursor.getColumnIndex(COL_PARECERENCERRAMENTO));
		//Data da Execu��o
		if (cursor.getString(cursor.getColumnIndex(COL_DATAEXECUCAO))!=null) {
			os.dataExecucao = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAEXECUCAO)));
		}
		os.horaInicialExecucao = cursor.getString(cursor.getColumnIndex(COL_HORAINICIALEXECUCAO));
		os.horaFinalExecucao = cursor.getString(cursor.getColumnIndex(COL_HORAFINALEXECUCAO));
		os.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		os.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		os.tipoRede = cursor.getInt(cursor.getColumnIndex(COL_TIPOREDE));
		os.descricaoTipoRede = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOREDE));
		os.idDiametroRede = cursor.getInt(cursor.getColumnIndex(COL_IDDIAMETROREDE));
		os.descricaoDiametroRede = cursor.getString(cursor.getColumnIndex(COL_DESCRICAODIAMETROREDE));
		os.idMaterialRede = cursor.getInt(cursor.getColumnIndex(COL_IDMATERIALREDE));
		os.descricaoMaterialRede = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOMATERIALREDE));
		os.profundidadeRede = cursor.getString(cursor.getColumnIndex(COL_PROFUNDIDADEREDE));
		os.pressaoRede = cursor.getString(cursor.getColumnIndex(COL_PRESSAOREDE));
		os.idAgenteExterno = cursor.getInt(cursor.getColumnIndex(COL_IDAGENTEEXTERNO));
		os.agenteExterno = cursor.getString(cursor.getColumnIndex(COL_AGENTEEXTERNO));
		os.idUsuario = cursor.getInt(cursor.getColumnIndex(COL_IDUSUARIO));
		os.descricaoUsuario = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOUSUARIO));
		os.logradouro = cursor.getString(cursor.getColumnIndex(COL_LOGRADOURO));
		os.bairro = cursor.getString(cursor.getColumnIndex(COL_BAIRRO));
		os.numeroImovel = cursor.getString(cursor.getColumnIndex(COL_NUMEROIMOVEL));
		os.idCliente = cursor.getInt(cursor.getColumnIndex(COL_IDCLIENTE));
		os.idImovel = cursor.getInt(cursor.getColumnIndex(COL_IDIMOVEL));
		os.nomeCliente = cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTE));
		os.idCausaRede = cursor.getInt(cursor.getColumnIndex(COL_IDCAUSAREDE));
		os.descricaoCausaRede = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOCAUSAREDE));
		//Data do Cancelamento
		if (cursor.getString(cursor.getColumnIndex(COL_DATACANCELAMENTO))!=null) {
			os.dataCancelamento = Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATACANCELAMENTO)));
		}
		os.horaCancelamento = cursor.getString(cursor.getColumnIndex(COL_HORACANCELAMENTO));
		os.idTipoServicoGerar = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICOGERAR));
		os.descricaoTipoServicoGerar = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPOSERVICOGERAR));
		os.idMovimentoRecebito = cursor.getInt(cursor.getColumnIndex(COL_IDMOVIMENTORECEBITO));
		os.idColetorEnvioItem = cursor.getInt(cursor.getColumnIndex(COL_IDCOLETORENVIOITEM));
		os.idColetorEnvio = cursor.getInt(cursor.getColumnIndex(COL_IDCOLETORENVIO));
		os.kmInicial = cursor.getInt(cursor.getColumnIndex(COL_KMINICIAL));
		os.kmFinal = cursor.getInt(cursor.getColumnIndex(COL_KMFINAL));
		os.idOrdemServico = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO));
		os.numeroLacreAnterior = cursor.getInt(cursor.getColumnIndex(COL_NUMEROLACREANTERIOR));
		os.numeroLocreNovo = cursor.getInt(cursor.getColumnIndex(COL_NUMEROLOCRENOVO));
		os.numeroHidrometro = cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO));
		os.leitura = cursor.getInt(cursor.getColumnIndex(COL_LEITURA));
		os.observacaoCampo = cursor.getString(cursor.getColumnIndex(COL_OBSERVACAOCAMPO));
		os.idSetorComercial = cursor.getInt(cursor.getColumnIndex(COL_IDSETORCOMERCIAL));
		os.numeroLote = cursor.getInt(cursor.getColumnIndex(COL_NUMEROLOTE));
		os.numeroSublote = cursor.getInt(cursor.getColumnIndex(COL_NUMEROSUBLOTE));
		os.sequenciaRota = cursor.getInt(cursor.getColumnIndex(COL_SEQUENCIAROTA));
		os.numeroQuadra = cursor.getString(cursor.getColumnIndex(COL_NUMEROQUADRA));
		os.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		os.tipoLogradouro = cursor.getString(cursor.getColumnIndex(COL_TIPOLOGRADOURO));
		os.indicadorOrdemServicoAvulsa = cursor.getInt(cursor.getColumnIndex(COL_INDICADORORDEMSERVICOAVULSA));
		os.latitude = cursor.getString(cursor.getColumnIndex(COL_LATITUDE));
		os.longitude = cursor.getString(cursor.getColumnIndex(COL_LONGITUDE));
		
		return os;
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
				vo.numeroOS = Integer.parseInt(values[0]);
			}
			//N�mero da RA
			if (values[1].length() > 0) {
				vo.numeroRA = Integer.parseInt(values[1]);
			}
			//Situa��o da OS
			if (values[2].length() > 0) {
				vo.situacaoOS = Integer.parseInt(values[2]);
			}
			//Situa��o da RA
			if (values[3].length() > 0) {
				vo.situacaoRA = Integer.parseInt(values[3]);
			}
			//Descri��o Situa��o da RA
			vo.desricaoSituacaoRA = values[4];
			//Descri��o Situa��o da OS
			vo.descricaoSituacaoOS = values[5];
			//Data Gera��o Os
			if (values[6].length() > 0) {
				vo.dataGeracaoOS = Util.stringToDate("dd/MM/yyyy",values[6].replace("\"", "").toString());
			}
			//Data Gera��o RA
			if (values[7].length() > 0) {
				vo.dataGeracaoRA = Util.stringToDate("dd/MM/yyyy",values[7].replace("\"", "").toString());
			}
			//Tipo Servi�o
			if (values[8].length() > 0) {
				vo.idTipoServico = Integer.parseInt(values[8]);
			}
			//Descri��o do Servi�o
			vo.descricaoTipoServico = values[9];
			//Observa��o OS
			vo.observacaoOS = values[10];
			//Observa��o RA
			vo.observacaoRA = values[11];
			//Id Unidade gera��o RA
			if (values[12].length() > 0) {
				vo.idUnidadeGeracao = Integer.parseInt(values[12]);
			}
			//Descri��o Unidade Gera��o RA
			vo.descricaoUnidadeGeracao = values[13];
			//Logradouro
			vo.logradouro = values[14];
			//Bairro
			vo.bairro = values[15];
			//Numero do Im�vel
			vo.numeroImovel = values[16];
			//Id do Cliente
			if (values[17].length() > 0) {
				vo.idCliente = Integer.parseInt(values[17]);
			}
			//Id do Im�vel
			if (values[18].length() > 0) {
				vo.idImovel = Integer.parseInt(values[18]);
			}
			//Nome do Cliente
			vo.nomeCliente = values[19];

			//IdMovimento Recebido
			if (values[20].length() > 0) {
				vo.idMovimentoRecebito = Integer.parseInt(values[20]);
			}
			//Id. Coletor Envio Item
			if (values[21].length() > 0) {
				vo.idColetorEnvioItem = Integer.parseInt(values[21]);
			}
			//Id. Equipe
			if (values[22].length() > 0) {
				vo.idEquipeExecucao = Integer.parseInt(values[22]);
			}
			//Descri��o Equipe Execu��o
			vo.descricaoEquipeExecucao = values[23];
			//Id. Coletor Envio
			if (values[24].length() > 0) {
				vo.idColetorEnvio = Integer.parseInt(values[24]);
			}
			//Id. do Coletor (25)
			//Mobile ID (26)
			//Descri��o do Coletor (27)
			//Data do Movimento (28)
			//Nome do Arquivo (29)
			//Hora do Movimento (30)
			//Id. Ordem de Servi�o (31) *** Diferen�a do n�mero da OS
			if (values[31].length() > 0) {
				vo.idOrdemServico = Integer.parseInt(values[31]);
			}
			//Data do Encerramento da OS (32)
			if (values[32].length() > 0) {
				vo.dataEncerramentoOS = Util.stringToDate("dd/MM/yyyy",values[32].replace("\"", "").toString());
			}

			//N�mero do Hidrometro
			if (values[33].length() > 0) {
				vo.numeroHidrometro = values[33];
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
				vo.numeroQuadra = values[37];
			}
			//Lote
			if (values[38].length() > 0) {
				vo.numeroLote = Integer.parseInt(values[38]);
			}
			//Sublote
			if (values[39].length() > 0) {
				vo.numeroSublote = Integer.parseInt(values[39]);
			}
			//Sequencial lote
			if (values[40].length() > 0) {
				vo.sequenciaRota = Integer.parseInt(values[40]);
			}
			//Setor Comercial
			if (values[41].length() > 0) {
				vo.idSetorComercial = Integer.parseInt(values[41]);
			}
			//Latitude
			if (values[42].length() > 0) {
				vo.latitude = values[42];
			}
			//Longitude
			if (values[43].length() > 0) {
				vo.longitude = values[43];
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			Log.w("ordem_servico", line);
		}

		return vo;
	}
 }
