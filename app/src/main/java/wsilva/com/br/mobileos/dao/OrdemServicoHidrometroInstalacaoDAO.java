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
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo._id)}) > 0;
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
		values.put(COL_IDORDEMSERVICOINSTALACAOHM, vo.idOrdemServicoInstalacaoHM);
		values.put(COL_NUMEROHIDROMETRO, vo.numeroHidrometro);
		if (vo.dataInstalacaoHidrometro!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRO,  Util.dateToString("yyyy-MM-dd", vo.dataInstalacaoHidrometro));
		}
		values.put(COL_IDTIPOMEDICAO, vo.idTipoMedicao);
		values.put(COL_IDLOCALINSTALACAOHIDROMETRO, vo.idLocalInstalacaoHidrometro);
		values.put(COL_IDPROTECAOHIDROMETRO, vo.idProtecaoHidrometro);
		values.put(COL_IDORDEMSERVICO, vo.idOrdemServico);
		values.put(COL_INDICADORTROCAPROTECAO, vo.indicadorTrocaProtecao);
		values.put(COL_INDICADORTROCAREGISTRO, vo.indicadorTrocaRegistro);
		values.put(COL_LEITURAINSTALACAO, vo.leituraInstalacao);
		values.put(COL_NUMEROSELO, vo.numeroSelo);
		values.put(COL_INDCADORCAVALETE, vo.indcadorCavalete);
		values.put(COL_IDFUNCIONARIO, vo.idFuncionario);
		values.put(COL_IDTIPOINSTALACAOHM, vo.idTipoInstalacaoHM);
		values.put(COL_HORAINSTALACAOHIDROMETRO, vo.horaInstalacaoHidrometro);
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
	public OrdemServicoHidrometroInstalacaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoHidrometroInstalacaoVO vo = new OrdemServicoHidrometroInstalacaoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idOrdemServicoInstalacaoHM = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICOINSTALACAOHM));
		vo.numeroHidrometro = cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))!=null) {
			vo.dataInstalacaoHidrometro = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO)));
		}
		vo.idTipoMedicao = cursor.getString(cursor.getColumnIndex(COL_IDTIPOMEDICAO));
		vo.idLocalInstalacaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDLOCALINSTALACAOHIDROMETRO));
		vo.idProtecaoHidrometro = cursor.getInt(cursor.getColumnIndex(COL_IDPROTECAOHIDROMETRO));
		vo.idOrdemServico = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO));
		vo.indicadorTrocaProtecao = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAPROTECAO));
		vo.indicadorTrocaRegistro = cursor.getInt(cursor.getColumnIndex(COL_INDICADORTROCAREGISTRO));
		vo.leituraInstalacao = cursor.getInt(cursor.getColumnIndex(COL_LEITURAINSTALACAO));
		vo.numeroSelo = cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO));
		vo.indcadorCavalete = cursor.getString(cursor.getColumnIndex(COL_INDCADORCAVALETE));
		vo.idFuncionario = cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO));
		vo.idTipoInstalacaoHM = cursor.getInt(cursor.getColumnIndex(COL_IDTIPOINSTALACAOHM));
		vo.horaInstalacaoHidrometro = cursor.getString(cursor.getColumnIndex(COL_HORAINSTALACAOHIDROMETRO));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		
		return vo;
	}
}
