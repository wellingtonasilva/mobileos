package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.VeiculosVO;

public class VeiculosDAO extends BasicDAO<VeiculosVO>
{
	public static final String COL_ID="_id";
	public static final String COL_NUMEROPLACAVEICULO="nnplacaveiculo";
	public static final String COL_MODELO="modelo";
	public static final String COL_TIPOVEICULO="tipomodelo";
	public static final String COL_MARCA="marca";
	public static final String COL_KMACUMULADA="kmacumulada";
	public static final String COL_DATAULTIMAREVISAO="dtultimarevisao";
	public static final String TABLE_NAME="veiculos";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 						+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_NUMEROPLACAVEICULO 		+ " TEXT,"
			+ 	COL_MODELO 					+ " TEXT,"
			+ 	COL_TIPOVEICULO 			+ " TEXT,"
			+ 	COL_MARCA 					+ " TEXT,"
			+ 	COL_KMACUMULADA 			+ " INTEGER,"
			+ 	COL_DATAULTIMAREVISAO 		+ " TEXT"
			+ ");";
	
	public VeiculosDAO(Context context) {
		super(context);
	}

	@Override
	public long inserir(VeiculosVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(VeiculosVO vo) 
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
	public List<VeiculosVO> listar()
	{
		List<VeiculosVO> data = new ArrayList<VeiculosVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			VeiculosVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public VeiculosVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public VeiculosVO obterPorNumeroPlaca(String placa) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_NUMEROPLACAVEICULO+ "=?", 
				new String[]{String.valueOf(placa)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}	

	@Override
	public ContentValues obterContentValues(VeiculosVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_NUMEROPLACAVEICULO, vo.numeroPlacaVeiculo);
		values.put(COL_MODELO, vo.modelo);
		values.put(COL_TIPOVEICULO, vo.tipoVeiculo);
		values.put(COL_MARCA, vo.marca);
		values.put(COL_KMACUMULADA, vo.kmAcumulada);
		values.put(COL_DATAULTIMAREVISAO, vo.dataUltimaRevisao);

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
	public VeiculosVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		VeiculosVO vo = new VeiculosVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.numeroPlacaVeiculo=cursor.getString(cursor.getColumnIndex(COL_NUMEROPLACAVEICULO));
		vo.modelo=cursor.getString(cursor.getColumnIndex(COL_MODELO));
		vo.tipoVeiculo=cursor.getString(cursor.getColumnIndex(COL_TIPOVEICULO));
		vo.marca=cursor.getString(cursor.getColumnIndex(COL_MARCA));
		vo.kmAcumulada=cursor.getInt(cursor.getColumnIndex(COL_KMACUMULADA));
		vo.dataUltimaRevisao=cursor.getString(cursor.getColumnIndex(COL_DATAULTIMAREVISAO));
		
		return vo;
	}

	@Override
	public VeiculosVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}
		
		String[] values=line.split(";");
		VeiculosVO vo=new VeiculosVO();
		//N�mero da Placa
		vo.numeroPlacaVeiculo=values[4];
		//Modelo
		vo.modelo=values[9];
		//Tipo de Ve�culo
		vo.tipoVeiculo=values[10];
		//Marca
		vo.marca=values[8];
		//Km Acumulada
		if (values[5].length() > 0) {
			vo.kmAcumulada=Integer.parseInt(values[5]);
		}
		//Data �ltima Revis�o
		vo.dataUltimaRevisao=values[6];
		
		return vo;
	}
}
