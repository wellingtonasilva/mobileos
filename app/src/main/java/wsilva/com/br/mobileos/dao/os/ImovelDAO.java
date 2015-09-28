package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.ImovelVO;

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
		values.put(COL_IDIMOVEL, vo.idImovel);
		values.put(COL_IDSITUACAOLIGACAOAGUA, vo.idSituacaoLigacaoAgua);
		values.put(COL_DESCRICAOSITUACAOLIGACAOAGUA, vo.descricaoSituacaoLigacaoAgua);
		values.put(COL_IDSITUACAOLIGACAOESGOTO, vo.idSituacaoLigacaoEsgoto);
		values.put(COL_DESCRICAOSITUACAOLIGACAOESGOTO, vo.descricaoSituacaoLigacaoEsgoto);
		values.put(COL_NUMEROINSCRICAO, vo.numeroInscricao);
		values.put(COL_NOMECLIENTERESPONSAVEL, vo.nomeClienteResponsavel);
		values.put(COL_NOMECLIENTEUSUARIO, vo.nomeClienteUsuario);
		values.put(COL_NOMECLIENTEPROPRIETARIO, vo.nomeClienteProprietario);
		values.put(COL_NUMEROCORTES, vo.numeroCortes);
		values.put(COL_NUMEROSUPRESSOES, vo.numeroSupressoes);
		values.put(COL_NUMEROREPARCELAMENTOS, vo.numeroReparcelamentos);
		values.put(COL_DIAVENCIMENTO, vo.diaVencimento);
		values.put(COL_INDICADORSITUACAOESPECIALCOBRANCA, vo.indicadorSituacaoEspecialCobranca);
		values.put(COL_INDICADORSITUACAOESPECIALFATURAMENTO, vo.indicadorSituacaoEspecialFaturamento);
		values.put(COL_IDGRUPOFATURAMENTO, vo.idGrupoFaturamento);
		values.put(COL_NUMEROROTA, vo.numeroRota);
		values.put(COL_SEQUENCIAROTA, vo.sequenciaRota);
		if (vo.dataLigacao!=null) {
			values.put(COL_DATALIGACAO, Util.dateToString("yyyy-MM-dd", vo.dataLigacao));
		}
		values.put(COL_NUMEROHIDROMETRO, vo.numeroHidrometro);
		if (vo.dataInstalacaoHidrometro!=null) {
			values.put(COL_DATAINSTALACAOHIDROMETRO, Util.dateToString("yyyy-MM-dd", vo.dataInstalacaoHidrometro));
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
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idImovel = cursor.getInt(cursor.getColumnIndex(COL_IDIMOVEL));
		vo.idSituacaoLigacaoAgua = cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOLIGACAOAGUA));
		vo.descricaoSituacaoLigacaoAgua = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOLIGACAOAGUA));
		vo.idSituacaoLigacaoEsgoto = cursor.getInt(cursor.getColumnIndex(COL_IDSITUACAOLIGACAOESGOTO));
		vo.descricaoSituacaoLigacaoEsgoto = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOSITUACAOLIGACAOESGOTO));
		vo.numeroInscricao = cursor.getString(cursor.getColumnIndex(COL_NUMEROINSCRICAO));
		vo.nomeClienteResponsavel = cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTERESPONSAVEL));
		vo.nomeClienteUsuario = cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTEUSUARIO));
		vo.nomeClienteProprietario = cursor.getString(cursor.getColumnIndex(COL_NOMECLIENTEPROPRIETARIO));
		vo.numeroCortes = cursor.getInt(cursor.getColumnIndex(COL_NUMEROCORTES));
		vo.numeroSupressoes = cursor.getInt(cursor.getColumnIndex(COL_NUMEROSUPRESSOES));
		vo.numeroReparcelamentos = cursor.getInt(cursor.getColumnIndex(COL_NUMEROREPARCELAMENTOS));
		vo.diaVencimento = cursor.getInt(cursor.getColumnIndex(COL_DIAVENCIMENTO));
		vo.indicadorSituacaoEspecialCobranca = cursor.getInt(cursor.getColumnIndex(COL_INDICADORSITUACAOESPECIALCOBRANCA));
		vo.indicadorSituacaoEspecialFaturamento = cursor.getInt(cursor.getColumnIndex(COL_INDICADORSITUACAOESPECIALFATURAMENTO));
		vo.idGrupoFaturamento = cursor.getInt(cursor.getColumnIndex(COL_IDGRUPOFATURAMENTO));
		vo.numeroRota = cursor.getInt(cursor.getColumnIndex(COL_NUMEROROTA));
		vo.sequenciaRota = cursor.getInt(cursor.getColumnIndex(COL_SEQUENCIAROTA));
		if (cursor.getString(cursor.getColumnIndex(COL_DATALIGACAO))!=null) {
			vo.dataLigacao = Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATALIGACAO)));
		}
		vo.numeroHidrometro = cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO))!=null) {
			vo.dataInstalacaoHidrometro = Util.stringToDate("yyyy-MM-dd",cursor.getString(cursor.getColumnIndex(COL_DATAINSTALACAOHIDROMETRO)));
		}
		
		return vo;
	}
}
