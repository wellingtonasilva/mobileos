package wsilva.com.br.mobileos.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.InterrupcaoVO;

public class InterrupcaoDAO extends BasicDAO<InterrupcaoVO> {

	public static final String COL_ID="_id";
	public static final String COL_DATAMOVIMENTO="dtmovimento";
	public static final String COL_NOMEEQUIPE="nmequipe";
	public static final String COL_MATRICULA="nnmatricula";
	public static final String COL_NUMEROOS="nnos";
	public static final String COL_IDINTERRUPCAOMOTIVO="idmotivo";
	public static final String COL_DESCRICAOINTERRUPCAOMOTIVO="dsmotivo";
	public static final String COL_OBSERVACAOINICIO="dsobsinicio";
	public static final String COL_OBSERVACAOFIM="dsobsfim";
	public static final String COL_DATAINICIO="dtinicio";
	public static final String COL_HORAINICIO="hrinicio";
	public static final String COL_DATAFIM="dtfim";
	public static final String COL_HORAFIM="hrfim";
	public static final String COL_INDICADORATIVO="icativo";
	public static final String COL_INDICADORENVIOUSMSINICIO="icenviousmsinicio";
	public static final String COL_INDICADORENVIOUSMSFIM="icenviousmsfim";
	public static final String COL_KMINICIAL="kminicial";
	public static final String COL_KMFINAL="kmfinal";
	public static final String TABLE_NAME="interrupcao";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_DATAMOVIMENTO 				+ " INTEGER,"
			+ COL_NOMEEQUIPE 					+ " TEXT,"
			+ COL_MATRICULA 					+ " INTEGER,"
			+ COL_NUMEROOS 						+ " INTEGER,"
			+ COL_IDINTERRUPCAOMOTIVO 			+ " INTEGER,"
			+ COL_DESCRICAOINTERRUPCAOMOTIVO 	+ " TEXT,"
			+ COL_OBSERVACAOINICIO 				+ " TEXT,"
			+ COL_OBSERVACAOFIM 				+ " TEXT,"
			+ COL_DATAINICIO 					+ " TEXT,"
			+ COL_HORAINICIO 					+ " TEXT,"
			+ COL_DATAFIM 						+ " TEXT,"
			+ COL_HORAFIM 						+ " TEXT,"
			+ COL_INDICADORATIVO 				+ " TEXT,"
			+ COL_INDICADORENVIOUSMSINICIO 		+ " TEXT,"
			+ COL_INDICADORENVIOUSMSFIM 		+ " TEXT,"
			+ COL_KMINICIAL						+ " INTEGER,"
			+ COL_KMFINAL						+ " INTEGER"
			+ ");";
	
	public InterrupcaoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(InterrupcaoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(InterrupcaoVO vo) 
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
	public List<InterrupcaoVO> listar() 
	{
		List<InterrupcaoVO> data = new ArrayList<InterrupcaoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			InterrupcaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	//Listar somente as Valas referente a uma deteriminada OS
	public List<InterrupcaoVO> listarPorOrdemServico(int numeroOS) 
	{
		List<InterrupcaoVO> data = new ArrayList<InterrupcaoVO>();
		Cursor cursor = obterCursorPorOrdemServico(numeroOS);
		while (!cursor.isAfterLast())
		{
			InterrupcaoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public InterrupcaoVO obterPorId(long id)
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public InterrupcaoVO obterPorIdInterrupcaoMotivo(int id)
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_IDINTERRUPCAOMOTIVO+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public InterrupcaoVO obterPorNaoFinalizada()
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_DATAFIM+ " is null", 
				null, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public boolean isInterrupcaoNaoFinalizada() 
	{
		boolean exist=false;
		Cursor cursor= rawQuery("select count(*) from " + TABLE_NAME 
				+ " where " + COL_DATAFIM + " is null", null);
		if (cursor!=null && cursor.getCount() > 0) {
			exist= (cursor.getInt(0) == 0 ? false : true);
		} 
		return exist;
	}	
	
	@Override
	public ContentValues obterContentValues(InterrupcaoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DATAMOVIMENTO, vo.dataMovimento);
		values.put(COL_NOMEEQUIPE, vo.nomeEquipe);
		values.put(COL_MATRICULA, vo.matricula);
		values.put(COL_NUMEROOS, vo.numeroOS);
		values.put(COL_IDINTERRUPCAOMOTIVO, vo.idInterrupcaoMotivo);
		values.put(COL_DESCRICAOINTERRUPCAOMOTIVO, vo.descricaoInterrupcaoMotivo);
		values.put(COL_OBSERVACAOINICIO, vo.observacaoInicio);
		values.put(COL_OBSERVACAOFIM, vo.observacaoFim);
		values.put(COL_DATAINICIO, vo.dataInicio);
		values.put(COL_HORAINICIO, vo.horaInicio);
		values.put(COL_DATAFIM, vo.dataFim);
		values.put(COL_HORAFIM, vo.horaFim);
		values.put(COL_INDICADORATIVO, vo.indicadorAtivo);
		values.put(COL_INDICADORENVIOUSMSINICIO, vo.indicadorEnviouSMSInicio);
		values.put(COL_INDICADORENVIOUSMSFIM, vo.indicadorEnviouSMSFim);
		values.put(COL_KMINICIAL, vo.kmInicial);
		values.put(COL_KMFINAL, vo.kmFinal);

		return values;
	}

	@Override
	public Cursor obterCursor()
	{
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_ID + " DESC");
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

	@Override
	public InterrupcaoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		InterrupcaoVO vo= new InterrupcaoVO();
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.dataMovimento=cursor.getString(cursor.getColumnIndex(COL_DATAMOVIMENTO));
		vo.nomeEquipe=cursor.getString(cursor.getColumnIndex(COL_NOMEEQUIPE));
		vo.matricula=cursor.getInt(cursor.getColumnIndex(COL_MATRICULA));
		vo.numeroOS=cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS));
		vo.idInterrupcaoMotivo= cursor.getInt(cursor.getColumnIndex(COL_IDINTERRUPCAOMOTIVO));
		vo.descricaoInterrupcaoMotivo= cursor.getString(cursor.getColumnIndex(COL_DESCRICAOINTERRUPCAOMOTIVO));
		vo.observacaoInicio=cursor.getString(cursor.getColumnIndex(COL_OBSERVACAOINICIO));
		vo.observacaoFim=cursor.getString(cursor.getColumnIndex(COL_OBSERVACAOFIM));
		vo.dataInicio=cursor.getString(cursor.getColumnIndex(COL_DATAINICIO));
		vo.horaInicio=cursor.getString(cursor.getColumnIndex(COL_HORAINICIO));
		vo.dataFim=cursor.getString(cursor.getColumnIndex(COL_DATAFIM));
		vo.horaFim=cursor.getString(cursor.getColumnIndex(COL_HORAFIM));
		vo.indicadorAtivo=cursor.getInt(cursor.getColumnIndex(COL_INDICADORATIVO));
		vo.indicadorEnviouSMSInicio= cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIOUSMSINICIO));
		vo.indicadorEnviouSMSFim= cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIOUSMSFIM));
		vo.kmInicial= cursor.getInt(cursor.getColumnIndex(COL_KMINICIAL));
		vo.kmFinal= cursor.getInt(cursor.getColumnIndex(COL_KMFINAL));
		
		return vo;
	}
	
	@Override
	public String obterLinhaCSV(InterrupcaoVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null) 
		{
			linha=  delimitador
					+ vo.dataMovimento 									+ ";"
					+ vo.nomeEquipe 									+ ";"
					+ String.valueOf(vo.matricula) 						+ ";"
					+ String.valueOf(vo.numeroOS) 						+ ";"
					+ String.valueOf(vo.idInterrupcaoMotivo) 			+ ";"
					+ vo.descricaoInterrupcaoMotivo 					+ ";"
					+ vo.observacaoInicio								+ ";"
					+ vo.observacaoFim 									+ ";"
					+ vo.dataInicio 									+ ";"
					+ vo.horaInicio										+ ";"
					+ vo.dataFim 										+ ";"
					+ vo.horaFim 										+ ";"
					+ String.valueOf(vo.indicadorAtivo) 				+ ";"
					+ String.valueOf(vo.indicadorEnviouSMSInicio)		+ ";"
					+ String.valueOf(vo.indicadorEnviouSMSFim) 			+ ";"
					+ String.valueOf(vo.kmInicial) 						+ ";"
					+ String.valueOf(vo.kmFinal) 						+ ";"
					+ "\n";
		}
		return linha;
	}

	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<InterrupcaoVO> valas=listar();
		int qtd=valas.size();
		String linha="";

		if (qtd>0){
			try 
			{
				File sdCard = Environment.getExternalStorageDirectory();
				File directory= new File(sdCard.getAbsolutePath() + directoryname);
				File file = new File(directory, filename);
				FileOutputStream output= new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(output);
				
				for (int i=0; i<qtd; i++)
				{
					InterrupcaoVO vo=valas.get(i);
					linha= obterLinhaCSV(vo, "");
					osw.write(linha);
				}
				osw.flush();
				osw.close();
				bolReturn=true;
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}
		return bolReturn;
	}

}
