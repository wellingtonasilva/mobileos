package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.OrdemServicoCorteVO;

public class OrdemServicoCorteDAO extends BasicDAO<OrdemServicoCorteVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDORDEMSERVICOCORTE="idoscorte";
	public static final String COL_IDORDEMSERVICO="idordemservico";
	public static final String COL_DATACORTE="dtcorte";
	public static final String COL_HORACORTE="tmcorte";
	public static final String COL_LEITURACORTE="nnleituracorte";
	public static final String COL_NUMEROSELO="nnselo";
	public static final String COL_IDFUNCIONARIO="idfuncionario";
	public static final String COL_NUMEROHIDROMETRO="nnhidrometro";
	public static final String COL_IDMOTIVOCORTE="idmotivocorte";
	public static final String COL_IDCORTETIPO="idcortetipo";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String TABLE_NAME="os_corte";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDORDEMSERVICOCORTE 		+ " INTEGER,"
			+ COL_IDORDEMSERVICO 			+ " INTEGER,"
			+ COL_DATACORTE 				+ " DATE,"
			+ COL_HORACORTE 				+ " TEXT,"
			+ COL_LEITURACORTE 				+ " INTEGER,"
			+ COL_NUMEROSELO 				+ " TEXT,"
			+ COL_IDFUNCIONARIO 			+ " TEXT,"
			+ COL_NUMEROHIDROMETRO 			+ " TEXT,"
			+ COL_IDMOTIVOCORTE 			+ " INTEGER,"
			+ COL_IDCORTETIPO				+ " INTEGER,"
			+ COL_IDEQUIPEEXECUCAO 			+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 	+ " TEXT,"
			+ COL_INDICADORENVIO			+ " INTEGER"
			+ ");";
	
	public OrdemServicoCorteDAO(Context context) 
	{
		super(context);
	}
	
	@Override
	public long inserir(OrdemServicoCorteVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(OrdemServicoCorteVO vo) 
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
	public List<OrdemServicoCorteVO> listar() 
	{
		List<OrdemServicoCorteVO> data = new ArrayList<OrdemServicoCorteVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			OrdemServicoCorteVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public OrdemServicoCorteVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public OrdemServicoCorteVO obterPorNumeroOs(int id) 
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
	public ContentValues obterContentValues(OrdemServicoCorteVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDORDEMSERVICOCORTE, vo.idOrdemServicoCorte);
		values.put(COL_IDORDEMSERVICO, vo.idOrdemServico);
		if (vo.dataCorte!=null) {
			values.put(COL_DATACORTE, Util.dateToString("yyyy-MM-dd", vo.dataCorte));
		}
		values.put(COL_HORACORTE, vo.horaCorte);
		values.put(COL_LEITURACORTE, vo.leituraCorte);
		values.put(COL_NUMEROSELO, vo.numeroSelo);
		values.put(COL_IDFUNCIONARIO, vo.idFuncionario);
		values.put(COL_NUMEROHIDROMETRO, vo.numeroHidrometro);
		values.put(COL_IDMOTIVOCORTE, vo.idMotivoCorte);
		values.put(COL_IDCORTETIPO, vo.idCorteTipo);
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
	public OrdemServicoCorteVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		OrdemServicoCorteVO vo = new OrdemServicoCorteVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idOrdemServicoCorte = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICOCORTE));
		vo.idOrdemServico = cursor.getInt(cursor.getColumnIndex(COL_IDORDEMSERVICO));
		if (cursor.getString(cursor.getColumnIndex(COL_DATACORTE)) !=null) {
			vo.dataCorte = Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATACORTE)));
		}
		vo.horaCorte = cursor.getString(cursor.getColumnIndex(COL_HORACORTE));
		vo.leituraCorte = cursor.getInt(cursor.getColumnIndex(COL_LEITURACORTE));
		vo.numeroSelo = cursor.getString(cursor.getColumnIndex(COL_NUMEROSELO));
		vo.idFuncionario = cursor.getString(cursor.getColumnIndex(COL_IDFUNCIONARIO));
		vo.numeroHidrometro = cursor.getString(cursor.getColumnIndex(COL_NUMEROHIDROMETRO));
		vo.idMotivoCorte = cursor.getInt(cursor.getColumnIndex(COL_IDMOTIVOCORTE));
		vo.idCorteTipo = cursor.getInt(cursor.getColumnIndex(COL_IDCORTETIPO));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		
		return vo;
	}
}
