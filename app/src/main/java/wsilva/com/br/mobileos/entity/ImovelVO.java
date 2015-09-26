package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class ImovelVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	
	private int idImovel;
	private int idSituacaoLigacaoAgua;
	private String descricaoSituacaoLigacaoAgua;
	private int idSituacaoLigacaoEsgoto;
	private String descricaoSituacaoLigacaoEsgoto;
	private String numeroInscricao;
	private String nomeClienteResponsavel;
	private String nomeClienteUsuario;
	private String nomeClienteProprietario;
	private int numeroCortes;
	private int numeroSupressoes;
	private int numeroReparcelamentos;
	private int diaVencimento;
	private int indicadorSituacaoEspecialCobranca;
	private int indicadorSituacaoEspecialFaturamento;
	private int idGrupoFaturamento;
	private int numeroRota;
	private int sequenciaRota;
	private Date dataLigacao;
	private String numeroHidrometro;
	private Date dataInstalacaoHidrometro;
	
	public ImovelVO() {
	}
	
	public ImovelVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(int idImovel) {
		this.idImovel = idImovel;
	}
	public int getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	public void setIdSituacaoLigacaoAgua(int idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}
	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}
	public int getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}
	public void setIdSituacaoLigacaoEsgoto(int idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}
	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}
	public void setDescricaoSituacaoLigacaoEsgoto(
			String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
	}
	public String getNumeroInscricao() {
		return numeroInscricao;
	}
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getNomeClienteProprietario() {
		return nomeClienteProprietario;
	}
	public void setNomeClienteProprietario(String nomeClienteProprietario) {
		this.nomeClienteProprietario = nomeClienteProprietario;
	}
	public int getNumeroCortes() {
		return numeroCortes;
	}
	public void setNumeroCortes(int numeroCortes) {
		this.numeroCortes = numeroCortes;
	}
	public int getNumeroSupressoes() {
		return numeroSupressoes;
	}
	public void setNumeroSupressoes(int numeroSupressoes) {
		this.numeroSupressoes = numeroSupressoes;
	}
	public int getNumeroReparcelamentos() {
		return numeroReparcelamentos;
	}
	public void setNumeroReparcelamentos(int numeroReparcelamentos) {
		this.numeroReparcelamentos = numeroReparcelamentos;
	}
	public int getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public int getIndicadorSituacaoEspecialCobranca() {
		return indicadorSituacaoEspecialCobranca;
	}
	public void setIndicadorSituacaoEspecialCobranca(
			int indicadorSituacaoEspecialCobranca) {
		this.indicadorSituacaoEspecialCobranca = indicadorSituacaoEspecialCobranca;
	}
	public int getIndicadorSituacaoEspecialFaturamento() {
		return indicadorSituacaoEspecialFaturamento;
	}
	public void setIndicadorSituacaoEspecialFaturamento(
			int indicadorSituacaoEspecialFaturamento) {
		this.indicadorSituacaoEspecialFaturamento = indicadorSituacaoEspecialFaturamento;
	}
	public int getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(int idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	public int getNumeroRota() {
		return numeroRota;
	}
	public void setNumeroRota(int numeroRota) {
		this.numeroRota = numeroRota;
	}
	public int getSequenciaRota() {
		return sequenciaRota;
	}
	public void setSequenciaRota(int sequenciaRota) {
		this.sequenciaRota = sequenciaRota;
	}
	public Date getDataLigacao() {
		return dataLigacao;
	}
	public void setDataLigacao(Date dataLigacao) {
		this.dataLigacao = dataLigacao;
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
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idImovel=Integer.parseInt(object.getProperty("idImovel").toString());
			this.idSituacaoLigacaoAgua=Integer.parseInt(object.getProperty("idSituacaoLigacaoAgua").toString());
			this.descricaoSituacaoLigacaoAgua=object.getProperty("descricaoSituacaoLigacaoAgua").toString();
			this.idSituacaoLigacaoEsgoto=Integer.parseInt(object.getProperty("idSituacaoLigacaoEsgoto").toString());
			this.descricaoSituacaoLigacaoEsgoto=object.getProperty("descricaoSituacaoLigacaoEsgoto").toString();
			this.numeroInscricao=object.getProperty("numeroInscricao").toString();
			this.nomeClienteResponsavel=object.getProperty("nomeClienteResponsavel").toString();
			this.nomeClienteUsuario=object.getProperty("nomeClienteUsuario").toString();
			this.nomeClienteProprietario=object.getProperty("nomeClienteProprietario").toString();
			this.numeroCortes=Integer.parseInt(object.getProperty("numeroCortes").toString());
			this.numeroSupressoes=Integer.parseInt(object.getProperty("numeroSupressoes").toString());
			this.numeroReparcelamentos=Integer.parseInt(object.getProperty("numeroReparcelamentos").toString());
			this.diaVencimento=Integer.parseInt(object.getProperty("diaVencimento").toString());
			this.indicadorSituacaoEspecialCobranca=Integer.parseInt(object.getProperty("indicadorSituacaoEspecialCobranca").toString());
			this.indicadorSituacaoEspecialFaturamento=Integer.parseInt(object.getProperty("indicadorSituacaoEspecialFaturamento").toString());
			this.idGrupoFaturamento=Integer.parseInt(object.getProperty("idGrupoFaturamento").toString());
			this.numeroRota=Integer.parseInt(object.getProperty("numeroRota").toString());
			this.sequenciaRota=Integer.parseInt(object.getProperty("sequenciaRota").toString());
			//this.dataLigacao=Integer.parseInt(object.getProperty("dataLigacao").toString());
			this.numeroHidrometro=object.getProperty("numeroHidrometro").toString();
			//this.dataInstalacaoHidrometro=Integer.parseInt(object.getProperty("dataInstalacaoHidrometro").toString());
		}
	}
}

