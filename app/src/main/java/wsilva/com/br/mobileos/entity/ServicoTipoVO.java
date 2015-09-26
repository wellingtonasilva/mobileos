package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class ServicoTipoVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdServicoTipo;
	private String DescricaoServicoTipo;
	private int idSubgrupo;
	private int indicadorAtualizacaoComercial;
	private int indicadorPavimento;
	private int indicadorServicoTerceirizado;
	private float valorServico; 
	private float tempoMedioExecucao;
	private int idDebitoTipo;
	private int idCreditoTipo;
	private int idPrioridade;
	private int idPerfilTipoServico;
	private int idTipoServicoReferencia;
	private int indicadorVistoria;
	private int indicadorFiscalizacaoInfracao;
	private float valorRemuneracao;
	private float percentualRemuneracao;
	private float prazoexEcucaoServico;
	private int indicadorTipoRemuneracao;
	private int indicadorInformarDeslocamento;
	private int indicadorInformarHorarioExecucao;
	private int indicadorInformarCausaVazamento;
	private int indicadorInformarRedeRamal;
	private int indicadorInformarRedeRamalEsgoto;
	private int indicadorInformarMaterial;
	private int indicadorInfomarVala;
	private int indicadorOrdemSeletiva;
	private int indicadorServicoCritico;
	private int indicadorAtividadeUnica;
	
	public ServicoTipoVO()
	{
	}
	
	public ServicoTipoVO(SoapObject object)
	{
		serialize(object);
	}
	
	
	public int getIdServicoTipo() {
		return IdServicoTipo;
	}
	public void setIdServicoTipo(int idServicoTipo) {
		IdServicoTipo = idServicoTipo;
	}
	public String getDescricaoServicoTipo() {
		return DescricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		DescricaoServicoTipo = descricaoServicoTipo;
	}
	public int getIdSubgrupo() {
		return idSubgrupo;
	}
	public void setIdSubgrupo(int idSubgrupo) {
		this.idSubgrupo = idSubgrupo;
	}
	public int getIndicadorAtualizacaoComercial() {
		return indicadorAtualizacaoComercial;
	}
	public void setIndicadorAtualizacaoComercial(int indicadorAtualizacaoComercial) {
		this.indicadorAtualizacaoComercial = indicadorAtualizacaoComercial;
	}
	public int getIndicadorPavimento() {
		return indicadorPavimento;
	}
	public void setIndicadorPavimento(int indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}
	public int getIndicadorServicoTerceirizado() {
		return indicadorServicoTerceirizado;
	}
	public void setIndicadorServicoTerceirizado(int indicadorServicoTerceirizado) {
		this.indicadorServicoTerceirizado = indicadorServicoTerceirizado;
	}
	public float getValorServico() {
		return valorServico;
	}
	public void setValorServico(float valorServico) {
		this.valorServico = valorServico;
	}
	public float getTempoMedioExecucao() {
		return tempoMedioExecucao;
	}
	public void setTempoMedioExecucao(float tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}
	public int getIdDebitoTipo() {
		return idDebitoTipo;
	}
	public void setIdDebitoTipo(int idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}
	public int getIdCreditoTipo() {
		return idCreditoTipo;
	}
	public void setIdCreditoTipo(int idCreditoTipo) {
		this.idCreditoTipo = idCreditoTipo;
	}
	public int getIdPrioridade() {
		return idPrioridade;
	}
	public void setIdPrioridade(int idPrioridade) {
		this.idPrioridade = idPrioridade;
	}
	public int getIdPerfilTipoServico() {
		return idPerfilTipoServico;
	}
	public void setIdPerfilTipoServico(int idPerfilTipoServico) {
		this.idPerfilTipoServico = idPerfilTipoServico;
	}
	public int getIdTipoServicoReferencia() {
		return idTipoServicoReferencia;
	}
	public void setIdTipoServicoReferencia(int idTipoServicoReferencia) {
		this.idTipoServicoReferencia = idTipoServicoReferencia;
	}
	public int getIndicadorVistoria() {
		return indicadorVistoria;
	}
	public void setIndicadorVistoria(int indicadorVistoria) {
		this.indicadorVistoria = indicadorVistoria;
	}
	public int getIndicadorFiscalizacaoInfracao() {
		return indicadorFiscalizacaoInfracao;
	}
	public void setIndicadorFiscalizacaoInfracao(int indicadorFiscalizacaoInfracao) {
		this.indicadorFiscalizacaoInfracao = indicadorFiscalizacaoInfracao;
	}
	public float getValorRemuneracao() {
		return valorRemuneracao;
	}
	public void setValorRemuneracao(float valorRemuneracao) {
		this.valorRemuneracao = valorRemuneracao;
	}
	public float getPercentualRemuneracao() {
		return percentualRemuneracao;
	}
	public void setPercentualRemuneracao(float percentualRemuneracao) {
		this.percentualRemuneracao = percentualRemuneracao;
	}
	public float getPrazoexEcucaoServico() {
		return prazoexEcucaoServico;
	}
	public void setPrazoexEcucaoServico(float prazoexEcucaoServico) {
		this.prazoexEcucaoServico = prazoexEcucaoServico;
	}
	public int getIndicadorTipoRemuneracao() {
		return indicadorTipoRemuneracao;
	}
	public void setIndicadorTipoRemuneracao(int indicadorTipoRemuneracao) {
		this.indicadorTipoRemuneracao = indicadorTipoRemuneracao;
	}
	public int getIndicadorInformarDeslocamento() {
		return indicadorInformarDeslocamento;
	}
	public void setIndicadorInformarDeslocamento(int indicadorInformarDeslocamento) {
		this.indicadorInformarDeslocamento = indicadorInformarDeslocamento;
	}
	public int getIndicadorInformarHorarioExecucao() {
		return indicadorInformarHorarioExecucao;
	}
	public void setIndicadorInformarHorarioExecucao(
			int indicadorInformarHorarioExecucao) {
		this.indicadorInformarHorarioExecucao = indicadorInformarHorarioExecucao;
	}
	public int getIndicadorInformarCausaVazamento() {
		return indicadorInformarCausaVazamento;
	}
	public void setIndicadorInformarCausaVazamento(
			int indicadorInformarCausaVazamento) {
		this.indicadorInformarCausaVazamento = indicadorInformarCausaVazamento;
	}
	public int getIndicadorInformarRedeRamal() {
		return indicadorInformarRedeRamal;
	}
	public void setIndicadorInformarRedeRamal(int indicadorInformarRedeRamal) {
		this.indicadorInformarRedeRamal = indicadorInformarRedeRamal;
	}
	public int getIndicadorInformarRedeRamalEsgoto() {
		return indicadorInformarRedeRamalEsgoto;
	}
	public void setIndicadorInformarRedeRamalEsgoto(
			int indicadorInformarRedeRamalEsgoto) {
		this.indicadorInformarRedeRamalEsgoto = indicadorInformarRedeRamalEsgoto;
	}
	public int getIndicadorInformarMaterial() {
		return indicadorInformarMaterial;
	}
	public void setIndicadorInformarMaterial(int indicadorInformarMaterial) {
		this.indicadorInformarMaterial = indicadorInformarMaterial;
	}
	public int getIndicadorInfomarVala() {
		return indicadorInfomarVala;
	}
	public void setIndicadorInfomarVala(int indicadorInfomarVala) {
		this.indicadorInfomarVala = indicadorInfomarVala;
	}
	public int getIndicadorOrdemSeletiva() {
		return indicadorOrdemSeletiva;
	}
	public void setIndicadorOrdemSeletiva(int indicadorOrdemSeletiva) {
		this.indicadorOrdemSeletiva = indicadorOrdemSeletiva;
	}
	public int getIndicadorServicoCritico() {
		return indicadorServicoCritico;
	}
	public void setIndicadorServicoCritico(int indicadorServicoCritico) {
		this.indicadorServicoCritico = indicadorServicoCritico;
	}
	public int getIndicadorAtividadeUnica() {
		return indicadorAtividadeUnica;
	}
	public void setIndicadorAtividadeUnica(int indicadorAtividadeUnica) {
		this.indicadorAtividadeUnica = indicadorAtividadeUnica;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idCreditoTipo=Integer.parseInt(object.getProperty("idCreditoTipo").toString());
			this.idDebitoTipo=Integer.parseInt(object.getProperty("idDebitoTipo").toString());
			this.idPerfilTipoServico=Integer.parseInt(object.getProperty("idPerfilTipoServico").toString());
			this.idPrioridade=Integer.parseInt(object.getProperty("idPrioridade").toString());
			this.IdServicoTipo=Integer.parseInt(object.getProperty("idServicoTipo").toString());
			this.idSubgrupo=Integer.parseInt(object.getProperty("idSubgrupo").toString());
			this.idTipoServicoReferencia=Integer.parseInt(object.getProperty("idTipoServicoReferencia").toString());
			this.indicadorAtividadeUnica=Integer.parseInt(object.getProperty("indicadorAtividadeUnica").toString());
			this.indicadorAtualizacaoComercial=Integer.parseInt(object.getProperty("indicadorAtualizacaoComercial").toString());
			this.indicadorFiscalizacaoInfracao=Integer.parseInt(object.getProperty("indicadorFiscalizacaoInfracao").toString());
			this.indicadorInfomarVala=Integer.parseInt(object.getProperty("indicadorInfomarVala").toString());
			this.indicadorInformarCausaVazamento=Integer.parseInt(object.getProperty("indicadorInformarCausaVazamento").toString());
			this.indicadorInformarDeslocamento=Integer.parseInt(object.getProperty("indicadorInformarDeslocamento").toString());
			this.indicadorInformarHorarioExecucao=Integer.parseInt(object.getProperty("indicadorInformarHorarioExecucao").toString());
			this.indicadorInformarMaterial=Integer.parseInt(object.getProperty("indicadorInformarMaterial").toString());
			this.indicadorInformarRedeRamal=Integer.parseInt(object.getProperty("indicadorInformarRedeRamal").toString());
			this.indicadorInformarRedeRamalEsgoto=Integer.parseInt(object.getProperty("indicadorInformarRedeRamalEsgoto").toString());
			this.indicadorOrdemSeletiva=Integer.parseInt(object.getProperty("indicadorOrdemSeletiva").toString());
			this.indicadorPavimento=Integer.parseInt(object.getProperty("indicadorPavimento").toString());
			this.indicadorServicoCritico=Integer.parseInt(object.getProperty("indicadorServicoCritico").toString());
			this.indicadorServicoTerceirizado=Integer.parseInt(object.getProperty("indicadorServicoTerceirizado").toString());
			this.indicadorTipoRemuneracao=Integer.parseInt(object.getProperty("indicadorTipoRemuneracao").toString());
			this.indicadorVistoria=Integer.parseInt(object.getProperty("indicadorVistoria").toString());
			this.percentualRemuneracao=Float.parseFloat(object.getProperty("percentualRemuneracao").toString());
			this.prazoexEcucaoServico=Float.parseFloat(object.getProperty("prazoexEcucaoServico").toString());
			this.tempoMedioExecucao=Float.parseFloat(object.getProperty("tempoMedioExecucao").toString());
			this.valorRemuneracao=Float.parseFloat(object.getProperty("valorRemuneracao").toString());
			this.valorServico=Float.parseFloat(object.getProperty("valorServico").toString());
			this.DescricaoServicoTipo=object.getProperty("servicoTipo").toString();
		}
	}
}
