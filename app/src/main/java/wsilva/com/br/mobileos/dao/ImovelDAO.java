package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.ImovelVO;

public class ImovelDAO extends BasicDAO<ImovelVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDIMOVEL="idimovel";
	public static final String COL_IDSITUACAOLIGACAOAGUA="idsitligacao_agua";
	public static final String COL_DESCRICAOSITUACAOLIGACAOAGUA="dssitligacao_agua";
	public static final String COL_IDSITUACAOLIGACAOESGOTO="idsitligacao_esgoto";
	public static final String COL_DESCRICAOSITUACAOLIGACAOESGOTO="dssitligacao_esgoto";
	public static final String COL_NUMEROINSCRICAO="nninscricao";
	public static final String COL_NOMECLIENTERESPONSAVEL="nmcliente_responsavel";
	public static final String COL_NOMECLIENTEUSUARIO="nmcliente_usuario";
	public static final String COL_NOMECLIENTEPROPRIETARIO="nmcliente_proprietario";
	public static final String COL_NUMEROCORTES="nncortes";
	public static final String COL_NUMEROSUPRESSOES="nnsupressoes";
	public static final String COL_NUMEROREPARCELAMENTOS="nnreparcelamentos";
	public static final String COL_DIAVENCIMENTO="nndiavencimento";
	public static final String COL_INDICADORSITUACAOESPECIALCOBRANCA="icsituacaocobranca";
	public static final String COL_INDICADORSITUACAOESPECIALFATURAMENTO="icsituacaofaturamento";
	public static final String COL_IDGRUPOFATURAMENTO="idgrupofaturamento";
	public static final String COL_NUMEROROTA="nnrota";
	public static final String COL_SEQUENCIAROTA="nnseqrota";
	public static final String COL_DATALIGACAO="dtligacao";
	public static final String COL_NUMEROHIDROMETRO="nnhidrometro";
	public static final String COL_DATAINSTALACAOHIDROMETRO="dtinstalacaohidrometro";
	public static final String TABLE_NAME="imovel";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDIMOVEL 							+ " INTEGER,"
			+ COL_IDSITUACAOLIGACAOAGUA 			+ " INTEGER,"
			+ COL_DESCRICAOSITUACAOLIGACAOAGUA 		+ " TEXT,"
			+ COL_IDSITUACAOLIGACAOESGOTO 			+ " INTEGER,"
			+ COL_DESCRICAOSITUACAOLIGACAOESGOTO 	+ " TEXT,"
			+ COL_NUMEROINSCRICAO 					+ " TEXT,"
			+ COL_NOMECLIENTERESPONSAVEL 			+ " TEXT,"
			+ COL_NOMECLIENTEUSUARIO 				+ " TEXT,"
			+ COL_NOMECLIENTEPROPRIETARIO 			+ " TEXT,"
			+ COL_NUMEROCORTES 						+ " INTEGER,"
			+ COL_NUMEROSUPRESSOES 					+ " INTEGER,"
			+ COL_NUMEROREPARCELAMENTOS 			+ " INTEGER,"
			+ COL_DIAVENCIMENTO 					+ " INTEGER,"
			+ COL_INDICADORSITUACAOESPECIALCOBRANCA + " INTEGER,"
			+ COL_INDICADORSITUACAOESPECIALFATURAMENTO + " INTEGER,"
			+ COL_IDGRUPOFATURAMENTO 				+ " INTEGER,"
			+ COL_NUMEROROTA 						+ " INTEGER,"
			+ COL_SEQUENCIAROTA 					+ " INTEGER,"
			+ COL_DATALIGACAO 						+ " DATE,"
			+ COL_NUMEROHIDROMETRO 					+ " TEXT,"
			+ COL_DATAINSTALACAOHIDROMETRO 			+ " DATE"
			+ ");";
	
	public ImovelDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ImovelVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(ImovelVO vo) 
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
	public List<ImovelVO> listar() 
	{
		List<ImovelVO> data = new ArrayList<ImovelVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ImovelVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public ImovelVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ImovelVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDIMOVEL, vo.getIdImovel());
		values.put(COL_IDSITUACAOLIGACAOAGUA, vo.getIdSituacaoLigacaoAgua());
		values.put(COL_DESCRICAOSITUACAOLIGACAOAGUA, vo.getDescricaoSituacaoLigacaoAgua());
		values.put(COL_IDSITUACAOLIGACAOESGOTO, vo.getIdSituacaoLigacaoEsgoto());
		values.put(COL_DESCRICAOSITUACAOLIGACAOESGOTO, vo.getDescricaoSituacaoLigacaoEsgoto());
		values.put(COL_NUMEROINSCRICAO, vo.getNumeroInscricao());
		values.put(COL_NOMECLIENTERESPONSAVEL, vo.getNomeClienteResponsavel());
		values.put(COL_NOMECLIENTEUSUARIO, vo.getNomeClienteUsuario());
		values.put(COL_NOMECLIENTEPROPRIETARIO, vo.getNomeClienteProprietario());
		values.put(COL_NUMEROCORTES, vo.getNumeroCortes());
		values.put(COL_NUMEROSUPRESSOES, vo.getNumeroSupressoes());
		values.put(COL_NUMEROREPARCELAMENTOS, vo.getNumeroReparcelamentos());
		values.put(COL_DIAVENCIMENTO, vo.getDiaVencimento());
		values.put(COL_INDICADORSITUACAOESPECIALCOBRANCA, vo.getIndicadorSituacaoEspecialCobranca());
		values.put(COL_INDICADORSITUACAOESPECIALFATURAMENTO, vo.getIndicadorSituacaoEspecialFaturamento());
		values.put(COL_IDGRUPOFATURAMENTO, vo.getIdGrupoFaturamento());
		values.put(COL_NUMEROROTA, vo.getNumeroRota());
		values.put(COL_SEQUENCIAROTA, vo.getSequenciaRota());
		if (vo.getDataLigacao()!=null) {
			values.put(COL_DATALIGACAO, Util.dateToString("yyyy-MM-dd", vo.getDataLigacao()));
		}
		values.put(COL_NUMEROHIDROMETRO, vo.getNumeroHidrometro());
		if (vo.getDataInstalacaoHidrometro()!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRO, Util.dateToString("yyyy-MM-dd", vo.getDataInstalacaoHidrometro()));	
		}
		
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
	public ImovelVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ImovelVO vo = new ImovelVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdImovel(cursor.getInt(cursor.getColumnIndex(COL_IDIMOVEL)));
		vo.setIdSituacaoLigacaoAgua(cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOLIGACAOAGUA)));
		vo.setDescricaoSituacaoLigacaoAgua(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOLIGACAOAGUA)));
		vo.setIdSituacaoLigacaoEsgoto(cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOLIGACAOESGOTO)));
		vo.setDescricaoSituacaoLigacaoEsgoto(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOLIGACAOESGOTO)));
		vo.setNumeroInscricao(cursor.getString(cursor.getColumnIndex(COL_NUMEROINSCRICAO)));
		vo.setNomeClienteResponsavel(cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTERESPONSAVEL)));
		vo.setNomeClienteUsuario(cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTEUSUARIO)));
		vo.setNomeClienteProprietario(cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTEPROPRIETARIO)));
		vo.setNumeroCortes(cursor.getInt(cursor.getColumnIndex(COL_NUMEROCORTES)));
		vo.setNumeroSupressoes(cursor.getInt(cursor.getColumnIndex(COL_NUMEROSUPRESSOES)));
		vo.setNumeroReparcelamentos(cursor.getInt(cursor.getColumnIndex(COL_NUMEROREPARCELAMENTOS)));
		vo.setDiaVencimento(cursor.getInt(cursor.getColumnIndex(COL_DIAVENCIMENTO)));
		vo.setIndicadorSituacaoEspecialCobranca(cursor.getInt(cursor.getColumnIndex(COL_INDICADORSITUACAOESPECIALCOBRANCA)));
		vo.setIndicadorSituacaoEspecialFaturamento(cursor.getInt(cursor.getColumnIndex(COL_INDICADORSITUACAOESPECIALFATURAMENTO)));
		vo.setIdGrupoFaturamento(cursor.getInt(cursor.getColumnIndex(COL_IDGRUPOFATURAMENTO)));
		vo.setNumeroRota(cursor.getInt(cursor.getColumnIndex(COL_NUMEROROTA)));
		vo.setSequenciaRota(cursor.getInt(cursor.getColumnIndex(COL_SEQUENCIAROTA)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATALIGACAO))!=null) {
			vo.setDataLigacao(Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATALIGACAO))));	
		}
		vo.setNumeroHidrometro(cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))!=null) {
			vo.setDataInstalacaoHidrometro(Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))));	
		}
		
		return vo;
	}

	@Override
	public ImovelVO obterObject(String line) 
	{
		return super.obterObject(line);
	}

	@Override
	public ImovelVO obterObject(JSONObject line) 
	{
		return super.obterObject(line);
	}

}
