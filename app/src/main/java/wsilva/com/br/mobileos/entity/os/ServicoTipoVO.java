package wsilva.com.br.mobileos.entity.os;


import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ServicoTipoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idServicoTipo;
	public String descricaoServicoTipo;
	public int idSubgrupo;
	public int indicadorAtualizacaoComercial;
	public int indicadorPavimento;
	public int indicadorServicoTerceirizado;
	public float valorServico;
	public float tempoMedioExecucao;
	public int idDebitoTipo;
	public int idCreditoTipo;
	public int idPrioridade;
	public int idPerfilTipoServico;
	public int idTipoServicoReferencia;
	public int indicadorVistoria;
	public int indicadorFiscalizacaoInfracao;
	public float valorRemuneracao;
	public float percentualRemuneracao;
	public float prazoexEcucaoServico;
	public int indicadorTipoRemuneracao;
	public int indicadorInformarDeslocamento;
	public int indicadorInformarHorarioExecucao;
	public int indicadorInformarCausaVazamento;
	public int indicadorInformarRedeRamal;
	public int indicadorInformarRedeRamalEsgoto;
	public int indicadorInformarMaterial;
	public int indicadorInfomarVala;
	public int indicadorOrdemSeletiva;
	public int indicadorServicoCritico;
	public int indicadorAtividadeUnica;
	
	public ServicoTipoVO()
	{
	}
	
	public ServicoTipoVO(SoapObject object)
	{
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idCreditoTipo=Integer.parseInt(object.getProperty("idCreditoTipo").toString());
			this.idDebitoTipo=Integer.parseInt(object.getProperty("idDebitoTipo").toString());
			this.idPerfilTipoServico=Integer.parseInt(object.getProperty("idPerfilTipoServico").toString());
			this.idPrioridade=Integer.parseInt(object.getProperty("idPrioridade").toString());
			this.idServicoTipo=Integer.parseInt(object.getProperty("idServicoTipo").toString());
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
			this.descricaoServicoTipo=object.getProperty("servicoTipo").toString();
		}
	}
}
