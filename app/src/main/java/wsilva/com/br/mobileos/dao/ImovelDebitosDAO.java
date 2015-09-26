package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.ImovelDebitosVO;

public class ImovelDebitosDAO extends BasicDAO<ImovelDebitosVO>
{
	public static final String COL_ID="_id";
	public static final String COL_IDIMOVELDEBITOS="idimoveldebitos";
	public static final String COL_IDIMOVEL="idimovel";
	public static final String COL_REFERENCIA="referencia";
	public static final String COL_DATAVENCIMENTO="dtvencimento";
	public static final String COL_DESCRICAOTIPODOCUMENTO="dstipodocumento";
	public static final String COL_VALORDOCUMENTO="vldocumento";
	public static final String COL_VALORACRESCIMENTO="vlacrescimo";
	public static final String COL_VALORTOTAL="vltotal";
	public static final String COL_NUMERODIASVENCIDO="diasvencido";
	public static final String TABLE_NAME="imovel_debitos";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDIMOVELDEBITOS			+ " INTEGER,"
			+ COL_IDIMOVEL					+ " INTEGER,"
			+ COL_REFERENCIA				+ " INTEGER,"
			+ COL_DATAVENCIMENTO			+ " DATE,"
			+ COL_DESCRICAOTIPODOCUMENTO 	+ " TEXT," 
			+ COL_VALORDOCUMENTO			+ " FLOAT,"
			+ COL_VALORACRESCIMENTO			+ " FLOAT,"
			+ COL_VALORTOTAL				+ " FLOAT,"
			+ COL_NUMERODIASVENCIDO			+ " INTEGER"			
			+ ");";
	
	public ImovelDebitosDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(ImovelDebitosVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(ImovelDebitosVO vo) 
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
	public List<ImovelDebitosVO> listar() 
	{
		List<ImovelDebitosVO> data = new ArrayList<ImovelDebitosVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ImovelDebitosVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		cursor.close();
		return data;
	}

	@Override
	public ImovelDebitosVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ImovelDebitosVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDIMOVELDEBITOS, vo.getIdImovelDebitos());
		values.put(COL_IDIMOVEL, vo.getIdImovel());
		values.put(COL_REFERENCIA, vo.getReferencia());
		if (vo.getDataVencimento()!=null) {
			values.put(COL_DATAVENCIMENTO, Util.dateToString("yyyy-MM-dd", vo.getDataVencimento()));
		}
		values.put(COL_DESCRICAOTIPODOCUMENTO, vo.getDescricaoTipoDocumento()); 
		values.put(COL_VALORDOCUMENTO, vo.getValorDocumento());
		values.put(COL_VALORACRESCIMENTO, vo.getValorAcrescimento());
		values.put(COL_VALORTOTAL, vo.getValorTotal());
		values.put(COL_NUMERODIASVENCIDO, vo.getNumeroDiasVencido());
		
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
	public ImovelDebitosVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ImovelDebitosVO vo = new ImovelDebitosVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIdImovelDebitos(cursor.getInt(cursor.getColumnIndex(COL_IDIMOVELDEBITOS)));
		vo.setIdImovel(cursor.getInt(cursor.getColumnIndex(COL_IDIMOVEL)));
		vo.setReferencia(cursor.getInt(cursor.getColumnIndex(COL_REFERENCIA)));
		if (cursor.getString(cursor.getColumnIndex(COL_DATAVENCIMENTO))!=null) {
			vo.setDataVencimento(Util.stringToDate("yyyy-MM-dd", cursor.getString(cursor.getColumnIndex(COL_DATAVENCIMENTO))));	
		}
		vo.setDescricaoTipoDocumento(cursor.getString(cursor.getColumnIndex(COL_DESCRICAOTIPODOCUMENTO)));
		vo.setValorDocumento(cursor.getDouble(cursor.getColumnIndex(COL_VALORDOCUMENTO)));
		vo.setValorAcrescimento(cursor.getDouble(cursor.getColumnIndex(COL_VALORACRESCIMENTO)));
		vo.setValorTotal(cursor.getDouble(cursor.getColumnIndex(COL_VALORTOTAL)));
		vo.setValorAcrescimento(cursor.getDouble(cursor.getColumnIndex(COL_NUMERODIASVENCIDO)));

		return vo;
	}

	@Override
	public ImovelDebitosVO obterObject(String line) 
	{
		return super.obterObject(line);
	}

	@Override
	public ImovelDebitosVO obterObject(JSONObject line) 
	{
		return super.obterObject(line);
	}
	
	public static void povoaTese(Context ctx)
	{
		ImovelDebitosDAO dao=new ImovelDebitosDAO(ctx);
		ImovelDebitosVO vo=new ImovelDebitosVO();
		
		dao.removerTodos();
		
		//Inserir
		vo.setDataVencimento(new Date());
		vo.setDescricaoTipoDocumento("Fatura Mensal");
		vo.setIdImovel(999);
		vo.setNumeroDiasVencido(10);
		vo.setReferencia(201201);
		vo.setValorAcrescimento(0.0);
		vo.setValorDocumento(10.00);
		vo.setValorTotal(10.00);
		dao.inserir(vo);
		
		vo.setDataVencimento(new Date());
		vo.setDescricaoTipoDocumento("Fatura Mensal");
		vo.setIdImovel(999);
		vo.setNumeroDiasVencido(10);
		vo.setReferencia(201202);
		vo.setValorAcrescimento(0.0);
		vo.setValorDocumento(20.00);
		vo.setValorTotal(20.00);
		dao.inserir(vo);
		
		vo.setDataVencimento(new Date());
		vo.setDescricaoTipoDocumento("Fatura Mensal");
		vo.setIdImovel(999);
		vo.setNumeroDiasVencido(10);
		vo.setReferencia(201203);
		vo.setValorAcrescimento(0.0);
		vo.setValorDocumento(30.00);
		vo.setValorTotal(30.00);
		dao.inserir(vo);
	}
		
		

}
