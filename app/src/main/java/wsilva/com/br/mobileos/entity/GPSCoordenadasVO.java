package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class GPSCoordenadasVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	
	private Date data;
	private String hora;
	private Double latitude;
	private Double longitude;
	private int idEquipe;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	
	public GPSCoordenadasVO() {
	}
	
	public GPSCoordenadasVO(SoapObject object) {
		serialize(object);
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public int getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}
	public int getIdEquipeExecucao() {
		return idEquipeExecucao;
	}
	public void setIdEquipeExecucao(int idEquipeExecucao) {
		this.idEquipeExecucao = idEquipeExecucao;
	}
	public String getDescricaoEquipeExecucao() {
		return descricaoEquipeExecucao;
	}
	public void setDescricaoEquipeExecucao(String descricaoEquipeExecucao) {
		this.descricaoEquipeExecucao = descricaoEquipeExecucao;
	}
	public int getIndicadorEnvio() {
		return indicadorEnvio;
	}
	public void setIndicadorEnvio(int indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
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
