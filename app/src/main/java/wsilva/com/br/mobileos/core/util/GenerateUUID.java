package wsilva.com.br.mobileos.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;

public class GenerateUUID 
{
	private Context context=null;
	
	public GenerateUUID(Context context)
	{
		this.context=context;
	}

	public String getCombinedUniqueID(String string)
	{
		return string;
	}
	
	public String getCombinedUniqueID()
	{
		String m_szUniqueID="";
		
		try 
		{
			String m_szLongID = getImeiID() + getPseudoUniqueID() +  getAndroidID() + getWlanMacAdress() + getBlueToothMacAddress();
	    	
			//Compute md5
	    	MessageDigest m = null;
			try 
			{
				m = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) 
			{
				e.printStackTrace();
			}
			m.update(m_szLongID.getBytes(),0,m_szLongID.length());
			
			// get md5 bytes
			byte p_md5Data[] = m.digest();
			
			// create a hex string
			m_szUniqueID = new String();
			for (int i=0;i<p_md5Data.length;i++) 
			{
				int b =  (0xFF & p_md5Data[i]);
				
				// if it is a single digit, make sure it have 0 in front (proper padding)
				if (b <= 0xF) m_szUniqueID+="0";
				
				// add number to string
				m_szUniqueID+=Integer.toHexString(b);
			}
			
			// hex string to uppercase
			m_szUniqueID = m_szUniqueID.toUpperCase();
			
		} catch (Exception e) 
		{
			Log.e("MOBILEOS", e.getMessage().toString());
		}
		
		return m_szUniqueID;
	}
	
	private String getImeiID()
	{
		String szImei="";
		
		try 
		{
			TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
			szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
		} catch (Exception e) 
		{
			Log.e("MOBILEOS", e.getMessage().toString());
		}
		
		return szImei;
	}
	
	private String getPseudoUniqueID()
	{
		String m_szDevIDShort="";
		try 
		{
			//we make this look like a valid IMEI
			//13 digits
			m_szDevIDShort = "35" + 
		        	Build.BOARD.length()%10+ Build.BRAND.length()%10 +
		        	Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +
		        	Build.DISPLAY.length()%10 + Build.HOST.length()%10 +
		        	Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +
		        	Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +
		        	Build.TAGS.length()%10 + Build.TYPE.length()%10 +
		        	Build.USER.length()%10 ; 			
		} catch (Exception e) 
		{
			Log.e("MOBILEOS", e.getMessage().toString());
		}
		
		return m_szDevIDShort;
	}
	
	private String getAndroidID()
	{
		String m_szAndroidID=null;
		try 
		{
			m_szAndroidID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		} catch (Exception e) 
		{
			Log.e("MOBILEOS", e.getMessage().toString());
		}
		
		return m_szAndroidID; 
	}
	

	private String getWlanMacAdress()
	{
		String m_szWLANMAC="";
		try 
		{
			WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
		} catch (Exception e) 
		{
			Log.e("MOBILEOS", e.getMessage().toString());
		}
		
		return m_szWLANMAC;
	}
	 
	private String getBlueToothMacAddress()
	{
		String m_szBTMAC="";
		try 
		{
			//Local Bluetooth adapter
			BluetoothAdapter m_BluetoothAdapter	= null; 
	    	m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	    	m_szBTMAC = m_BluetoothAdapter.getAddress();
		} catch (Exception e) 
		{
			Log.e("MOBILEOS", e.getMessage().toString());
		}
		
		return m_szBTMAC;
	}
	
	public static String formatCombinedUniqueID(String id)
	{
		String key1=id.substring(0, 8);
		String key2=id.substring(8,16);
		String key3=id.substring(16,24);
		String key4=id.substring(24, 32);
		
		return key1 + "-" + key2 + "-" + key3 + "-" + key4;
	}
	
	
	
	
}
