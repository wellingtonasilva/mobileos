package wsilva.com.br.mobileos.entity;

public class ConfiguracoesVO extends EntityVO 
{

	public static final long serialVersionUID = 1L;
	public String ftpIP;
	public String ftpUsuario;
	public String ftpSenha;
	public int ftpPorta;
	
	public String dispositivoNome;
	public String dispositivoID;
	public String dispositivoPastaDownload;
	public String dispositivoPastaUpload;
	public String dispositivoPastaImage;
	
	public int utilizarFTPEnviarArquivos;
	public int utilizarFTPReceberArquivos;
	public int enviarArquivosAposExportacao;
	public int enviarFotosViaFTP;
	
	public String webURL;
	public int utilizarWEBEnviarArquivos;
	public int utilizarWEBPReceberArquivos;
	public int enviarFotosViaWEB;	
	
	public String smsTelefone;
	public int enviarSmsAposIniciar;
	public int enviarSmsAposEncerrar;
	
	public String coletorEmpresa;
	public String coletorCodigo;
	public String coletorEquipe;
	
	public String dispositivoSerial;
	public int permiteExecucaoEmParaleloOS;
	public int exigeChecklist;
	public int exigeInicioAtividade;
	public String emailDestinatario;
	public int enviarEmailAposEncerrar;
	public int enviarEmailAposExportar;
	public int logomarca;
}
