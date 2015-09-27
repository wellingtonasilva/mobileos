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
		values.put(COL_DESCRICAOSERVICOTIPO, vo.descricaoServicoTipo);
		values.put(COL_IDSERVICOTIPO, vo.idServicoTipo);
		values.put(COL_IDSUBGRUPO, vo.idSubgrupo);
		values.put(COL_INDICADORATUALIZACAOCOMERCIAL, vo.indicadorAtualizacaoComercial);
		values.put(COL_INDICADORPAVIMENTO, vo.indicadorPavimento);
		values.put(COL_INDICADORSERVICOTERCEIRIZADO, vo.indicadorServicoTerceirizado);
		values.put(COL_VALORSERVICO, vo.valorServico);
		values.put(COL_TEMPOMEDIOEXECUCAO, vo.tempoMedioExecucao);
		values.put(COL_IDDEBITOTIPO, vo.idDebitoTipo);
		values.put(COL_IDCREDITOTIPO, vo.idCreditoTipo);
		values.put(COL_IDPRIORIDADE, vo.idPrioridade);
		values.put(COL_IDPERFILTIPOSERVICO,  vo.idPerfilTipoServico);
		values.put(COL_IDTIPOSERVICOREFERENCIA, vo.idTipoServicoReferencia);
		values.put(COL_INDICADORVISTORIA, vo.indicadorVistoria);
		values.put(COL_INDICADORFISCALIZACAOINFRACAO, vo.indicadorFiscalizacaoInfracao);
		values.put(COL_VALORREMUNERACAO, vo.valorRemuneracao);
		values.put(COL_PERCENTUALREMUNERACAO, vo.percentualRemuneracao);
		values.put(COL_PRAZOEXECUCAOSERVICO, vo.prazoexEcucaoServico);
		values.put(COL_INDICADORTIPOREMUNERACAO, vo.indicadorTipoRemuneracao);
		values.put(COL_INDICADORINFORMARDESLOCAMENTO, vo.indicadorInformarDeslocamento);
		values.put(COL_INDICADORINFORMARHORARIOEXECUCAO, vo.indicadorInformarHorarioExecucao);
		values.put(COL_INDICADORINFORMARCAUSAVAZAMENTO, vo.indicadorInformarCausaVazamento);
		values.put(COL_INDICADORINFORMARREDERAMAL, vo.indicadorInformarRedeRamal);
		values.put(COL_INDICADORINFORMARREDERAMALESGOTO, vo.indicadorInformarRedeRamalEsgoto);
		values.put(COL_INDICADORINFORMARMATERIAL, vo.indicadorInformarMaterial);
		values.put(COL_INDICADORINFOMARVALA, vo.indicadorInfomarVala);
		values.put(COL_INDICADORORDEMSELETIVA, vo.indicadorOrdemSeletiva);
		values.put(COL_INDICADORSERVICOCRITICO, vo.indicadorServicoCritico);
		values.put(COL_INDICADORATIVIDADEUNICA, vo.indicadorAtividadeUnica);

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
		vo.descricaoServicoTipo = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSERVICOTIPO));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idServicoTipo = cursor.getInt(cursor.getColumnIndex(COL_IDSERVICOTIPO));
		vo.idSubgrupo = cursor.getInt(cursor.getColumnIndex(COL_IDSUBGRUPO));
		vo.indicadorAtualizacaoComercial = cursor.getInt(cursor.getColumnIndex(COL_INDICADORATUALIZACAOCOMERCIAL));
		vo.indicadorPavimento = cursor.getInt(cursor.getColumnIndex(COL_INDICADORPAVIMENTO));
		vo.indicadorServicoTerceirizado = cursor.getInt(cursor.getColumnIndex(COL_INDICADORSERVICOTERCEIRIZADO));
		vo.valorServico = cursor.getFloat(cursor.getColumnIndex(COL_VALORSERVICO));
		vo.tempoMedioExecucao = cursor.getFloat(cursor.getColumnIndex(COL_TEMPOMEDIOEXECUCAO));
		vo.idDebitoTipo = cursor.getInt(cursor.getColumnIndex(COL_IDDEBITOTIPO));
		vo.idCreditoTipo = cursor.getInt(cursor.getColumnIndex(COL_IDCREDITOTIPO));
		vo.idPrioridade = cursor.getInt(cursor.getColumnIndex(COL_IDPRIORIDADE));
		vo.idPerfilTipoServico = cursor.getInt(cursor.getColumnIndex(COL_IDPERFILTIPOSERVICO));
		vo.idTipoServicoReferencia = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSERVICOREFERENCIA));
		vo.indicadorVistoria = cursor.getInt(cursor.getColumnIndex(COL_INDICADORVISTORIA));
		vo.indicadorFiscalizacaoInfracao = cursor.getInt(cursor.getColumnIndex(COL_INDICADORFISCALIZACAOINFRACAO));
		vo.valorRemuneracao = cursor.getFloat(cursor.getColumnIndex(COL_VALORREMUNERACAO));
		vo.percentualRemuneracao = cursor.getFloat(cursor.getColumnIndex(COL_PERCENTUALREMUNERACAO));
		vo.prazoexEcucaoServico = cursor.getFloat(cursor.getColumnIndex(COL_PRAZOEXECUCAOSERVICO));
		vo.indicadorTipoRemuneracao = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTIPOREMUNERACAO));
		vo.indicadorInformarDeslocamento = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARDESLOCAMENTO));
		vo.indicadorInformarHorarioExecucao = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARHORARIOEXECUCAO));
		vo.indicadorInformarCausaVazamento = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARCAUSAVAZAMENTO));
		vo.indicadorInformarRedeRamal = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARREDERAMAL));
		vo.indicadorInformarRedeRamalEsgoto = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARREDERAMALESGOTO));
		vo.indicadorInformarMaterial = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFORMARMATERIAL));
		vo.indicadorInfomarVala = cursor.getInt(cursor.getColumnIndex(COL_INDICADORINFOMARVALA));
		vo.indicadorOrdemSeletiva = cursor.getInt(cursor.getColumnIndex(COL_INDICADORORDEMSELETIVA));
		vo.indicadorServicoCritico = cursor.getInt(cursor.getColumnIndex(COL_INDICADORSERVICOCRITICO));
		vo.indicadorAtividadeUnica = cursor.getInt(cursor.getColumnIndex(COL_INDICADORATIVIDADEUNICA));

		return vo;
		
	}
}
