package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class MotivoEncerramentoVO extends EntityVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int IdMotivoEncerramento;
	private String DescricaoMotivoEncerramento;
	
	public MotivoEncerramentoVO() {
	}
	
	public MotivoEncerramentoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdMotivoEncerramento() {
		return IdMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(int idMotivoEncerramento) {
		IdMotivoEncerramento = idMotivoEncerramento;
	}
	public String getDescricaoMotivoEncerramento() {
		return DescricaoMotivoEncerramento;
	}
	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		DescricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdMotivoEncerramento=Integer.parseInt(object.getProperty("idMotivoEncerramento").toString());
			this.DescricaoMotivoEncerramento=object.getProperty("motivoEncerramento").toString();
		}
	}
	
}
