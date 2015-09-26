package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class CorteTipoVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;

	private int idCorteTipo;
	private String descricaoCorteTipo;
	
	public CorteTipoVO() {
	}
	
	public CorteTipoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdCorteTipo() {
		return idCorteTipo;
	}
	public void setIdCorteTipo(int idCorteTipo) {
		this.idCorteTipo = idCorteTipo;
	}
	public String getDescricaoCorteTipo() {
		return descricaoCorteTipo;
	}
	public void setDescricaoCorteTipo(String descricaoCorteTipo) {
		this.descricaoCorteTipo = descricaoCorteTipo;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idCorteTipo=Integer.parseInt(object.getProperty("idCorteTipo").toString());
			this.descricaoCorteTipo=object.getProperty("descricaoCorteTipo").toString();
		}
	}
}
