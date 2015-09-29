package wsilva.com.br.mobileos.core.util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import wsilva.com.br.mobileos.dao.checklist.ChecklistDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistGrupoDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistItemDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistOpcaoDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistRespostaDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistRespostaGrupoDAO;
import wsilva.com.br.mobileos.dao.checklist.FuncionariosDAO;
import wsilva.com.br.mobileos.dao.checklist.VeiculosDAO;
import wsilva.com.br.mobileos.dao.os.AgenteExternoDAO;
import wsilva.com.br.mobileos.dao.os.ArquivoImportadosDAO;
import wsilva.com.br.mobileos.dao.os.ColetorWebDAO;
import wsilva.com.br.mobileos.dao.os.ConfiguracoesDAO;
import wsilva.com.br.mobileos.dao.os.CorteTipoDAO;
import wsilva.com.br.mobileos.dao.os.EquipeComponentesDAO;
import wsilva.com.br.mobileos.dao.os.EquipeDAO;
import wsilva.com.br.mobileos.dao.os.FotoDAO;
import wsilva.com.br.mobileos.dao.os.GPSCoordenadasDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalArmazenagemDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoSubstituicaoDAO;
import wsilva.com.br.mobileos.dao.os.ImovelDAO;
import wsilva.com.br.mobileos.dao.os.ImovelDebitosDAO;
import wsilva.com.br.mobileos.dao.ocorrencia.InterrupcaoDAO;
import wsilva.com.br.mobileos.dao.ocorrencia.InterrupcaoMotivoDAO;
import wsilva.com.br.mobileos.dao.os.LigacaoAguaSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.LigacaoEsgotoSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.LocalOcorrenciaDAO;
import wsilva.com.br.mobileos.dao.os.LogradouroTipoDAO;
import wsilva.com.br.mobileos.dao.os.MaterialDAO;
import wsilva.com.br.mobileos.dao.os.MaterialUtilizadoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoCorteDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoCorteDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoHidrometroInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoHidrometroSubstituicaoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoReligacaoDAO;
import wsilva.com.br.mobileos.dao.os.PavimentoTipoDAO;
import wsilva.com.br.mobileos.dao.os.RedeCausaVazamentoDAO;
import wsilva.com.br.mobileos.dao.os.RedeDiametroDAO;
import wsilva.com.br.mobileos.dao.os.RedeMaterialDAO;
import wsilva.com.br.mobileos.dao.os.RedeTipoDAO;
import wsilva.com.br.mobileos.dao.os.ReligacaoTipoDAO;
import wsilva.com.br.mobileos.dao.os.ServicoTipoDAO;
import wsilva.com.br.mobileos.dao.os.UsuarioDAO;
import wsilva.com.br.mobileos.dao.os.ValaDAO;


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
			db.execSQL(OrdemServicoDAO.CREATE_TABLE);
			db.execSQL(AgenteExternoDAO.CREATE_TABLE);
			db.execSQL(EquipeDAO.CREATE_TABLE);
			db.execSQL(EquipeComponentesDAO.CREATE_TABLE);
			db.execSQL(FotoDAO.CREATE_TABLE);
			db.execSQL(LocalOcorrenciaDAO.CREATE_TABLE);
			db.execSQL(MaterialUtilizadoDAO.CREATE_TABLE);
			db.execSQL(MaterialDAO.CREATE_TABLE);
			db.execSQL(MotivoEncerramentoDAO.CREATE_TABLE);
			db.execSQL(RedeDiametroDAO.CREATE_TABLE);
			db.execSQL(RedeMaterialDAO.CREATE_TABLE);
			db.execSQL(RedeTipoDAO.CREATE_TABLE);
			db.execSQL(ServicoTipoDAO.CREATE_TABLE);
			db.execSQL(ValaDAO.CREATE_TABLE);
			db.execSQL(UsuarioDAO.CREATE_TABLE);
			db.execSQL(RedeCausaVazamentoDAO.CREATE_TABLE);
			db.execSQL(PavimentoTipoDAO.CREATE_TABLE);
			db.execSQL(GPSCoordenadasDAO.CREATE_TABLE);
			db.execSQL(ConfiguracoesDAO.CREATE_TABLE);
			db.execSQL(ArquivoImportadosDAO.CREATE_TABLE);
			db.execSQL(ColetorWebDAO.CREATE_TABLE);
			db.execSQL(MotivoCorteDAO.CREATE_TABLE);
			db.execSQL(OrdemServicoCorteDAO.CREATE_TABLE);
			db.execSQL(ImovelDAO.CREATE_TABLE);
			db.execSQL(ImovelDebitosDAO.CREATE_TABLE);
			db.execSQL(OrdemServicoReligacaoDAO.CREATE_TABLE);
			db.execSQL(HidrometroLocalInstalacaoDAO.CREATE_TABLE);
			db.execSQL(HidrometroProtecaoDAO.CREATE_TABLE);
			db.execSQL(HidrometroLocalArmazenagemDAO.CREATE_TABLE);
			db.execSQL(HidrometroSituacaoDAO.CREATE_TABLE);
			db.execSQL(HidrometroTipoSubstituicaoDAO.CREATE_TABLE);
			db.execSQL(OrdemServicoHidrometroSubstituicaoDAO.CREATE_TABLE);
			db.execSQL(OrdemServicoHidrometroInstalacaoDAO.CREATE_TABLE);
			db.execSQL(LigacaoAguaSituacaoDAO.CREATE_TABLE);
			db.execSQL(LigacaoEsgotoSituacaoDAO.CREATE_TABLE);
			db.execSQL(CorteTipoDAO.CREATE_TABLE);
			db.execSQL(HidrometroTipoInstalacaoDAO.CREATE_TABLE);
			db.execSQL(ReligacaoTipoDAO.CREATE_TABLE);
			db.execSQL(ChecklistGrupoDAO.CREATE_TABLE);
			db.execSQL(ChecklistItemDAO.CREATE_TABLE);
			db.execSQL(ChecklistOpcaoDAO.CREATE_TABLE);
			db.execSQL(ChecklistRespostaDAO.CREATE_TABLE);
			db.execSQL(ChecklistDAO.CREATE_TABLE);
			db.execSQL(ChecklistRespostaGrupoDAO.CREATE_TABLE);
			db.execSQL(InterrupcaoDAO.CREATE_TABLE);
			db.execSQL(InterrupcaoMotivoDAO.CREATE_TABLE);
			db.execSQL(FuncionariosDAO.CREATE_TABLE);
			db.execSQL(VeiculosDAO.CREATE_TABLE);
			db.execSQL(LogradouroTipoDAO.CREATE_TABLE);
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
