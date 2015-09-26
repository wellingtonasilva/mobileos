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
import wsilva.com.br.mobileos.entity.ConfiguracoesVO;

public class ConfiguracoesDAO extends BasicDAO<ConfiguracoesVO>
{
	public static final String COL_ID="_id";
	public static final String COL_FTPIP="ftpip";
	public static final String COL_FTPUSUARIO="ftpusuario";
	public static final String COL_FTPSENHA="ftpsenha";
	public static final String COL_FTPPORTA="ftpporta";
	public static final String COL_DISPOSITIVO_NOME="dispositivo_nome";
	public static final String COL_DISPOSITIVO_ID="dispositivo_id";
	public static final String COL_DISPOSITIVO_PASTA_DOWNLOAD="pasta_donwload";
	public static final String COL_DISPOSITIVO_PASTA_UPLOAD="pasta_upload";
	public static final String COL_DISPOSITIVO_PASTA_IMAGE="pasta_image";
	public static final String COL_UTILIZAR_FTP_ENVIAR="utilizar_ftp_enviar";
	public static final String COL_UTILIZAR_FTP_RECEBER="utilizar_ftp_receber";
	public static final String COL_ENVIAR_APOS_EXPORTACAO="enviar_apos_exportacao";
	public static final String COL_ENVIAR_FOTOS_VIA_FTP="enviar_fotos_ftp";
	public static final String COL_DISPOSITIVO_SERIAL="dispositivo_serial";
	public static final String COL_WEBURL="weburl";
	public static final String COL_UTILIZARWEBENVIARARQUIVOS="utilizar_web_enviar";
	public static final String COL_UTILIZARWEBPRECEBERARQUIVOS="utilizar_web_receber";
	public static final String COL_ENVIARFOTOSVIAWEB="enviar_fotos_web";
	public static final String COL_SMSTELEFONE="smstelefone";
	public static final String COL_ENVIARSMSAPOSINICIAR="enviarsmsaposiniciar";
	public static final String COL_ENVIARSMSAPOSENCERRAR="enviarsmsaposencerrar";
	public static final String COL_COLETOREMPRESA="empresa";
	public static final String COL_COLETORCODIGO="idcoletor";
	public static final String COL_COLETOREQUIPE="idequipe";
	public static final String COL_PERMITE_EXECUCAO_EMPARALELOOS="execucao_paralelo_os";
	public static final String COL_EXIGE_CHECKLIST="icchecklist";
	public static final String COL_EXIGE_INICIO_ATIVIDADE="icinicioatividade";
	public static final String COL_EMAILDESTINATARIO="dsemaildestinatario";
	public static final String COL_ENVIAREMAILAPOSENCERRAR="enviaremailaposencerrar";
	public static final String COL_ENVIAREMAILAPOSEXPORTAR="enviaremailaposexportar";
	public static final String COL_LOGOMARCA="logomarca";
	
	
	public static final String TABLE_NAME="configuracoes";
	public static final String CREATE_TABLE=
				"CREATE TABLE " + TABLE_NAME + "("
			+	COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ 	COL_FTPIP 			+ " TEXT,"
			+	COL_FTPUSUARIO 		+ " TEXT,"
			+ 	COL_FTPSENHA		+ " TEXT,"
			+	COL_FTPPORTA		+ " INTEGER,"
			+ 	COL_DISPOSITIVO_NOME 				+ " TEXT,"
			+	COL_DISPOSITIVO_ID					+ " TEXT,"
			+	COL_DISPOSITIVO_PASTA_DOWNLOAD 		+ " TEXT,"
			+	COL_DISPOSITIVO_PASTA_UPLOAD 		+ " TEXT,"
			+	COL_DISPOSITIVO_PASTA_IMAGE 		+ " TEXT,"
			+	COL_UTILIZAR_FTP_ENVIAR				+ " INTEGER,"
			+	COL_UTILIZAR_FTP_RECEBER			+ " INTEGER,"
			+ 	COL_ENVIAR_APOS_EXPORTACAO			+ " INTEGER,"
			+ 	COL_ENVIAR_FOTOS_VIA_FTP			+ " INTEGER,"
			+ 	COL_DISPOSITIVO_SERIAL				+ " TEXT,"
			+	COL_WEBURL							+ " TEXT,"
			+ 	COL_UTILIZARWEBENVIARARQUIVOS		+ " TEXT,"
			+ 	COL_UTILIZARWEBPRECEBERARQUIVOS		+ " TEXT,"
			+ 	COL_ENVIARFOTOSVIAWEB				+ " TEXT,"
			+	COL_SMSTELEFONE						+ " TEXT,"
			+ 	COL_ENVIARSMSAPOSINICIAR			+ " INTEGER,"
			+ 	COL_ENVIARSMSAPOSENCERRAR			+ " INTEGER,"
			+ 	COL_COLETOREMPRESA					+ " TEXT,"
			+ 	COL_COLETORCODIGO					+ " TEXT,"
			+ 	COL_COLETOREQUIPE					+ " TEXT,"
			+	COL_PERMITE_EXECUCAO_EMPARALELOOS	+ " INTEGER,"
			+ 	COL_EXIGE_CHECKLIST					+ " INTEGER,"
			+ 	COL_EXIGE_INICIO_ATIVIDADE			+ " INTEGER,"
			+ 	COL_EMAILDESTINATARIO				+ " TEXT,"
			+ 	COL_ENVIAREMAILAPOSENCERRAR			+ " INTEGER,"
			+ 	COL_ENVIAREMAILAPOSEXPORTAR			+ " INTEGER,"
			+ 	COL_LOGOMARCA						+ " INTEGER"
			+ ");";
	
	public ConfiguracoesDAO(Context context) 
	{
		super(context);
		if (!existTabela(TABLE_NAME)) {
			db.execSQL(CREATE_TABLE);
		}
	}
	
	@Override
	public long inserir(ConfiguracoesVO vo) 
	{
		ContentValues values=obterContentValues(vo);
		return db.insert(TABLE_NAME, null, values);
	}

	@Override
	public boolean atualizar(ConfiguracoesVO vo) 
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
	public List<ConfiguracoesVO> listar()
	{
		List<ConfiguracoesVO> data = new ArrayList<ConfiguracoesVO>();
		Cursor cursor = obterCursor();
		while (!cursor.isAfterLast())
		{
			ConfiguracoesVO vo = obterObject(cursor);
			data.add(vo);
			cursor.moveToNext();
		}
		
		cursor.close();
		return data;
	}

	@Override
	public ConfiguracoesVO obterPorId(long id) 
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
	public ContentValues obterContentValues(ConfiguracoesVO vo) 
	{
		ContentValues values=new ContentValues();
		values.put(COL_DISPOSITIVO_ID, vo.dispositivoID);
		values.put(COL_DISPOSITIVO_NOME, vo.dispositivoNome);
		values.put(COL_DISPOSITIVO_PASTA_DOWNLOAD, vo.dispositivoPastaDownload);
		values.put(COL_DISPOSITIVO_PASTA_UPLOAD, vo.dispositivoPastaUpload);
		values.put(COL_DISPOSITIVO_PASTA_IMAGE, vo.dispositivoPastaImage);
		values.put(COL_ENVIAR_APOS_EXPORTACAO, vo.enviarArquivosAposExportacao);
		values.put(COL_ENVIAR_FOTOS_VIA_FTP, vo.enviarFotosViaFTP);
		values.put(COL_FTPIP, vo.ftpIP);
		values.put(COL_FTPPORTA, vo.ftpPorta);
		values.put(COL_FTPSENHA, vo.ftpSenha);
		values.put(COL_FTPUSUARIO, vo.ftpUsuario);
		values.put(COL_UTILIZAR_FTP_ENVIAR, vo.utilizarFTPEnviarArquivos);
		values.put(COL_UTILIZAR_FTP_RECEBER, vo.utilizarFTPReceberArquivos);
		values.put(COL_DISPOSITIVO_SERIAL, vo.dispositivoSerial);
		values.put(COL_WEBURL, vo.webURL);
		values.put(COL_UTILIZARWEBENVIARARQUIVOS, vo.utilizarWEBEnviarArquivos);
		values.put(COL_UTILIZARWEBPRECEBERARQUIVOS, vo.utilizarWEBPReceberArquivos);
		values.put(COL_ENVIARFOTOSVIAWEB, vo.enviarFotosViaWEB);
		values.put(COL_SMSTELEFONE, vo.smsTelefone);
		values.put(COL_ENVIARSMSAPOSINICIAR, vo.enviarSmsAposIniciar);
		values.put(COL_ENVIARSMSAPOSENCERRAR, vo.enviarSmsAposEncerrar);
		values.put(COL_COLETOREMPRESA, vo.coletorEmpresa);
		values.put(COL_COLETORCODIGO, vo.coletorCodigo);
		values.put(COL_COLETOREQUIPE, vo.coletorEquipe);
		values.put(COL_PERMITE_EXECUCAO_EMPARALELOOS, vo.permiteExecucaoEmParaleloOS);
		values.put(COL_EXIGE_CHECKLIST, vo.exigeChecklist);
		values.put(COL_EXIGE_INICIO_ATIVIDADE, vo.exigeInicioAtividade);
		values.put(COL_EMAILDESTINATARIO, vo.emailDestinatario);
		values.put(COL_ENVIAREMAILAPOSENCERRAR, vo.enviarEmailAposEncerrar);
		values.put(COL_ENVIAREMAILAPOSEXPORTAR, vo.enviarEmailAposExportar);
		values.put(COL_LOGOMARCA, vo.logomarca);
		
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
	public ConfiguracoesVO obterObject(Cursor cursor) 
	{
		if (cursor==null || cursor.getCount() < 1) {
			return null;
		}
		
		ConfiguracoesVO vo = new ConfiguracoesVO();
		vo.dispositivoID=cursor.getString(cursor.getColumnIndex(COL_DISPOSITIVO_ID));
		vo.dispositivoNome=cursor.getString(cursor.getColumnIndex(COL_DISPOSITIVO_NOME));
		vo.dispositivoPastaDownload=cursor.getString(cursor.getColumnIndex(COL_DISPOSITIVO_PASTA_DOWNLOAD));
		vo.dispositivoPastaUpload=cursor.getString(cursor.getColumnIndex(COL_DISPOSITIVO_PASTA_UPLOAD));
		vo.dispositivoPastaImage=cursor.getString(cursor.getColumnIndex(COL_DISPOSITIVO_PASTA_IMAGE));
		vo.setEntityId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
		vo.enviarArquivosAposExportacao=cursor.getInt(cursor.getColumnIndex(COL_ENVIAR_APOS_EXPORTACAO));
		vo.enviarFotosViaFTP=cursor.getInt(cursor.getColumnIndex(COL_ENVIAR_FOTOS_VIA_FTP));
		vo.ftpIP=cursor.getString(cursor.getColumnIndex(COL_FTPIP));
		vo.ftpPorta=cursor.getInt(cursor.getColumnIndex(COL_FTPPORTA));
		vo.ftpSenha=cursor.getString(cursor.getColumnIndex(COL_FTPSENHA));
		vo.ftpUsuario=cursor.getString(cursor.getColumnIndex(COL_FTPUSUARIO));
		vo.utilizarFTPEnviarArquivos=cursor.getInt(cursor.getColumnIndex(COL_UTILIZAR_FTP_ENVIAR));
		vo.utilizarFTPReceberArquivos=cursor.getInt(cursor.getColumnIndex(COL_UTILIZAR_FTP_RECEBER));
		vo.dispositivoSerial=cursor.getString(cursor.getColumnIndex(COL_DISPOSITIVO_SERIAL));
		vo.webURL=cursor.getString(cursor.getColumnIndex(COL_WEBURL));
		vo.utilizarWEBEnviarArquivos=cursor.getInt(cursor.getColumnIndex(COL_UTILIZARWEBENVIARARQUIVOS));
		vo.utilizarWEBPReceberArquivos=cursor.getInt(cursor.getColumnIndex(COL_UTILIZARWEBPRECEBERARQUIVOS));
		vo.enviarFotosViaWEB=cursor.getInt(cursor.getColumnIndex(COL_ENVIARFOTOSVIAWEB));
		vo.smsTelefone=cursor.getString(cursor.getColumnIndex(COL_SMSTELEFONE));
		vo.enviarSmsAposIniciar=cursor.getInt(cursor.getColumnIndex(COL_ENVIARSMSAPOSINICIAR));
		vo.enviarSmsAposEncerrar=cursor.getInt(cursor.getColumnIndex(COL_ENVIARSMSAPOSENCERRAR));
		vo.coletorEmpresa=cursor.getString(cursor.getColumnIndex(COL_COLETOREMPRESA));
		vo.coletorCodigo=cursor.getString(cursor.getColumnIndex(COL_COLETORCODIGO));
		vo.coletorEquipe=cursor.getString(cursor.getColumnIndex(COL_COLETOREQUIPE));
		vo.permiteExecucaoEmParaleloOS=cursor.getInt(cursor.getColumnIndex(COL_PERMITE_EXECUCAO_EMPARALELOOS));
		vo.exigeChecklist=cursor.getInt(cursor.getColumnIndex(COL_EXIGE_CHECKLIST));
		vo.exigeInicioAtividade=cursor.getInt(cursor.getColumnIndex(COL_EXIGE_INICIO_ATIVIDADE));
		vo.emailDestinatario=cursor.getString(cursor.getColumnIndex(COL_EMAILDESTINATARIO));
		vo.enviarEmailAposEncerrar=cursor.getInt(cursor.getColumnIndex(COL_ENVIAREMAILAPOSENCERRAR));
		vo.enviarEmailAposExportar=cursor.getInt(cursor.getColumnIndex(COL_ENVIAREMAILAPOSEXPORTAR));
		vo.logomarca=cursor.getInt(cursor.getColumnIndex(COL_LOGOMARCA));
		
		return vo;
	}
	
	@Override
	public String obterLinhaCSV(ConfiguracoesVO vo, String delimitador) 
	{
		String linha="";
		if (vo!=null)
		{
			linha=	delimitador									
					+ vo.coletorEmpresa					+ ";"
					+ vo.coletorCodigo 					+ ";"
					+ vo.coletorEquipe 					
					+ "\n";
		}
		return linha;
	}
	
	
	public void saveToFile(String directoryname, String filename)
	{
		List<ConfiguracoesVO> configuracoes=listar();
		int qtd=configuracoes.size();
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
					ConfiguracoesVO vo=configuracoes.get(i);
					linha=obterLinhaCSV(vo, "");
					osw.write(linha);
				}
				osw.flush();
				osw.close();
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
