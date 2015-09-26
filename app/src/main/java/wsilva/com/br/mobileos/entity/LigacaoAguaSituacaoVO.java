package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class LigacaoAguaSituacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idSituacaoLigacaoAgua;
	private String descricaoSituacaoLigacaoAgua;
	
	public LigacaoAguaSituacaoVO() {
	}
	
	public LigacaoAguaSituacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	public void setIdSituacaoLigacaoAgua(int idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}
	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idSituacaoLigacaoAgua=Integer.parseInt(object.getProperty("idSituacaoLigacaoAgua").toString());
			this.descricaoSituacaoLigacaoAgua=object.getProperty("descricaoSituacaoLigacaoAgua").toString();
		}
	}
}
