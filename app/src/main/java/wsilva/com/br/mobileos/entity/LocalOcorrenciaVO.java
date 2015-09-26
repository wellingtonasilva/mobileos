package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class LocalOcorrenciaVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdLocalOcorrencia;
	private String DescricaoLocalOcorrencia;
	
	public LocalOcorrenciaVO() {
	}
	
	public LocalOcorrenciaVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdLocalOcorrencia() {
		return IdLocalOcorrencia;
	}
	public void setIdLocalOcorrencia(int idLocalOcorrencia) {
		IdLocalOcorrencia = idLocalOcorrencia;
	}
	public String getDescricaoLocalOcorrencia() {
		return DescricaoLocalOcorrencia;
	}
	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		DescricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.IdLocalOcorrencia=Integer.parseInt(object.getProperty("").toString());
			this.DescricaoLocalOcorrencia=object.getProperty("").toString();
		}
	}
	
	
}
