package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class MotivoCorteVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int idMotivoCorte;
	private String descricaoMotivoCorte;
	
	public MotivoCorteVO() {
	}
	
	public MotivoCorteVO(SoapObject object) {
		serialize(object);
	}
	public int getIdMotivoCorte() {
		return idMotivoCorte;
	}
	public void setIdMotivoCorte(int idMotivoCorte) {
		this.idMotivoCorte = idMotivoCorte;
	}
	public String getDescricaoMotivoCorte() {
		return descricaoMotivoCorte;
	}
	public void setDescricaoMotivoCorte(String descricaoMotivoCorte) {
		this.descricaoMotivoCorte = descricaoMotivoCorte;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idMotivoCorte=Integer.parseInt(object.getProperty("idMotivoCorte").toString());
			this.descricaoMotivoCorte=object.getProperty("descricaoMotivoCorte").toString();
		}
	}
}

