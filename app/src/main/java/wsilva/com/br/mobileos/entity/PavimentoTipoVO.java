package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class PavimentoTipoVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int IdPavimentoTipo;
	private String DescricaoPavimentoTipo;
	
	public PavimentoTipoVO() {
	}
	
	public PavimentoTipoVO(SoapObject object) {
		serialize(object);
	}
	public int getIdPavimentoTipo() {
		return IdPavimentoTipo;
	}
	public void setIdPavimentoTipo(int idPavimentoTipo) {
		IdPavimentoTipo = idPavimentoTipo;
	}
	public String getDescricaoPavimentoTipo() {
		return DescricaoPavimentoTipo;
	}
	public void setDescricaoPavimentoTipo(String descricaoPavimentoTipo) {
		DescricaoPavimentoTipo = descricaoPavimentoTipo;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.IdPavimentoTipo=Integer.parseInt(object.getProperty("idPavimentoTipo").toString());
			this.DescricaoPavimentoTipo=object.getProperty("pavimentoTipo").toString();
		}
	}

}
