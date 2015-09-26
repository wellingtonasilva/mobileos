package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class OrdemServicoCorteVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idOrdemServicoCorte;
	private int idOrdemServico;
	private Date dataCorte;
	private String horaCorte;
	private int leituraCorte;
	private String numeroSelo;
	private String idFuncionario;
	private String numeroHidrometro;
	private int idMotivoCorte;
	private int idCorteTipo;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	
	public OrdemServicoCorteVO() {
	}
	
	public OrdemServicoCorteVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdOrdemServicoCorte() {
		return idOrdemServicoCorte;
	}
	public void setIdOrdemServicoCorte(int idOrdemServicoCorte) {
		this.idOrdemServicoCorte = idOrdemServicoCorte;
	}
	public int getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Date getDataCorte() {
		return dataCorte;
	}
	public void setDataCorte(Date dataCorte) {
		this.dataCorte = dataCorte;
	}
	public String getHoraCorte() {
		return horaCorte;
	}
	public void setHoraCorte(String horaCorte) {
		this.horaCorte = horaCorte;
	}
	public int getLeituraCorte() {
		return leituraCorte;
	}
	public void setLeituraCorte(int leituraCorte) {
		this.leituraCorte = leituraCorte;
	}
	public String getNumeroSelo() {
		return numeroSelo;
	}
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public int getIdMotivoCorte() {
		return idMotivoCorte;
	}
	public void setIdMotivoCorte(int idMotivoCorte) {
		this.idMotivoCorte = idMotivoCorte;
	}
	public int getIdCorteTipo() {
		return idCorteTipo;
	}
	public void setIdCorteTipo(int idCorteTipo) {
		this.idCorteTipo = idCorteTipo;
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
			this.idOrdemServicoCorte=Integer.parseInt(object.getProperty("idOrdemServicoCorte").toString());
			this.idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			//this.dataCorte=Integer.parseInt(object.getProperty("dataCorte").toString());
			this.horaCorte=object.getProperty("horaCorte").toString();
			this.leituraCorte=Integer.parseInt(object.getProperty("leituraCorte").toString());
			this.numeroSelo=object.getProperty("numeroSelo").toString();
			this.idFuncionario=object.getProperty("idFuncionario").toString();
			this.numeroHidrometro=object.getProperty("numeroHidrometro").toString();
			this.idMotivoCorte=Integer.parseInt(object.getProperty("idMotivoCorte").toString());
			this.idCorteTipo=Integer.parseInt(object.getProperty("idCorteTipo").toString());
		}
	}
}
