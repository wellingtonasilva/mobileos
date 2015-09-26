package wsilva.com.br.mobileos.core.util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexao 
{
	private static final String DATABASE_NAME = "mobileos.db";
	private static final int DATABASE_VERSION = 1;
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	private final Context context;
	
	public Conexao(Context context)
	{
		this.context=context;
	}

	public SQLiteDatabase open() throws SQLException
	{
		helper=new DatabaseHelper(context, DATABASE_NAME, DATABASE_VERSION);
		db=helper.getReadableDatabase();
		return db;
	}
	
	public void close()
	{
		helper.close();
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{

		@Override
		public void onOpen(SQLiteDatabase db) 
		{
			super.onOpen(db);
			if (!db.isReadOnly())
			{
				db.execSQL("PRAGMA foreign_keys=ON;");
				db.execSQL("PRAGMA encoding='UTF-8';");
			}
				
		}
		
		public DatabaseHelper(Context context, String databaseName, int dabaseVersion) 
		{
			super(context, databaseName, null, dabaseVersion);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
		}

        public void execSQLScript(SQLiteDatabase db, String[] sql){
            for(String s : sql){
                db.execSQL(s);
            }
        }
	}
	
}
