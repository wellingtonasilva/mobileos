package wsilva.com.br.mobileos.dao.ocorrencia;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import wsilva.com.br.mobileos.core.dao.BasicDAO;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoMotivoVO;

public class InterrupcaoMotivoDAO extends BasicDAO<InterrupcaoMotivoVO> {

	public static final String COL_ID="_id";
	public static final String COL_IDINTERRUPCAOMOTIVO="idmotivo";
	public static final String COL_DESCRICAO="dsmotivo";
	public static final String COL_INDICADOR_ENVIAR_SMS_INICIO="icenviarsmsinicio";
	public static final String COL_INDICADOR_ENVIAR_SMS_FIM="icenviarsmsfim";
	public static final String COL_INDICADORINICIOATIVIDADE="icinicioatividade";
	public static final String COL_INDICADORFIMATIVIDADE="fimatividade";
	public static final String COL_INDICADORCHECKLISTSAIDA="checklistsaida";
	public static final String COL_INDICADORCHECKLISTRETORNO="checklistretorno";
	public static final String COL_INDICADORSOLICITARKMINICIO="icsolicitakminicio";
	public static final String COL_INDICADORSOLICITARKMFIM="icsolicitakmfim";
	
	public static final String TABLE_NAME="interrupcao_motivo";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_IDINTERRUPCAOMOTIVO 			+ " INTEGER,"
			+ 	COL_DESCRICAO 						+ " TEXT,"
			+ 	COL_INDICADOR_ENVIAR_SMS_INICIO 	+ " INTEGER,"
			+ 	COL_INDICADOR_ENVIAR_SMS_FIM 		+ " INTEGER,"
			+ 	COL_INDICADORINICIOATIVIDADE 		+ " INTEGER,"
			+ 	COL_INDICADORFIMATIVIDADE 			+ " INTEGER,"
			+ 	COL_INDICADORCHECKLISTSAIDA 		+ " INTEGER,"
			+ 	COL_INDICADORCHECKLISTRETORNO 		+ " INTEGER,"
			+ 	COL_INDICADORSOLICITARKMINICIO 		+ " INTEGER,"
			+ 	COL_INDICADORSOLICITARKMFIM 		+ " INTEGER"
			+ ");";
	
	
	public InterrupcaoMotivoDAO(Context context) {
		super(context);
	}
	
	@Override
	public long inserir(InterrupcaoMotivoVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(InterrupcaoMotivoVO vo) 
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
	public List<InterrupcaoMotivoVO> listar()
	{
		List<InterrupcaoMotivoVO> data = new ArrayList<InterrupcaoMotivoVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			InterrupcaoMotivoVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public InterrupcaoMotivoVO obterPorId(long id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_ID+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}

	public InterrupcaoMotivoVO obterPorIdInterrupcaoMotivo(int id) 
	{
		Cursor cursor=db.query(true,TABLE_NAME, null, COL_IDINTERRUPCAOMOTIVO+ "=?", 
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null)
		{
			cursor.moveToFirst();
		}
		return obterObject(cursor);
	}
	
	@Override
	public ContentValues obterContentValues(InterrupcaoMotivoVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_IDINTERRUPCAOMOTIVO, vo.idInterrupcaoMotivo);
		values.put(COL_DESCRICAO, vo.descricao);
		values.put(COL_INDICADOR_ENVIAR_SMS_INICIO, vo.indicadorEnviarSMSInicio);
		values.put(COL_INDICADOR_ENVIAR_SMS_FIM, vo.indicadorEnviarSMSFim);
		values.put(COL_INDICADORINICIOATIVIDADE, vo.indicadorInicioAtividade);
		values.put(COL_INDICADORFIMATIVIDADE, vo.indicadorFimAtividade);
		values.put(COL_INDICADORCHECKLISTSAIDA, vo.indicadorChecklistSaida);
		values.put(COL_INDICADORCHECKLISTRETORNO, vo.indicadorChecklistRetorno);
		values.put(COL_INDICADORSOLICITARKMINICIO, vo.indicadorSolicitarKMInicio);
		values.put(COL_INDICADORSOLICITARKMFIM, vo.indicadorSolicitarKMFim);
		
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
	public InterrupcaoMotivoVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		InterrupcaoMotivoVO vo = new InterrupcaoMotivoVO();
		vo._id = cursor.getInt(cursor.getColumnIndex(COL_ID));
		vo.idInterrupcaoMotivo=cursor.getInt(cursor.getColumnIndex(COL_IDINTERRUPCAOMOTIVO));
		vo.descricao=cursor.getString(cursor.getColumnIndex(COL_DESCRICAO));
		vo.indicadorEnviarSMSInicio=cursor.getInt(cursor.getColumnIndex(COL_INDICADOR_ENVIAR_SMS_INICIO));
		vo.indicadorEnviarSMSFim=cursor.getInt(cursor.getColumnIndex(COL_INDICADOR_ENVIAR_SMS_FIM));
		vo.indicadorInicioAtividade=cursor.getInt(cursor.getColumnIndex(COL_INDICADORINICIOATIVIDADE));
		vo.indicadorFimAtividade=cursor.getInt(cursor.getColumnIndex(COL_INDICADORFIMATIVIDADE));
		vo.indicadorChecklistSaida=cursor.getInt(cursor.getColumnIndex(COL_INDICADORCHECKLISTSAIDA));
		vo.indicadorChecklistRetorno=cursor.getInt(cursor.getColumnIndex(COL_INDICADORCHECKLISTRETORNO));
		vo.indicadorSolicitarKMInicio=cursor.getInt(cursor.getColumnIndex(COL_INDICADORSOLICITARKMINICIO));
		vo.indicadorSolicitarKMFim=cursor.getInt(cursor.getColumnIndex(COL_INDICADORSOLICITARKMFIM));
		
		return vo;
	}

	@Override
	public InterrupcaoMotivoVO obterObject(String line)
	{
		if (line.length() <= 0) {
			return null;
		}

		String[] values=line.split(";");
		InterrupcaoMotivoVO vo=new InterrupcaoMotivoVO();

		//1;Inciar Atividade;0;0;1;0;0;0;0;0;

		//C�digo
		if (values[0].length() > 0) {
			vo.idInterrupcaoMotivo=Integer.parseInt(values[0]);
		}
		//Descri��o
		vo.descricao=values[1];
		//Enviar SMS no in�cio
		if (values[2].length() > 0) {
			vo.indicadorEnviarSMSInicio=Integer.parseInt(values[2]);
		}
		//Enviar SMS no fim
		if (values[3].length() > 0) {
			vo.indicadorEnviarSMSFim=Integer.parseInt(values[3]);
		}

		//Inicio de Atividade
		if (values[4].length() > 0) {
			vo.indicadorInicioAtividade=Integer.parseInt(values[4]);
		}
		//Fim de atividade
		if (values[5].length() > 0) {
			vo.indicadorFimAtividade=Integer.parseInt(values[5]);
		}
		//Checklist - Sa�da
		if (values[6].length() > 0) {
			vo.indicadorChecklistSaida=Integer.parseInt(values[6]);
		}
		//Checklist - Retorno
		if (values[7].length() > 0) {
			vo.indicadorChecklistRetorno=Integer.parseInt(values[7]);
		}
		//Solicitar Km no Inicio
		if (values[8].length() > 0) {
			vo.indicadorSolicitarKMInicio=Integer.parseInt(values[8]);
		}
		//Solicitar Km no Fim
		if (values[9].length() > 0) {
			vo.indicadorSolicitarKMFim=Integer.parseInt(values[9]);
		}

		return vo;
	}
}
