package wsilva.com.br.mobileos.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.widget.Adapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import org.json.JSONException;
import org.json.JSONObject;

import wsilva.com.br.mobileos.core.dao.IBasicDAO;


public class Util
{
	public static final String RESULT_ERROR				="ERROR";
	public static final String UUID_SERIAL_PORT_PROFILE = "00001101-0000-1000-8000-00805F9B34FB";
	public static final String WEBSERVICE_URL 			= "http://wassistemas.azurewebsites.net/cciweb/cciservice?wsdl";
	public static final String WEBSERVICE_NAMESPACE 	= "http://wassistemas.azurewebsites.net/";
    public static final String DATE_FORMAT_FROM 		= "dd/MM/yyyy";
    public static final String DATE_FORMAT_TO   		= "yyyy-MM-dd";
	public static final int MAXIMA_TENTATIVA_CONEXAO	= 3;
	public static final boolean IN_DEBUG=false;
	public static final String PATH_BASE="/wsilva";
	public static final String PATH_DOWNLOAD="/wsilva/in";
	public static final String PATH_UPLOAD="/wsilva/out";
	public static final String PATH_IMAGE="/wsilva/image";
	public static final String PATH_THUMB="/wsilva/thumb";
	public static final String PATH_EMPRESA="/m000";
	public static final String TAG_DEBUG="MOBILEOS";
	public static final String TAG_ORDEM_SERVICO="os";
	public static final String TAG_MATERIAL_UTILIZADO="material_utilizado";
	public static final String TAG_VALA="vala";
	public static final String FTP_IP="192.168.0.194";
	public static final String FTP_USERNAME="mobileos";
	public static final String FTP_PASSWORD="123";
	public static final String FTP_PORT="21";
	public static final String WEB_URL="http://cpro19731.publiccloud.com.br/m000";
	public static final String PREFERENCE_NAME	="MobileOS";
	public static final String DESCRICAO_OS_FOTO1= "1a. Foto do inicio do serviço.";
	public static final String DESCRICAO_OS_FOTO2= "2a. Foto do inicio do serviço.";
	public static final String DESCRICAO_OS_FOTO3= "1a. Foto do termino do serviço.";
	public static final String DESCRICAO_OS_FOTO4= "2a. Foto do termino do serviço.";
	public static final String DESCRICAO_OS_FOTO5= "Foto do croqui da Rede.";
	public static final String DESCRICAO_OS_FOTO6= "1a. Foto do cancelamento do serviço.";
	public static final String DESCRICAO_OS_FOTO7= "2a. Foto do cancelamento do serviço.";
	public static final String DESCRICAO_OS_FOTO_VALA_1= "1a. foto da vala.";
	public static final String DESCRICAO_OS_FOTO_VALA_2= "2a. foto da vala.";
	public static final String DESCRICAO_OS_FOTO_DIVERSAS= "Foto Diversas.";
	public static final String TITULO_OS_FOTO_INICIO_1	 = "O sistema irá capturar a primeira foto do início do serviço.";
	public static final String TITULO_OS_FOTO_INICIO_2	 = "O sistema irá capturar a segunda foto do início do serviço.";
	public static final String TITULO_OS_FOTO_FINAL_1	 = "O sistema irá capturar a primeira foto do encerramento do serviço.";
	public static final String TITULO_OS_FOTO_FINAL_2	 = "O sistema irá capturar a segunda foto do encerramento do serviço.";
	public static final String TITULO_OS_FOTO_VALA_1	 = "O sistema irá capturar a primeira foto da vala.";
	public static final String TITULO_OS_FOTO_VALA_2	 = "O sistema irá capturar a segunda foto da vala.";
	public static final String TITULO_OS_FOTO_DIVERSAS	 = "O sistema irá capturar uma foto do serviço.";
	public static final int AGENTEEXTERNO		=1;
	public static final int COLETORWEB			=2;
	public static final int USUARIO				=3;
	public static final int REDETIPO			=4;
	public static final int PAVIMENTOTIPO		=5;
	public static final int SERVICOTIPO			=6;
	public static final int REDEMATERIAL		=7;
	public static final int MATERIAL			=8;
	public static final int MOTIVOENCERRAMENTO	=9;
	public static final int LOCALOCORRENCIA		=10;
	public static final int REDEDIAMETRO		=11;
	public static final int REDECAUSAVAZAMENTO	=12;
	public static final int ORDEMSERVICO		=13;
	public static final int MATERIAL_UTILIZADO	=14;
	public static final int VALA				=15;
	public static final int COORDENADAS_GPS		=16;
	public static final int LIGACAOAGUASITUACAO		=17;
	public static final int LIGACAOESGOTOSITUACAO	=18;
	public static final int MOTIVOCORTE				=19;
	public static final int CORTE_TIPO				=20;
	public static final int HIDROMETRO_TIPO_INSTALACAO		=21;
	public static final int HIDROMETRO_TIPO_SUBSTITUICAO	=22;
	public static final int RELIGACAO_TIPO					=23;
	public static final int HIDROMETRO_LOCAL_ARMAZENAGEM	=25;
	public static final int HIDROMETRO_LOCAL_INSTALACAO		=26;
	public static final int HIDROMETRO_PROTECAO				=27;
	public static final int HIDROMETRO_SITUACAO				=28;
	public static final int ORDEM_SERVICO_CORTE				=29;
	public static final int FOTOS							=30;
	public static final int ORDEM_SERVICO_HIDROMETRO_INSTALACAO		= 31;
	public static final int ORDEM_SERVICO_HIDROMETRO_SUBSTITUICAO	= 32;
	public static final int ORDEM_SERVICO_RELIGACAO					= 33;
	public static final int CONFIGURACAO							= 34;
	public static final int CHECKLIST_RESPOSTAS						= 35;
	public static final int CHECKLIST								= 36;
	public static final int INTERRUPCAO								= 37;

	//Dialog
	public static final int DIALOG_PROGRESS_ENVIAR			=101;
	public static final int DIALOG_PROGRESS_RECEBER			=102;
	public static final int DIALOG_PROGRESS_EXPORTAR		=103;
	public static final int DIALOG_PROGRESS_IMPORTAR		=114;
	public static final int DIALOG_OS_PESQUISAR				=104;
	public static final int DIALOG_OS_INICIAR				=105;
	public static final int DIALOG_OS_EXECUCAO				=106;
	public static final int DIALOG_OS_AVULSA				=107;
	public static final int DIALOG_OS_CANCELAR				=108;
	public static final int DIALOG_OS_DADOS_GERAIS			=109;
	public static final int DIALOG_OS_CONFIRMA_ENCERRAMENTO	=110;
	public static final int DIALOG_OS_GERAR_NOVA			=111;
	public static final int DIALOG_OS_CONFIRMA_GERAR_NOVA_OS=112;
	public static final int DIALOG_OS_CONFIRMA_CANCELAMENTO	=113;
	public static final int DIALOG_OS_MUDAR_NUMERO_OS		=114;
	public static final int DIALOG_OS_CONFIRMA_MUDAR_NUMERO_OS	=115;
	public static final int DIALOG_CONFIRMA_EXCLUSAO		=116;
	public static final int DIALOG_EDITAR					=117;
	public static final int DIALOG_EXCLUIR					=118;
	public static final int DIALOG_INTERRUPCAO_FINALIZAR	=119;
	public static final int DIALOG_CONFIRMACAO				=120;
	public static final int DIALOG_OS_TIRAR_FOTO1			=1;
	public static final int DIALOG_OS_TIRAR_FOTO2			=2;
	public static final int DIALOG_OS_TIRAR_FOTO3			=3;
	public static final int DIALOG_OS_TIRAR_FOTO4			=4;
	public static final int DIALOG_OS_TIRAR_FOTO5			=5;
	public static final int DIALOG_OS_TIRAR_FOTO6			=6;
	public static final int DIALOG_OS_TIRAR_FOTO7			=7;
	public static final int DIALOG_OS_TIRAR_FOTO_VALA_1		=8;
	public static final int DIALOG_OS_TIRAR_FOTO_VALA_2		=9;
	public static final int DIALOG_OS_TIRAR_FOTO_DIVERSAS	=10;


	//Status da OS
	public static final int OS_ID_STATUS_PENDENTE			=0;
	public static final int OS_ID_STATUS_ENCERRADA			=2;
	public static final int OS_ID_STATUS_CANCELADA			=3;
	public static final int OS_ID_STATUS_EM_EXECUCAO		=4;
	public static final String OS_STATUS_PENDENTE			="Pendente";
	public static final String OS_STATUS_ENCERRADA			="Encerrada";
	public static final String OS_STATUS_CANCELADA			="Cancelada";
	public static final String OS_STATUS_EM_EXECUCAO		="Em Execução";

	//Serviços
	public static final int OS_REATERRO_VALA								= 1;
	public static final int OS_RECOMPOSICAO_CALCADA							= 2;
	public static final int OS_RECOMPOSICAO_ASFALTICA_MANUTENCAO			= 3;
	public static final int OS_RECOMPOSICAO_ASFALTICA_LIGACAO_NOVA			= 4;
	public static final int OS_LIGACAO_NOVA									= 5;
	public static final int OS_VAZAMENTO_REDE								= 6;
	public static final int OS_VAZAMENTO_CAVALETE							= 7;
	public static final int OS_VAZAMENTO_RAMAL								= 8;
	public static final int OS_RETIRADA_ENTULHO								= 9;
	public static final int OS_CORTE_RAMAL									= 10;
	public static final int OS_INSTALACAO_HIDROMETRO						= 11;
	public static final int OS_SUPRESSAO									= 12;
	public static final int OS_SUBSTITUICAO_HIDROMETRO						= 13;
	public static final int OS_RELIGACAO									= 14;
	public static final int OS_REMANEJAMENTO								= 15;
	public static final int CHECKLIST_SAIDA									= 0;
	public static final int CHECKLIST_RETORNO								= 1;


    public static String getReleaseNumber(Context context)
	{
		PackageManager packageManager = (PackageManager) context.getPackageManager();
		try {
			return packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			return "";
		}
	}
	
	public static String getIMEI(Context context)
	{
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}
	
	public static SimpleCursorAdapter criarSimpleCursorAdapter(Context context, IBasicDAO<?> basicDAO,
			String[] from, int[] to, Activity activity)
	{
		
		Cursor cursor=basicDAO.obterCursor();
		activity.startManagingCursor(cursor);
		SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(context, 
				android.R.layout.simple_spinner_item, cursor, from, to);
		simpleCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return simpleCursorAdapter;
	}
	
	public static SimpleCursorAdapter criarSimpleCursorAdapter(Context context, IBasicDAO<?> basicDAO,
			String[] from, int[] to, Activity activity, int layoutCursorAdapter, int layoutDropDownView)
	{
		
		Cursor cursor=basicDAO.obterCursor();
		activity.startManagingCursor(cursor);
		SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(context, 
				layoutCursorAdapter, cursor, from, to);
		simpleCursorAdapter.setDropDownViewResource(layoutDropDownView);
		return simpleCursorAdapter;
	}

    public static SimpleCursorAdapter criarSimpleCursorAdapter(Context context, Cursor cursor,
                                                               String[] from, int[] to, Activity activity)
    {
        Cursor _cursor=cursor;
        activity.startManagingCursor(_cursor);
        SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(context,
                android.R.layout.simple_spinner_item, _cursor, from, to);
        simpleCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return simpleCursorAdapter;
    }

    public static int getItemId(Spinner spinner, String column)
	{
		int position=spinner.getSelectedItemPosition();
		Adapter adapter= spinner.getAdapter();
		Cursor c=(Cursor) adapter.getItem(position);
		int result= c.getInt(c.getColumnIndex(column));
		
		return result;
	}
	
	public static int getItemId(Spinner spinner, String column, int position)
    {
        Adapter adapter= spinner.getAdapter();
        Cursor c=(Cursor) adapter.getItem(position);
        int result= c.getInt(c.getColumnIndex(column));

        return result;
    }

    public static String getItemDescricao(Spinner spinner, String column)
    {
        int position=spinner.getSelectedItemPosition();
        Adapter adapter= spinner.getAdapter();
        Cursor c=(Cursor) adapter.getItem(position);
        String result= c.getString(c.getColumnIndex(column));

        return result;
    }
	
	public static String getItemDescricao(Spinner spinner, String column, int position)
	{
		Adapter adapter= spinner.getAdapter();
		Cursor c=(Cursor) adapter.getItem(position);
		String result= c.getString(c.getColumnIndex(column));
		
		return result;
	}
	
	public static int getItemPositionFromId(Spinner spinner, String column, int id) 
	{
		int position=0;
		Adapter adapter= spinner.getAdapter();
		
		//Quantidade de registros
		int qtd=adapter.getCount();
		
		//Procura na lista
		for (int i=0; i<qtd; i++) 
		{
			//Cursor
			Cursor c=(Cursor) adapter.getItem(i);
			//Verifica se é o mesmo valor
			if (c.getInt(c.getColumnIndex(column))==id) {
				position=i;
				break;
			}
		}
		
		return position;
	}
	
	public static UUID getSerialPortUUID() {
	    return UUID.fromString( UUID_SERIAL_PORT_PROFILE );
	}
	
	public static boolean isSupportBluetooth(Context context)
	{
		boolean bolReturn = false;
		try 
		{
			android.bluetooth.BluetoothAdapter bluetoothAdapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter();
			if (bluetoothAdapter == null) {
			    // Device does not support Bluetooth
				bolReturn =  false;
			} else {
				bolReturn =  true;
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bolReturn;
	}
	
	public static void createAlertDialog(Context context, String dialogMessage, String dialogTitle,
			String dialogOK)
	{
		//Cria Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		//Configura Message
		builder.setMessage(dialogMessage);
		builder.setTitle(dialogTitle);
		builder.setPositiveButton(dialogOK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
		//Cria Alerta
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public static void createAlertDialog(Context context, int dialogMessage, int dialogTitle,
			int dialogOK)
	{
		Resources resources = context.getResources();
		//Cria Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		//Configura Message
		builder.setMessage(resources.getText(dialogMessage));
		builder.setTitle(resources.getText(dialogTitle));
		builder.setPositiveButton(resources.getText(dialogOK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
		//Cria Alerta
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public static void createAlertDialog(Context context, String dialogMessage, int dialogTitle,
			int dialogOK)
	{
		Resources resources = context.getResources();
		//Cria Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		//Configura Message
		builder.setMessage(dialogMessage);
		builder.setTitle(resources.getText(dialogTitle));
		builder.setPositiveButton(resources.getText(dialogOK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
		//Cria Alerta
		AlertDialog dialog = builder.create();
		dialog.show();
	}


    public static Date stringToDate(String format, String date)
    {
		if (date==null || date.length()==0) return null;

        Date datRetorno=null;
        SimpleDateFormat sdf=new SimpleDateFormat(format);

        try
        {
            datRetorno=sdf.parse(date);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return datRetorno;
    }

    public static String dateToString(String format, Date date)
    {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String datRetorno="";

        if (date==null) {
            return datRetorno;
        } else
        {
            try
            {
                datRetorno=sdf.format(date);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return datRetorno;
    }

	public static Date stringToDate(String format, JSONObject date)
	{
		Date result= null;
		try
		{
			result = new Date(date.getInt("year"), date.getInt("month"), date.getInt("date"));
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		return result;
	}

	public static boolean isExternalStorageReadOnly() {
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
			return true;
		}
		return false;
	}

	public static boolean isExternalStorageAvailable() {
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
			return true;
		}
		return false;
	}

	public static boolean isOnline(Context context)
	{
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		return (networkInfo != null && networkInfo.isConnected());
	}

	public static void lockScreenOrientation(Activity activity)
	{
		int currentOrientation = activity.getResources().getConfiguration().orientation;
		if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}

	public static void unlockScreenOrientation(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}
}
