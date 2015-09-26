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
		return db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(vo.getEntityId())}) > 0;
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
		values.put(COL_IDCOLETOR, vo.getIdColetor());
		values.put(COL_ICAGENTEEXTERNO, vo.getIcAgenteExterno());
		values.put(COL_ICCAUSAVAZAMENTO, vo.getIcCausaVazamento());
		values.put(COL_ICDIAMETROREDE, vo.getIcDiametroRede());
		values.put(COL_ICLOCALOCORRENCIA, vo.getIcLocalOcorrencia());
		values.put(COL_ICMOTIVOENCERRAMENTO, vo.getIcMotivoEncerramento());
		values.put(COL_ICMATERIAL, vo.getIcMaterial());
		values.put(COL_ICMATERIALREDE, vo.getIcMaterialRede());
		values.put(COL_ICSERVICOTIPO, vo.getIcServicoTipo());
		values.put(COL_ICTIPOPAVIMENTO, vo.getIcTipoPavimento());
		values.put(COL_ICTIPOREDE, vo.getIcTipoRede());
		values.put(COL_ICUSUARIOS, vo.getIcUsuarios());
		values.put(COL_ICORDEMSERVICO, vo.getIcOrdemServico());
		values.put(COL_ICRESERVA01, vo.getIcReserva01());
		values.put(COL_ICRESERVA02, vo.getIcReserva02());
		values.put(COL_ICRESERVA03, vo.getIcReserva02());
		values.put(COL_ICRESERVA04, vo.getIcReserva02());
		values.put(COL_ICRESERVA05, vo.getIcReserva02());
		
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
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.setIcAgenteExterno(cursor.getInt(cursor.getColumnIndex(COL_ICAGENTEEXTERNO)));
		vo.setIcCausaVazamento(cursor.getInt(cursor.getColumnIndex(COL_ICCAUSAVAZAMENTO)));
		vo.setIcDiametroRede(cursor.getInt(cursor.getColumnIndex(COL_ICDIAMETROREDE)));
		vo.setIcLocalOcorrencia(cursor.getInt(cursor.getColumnIndex(COL_ICLOCALOCORRENCIA)));
		vo.setIcMaterial(cursor.getInt(cursor.getColumnIndex(COL_ICMATERIAL)));
		vo.setIcMaterialRede(cursor.getInt(cursor.getColumnIndex(COL_ICMATERIALREDE)));
		vo.setIcMotivoEncerramento(cursor.getInt(cursor.getColumnIndex(COL_ICMOTIVOENCERRAMENTO)));
		vo.setIcOrdemServico(cursor.getInt(cursor.getColumnIndex(COL_ICORDEMSERVICO)));
		vo.setIcReserva01(cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA01)));
		vo.setIcReserva02(cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA02)));
		vo.setIcReserva03(cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA03)));
		vo.setIcReserva04(cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA04)));
		vo.setIcReserva05(cursor.getInt(cursor.getColumnIndex(COL_ICRESERVA05)));
		vo.setIcServicoTipo(cursor.getInt(cursor.getColumnIndex(COL_ICSERVICOTIPO)));
		vo.setIcTipoPavimento(cursor.getInt(cursor.getColumnIndex(COL_ICTIPOPAVIMENTO)));
		vo.setIcTipoRede(cursor.getInt(cursor.getColumnIndex(COL_ICTIPOREDE)));
		vo.setIcUsuarios(cursor.getInt(cursor.getColumnIndex(COL_ICUSUARIOS)));
		vo.setIdColetor(cursor.getInt(cursor.getColumnIndex(COL_IDCOLETOR)));
		
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


	@Override
	public ColetorWebVO obterObject(JSONObject line) 
	{
		if (line ==null) {
			return null;
		}
		
		ColetorWebVO vo=new ColetorWebVO();
		
		try 
		{
			//C�digo
			if (line.getString("idColetor").length() > 0) {
				vo.setIdColetor(Integer.parseInt(line.getString("idColetor")));
			}
			//Servi�o Tipo
			if (line.getString("icServicoTipo").length() > 0) {
				vo.setIcServicoTipo(Integer.parseInt(line.getString("icServicoTipo")));
			}
			//Ordem de Servi�o
			if (line.getString("icOrdemServico").length() > 0) {
				vo.setIcOrdemServico(Integer.parseInt(line.getString("icOrdemServico")));
			}
			//Local Ocorr�ncia
			if (line.getString("icLocalOcorrencia").length() > 0) {
				vo.setIcLocalOcorrencia(Integer.parseInt(line.getString("icLocalOcorrencia")));
			}
			//Usu�rios
			if (line.getString("icUsuarios").length() > 0) {
				vo.setIcUsuarios(Integer.parseInt(line.getString("icUsuarios")));
			}
			//Agente Externo
			if (line.getString("icAgenteExterno").length() > 0) {
				vo.setIcAgenteExterno(Integer.parseInt(line.getString("icAgenteExterno")));
			}
			//Material Rede
			if (line.getString("icMaterialRede").length() > 0) {
				vo.setIcMaterialRede(Integer.parseInt(line.getString("icMaterialRede")));
			}
			//Reserva 1
			if (line.getString("icReserva01").length() > 0) {
				vo.setIcReserva01(Integer.parseInt(line.getString("icReserva01")));
			}
			//Reserva 2
			if (line.getString("icReserva02").length() > 0) {
				vo.setIcReserva02(Integer.parseInt(line.getString("icReserva02")));
			}
			//Reserva 3
			if (line.getString("icReserva03").length() > 0) {
				vo.setIcReserva03(Integer.parseInt(line.getString("icReserva03")));
			}
			//Reserva 4
			if (line.getString("icReserva04").length() > 0) {
				vo.setIcReserva04(Integer.parseInt(line.getString("icReserva04")));
			}
			//Reserva 5
			if (line.getString("icReserva05").length() > 0) {
				vo.setIcReserva05(Integer.parseInt(line.getString("icReserva05")));
			}
			//Causa do Vazamento
			if (line.getString("icCausaVazamento").length() > 0) {
				vo.setIcCausaVazamento(Integer.parseInt(line.getString("icCausaVazamento")));
			}
			//Di�metro da Rede
			if (line.getString("icDiametroRede").length() > 0) {
				vo.setIcDiametroRede(Integer.parseInt(line.getString("icDiametroRede")));
			}
			//Tipo de Rede
			if (line.getString("icTipoRede").length() > 0) {
				vo.setIcTipoRede(Integer.parseInt(line.getString("icTipoRede")));
			}
			//Material
			if (line.getString("icMaterial").length() > 0) {
				vo.setIcMaterial(Integer.parseInt(line.getString("icMaterial")));
			}
			//Tipo pavimento
			if (line.getString("icTipoPavimento").length() > 0) {
				vo.setIcTipoPavimento(Integer.parseInt(line.getString("icTipoPavimento")));
			}
			//Tipo pavimento
			if (line.getString("icMotivoEncerramento").length() > 0) {
				vo.setIcMotivoEncerramento(Integer.parseInt(line.getString("icMotivoEncerramento")));
			}
			
		} catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public void povoaTabelaFromJSON(String url, String idMobile)
	{
		//Lista de parametros POST
		List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("idMobile", idMobile));
		
		//Ler objetos do servidor
		List<JSONObject> jsonObjects=lerDadosFromFile(url + "/ColetorWebServlet", postParameters);
		int iLinhas=jsonObjects.size();
		
		for (int i=0; i<iLinhas; i++)
		{
			JSONObject jsonObject= jsonObjects.get(i);
			ColetorWebVO vo=obterObject(jsonObject);
			if (vo!=null) inserir(vo);
		}
	}
	
	

}
