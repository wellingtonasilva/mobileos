package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.UsuarioVO;

public class UsuarioDAO extends BasicDAO<UsuarioVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDUSUARIO="idusuario";
	public static final String COL_LOGIN="login";
	public static final String COL_NOME="nome";
	public static final String COL_SENHA="senha";
	public static final String TABLE_NAME="usuario";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_IDUSUARIO + " INTEGER,"
			+ COL_LOGIN + " TEXT,"
			+ COL_NOME + " TEXT,"
			+ COL_SENHA + " TEXT"
			+ ");";
	
	public UsuarioDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(UsuarioVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(UsuarioVO vo) 
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
	public List<UsuarioVO> listar() 
	{
		List<UsuarioVO> data = new ArrayList<UsuarioVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			UsuarioVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public UsuarioVO obterPorId(long id) 
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
	public ContentValues obterContentValues(UsuarioVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDUSUARIO, vo.idUsuario);
		values.put(COL_LOGIN, vo.login);
		values.put(COL_NOME, vo.nome);
		values.put(COL_SENHA, vo.senha);
		
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
	public UsuarioVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		UsuarioVO vo=new UsuarioVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idUsuario = cursor.getInt(cursor.getColumnIndex(COL_IDUSUARIO));
		vo.login = cursor.getString(cursor.getColumnIndex(COL_LOGIN));
		vo.nome = cursor.getString(cursor.getColumnIndex(COL_NOME));
		vo.senha = cursor.getString(cursor.getColumnIndex(COL_SENHA));
		
		return vo;
	}

	@Override
	public int quantidadeRegistros() 
	{
		Cursor cursor=db.query(true, TABLE_NAME, new String[]{COL_ID}, null, null, null, null, null, null);
		if (cursor!=null) {
			return cursor.getCount();
		} else {
			return 0;
		}
	}
	
	public UsuarioVO obterPorLogin(String login)
	{
		Cursor cursor=db.query(true, TABLE_NAME, null, COL_LOGIN+"=?", 
				new String[]{login}, null, null, null, null);
		if (cursor!=null) 
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
}
