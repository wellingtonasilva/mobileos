package wsilva.com.br.mobileos.entity;

import java.io.Serializable;
import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class FotoVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	public int numeroOS;
	public String nomeFoto;
	public String descricaoFoto;
	public int tipoFoto;
	public Double latitude;
	public Double longitude;
	public Date dataFoto;
	public String horaFoto;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	public int numeroFoto;
	
	public FotoVO() {
	}
	
	public FotoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.numeroOS=Integer.parseInt(object.getProperty("numeroOS").toString());
			this.nomeFoto=object.getProperty("nomeFoto").toString();
			this.descricaoFoto=object.getProperty("descricaoFoto").toString();
			this.tipoFoto=Integer.parseInt(object.getProperty("idTipoFoto").toString());
			this.latitude=Double.parseDouble(object.getProperty("latitude").toString());
			this.longitude=Double.parseDouble(object.getProperty("longitude").toString());
			this.horaFoto=object.getProperty("horaFoto").toString();
		}
	}
	
	
}
