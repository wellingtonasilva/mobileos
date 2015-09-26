package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class OrdemServicoHidrometroInstalacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idOrdemServicoInstalacaoHM;
	private String numeroHidrometro;
	private Date dataInstalacaoHidrometro;
	private String idTipoMedicao;
	private int idLocalInstalacaoHidrometro;
	private int idProtecaoHidrometro;
	private int idOrdemServico;
	private int indicadorTrocaProtecao;
	private int indicadorTrocaRegistro;
	private int leituraInstalacao;
	private String numeroSelo;
	private String indcadorCavalete;
	private String idFuncionario;
	private int idTipoInstalacaoHM;
	private String horaInstalacaoHidrometro;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	
	public OrdemServicoHidrometroInstalacaoVO() {
	}
	
	public OrdemServicoHidrometroInstalacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdOrdemServicoInstalacaoHM() {
		return idOrdemServicoInstalacaoHM;
	}
	public void setIdOrdemServicoInstalacaoHM(int idOrdemServicoInstalacaoHM) {
		this.idOrdemServicoInstalacaoHM = idOrdemServicoInstalacaoHM;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public Date getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}
	public void setDataInstalacaoHidrometro(Date dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}
	public String getIdTipoMedicao() {
		return idTipoMedicao;
	}
	public void setIdTipoMedicao(String idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}
	public int getIdLocalInstalacaoHidrometro() {
		return idLocalInstalacaoHidrometro;
	}
	public void setIdLocalInstalacaoHidrometro(int idLocalInstalacaoHidrometro) {
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
	}
	public int getIdProtecaoHidrometro() {
		return idProtecaoHidrometro;
	}
	public void setIdProtecaoHidrometro(int idProtecaoHidrometro) {
		this.idProtecaoHidrometro = idProtecaoHidrometro;
	}
	public int getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public int getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(int indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public int getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(int indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
	public int getLeituraInstalacao() {
		return leituraInstalacao;
	}
	public void setLeituraInstalacao(int leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}
	public String getNumeroSelo() {
		return numeroSelo;
	}
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}
	public String getIndcadorCavalete() {
		return indcadorCavalete;
	}
	public void setIndcadorCavalete(String indcadorCavalete) {
		this.indcadorCavalete = indcadorCavalete;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public int getIdTipoInstalacaoHM() {
		return idTipoInstalacaoHM;
	}
	public void setIdTipoInstalacaoHM(int idTipoInstalacaoHM) {
		this.idTipoInstalacaoHM = idTipoInstalacaoHM;
	}
	public String getHoraInstalacaoHidrometro() {
		return horaInstalacaoHidrometro;
	}
	public void setHoraInstalacaoHidrometro(String horaInstalacaoHidrometro) {
		this.horaInstalacaoHidrometro = horaInstalacaoHidrometro;
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
			this.idOrdemServicoInstalacaoHM=Integer.parseInt(object.getProperty("idOrdemServicoInstalacaoHM").toString());
			this.numeroHidrometro=object.getProperty("numeroHidrometro").toString();
			this.dataInstalacaoHidrometro=new Date();
			this.idTipoMedicao=object.getProperty("idTipoMedicao").toString();
			this.idLocalInstalacaoHidrometro=Integer.parseInt(object.getProperty("idLocalInstalacaoHidrometro").toString());
			this.idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			this.idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			this.indicadorTrocaProtecao=Integer.parseInt(object.getProperty("indicadorTrocaProtecao").toString());
			this.indicadorTrocaRegistro=Integer.parseInt(object.getProperty("indicadorTrocaRegistro").toString());
			this.leituraInstalacao=Integer.parseInt(object.getProperty("leituraInstalacao").toString());
			this.numeroSelo=object.getProperty("numeroSelo").toString();
			this.indcadorCavalete=object.getProperty("indcadorCavalete").toString();
			this.idFuncionario=object.getProperty("idFuncionario").toString();
			this.idTipoInstalacaoHM=Integer.parseInt(object.getProperty("idTipoInstalacaoHM").toString());
			this.horaInstalacaoHidrometro=object.getProperty("horaInstalacaoHidrometro").toString();
		}
	}
	
	
}