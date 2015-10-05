package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.FotoVO;

public class FotoDAO extends BasicDAO<FotoVO>
{
	public static final String COL_ID="_id";
	public static final String COL_NUMEROOS="numeroos";
	public static final String COL_NOMEFOTO="nmfoto";
	public static final String COL_DESCRICAOFOTO="dsfoto";
	public static final String COL_LATITUDE="nnlatitude";
	public static final String COL_LONGITUDE="nnlongitude";
	public static final String COL_DATAFOTO="dtfoto";
	public static final String COL_HORAFOTO="tmfoto";
	public static final String COL_TIPOFOTO="tipofoto";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String COL_NUMEROFOTO="nnfoto";
	public static final String COL_FILENAME="dsfilename";
	public static final String TABLE_NAME="foto";	
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_NUMEROOS 				+ " INTEGER,"
			+ 	COL_NOMEFOTO 				+ " TEXT,"
			+ 	COL_DESCRICAOFOTO 			+ " TEXT,"
			+ 	COL_LATITUDE 				+ " REAL,"
			+ 	COL_LONGITUDE 				+ " REAL,"
			+ 	COL_DATAFOTO 				+ " DATE,"
			+ 	COL_HORAFOTO 				+ " TEXT,"
			+ 	COL_TIPOFOTO 				+ " INTEGER,"
			+ 	COL_IDEQUIPEEXECUCAO 		+ " INTEGER,"
			+ 	COL_DESCRICAOEQUIPEEXECUCAO + " TEXT,"
			+ 	COL_INDICADORENVIO			+ " INTEGER,"
			+ 	COL_NUMEROFOTO				+ " INTEGER,"
			+ 	COL_FILENAME 				+ " TEXT"
			+ 	");";
	
	public FotoDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(FotoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		//Define o n�mero da foto
		if (vo.numeroFoto==0) {
			vo.numeroFoto=obterNumeroFoto(vo.numeroOS);
		}
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(FotoVO vo) 
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
	public List<FotoVO> listar() 
	{
		List<FotoVO> data = new ArrayList<FotoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			FotoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	//Listar somente as Fotos referente a uma deteriminada OS
	public List<FotoVO> listarPorOrdemServico(int numeroOS) 
	{
		List<FotoVO> data = new ArrayList<FotoVO>();
		Cursor cursor = obterCursorPorOrdemServico(numeroOS);
		while (!cursor.isAfterLast())
		{
			FotoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public FotoVO obterPorId(long id) 
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
	public ContentValues obterContentValues(FotoVO vo) 
	{
		ContentValues values=new ContentValues();
		
		if (vo.dataFoto!=null)
		{
			values.put(COL_DATAFOTO, vo.dataFoto.getTime());	
		}
		values.put(COL_DESCRICAOFOTO, vo.descricaoFoto);
		values.put(COL_HORAFOTO, vo.horaFoto);
		values.put(COL_LATITUDE, vo.latitude);
		values.put(COL_LONGITUDE, vo.longitude);
		values.put(COL_NOMEFOTO, vo.nomeFoto);
		values.put(COL_NUMEROOS, vo.numeroOS);
		values.put(COL_TIPOFOTO, vo.tipoFoto);
		values.put(COL_IDEQUIPEEXECUCAO, vo.idEquipeExecucao);
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.descricaoEquipeExecucao);
		values.put(COL_INDICADORENVIO, vo.indicadorEnvio);
		values.put(COL_NUMEROFOTO, vo.numeroFoto);
		values.put(COL_FILENAME, vo.filename);
		
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
	
	public Cursor obterCursorPorOrdemServico(int numeroOS)
	{
		Cursor cursor = db.query(TABLE_NAME, null, COL_NUMEROOS+"=?", new String[]{String.valueOf(numeroOS)}, 
				null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}
	
	public int obterNumeroFoto(int numeroOS)
	{
		int ultimoRegistro=0;
		Cursor cursor= rawQuery
			(	"select coalesce(max(" + COL_NUMEROFOTO + "),0) from " + TABLE_NAME 
			+  	" where " + COL_NUMEROOS + "=" + String.valueOf(numeroOS), null);
		if (cursor!=null && cursor.getCount() > 0) 
		{	
			ultimoRegistro= (cursor.getInt(0) == 0 ? 1 : cursor.getInt(0)+1);
		} else {
			ultimoRegistro=1;
		}
		
		return ultimoRegistro;
	}

	@Override
	public FotoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		FotoVO vo = new FotoVO();
		vo.dataFoto = new Date(cursor.getLong(cursor.getColumnIndex(COL_DATAFOTO)));
		vo.descricaoFoto = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOFOTO));
		vo._id  = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.horaFoto = cursor.getString(cursor.getColumnIndex(COL_HORAFOTO));
		vo.latitude = cursor.getDouble(cursor.getColumnIndex(COL_LATITUDE));
		vo.longitude = cursor.getDouble(cursor.getColumnIndex(COL_LONGITUDE));
		vo.nomeFoto = cursor.getString(cursor.getColumnIndex(COL_NOMEFOTO));
		vo.numeroOS = cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS));
		vo.tipoFoto = cursor.getInt(cursor.getColumnIndex(COL_TIPOFOTO));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		vo.numeroFoto = cursor.getInt(cursor.getColumnIndex(COL_NUMEROFOTO));
		vo.filename = cursor.getString(cursor.getColumnIndex(COL_FILENAME));
		
		return vo;
	}
}
