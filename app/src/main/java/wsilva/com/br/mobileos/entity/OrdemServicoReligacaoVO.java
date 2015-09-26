package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class OrdemServicoReligacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idOrdemServicoReligacao;
	private Date dataReligacao;
	private String horaReligacao;
	private String idFuncionario;
	private String numeroHidrometro;
	private Date dataInstalacaoHidrometro;
	private int idLocalInstalacaoHidrometro;
	private int idProtecaoHidrometro;
	private int leituraInstalacao;
	private String numeroSelo;
	private String indicadorCavalete;
	private int idOrdemServico;
	private int indicadorTrocaRegistro;
	private int indicadorTrocaProtecao;
	private int idTipoReligacao;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	
	public OrdemServicoReligacaoVO() {
	}
	
	public OrdemServicoReligacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdOrdemServicoReligacao() {
		return idOrdemServicoReligacao;
	}
	public void setIdOrdemServicoReligacao(int idOrdemServicoReligacao) {
		this.idOrdemServicoReligacao = idOrdemServicoReligacao;
	}
	public Date getDataReligacao() {
		return dataReligacao;
	}
	public void setDataReligacao(Date dataReligacao) {
		this.dataReligacao = dataReligacao;
	}
	public String getHoraReligacao() {
		return horaReligacao;
	}
	public void setHoraReligacao(String horaReligacao) {
		this.horaReligacao = horaReligacao;
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
	public Date getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}
	public void setDataInstalacaoHidrometro(Date dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
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
	public String getIndicadorCavalete() {
		return indicadorCavalete;
	}
	public void setIndicadorCavalete(String indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}
	public int getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public int getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(int indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
	public int getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(int indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public int getIdTipoReligacao() {
		return idTipoReligacao;
	}
	public void setIdTipoReligacao(int idTipoReligacao) {
		this.idTipoReligacao = idTipoReligacao;
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
			idOrdemServicoReligacao=Integer.parseInt(object.getProperty("idOrdemServicoReligacao").toString());
			//dataReligacao=object.getProperty("dataReligacao").toString();
			horaReligacao=object.getProperty("horaReligacao").toString();
			idFuncionario=object.getProperty("idFuncionario").toString();
			numeroHidrometro=object.getProperty("numeroHidrometro").toString();
			//dataInstalacaoHidrometro=object.getProperty("dataInstalacaoHidrometro").toString();
			idLocalInstalacaoHidrometro=Integer.parseInt(object.getProperty("idLocalInstalacaoHidrometro").toString());
			idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			leituraInstalacao=Integer.parseInt(object.getProperty("leituraInstalacao").toString());
			numeroSelo=object.getProperty("numeroSelo").toString();
			indicadorCavalete=object.getProperty("indicadorCavalete").toString();
			idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			indicadorTrocaRegistro=Integer.parseInt(object.getProperty("indicadorTrocaRegistro").toString());
			indicadorTrocaProtecao=Integer.parseInt(object.getProperty("indicadorTrocaProtecao").toString());
		}
	}
	
	
	
}
