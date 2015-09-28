package wsilva.com.br.mobileos.entity.os;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ImovelVO extends EntityVO
{
	private static final long serialVersionUID = 1L;
	public int idImovel;
	public int idSituacaoLigacaoAgua;
	public String descricaoSituacaoLigacaoAgua;
	public int idSituacaoLigacaoEsgoto;
	public String descricaoSituacaoLigacaoEsgoto;
	public String numeroInscricao;
	public String nomeClienteResponsavel;
	public String nomeClienteUsuario;
	public String nomeClienteProprietario;
	public int numeroCortes;
	public int numeroSupressoes;
	public int numeroReparcelamentos;
	public int diaVencimento;
	public int indicadorSituacaoEspecialCobranca;
	public int indicadorSituacaoEspecialFaturamento;
	public int idGrupoFaturamento;
	public int numeroRota;
	public int sequenciaRota;
	public Date dataLigacao;
	public String numeroHidrometro;
	public Date dataInstalacaoHidrometro;
	
	public ImovelVO() {
	}
	
	public ImovelVO(SoapObject object) {
		serialize(object);
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

