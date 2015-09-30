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
import wsilva.com.br.mobileos.entity.checklist.ChecklistRespostaVO;

public class ChecklistRespostaDAO extends BasicDAO<ChecklistRespostaVO>
{
	public static final String COL_ID="_id";
	public static final String COL_DATA="data";
	public static final String COL_PLACAVEICULO="nnplacaveiculo";
	public static final String COL_IDGRUPO="idgrupo";
	public static final String COL_IDITEM="iditem";
	public static final String COL_IDOPCAOSAIDA="idopcaosaida";
	public static final String COL_DATASAIDA="dtsaida";
	public static final String COL_HORASAIDA="hrsaida";
	public static final String COL_IDOPCAORETORNO="idopcaoretorno";
	public static final String COL_DATARETORNO="dtretorno";
	public static final String COL_HORARETORNO="hrretorno";
	public static final String TABLE_NAME="checklist_respostas";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_DATA 			+ " TEXT,"
			+ 	COL_PLACAVEICULO 	+ " TEXT,"
			+ 	COL_IDGRUPO 		+ " INTEGER,"
			+ 	COL_IDITEM 			+ " INTEGER,"
			+ 	COL_IDOPCAOSAIDA 	+ " INTEGER,"
			+ 	COL_DATASAIDA 		+ " TEXT,"
			+ 	COL_HORASAIDA 		+ " TEXT,"
			+ 	COL_IDOPCAORETORNO 	+ " INTEGER,"
			+ 	COL_DATARETORNO 	+ " TEXT,"
			+ 	COL_HORARETORNO 	+ " TEXT"
			+ ");";
	
	public ChecklistRespostaDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(ChecklistRespostaVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ChecklistRespostaVO vo) 
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
	public List<ChecklistRespostaVO> listar()
	{
		List<ChecklistRespostaVO> data = new ArrayList<ChecklistRespostaVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ChecklistRespostaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	public List<ChecklistRespostaVO> listarPorData(String dataMovimento) 
	{
		List<ChecklistRespostaVO> data = new ArrayList<ChecklistRespostaVO>();
		Cursor cursor = obterCursorPorData(dataMovimento);
		while (!cursor.isAfterLast())
		{
			ChecklistRespostaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ChecklistRespostaVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public ChecklistRespostaVO obterPorItem(int iditem) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_IDITEM+ "=?", 
				new String[]{String.valueOf(iditem)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}	

	@Override
	public ContentValues obterContentValues(ChecklistRespostaVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDGRUPO, vo.idGrupo);
		values.put(COL_IDITEM, vo.idItem);
		values.put(COL_DATA, vo.data);
		values.put(COL_PLACAVEICULO, vo.placaVeiculo);
		values.put(COL_IDOPCAOSAIDA, vo.idOpcaoSaida);
		values.put(COL_DATASAIDA, vo.dataSaida);
		values.put(COL_HORASAIDA, vo.horaSaida);
		values.put(COL_IDOPCAORETORNO, vo.idOpcaoRetorno);
		values.put(COL_DATARETORNO, vo.dataRetorno);
		values.put(COL_HORARETORNO, vo.horaRetorno);
		
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
	
	public Cursor obterCursorPorData(String dataMovimento)
	{
		Cursor cursor = db.query(TABLE_NAME, null, COL_DATA +"=?", new String[]{String.valueOf(dataMovimento)}, 
				null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		
		return cursor;
	}	

	@Override
	public ChecklistRespostaVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ChecklistRespostaVO vo = new ChecklistRespostaVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.data=cursor.getString(cursor.getColumnIndex(COL_DATA));
		vo.placaVeiculo=cursor.getString(cursor.getColumnIndex(COL_PLACAVEICULO));
		vo.idGrupo=cursor.getInt(cursor.getColumnIndex(COL_IDGRUPO));
		vo.idItem=cursor.getInt(cursor.getColumnIndex(COL_IDITEM));
		vo.idOpcaoSaida=cursor.getInt(cursor.getColumnIndex(COL_IDOPCAOSAIDA));
		vo.dataSaida=cursor.getString(cursor.getColumnIndex(COL_DATASAIDA));
		vo.horaSaida=cursor.getString(cursor.getColumnIndex(COL_HORASAIDA));
		vo.idOpcaoRetorno=cursor.getInt(cursor.getColumnIndex(COL_IDOPCAORETORNO));
		vo.dataRetorno=cursor.getString(cursor.getColumnIndex(COL_DATARETORNO));
		vo.horaRetorno=cursor.getString(cursor.getColumnIndex(COL_HORARETORNO));		
		
		return vo;
	}
	
	@Override
	public String obterLinhaCSV(ChecklistRespostaVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null) 
		{
			linha=  delimitador
					+ vo.data 							+ ";"
					+ vo.dataRetorno 					+ ";"
					+ vo.dataSaida 						+ ";"
					+ vo.horaRetorno 					+ ";"
					+ vo.horaSaida 						+ ";"
					+ String.valueOf(vo.idGrupo) 		+ ";"
					+ String.valueOf(vo.idItem)	 		+ ";"
					+ String.valueOf(vo.idOpcaoRetorno) + ";"
					+ String.valueOf(vo.idOpcaoSaida)	+ ";"
					+ vo.placaVeiculo
					+ "\n";
		}
		return linha;
	}	
	
	public boolean saveToFile(String directoryname, String filename)
	{
		boolean bolReturn=false;
		List<ChecklistRespostaVO> lista=listar();
		int qtd=lista.size();
		String linha="";

		if (qtd>0) {
			try 
			{
				File sdCard = Environment.getExternalStorageDirectory();
				File directory= new File(sdCard.getAbsolutePath() + directoryname);
				File file = new File(directory, filename);
				FileOutputStream output= new FileOutputStream(file);
				OutputStreamWriter osw=new OutputStreamWriter(output);
				
				for (int i=0; i<qtd; i++)
				{
					ChecklistRespostaVO vo=lista.get(i);
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
