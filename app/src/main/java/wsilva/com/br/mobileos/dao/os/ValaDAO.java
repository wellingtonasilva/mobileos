package wsilva.com.br.mobileos.dao.os;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.os.ValaVO;

public class ValaDAO extends BasicDAO<ValaVO>
{
	
	public static final String COL_ID="_id";
	public static final String COL_NUMEROOS="numeroos";
	public static final String COL_NUMEROVALA="numerovala";
	public static final String COL_IDLOCALOCORRENCIA="tipolocalocorrencia";
	public static final String COL_DESCRICAOLOCALOCORRENCIA="dslocalocorrencia";
	public static final String COL_IDPAVIMENTO="idpavimento";
	public static final String COL_DESCRICAOPAVIMENTO="dspavimento";
	public static final String COL_COMPRIMENTO="nncomprimento";
	public static final String COL_LARGURA="nnlargura";
	public static final String COL_PROFUNDIDADE="nnprofundidade";
	public static final String COL_INDICADORATERRO="icaterro";
	public static final String COL_INDICADORENTULHO="icentulho";
	public static final String COL_QUANTIDADEBAGS="qtbags";
	public static final String COL_INDICADORATERRADOPELAEQUIPE="icaterradoaequipe";
	public static final String COL_IDEQUIPEEXECUCAO="idequipeexecucao";
	public static final String COL_DESCRICAOEQUIPEEXECUCAO="descricaoequipeexecucao";
	public static final String COL_INDICADORENVIO="icenvio";
	public static final String COL_INDICADOR_FOTOVALAABERTA="icfotovalaaberta";
	public static final String COL_INDICADOR_FOTOVALAFECHADA="icfotovalafechada";
	public static final String TABLE_NAME="vala";
	public static final String CREATE_TABLE=
			"CREATE TABLE " + TABLE_NAME + "("
			+ COL_ID 						+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COL_NUMEROOS 					+ " INTEGER, "
			+ COL_NUMEROVALA 				+ " INTEGER,"
			+ COL_IDLOCALOCORRENCIA 		+ " INTEGER,"
			+ COL_DESCRICAOLOCALOCORRENCIA 	+ " TEXT,"
			+ COL_IDPAVIMENTO 				+ "  INTEGER,"
			+ COL_DESCRICAOPAVIMENTO 		+  " TEXT,"
			+ COL_COMPRIMENTO 				+  " REAL,"
			+ COL_LARGURA					+ " REAL,"
			+ COL_PROFUNDIDADE 				+ "  REAL,"
			+ COL_INDICADORATERRO 			+ " INTEGER,"
			+ COL_INDICADORENTULHO 			+ " INTEGER,"
			+ COL_QUANTIDADEBAGS 				+ " INTEGER,"
			+ COL_INDICADORATERRADOPELAEQUIPE	+ " INTEGER,"
			+ COL_IDEQUIPEEXECUCAO 				+ " INTEGER,"
			+ COL_DESCRICAOEQUIPEEXECUCAO 		+ " TEXT,"
			+ COL_INDICADORENVIO				+ " INTEGER,"
			+ COL_INDICADOR_FOTOVALAABERTA		+ " INTEGER,"
			+ COL_INDICADOR_FOTOVALAFECHADA		+ " INTEGER"
			+ ");";
			
	public ValaDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(ValaVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);	
	}

	@Override
	public boolean atualizar(ValaVO vo) 
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
	public List<ValaVO> listar() 
	{
		List<ValaVO> data = new ArrayList<ValaVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ValaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}
	
	//Listar somente as Valas referente a uma deteriminada OS
	public List<ValaVO> listarPorOrdemServico(int numeroOS) 
	{
		List<ValaVO> data = new ArrayList<ValaVO>();
		Cursor cursor = obterCursorPorOrdemServico(numeroOS);
		while (!cursor.isAfterLast())
		{
			ValaVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ValaVO obterPorId(long id)
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
	public ContentValues obterContentValues(ValaVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_COMPRIMENTO, vo.comprimento);
		values.put(COL_DESCRICAOLOCALOCORRENCIA, vo.descricaoLocalOcorrencia);
		values.put(COL_DESCRICAOPAVIMENTO, vo.descricaoPavimento);
		values.put(COL_IDLOCALOCORRENCIA, vo.idLocalOcorrencia);
		values.put(COL_IDPAVIMENTO, vo.idPavimento);
		values.put(COL_INDICADORATERRO, vo.indicadorAterro);
		values.put(COL_INDICADORENTULHO, vo.indicadorEntulho);
		values.put(COL_LARGURA, vo.largura);
		values.put(COL_NUMEROOS, vo.numeroOS);
		values.put(COL_NUMEROVALA, vo.numeroVala);
		values.put(COL_PROFUNDIDADE, vo.profundidade);
		values.put(COL_QUANTIDADEBAGS, vo.quantidadeBags);
		values.put(COL_INDICADORATERRADOPELAEQUIPE, vo.indicadorAterradoPelaEquipe);
		values.put(COL_IDEQUIPEEXECUCAO, vo.idEquipeExecucao);
		values.put(COL_DESCRICAOEQUIPEEXECUCAO, vo.descricaoEquipeExecucao);
		values.put(COL_INDICADORENVIO, vo.indicadorEnvio);
		values.put(COL_INDICADOR_FOTOVALAABERTA, vo.indicadorFotoValaAberta);
		values.put(COL_INDICADOR_FOTOVALAFECHADA, vo.indicadorFotoValaFechada);
		
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

	@Override
	public ValaVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ValaVO vo= new ValaVO();
		vo.comprimento = cursor.getDouble(cursor.getColumnIndex(COL_COMPRIMENTO));
		vo.descricaoLocalOcorrencia = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOLOCALOCORRENCIA));
		vo.descricaoPavimento = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOPAVIMENTO));
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idLocalOcorrencia = cursor.getInt(cursor.getColumnIndex(COL_IDLOCALOCORRENCIA));
		vo.idPavimento = cursor.getInt(cursor.getColumnIndex(COL_IDPAVIMENTO));
		vo.indicadorAterro = cursor.getInt(cursor.getColumnIndex(COL_INDICADORATERRO));
		vo.indicadorEntulho = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENTULHO));
		vo.largura = cursor.getDouble(cursor.getColumnIndex(COL_LARGURA));
		vo.numeroOS = cursor.getInt(cursor.getColumnIndex(COL_NUMEROOS));
		vo.numeroVala = cursor.getInt(cursor.getColumnIndex(COL_NUMEROVALA));
		vo.profundidade = cursor.getDouble(cursor.getColumnIndex(COL_PROFUNDIDADE));
		vo.quantidadeBags = cursor.getInt(cursor.getColumnIndex(COL_QUANTIDADEBAGS));
		vo.indicadorAterradoPelaEquipe = cursor.getInt(cursor.getColumnIndex(COL_INDICADORATERRADOPELAEQUIPE));
		vo.idEquipeExecucao = cursor.getInt(cursor.getColumnIndex(COL_IDEQUIPEEXECUCAO));
		vo.descricaoEquipeExecucao = cursor.getString(cursor.getColumnIndex(COL_DESCRICAOEQUIPEEXECUCAO));
		vo.indicadorEnvio = cursor.getInt(cursor.getColumnIndex(COL_INDICADORENVIO));
		vo.indicadorFotoValaAberta = cursor.getInt(cursor.getColumnIndex(COL_INDICADOR_FOTOVALAABERTA));
		vo.indicadorFotoValaFechada = cursor.getInt(cursor.getColumnIndex(COL_INDICADOR_FOTOVALAFECHADA));
		
		return vo;
	}

	public int obterUltimoRegistro(int numeroOs)
	{
		int ultimoRegistro=0;
		Cursor cursor= rawQuery("select count(*) from " + TABLE_NAME 
				+ " where " + COL_NUMEROOS + "=" + String.valueOf(numeroOs), null);
		if (cursor!=null && cursor.getCount() > 0) {
			ultimoRegistro= (cursor.getInt(0) == 0 ? 1 : cursor.getInt(0)+1);
		} else {
			ultimoRegistro=1;
		}
		
		return ultimoRegistro;
	}
}
