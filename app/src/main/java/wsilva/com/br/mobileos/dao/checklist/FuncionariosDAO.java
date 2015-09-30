package wsilva.com.br.mobileos.dao.checklist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.checklist.FuncionariosVO;

public class FuncionariosDAO extends BasicDAO<FuncionariosVO>
{
	public static final String COL_ID="_id";
	public static final String COL_MATRICULA="matricula";
	public static final String COL_NOMEFUNCIONARIO="nmfuncionario";
	public static final String COL_NOMESETOR="nmsetor";
	public static final String COL_NUMEROCNH="nncnh";
	public static final String COL_SENHA="senha";
	public static final String COL_ATIVO="ativo";
	public static final String TABLE_NAME="funcionarios";
	public static final String CREATE_TABLE=
				"CREATE TABLE " 	+ TABLE_NAME + "("
			+	COL_ID 				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_MATRICULA 		+ " INTEGER,"
			+ 	COL_NOMEFUNCIONARIO + " TEXT,"
			+ 	COL_NOMESETOR 		+ " TEXT,"
			+ 	COL_NUMEROCNH 		+ " TEXT,"
			+ 	COL_SENHA 			+ " TEXT,"
			+ 	COL_ATIVO 			+ " INTEGER"
			+ ");";
	
	public FuncionariosDAO(Context context) {
		super(context);
	}
	
	
	@Override
	public long inserir(FuncionariosVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(FuncionariosVO vo) 
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
	public List<FuncionariosVO> listar()
	{
		List<FuncionariosVO> data = new ArrayList<FuncionariosVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			FuncionariosVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public FuncionariosVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	public FuncionariosVO obterPorMatricula(int matricula) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_MATRICULA+ "=?", 
				new String[]{String.valueOf(matricula)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}	

	@Override
	public ContentValues obterContentValues(FuncionariosVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_MATRICULA, vo.matricula);
		values.put(COL_NOMEFUNCIONARIO, vo.nomeFuncionario);
		values.put(COL_NOMESETOR, vo.nomeSetor);
		values.put(COL_NUMEROCNH, vo.numeroCNH);
		values.put(COL_SENHA, vo.senha);
		values.put(COL_ATIVO, vo.isAtivo);
		
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
	public FuncionariosVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		FuncionariosVO vo = new FuncionariosVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.matricula=cursor.getInt(cursor.getColumnIndex(COL_MATRICULA));
		vo.nomeFuncionario=cursor.getString(cursor.getColumnIndex(COL_NOMEFUNCIONARIO));
		vo.nomeSetor=cursor.getString(cursor.getColumnIndex(COL_NOMESETOR));
		vo.numeroCNH=cursor.getString(cursor.getColumnIndex(COL_NUMEROCNH));
		vo.senha=cursor.getString(cursor.getColumnIndex(COL_SENHA));
		vo.isAtivo= (cursor.getInt(cursor.getColumnIndex(COL_ATIVO))==1?true:false);
		
		return vo;
	}

	@Override
	public FuncionariosVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		FuncionariosVO vo=new FuncionariosVO();
		//Matricula
		if (values[1].length() > 0) {
			vo.matricula=Integer.parseInt(values[1]);
		}
		//Funcion�rio
		vo.nomeFuncionario=values[2];
		//CNH
		vo.numeroCNH=values[3];
		//Senha
		vo.senha=values[4];
		//Setor
		vo.nomeSetor=values[5];
		
		return vo;
	}

}
