package wsilva.com.br.mobileos.entity.os;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ValaVO extends EntityVO
{
	private static final long serialVersionUID = 1L;
	public int numeroOS;
	public int numeroVala;
	public int idLocalOcorrencia;
	public String descricaoLocalOcorrencia;
	public int idPavimento;
	public String descricaoPavimento;
	public Double comprimento;
	public Double largura;
	public Double profundidade;
	public int indicadorAterro;
	public int indicadorEntulho;
	public int quantidadeBags;
	public int indicadorAterradoPelaEquipe;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	public int indicadorFotoValaAberta;
	public int indicadorFotoValaFechada;
}
