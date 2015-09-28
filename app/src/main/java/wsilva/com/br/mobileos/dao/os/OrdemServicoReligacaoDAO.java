package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.OrdemServicoReligacaoVO;

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
		values.put(COL_IDORDEMSERVICORELIGACAO, vo.idOrdemServicoReligacao);
		if (vo.dataReligacao!=null){
			values.put(COL_DATARELIGACAO, Util.dateToString("yyyy-MM-dd", vo.dataReligacao));
		}
		values.put(COL_HORARELIGACAO, vo.horaReligacao);
		values.put(COL_IDFUNCIONARIO, vo.idFuncionario);
		values.put(COL_NUMEROHIDROMETRO, vo.numeroHidrometro);
		if (vo.dataInstalacaoHidrometro!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRO, Util.dateToString("yyyy-MM-dd", vo.dataInstalacaoHidrometro));
		}
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO, vo.idLocalInstalacaoHidrometro);
		values.put(COL_IDPROTECAOHIDROMETRO, vo.idProtecaoHidrometro);
		values.put(COL_LEITURAINSTALACAO, vo.leituraInstalacao);
		values.put(COL_NUMEROSELO, vo.numeroSelo);
		values.put(COL_INDICADORCAVALETE, vo.indicadorCavalete);
		values.put(COL_IDORDEMSERVICO, vo.idOrdemServico);
		values.put(COL_INDICADORTROCAPROTECAO, vo.indicadorTrocaProtecao);
		values.put(COL_INDICADORTROCAREGISTRO, vo.indicadorTrocaRegistro);
		values.put(COL_IDTIPORELIGACAO, vo.idTipoReligacao);
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
	public OrdemServicoReligacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoReligacaoVO vo = new OrdemServicoReligacaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idOrdemServicoReligacao = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICORELIGACAO));
		if (cursor.getString(cursor.getColumnIndex(COL_DATARELIGACAO))!=null) {
			vo.dataReligacao = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATARELIGACAO)));
		}
		vo.horaReligacao = cursor.getString(cursor.getColumnIndex(COL_HORARELIGACAO));
		vo.idFuncionario = cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO));
		vo.numeroHidrometro = cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))!=null){
			vo.dataInstalacaoHidrometro = Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO)));
		}
		vo.idLocalInstalacaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO));
		vo.idProtecaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO));
		vo.leituraInstalacao = cursor.getInt(cursor.getColumnIndex(COL_LEITURAINSTALACAO));
		vo.numeroSelo = cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO));
		vo.indicadorCavalete = cursor.getString(cursor.getColumnIndex(COL_INDICADORCAVALETE));
		vo.idOrdemServico = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO));
		vo.indicadorTrocaProtecao = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAPROTECAO));
		vo.indicadorTrocaRegistro = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAREGISTRO));
		vo.idTipoReligacao = cursor.getInt(cursor.getColumnIndex(COL_IDTIPORELIGACAO));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		
		return vo;
	}
}
