package wsilva.com.br.mobileos.entity;

import java.util.Date;

public class ArquivoImportadosVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private String nomeArquivo;
	private Date dataImportacao;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public Date getDataImportacao() {
		return dataImportacao;
	}
	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}
}
