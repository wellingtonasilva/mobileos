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
