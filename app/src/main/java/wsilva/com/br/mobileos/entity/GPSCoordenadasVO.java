package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class GPSCoordenadasVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public Date data;
	public String hora;
	public Double latitude;
	public Double longitude;
	public int idEquipe;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	
	public GPSCoordenadasVO() {
	}
	
	public GPSCoordenadasVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			//this.data=object.getProperty("dataCoordenada").toString();
			this.hora=object.getProperty("horaCoordenada").toString();
			this.latitude=Double.parseDouble(object.getProperty("latitude").toString());
			this.longitude=Double.parseDouble(object.getProperty("longitude").toString());
			//this.idEquipe=Integer.parseInt(object.getProperty("").toString());
		}
	}
}
