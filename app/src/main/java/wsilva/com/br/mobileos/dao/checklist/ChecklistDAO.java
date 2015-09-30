package wsilva.com.br.mobileos.dao.checklist;

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
import wsilva.com.br.mobileos.entity.checklist.ChecklistVO;

public class ChecklistDAO extends BasicDAO<ChecklistVO>
{
	public static final String COL_ID="_id";
	public static final String COL_DATAMOVIMENTO="dtmovimento";
	public static final String COL_TIPOVEICULO="tipoveiculo";
	public static final String COL_MATRICULA="matricula";
	public static final String COL_NOMECONDUTOR="nmcondutor";
	public static final String COL_NOMESETOR="nmsetor";
	public static final String COL_DATASAIDA="dtsaida";
	public static final String COL_HORASAIDA="hrsaida";
	public static final String COL_KMSAIDA="kmsaida";
	public static final String COL_DATARETORNO="dtretorno";
	public static final String COL_HORARETORNO="hrretorno";
	public static final String COL_KMRETORNO="kmretorno";
	public static final String COL_NUMEROPLACAVEICULOO="nnplacaveiculo";
	public static final String COL_FINALIZOUSAIDA="finalizousaida";
	public static final String COL_FINALIZOURETORNO="finalizouretorno";
	public static final String COL_NUMEROCNH="nncnh";
	public static final String TABLE_NAME="checklist";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 						+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_DATAMOVIMENTO 			+ " TEXT,"
			+ 	COL_TIPOVEICULO 			+ " TEXT,"
			+ 	COL_MATRICULA 				+ " INTEGER,"
			+ 	COL_NOMECONDUTOR 			+ " TEXT,"
			+ 	COL_NOMESETOR 				+ " TEXT,"
			+ 	COL_DATASAIDA 				+ " TEXT,"
			+ 	COL_HORASAIDA 				+ " TEXT,"
			+ 	COL_KMSAIDA 				+ " INTEGER,"
			+ 	COL_DATARETORNO 			+ " TEXT,"
			+ 	COL_HORARETORNO 			+ " TEXT,"
			+ 	COL_KMRETORNO 				+ " INTEGER,"
			+ 	COL_NUMEROPLACAVEICULOO 	+ " TEXT,"
			+ 	COL_FINALIZOUSAIDA			+ " INTEGER,"
			+ 	COL_FINALIZOURETORNO		+ " INTEGER,"
			+ 	COL_NUMEROCNH				+ " TEXT"
			+ ");";
	
	public ChecklistDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ChecklistVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistVO vo) 
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
	public List<ChecklistVO> listar()
	{
		List<ChecklistVO> data = new ArrayList<ChecklistVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public ChecklistVO obterPorDataMovimento(String data) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_DATAMOVIMENTO+ "=?", 
				new String[]{String.valueOf(data)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}	
	
	public ChecklistVO obterUltimoChecklist() 
	{
		List<ChecklistVO> lista=listar();
		if (lista!=null && lista.size() > 0) {
			return lista.get(0);
		} else {
			return null;
		}
	}	

	@Override
	public ContentValues obterContentValues(ChecklistVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DATAMOVIMENTO, vo.dataMovimento);
		values.put(COL_TIPOVEICULO, vo.tipoVeiculo);
		values.put(COL_MATRICULA, vo.matricula);
		values.put(COL_NOMECONDUTOR, vo.nomeCondutor);
		values.put(COL_NOMESETOR, vo.nomeSetor);
		values.put(COL_DATASAIDA, vo.dataSaida);
		values.put(COL_HORASAIDA, vo.horaSaida);
		values.put(COL_KMSAIDA, vo.kmSaida);
		values.put(COL_DATARETORNO, vo.dataRetorno);
		values.put(COL_HORARETORNO, vo.horaRetorno);
		values.put(COL_KMRETORNO, vo.kmRetorno);
		values.put(COL_NUMEROPLACAVEICULOO, vo.numeroPlacaVeiculo);
		values.put(COL_FINALIZOUSAIDA, vo.finalizouSaida);
		values.put(COL_FINALIZOURETORNO, vo.finalizouRetorno);
		values.put(COL_NUMEROCNH, vo.numeroCNH);
		
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
	public ChecklistVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistVO vo = new ChecklistVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.dataMovimento=cursor.getString(cursor.getColumnIndex(COL_DATAMOVIMENTO));
		vo.tipoVeiculo=cursor.getString(cursor.getColumnIndex(COL_TIPOVEICULO));
		vo.matricula=cursor.getInt(cursor.getColumnIndex(COL_MATRICULA));
		vo.nomeCondutor=cursor.getString(cursor.getColumnIndex(COL_NOMECONDUTOR));
		vo.nomeSetor=cursor.getString(cursor.getColumnIndex(COL_NOMESETOR));
		vo.dataSaida=cursor.getString(cursor.getColumnIndex(COL_DATASAIDA));
		vo.horaSaida=cursor.getString(cursor.getColumnIndex(COL_HORASAIDA));
		vo.kmSaida=cursor.getInt(cursor.getColumnIndex(COL_KMSAIDA));
		vo.dataRetorno=cursor.getString(cursor.getColumnIndex(COL_DATARETORNO));
		vo.horaRetorno=cursor.getString(cursor.getColumnIndex(COL_HORARETORNO));
		vo.kmRetorno=cursor.getInt(cursor.getColumnIndex(COL_KMRETORNO));
		vo.numeroPlacaVeiculo=cursor.getString(cursor.getColumnIndex(COL_NUMEROPLACAVEICULOO));
		vo.finalizouSaida=cursor.getInt(cursor.getColumnIndex(COL_FINALIZOUSAIDA));
		vo.finalizouRetorno=cursor.getInt(cursor.getColumnIndex(COL_FINALIZOURETORNO));
		vo.numeroCNH=cursor.getString(cursor.getColumnIndex(COL_NUMEROCNH));
		
		return vo;
	}
	
	@Override
	public String obterLinhaCSV(ChecklistVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null) 
		{
			linha=  delimitador
					+ vo.dataMovimento 						+ ";"
					+ vo.tipoVeiculo 						+ ";"
					+ vo.matricula 							+ ";"
					+ vo.nomeCondutor 						+ ";"
					+ vo.nomeSetor 							+ ";"
					+ vo.dataSaida 							+ ";"
					+ vo.horaSaida 							+ ";"
					+ String.valueOf(vo.kmSaida) 			+ ";"
					+ vo.dataRetorno 						+ ";"
					+ vo.horaRetorno 						+ ";"
					+ String.valueOf(vo.kmRetorno) 			+ ";"
					+ vo.numeroPlacaVeiculo 				+ ";"
					+ vo.numeroCNH
					+ "\n";
		}
		return linha;
	}	
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<ChecklistVO> lista=listar();
		int qtd=lista.size();
		String linha="";

		try 
		{
			File sdCard = Environment.getExternalStorageDirectory();
			File directory= new File(sdCard.getAbsolutePath() + directoryname);
			File file = new File(directory, filename);
			FileOutputStream output= new FileOutputStream(file);
			OutputStreamWriter osw=new OutputStreamWriter(output);
			
			for (int i=0; i<qtd; i++)
			{
				ChecklistVO vo=lista.get(i);
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
		return bolReturn;
	}

}
