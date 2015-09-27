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
		values.put(COL_IDORDEMSERVICOSUBSTITUICAOHM,vo.idOrdemServicoSubstituicaoHM);
		values.put(COL_NUMERHIDROMETROATUAL,vo.numerHidrometroAtual);
		values.put(COL_INDICADORTIPOMEDICAOATUAL,vo.indicadorTipoMedicaoAtual);
		if (vo.dataRetirada!=null){
			values.put(COL_DATARETIRADA, Util.dateToString("yyyy-MM-dd", vo.dataRetirada));
		}
		values.put(COL_LEITURARETIRADA, vo.leituraRetirada);
		values.put(COL_IDSITUACAOHIDROMETRO,vo.idSituacaoHidrometro);
		values.put(COL_IDLOCALARMAZENAGEMHIDROMETRO,vo.idLocalArmazenagemHidrometro);
		values.put(COL_NUMEROHIDROMETRONOVO,vo.numeroHidrometroNovo);
		if (vo.dataInstalacaoHidrometroNovo!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRONOVO, Util.dateToString("yyyy-MM-dd", vo.dataInstalacaoHidrometroNovo));
		}
		values.put(COL_INDICADORTIPOMEDICAO,vo.indicadorTipoMedicao);
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO,vo.idLocalInstalacaoHidrometro);
		values.put(COL_IDPROTECAOHIDROMETRO,vo.idProtecaoHidrometro);
		values.put(COL_INDICADORTROCAPROTECAO,vo.indicadorTrocaProtecao);
		values.put(COL_INDICADORTROCAREGISTRO,vo.indicadorTrocaRegistro);
		values.put(COL_LEITURAINSTALACAO,vo.leituraInstalacao);
		values.put(COL_NUMEROSELO,vo.numeroSelo);
		values.put(COL_INDICADORCAVALETE,vo.indicadorCavalete);
		values.put(COL_IDFUNCIONARIO,vo.idFuncionario);
		values.put(COL_IDORDEMSERVICO,vo.idOrdemServico);
		values.put(COL_IDTIPOSUBSTITUICAOHM,vo.idTipoSubstituicaoHM);
		values.put(COL_HORAINSTALACAOHIDROMETRONOVO,vo.horaInstalacaoHidrometroNovo);
		values.put(COL_IDEQUIPEEXECUCAO, vo.idEquipeExecucao);
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.descricaoEquipeExecucao);
		values.put(COL_INDICADORENVIO, vo.indicadorEnvio);
				
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
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idOrdemServicoSubstituicaoHM = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICOSUBSTITUICAOHM));
		vo.numerHidrometroAtual = cursor.getString(cursor.getColumnIndex(COL_NUMERHIDROMETROATUAL));
		vo.indicadorTipoMedicaoAtual = cursor.getString(cursor.getColumnIndex(COL_INDICADORTIPOMEDICAOATUAL));
		if (cursor.getString(cursor.getColumnIndex(COL_DATARETIRADA))!=null) {
			vo.dataRetirada = Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATARETIRADA)));
		}
		vo.leituraRetirada = cursor.getInt(cursor.getColumnIndex(COL_LEITURARETIRADA));
		vo.idSituacaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOHIDROMETRO));
		vo.idLocalArmazenagemHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDLOCALARMAZENAGEMHIDROMETRO));
		vo.numeroHidrometroNovo = cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRONOVO));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRONOVO))!=null){
			vo.dataInstalacaoHidrometroNovo = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRONOVO)));
		}
		vo.indicadorTipoMedicao = cursor.getString(cursor.getColumnIndex(COL_INDICADORTIPOMEDICAO));
		vo.idLocalInstalacaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO));
		vo.idProtecaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO));
		vo.indicadorTrocaProtecao = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAPROTECAO));
		vo.indicadorTrocaRegistro = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAREGISTRO));
		vo.leituraInstalacao = cursor.getInt(cursor.getColumnIndex(COL_LEITURAINSTALACAO));
		vo.numeroSelo = cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO));
		vo.indicadorCavalete = cursor.getString(cursor.getColumnIndex(COL_INDICADORCAVALETE));
		vo.idFuncionario = cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO));
		vo.idOrdemServico = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO));
		vo.idTipoSubstituicaoHM = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOSUBSTITUICAOHM));
		vo.horaInstalacaoHidrometroNovo = cursor.getString(cursor.getColumnIndex(COL_HORAINSTALACAOHIDROMETRONOVO));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));

		return vo;
	}
}
