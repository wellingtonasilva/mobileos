package wsilva.com.br.mobileos.entity;

public class ValaVO extends EntityVO  
{

	private static final long serialVersionUID = 1L;
	private int NumeroOS;
	private int NumeroVala;
	private int IdLocalOcorrencia;
	private String DescricaoLocalOcorrencia;
	private int IdPavimento;
	private String DescricaoPavimento;
	private Double Comprimento;
	private Double Largura;
	private Double Profundidade;
	private int IndicadorAterro;
	private int IndicadorEntulho;
	private int QuantidadeBags;
	private int indicadorAterradoPelaEquipe;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	private int indicadorFotoValaAberta;
	private int indicadorFotoValaFechada;
	
	public int getNumeroOS() {
		return NumeroOS;
	}
	public void setNumeroOS(int numeroOS) {
		NumeroOS = numeroOS;
	}
	public int getNumeroVala() {
		return NumeroVala;
	}
	public void setNumeroVala(int numeroVala) {
		NumeroVala = numeroVala;
	}
	public int getIdLocalOcorrencia() {
		return IdLocalOcorrencia;
	}
	public void setIdLocalOcorrencia(int idLocalOcorrencia) {
		IdLocalOcorrencia = idLocalOcorrencia;
	}
	public String getDescricaoLocalOcorrencia() {
		return DescricaoLocalOcorrencia;
	}
	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		DescricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}
	public int getIdPavimento() {
		return IdPavimento;
	}
	public void setIdPavimento(int idPavimento) {
		IdPavimento = idPavimento;
	}
	public String getDescricaoPavimento() {
		return DescricaoPavimento;
	}
	public void setDescricaoPavimento(String descricaoPavimento) {
		DescricaoPavimento = descricaoPavimento;
	}
	public Double getComprimento() {
		return Comprimento;
	}
	public void setComprimento(Double comprimento) {
		Comprimento = comprimento;
	}
	public Double getLargura() {
		return Largura;
	}
	public void setLargura(Double largura) {
		Largura = largura;
	}
	public Double getProfundidade() {
		return Profundidade;
	}
	public void setProfundidade(Double profundidade) {
		Profundidade = profundidade;
	}
	public int getIndicadorAterro() {
		return IndicadorAterro;
	}
	public void setIndicadorAterro(int indicadorAterro) {
		IndicadorAterro = indicadorAterro;
	}
	public int getIndicadorEntulho() {
		return IndicadorEntulho;
	}
	public void setIndicadorEntulho(int indicadorEntulho) {
		IndicadorEntulho = indicadorEntulho;
	}
	public int getQuantidadeBags() {
		return QuantidadeBags;
	}
	public void setQuantidadeBags(int quantidadeBags) {
		QuantidadeBags = quantidadeBags;
	}
	public int getIndicadorAterradoPelaEquipe() {
		return indicadorAterradoPelaEquipe;
	}
	public void setIndicadorAterradoPelaEquipe(int indicadorAterradoPelaEquipe) {
		this.indicadorAterradoPelaEquipe = indicadorAterradoPelaEquipe;
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
	public int getIndicadorFotoValaAberta() {
		return indicadorFotoValaAberta;
	}
	public void setIndicadorFotoValaAberta(int indicadorFotoValaAberta) {
		this.indicadorFotoValaAberta = indicadorFotoValaAberta;
	}
	public int getIndicadorFotoValaFechada() {
		return indicadorFotoValaFechada;
	}
	public void setIndicadorFotoValaFechada(int indicadorFotoValaFechada) {
		this.indicadorFotoValaFechada = indicadorFotoValaFechada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Comprimento == null) ? 0 : Comprimento.hashCode());
		result = prime
				* result
				+ ((DescricaoLocalOcorrencia == null) ? 0
						: DescricaoLocalOcorrencia.hashCode());
		result = prime
				* result
				+ ((DescricaoPavimento == null) ? 0 : DescricaoPavimento
						.hashCode());
		result = prime * result + IdLocalOcorrencia;
		result = prime * result + IdPavimento;
		result = prime * result + IndicadorAterro;
		result = prime * result + IndicadorEntulho;
		result = prime * result + ((Largura == null) ? 0 : Largura.hashCode());
		result = prime * result + NumeroOS;
		result = prime * result + NumeroVala;
		result = prime * result
				+ ((Profundidade == null) ? 0 : Profundidade.hashCode());
		result = prime * result + QuantidadeBags;
		result = prime
				* result
				+ ((descricaoEquipeExecucao == null) ? 0
						: descricaoEquipeExecucao.hashCode());
		result = prime * result + idEquipeExecucao;
		result = prime * result + indicadorAterradoPelaEquipe;
		result = prime * result + indicadorEnvio;
		result = prime * result + indicadorFotoValaAberta;
		result = prime * result + indicadorFotoValaFechada;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValaVO other = (ValaVO) obj;
		if (Comprimento == null) {
			if (other.Comprimento != null)
				return false;
		} else if (!Comprimento.equals(other.Comprimento))
			return false;
		if (DescricaoLocalOcorrencia == null) {
			if (other.DescricaoLocalOcorrencia != null)
				return false;
		} else if (!DescricaoLocalOcorrencia
				.equals(other.DescricaoLocalOcorrencia))
			return false;
		if (DescricaoPavimento == null) {
			if (other.DescricaoPavimento != null)
				return false;
		} else if (!DescricaoPavimento.equals(other.DescricaoPavimento))
			return false;
		if (IdLocalOcorrencia != other.IdLocalOcorrencia)
			return false;
		if (IdPavimento != other.IdPavimento)
			return false;
		if (IndicadorAterro != other.IndicadorAterro)
			return false;
		if (IndicadorEntulho != other.IndicadorEntulho)
			return false;
		if (Largura == null) {
			if (other.Largura != null)
				return false;
		} else if (!Largura.equals(other.Largura))
			return false;
		if (NumeroOS != other.NumeroOS)
			return false;
		if (NumeroVala != other.NumeroVala)
			return false;
		if (Profundidade == null) {
			if (other.Profundidade != null)
				return false;
		} else if (!Profundidade.equals(other.Profundidade))
			return false;
		if (QuantidadeBags != other.QuantidadeBags)
			return false;
		if (descricaoEquipeExecucao == null) {
			if (other.descricaoEquipeExecucao != null)
				return false;
		} else if (!descricaoEquipeExecucao
				.equals(other.descricaoEquipeExecucao))
			return false;
		if (idEquipeExecucao != other.idEquipeExecucao)
			return false;
		if (indicadorAterradoPelaEquipe != other.indicadorAterradoPelaEquipe)
			return false;
		if (indicadorEnvio != other.indicadorEnvio)
			return false;
		if (indicadorFotoValaAberta != other.indicadorFotoValaAberta)
			return false;
		if (indicadorFotoValaFechada != other.indicadorFotoValaFechada)
			return false;
		return true;
	}
	
}
