package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class EquipeComponentesVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdEquipe;
	private int IdEquipeComponente;
	private int IndicadorResponsavel;
	private String NomeComponente;
	private String IdFuncionario;
	
	public EquipeComponentesVO() {
	}
	
	public EquipeComponentesVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdEquipe() {
		return IdEquipe;
	}
	public void setIdEquipe(int idEquipe) {
		IdEquipe = idEquipe;
	}
	public int getIdEquipeComponente() {
		return IdEquipeComponente;
	}
	public void setIdEquipeComponente(int idEquipeComponente) {
		IdEquipeComponente = idEquipeComponente;
	}
	public int getIndicadorResponsavel() {
		return IndicadorResponsavel;
	}
	public void setIndicadorResponsavel(int indicadorResponsavel) {
		IndicadorResponsavel = indicadorResponsavel;
	}
	public String getNomeComponente() {
		return NomeComponente;
	}
	public void setNomeComponente(String nomeComponente) {
		NomeComponente = nomeComponente;
	}
	public String getIdFuncionario() {
		return IdFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		IdFuncionario = idFuncionario;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdEquipe=0;
			this.IdEquipeComponente=Integer.parseInt(object.getProperty("idEquipeComponente").toString());
			this.IndicadorResponsavel=Integer.parseInt(object.getProperty("indicadorResponsavel").toString());
			this.NomeComponente="";
			this.IdFuncionario=object.getProperty("idFuncionario").toString();
		}
	}
	
}
