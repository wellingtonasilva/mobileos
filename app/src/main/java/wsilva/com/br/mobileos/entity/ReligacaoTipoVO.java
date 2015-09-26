package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class ReligacaoTipoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idTipoReligacao;
	private String descricaoTipoReligacao;
	
	public ReligacaoTipoVO() {
	}
	
	public ReligacaoTipoVO(SoapObject object) {
		serialize(object);
	}
	public int getIdTipoReligacao() {
		return idTipoReligacao;
	}
	public void setIdTipoReligacao(int idTipoReligacao) {
		this.idTipoReligacao = idTipoReligacao;
	}
	public String getDescricaoTipoReligacao() {
		return descricaoTipoReligacao;
	}
	public void setDescricaoTipoReligacao(String descricaoTipoReligacao) {
		this.descricaoTipoReligacao = descricaoTipoReligacao;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idTipoReligacao=Integer.parseInt(object.getProperty("idTipoReligacao").toString());
			this.descricaoTipoReligacao=object.getProperty("descricaoTipoReligacao").toString();
		}
	}

}
