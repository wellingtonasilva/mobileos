package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class OrdemServicoHidrometroSubstituicaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idOrdemServicoSubstituicaoHM;
	private String numerHidrometroAtual;
	private String indicadorTipoMedicaoAtual;
	private Date dataRetirada;
	private int leituraRetirada;
	private int idSituacaoHidrometro;
	private int idLocalArmazenagemHidrometro;
	private String numeroHidrometroNovo;
	private Date dataInstalacaoHidrometroNovo;
	private String indicadorTipoMedicao;
	private int idLocalInstalacaoHidrometro;
	private int idProtecaoHidrometro;
	private int indicadorTrocaProtecao;
	private int indicadorTrocaRegistro;
	private int leituraInstalacao;
	private String numeroSelo;
	private String indicadorCavalete;
	private String idFuncionario;
	private int idOrdemServico;
	private int idTipoSubstituicaoHM;
	private String horaInstalacaoHidrometroNovo;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	
	public OrdemServicoHidrometroSubstituicaoVO() {
	}
	
	public OrdemServicoHidrometroSubstituicaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdOrdemServicoSubstituicaoHM() {
		return idOrdemServicoSubstituicaoHM;
	}
	public void setIdOrdemServicoSubstituicaoHM(int idOrdemServicoSubstituicaoHM) {
		this.idOrdemServicoSubstituicaoHM = idOrdemServicoSubstituicaoHM;
	}
	public String getNumerHidrometroAtual() {
		return numerHidrometroAtual;
	}
	public void setNumerHidrometroAtual(String numerHidrometroAtual) {
		this.numerHidrometroAtual = numerHidrometroAtual;
	}
	public String getIndicadorTipoMedicaoAtual() {
		return indicadorTipoMedicaoAtual;
	}
	public void setIndicadorTipoMedicaoAtual(String indicadorTipoMedicaoAtual) {
		this.indicadorTipoMedicaoAtual = indicadorTipoMedicaoAtual;
	}
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public int getLeituraRetirada() {
		return leituraRetirada;
	}
	public void setLeituraRetirada(int leituraRetirada) {
		this.leituraRetirada = leituraRetirada;
	}
	public int getIdSituacaoHidrometro() {
		return idSituacaoHidrometro;
	}
	public void setIdSituacaoHidrometro(int idSituacaoHidrometro) {
		this.idSituacaoHidrometro = idSituacaoHidrometro;
	}
	public int getIdLocalArmazenagemHidrometro() {
		return idLocalArmazenagemHidrometro;
	}
	public void setIdLocalArmazenagemHidrometro(int idLocalArmazenagemHidrometro) {
		this.idLocalArmazenagemHidrometro = idLocalArmazenagemHidrometro;
	}
	public String getNumeroHidrometroNovo() {
		return numeroHidrometroNovo;
	}
	public void setNumeroHidrometroNovo(String numeroHidrometroNovo) {
		this.numeroHidrometroNovo = numeroHidrometroNovo;
	}
	public Date getDataInstalacaoHidrometroNovo() {
		return dataInstalacaoHidrometroNovo;
	}
	public void setDataInstalacaoHidrometroNovo(Date dataInstalacaoHidrometroNovo) {
		this.dataInstalacaoHidrometroNovo = dataInstalacaoHidrometroNovo;
	}
	public String getIndicadorTipoMedicao() {
		return indicadorTipoMedicao;
	}
	public void setIndicadorTipoMedicao(String indicadorTipoMedicao) {
		this.indicadorTipoMedicao = indicadorTipoMedicao;
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
	public String getIndicadorCavalete() {
		return indicadorCavalete;
	}
	public void setIndicadorCavalete(String indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public int getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public int getIdTipoSubstituicaoHM() {
		return idTipoSubstituicaoHM;
	}
	public void setIdTipoSubstituicaoHM(int idTipoSubstituicaoHM) {
		this.idTipoSubstituicaoHM = idTipoSubstituicaoHM;
	}
	public String getHoraInstalacaoHidrometroNovo() {
		return horaInstalacaoHidrometroNovo;
	}
	public void setHoraInstalacaoHidrometroNovo(String horaInstalacaoHidrometroNovo) {
		this.horaInstalacaoHidrometroNovo = horaInstalacaoHidrometroNovo;
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
			this.idOrdemServicoSubstituicaoHM=Integer.parseInt(object.getProperty("idOrdemServicoSubstituicaoHM").toString());
			this.numerHidrometroAtual=object.getProperty("numerHidrometroAtual").toString();
			this.indicadorTipoMedicaoAtual=object.getProperty("indicadorTipoMedicaoAtual").toString();
			//this.dataRetirada=object.getProperty("indicadorTipoMedicaoAtual").toString();
			this.leituraRetirada=Integer.parseInt(object.getProperty("leituraRetirada").toString());
			this.idSituacaoHidrometro=Integer.parseInt(object.getProperty("idSituacaoHidrometro").toString());
			this.idLocalArmazenagemHidrometro=Integer.parseInt(object.getProperty("idLocalArmazenagemHidrometro").toString());
			this.numeroHidrometroNovo=object.getProperty("numeroHidrometroNovo").toString();
			//this.dataInstalacaoHidrometroNovo=object.getProperty("dataInstalacaoHidrometroNovo").toString();
			this.indicadorTipoMedicao=object.getProperty("indicadorTipoMedicao").toString();
			this.idLocalInstalacaoHidrometro=Integer.parseInt(object.getProperty("idLocalInstalacaoHidrometro").toString());
			this.idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			this.indicadorTrocaProtecao=Integer.parseInt(object.getProperty("indicadorTrocaProtecao").toString());
			this.indicadorTrocaRegistro=Integer.parseInt(object.getProperty("indicadorTrocaRegistro").toString());
			this.leituraInstalacao=Integer.parseInt(object.getProperty("leituraInstalacao").toString());
			this.numeroSelo=object.getProperty("numeroSelo").toString();
			this.indicadorCavalete=object.getProperty("indicadorCavalete").toString();
			this.idFuncionario=object.getProperty("idFuncionario").toString();
			this.idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			this.idTipoSubstituicaoHM=Integer.parseInt(object.getProperty("idTipoSubstituicaoHM").toString());
			this.horaInstalacaoHidrometroNovo=object.getProperty("idOrdemServicoSubstituicaoHM").toString();
		}
	}
	
}
