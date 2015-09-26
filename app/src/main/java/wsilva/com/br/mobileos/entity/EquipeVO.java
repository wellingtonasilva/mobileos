package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class EquipeVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdEquipe;
	private String NomeEquipe;
	private String NumeroPlacaVeiculo;
	private int CargaHorarioTrabalhoDia;
	
	public EquipeVO() {
	}
	
	public EquipeVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdEquipe() {
		return IdEquipe;
	}
	public void setIdEquipe(int idEquipe) {
		IdEquipe = idEquipe;
	}
	public String getNomeEquipe() {
		return NomeEquipe;
	}
	public void setNomeEquipe(String nomeEquipe) {
		NomeEquipe = nomeEquipe;
	}
	public String getNumeroPlacaVeiculo() {
		return NumeroPlacaVeiculo;
	}
	public void setNumeroPlacaVeiculo(String numeroPlacaVeiculo) {
		NumeroPlacaVeiculo = numeroPlacaVeiculo;
	}
	public int getCargaHorarioTrabalhoDia() {
		return CargaHorarioTrabalhoDia;
	}
	public void setCargaHorarioTrabalhoDia(int cargaHorarioTrabalhoDia) {
		CargaHorarioTrabalhoDia = cargaHorarioTrabalhoDia;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.IdEquipe=Integer.parseInt(object.getProperty("idEquipe").toString());
			this.NomeEquipe=object.getProperty("nomeEquipe").toString();
			this.NumeroPlacaVeiculo=object.getProperty("numeroPlacaVeiculo").toString();
			this.CargaHorarioTrabalhoDia=Integer.parseInt(object.getProperty("cargaHorarioTrabalho").toString());
		}
	}
	
}
