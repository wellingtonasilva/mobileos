package wsilva.com.br.mobileos.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.ColetorWebVO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ColetorWebDAO extends BasicDAO<ColetorWebVO>
{

	public static final String COL_ID="_id";
	public static final String COL_IDCOLETOR="idcoletor";
	public static final String COL_ICAGENTEEXTERNO="icagenteexterno";
	public static final String COL_ICCAUSAVAZAMENTO="iccausavazamento";
	public static final String COL_ICDIAMETROREDE="icdiametrorede";
	public static final String COL_ICLOCALOCORRENCIA="iclocalocorrencia";
	public static final String COL_ICMOTIVOENCERRAMENTO="icmotivoencerramento";
	public static final String COL_ICMATERIAL="icmaterial";
	public static final String COL_ICMATERIALREDE="icmaterialrede";
	public static final String COL_ICSERVICOTIPO="icservicotipo";
	public static final String COL_ICTIPOPAVIMENTO="ictipopavimento";
	public static final String COL_ICTIPOREDE="ictiporede";
	public static final String COL_ICUSUARIOS="icusuarios";
	public static final String COL_ICORDEMSERVICO="icordemservico";
	public static final String COL_ICRESERVA01="icreserva01";
	public static final String COL_ICRESERVA02="icreserva02";
	public static final String COL_ICRESERVA03="icreserva03";
	public static final String COL_ICRESERVA04="icreserva04";
	public static final String COL_ICRESERVA05="icreserva05";
	public static final String TABLE_NAME="coletorweb";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+ 	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDCOLETOR 			+ " INTEGER,"
			+ 	COL_ICAGENTEEXTERNO 	+ " INTEGER,"
			+ 	COL_ICCAUSAVAZAMENTO 	+ " INTEGER,"
			+	COL_ICDIAMETROREDE 		+ " INTEGER,"
			+	COL_ICLOCALOCORRENCIA 	+ " INTEGER,"
			+ 	COL_ICMOTIVOENCERRAMENTO+ " INTEGER,"
			+	COL_ICMATERIAL 			+ " INTEGER,"
			+	COL_ICMATERIALREDE 		+ " INTEGER,"
			+ 	COL_ICSERVICOTIPO 		+ " INTEGER,"
			+	COL_ICTIPOPAVIMENTO		+ " INTEGER,"
			+	COL_ICTIPOREDE 			+ " INTEGER,"
			+	COL_ICUSUARIOS 			+ " INTEGER,"
			+	COL_ICORDEMSERVICO 		+ " INTEGER,"
			+	COL_ICRESERVA01 		+ " INTEGER,"
			+	COL_ICRESERVA02 		+ " INTEGER,"
			+	COL_ICRESERVA03			+ " INTEGER,"
			+	COL_ICRESERVA04 		+ " INTEGER,"
			+	COL_ICRESERVA05			+ " INTEGER"
			+  ");";
	
	public ColetorWebDAO(Context context) 
	{
		super(context);
	}

	@Override
	public long inserir(ColetorWebVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ColetorWebVO vo) 
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
	public List<ColetorWebVO> listar() 
	{
		List<ColetorWebVO> data = new ArrayList<ColetorWebVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ColetorWebVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ContentValues obterContentValues(ColetorWebVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDCOLETOR, vo.idColetor);
		values.put(COL_ICAGENTEEXTERNO, vo.icAgenteExterno);
		values.put(COL_ICCAUSAVAZAMENTO, vo.icCausaVazamento);
		values.put(COL_ICDIAMETROREDE, vo.icDiametroRede);
		values.put(COL_ICLOCALOCORRENCIA, vo.icLocalOcorrencia);
		values.put(COL_ICMOTIVOENCERRAMENTO, vo.icMotivoEncerramento);
		values.put(COL_ICMATERIAL, vo.icMaterial);
		values.put(COL_ICMATERIALREDE, vo.icMaterialRede);
		values.put(COL_ICSERVICOTIPO, vo.icServicoTipo);
		values.put(COL_ICTIPOPAVIMENTO, vo.icTipoPavimento);
		values.put(COL_ICTIPOREDE, vo.icTipoRede);
		values.put(COL_ICUSUARIOS, vo.icUsuarios);
		values.put(COL_ICORDEMSERVICO, vo.icOrdemServico);
		values.put(COL_ICRESERVA01, vo.icReserva01);
		values.put(COL_ICRESERVA02, vo.icReserva02);
		values.put(COL_ICRESERVA03, vo.icReserva03);
		values.put(COL_ICRESERVA04, vo.icReserva04);
		values.put(COL_ICRESERVA05, vo.icReserva05);

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
	public ColetorWebVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ColetorWebVO vo = new ColetorWebVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.icAgenteExterno = cursor.getInt(cursor.getColumnIndex(COL_ICAGENTEEXTERNO));
		vo.icCausaVazamento = cursor.getInt(cursor.getColumnIndex(COL_ICCAUSAVAZAMENTO));
		vo.icDiametroRede = cursor.getInt(cursor.getColumnIndex(COL_ICDIAMETROREDE));
		vo.icLocalOcorrencia = cursor.getInt(cursor.getColumnIndex(COL_ICLOCALOCORRENCIA));
		vo.icMaterial = cursor.getInt(cursor.getColumnIndex(COL_ICMATERIAL));
		vo.icMaterialRede = cursor.getInt(cursor.getColumnIndex(COL_ICMATERIALREDE));
		vo.icMotivoEncerramento = cursor.getInt(cursor.getColumnIndex(COL_ICMOTIVOENCERRAMENTO));
		vo.icOrdemServico = cursor.getInt(cursor.getColumnIndex(COL_ICORDEMSERVICO));
		vo.icReserva01 = cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA01));
		vo.icReserva02 = cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA02));
		vo.icReserva03 = cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA03));
		vo.icReserva04 = cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA04));
		vo.icReserva05 = cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA05));
		vo.icServicoTipo = cursor.getInt(cursor.getColumnIndex(COL_ICSERVICOTIPO));
		vo.icTipoPavimento = cursor.getInt(cursor.getColumnIndex(COL_ICTIPOPAVIMENTO));
		vo.icTipoRede = cursor.getInt(cursor.getColumnIndex(COL_ICTIPOREDE));
		vo.icUsuarios = cursor.getInt(cursor.getColumnIndex(COL_ICUSUARIOS));
		vo.idColetor = cursor.getInt(cursor.getColumnIndex(COL_IDCOLETOR));
		
		return vo;
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
}
