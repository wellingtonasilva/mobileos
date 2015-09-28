package wsilva.com.br.mobileos.entity.os;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class OrdemServicoReligacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idOrdemServicoReligacao;
	public Date dataReligacao;
	public String horaReligacao;
	public String idFuncionario;
	public String numeroHidrometro;
	public Date dataInstalacaoHidrometro;
	public int idLocalInstalacaoHidrometro;
	public int idProtecaoHidrometro;
	public int leituraInstalacao;
	public String numeroSelo;
	public String indicadorCavalete;
	public int idOrdemServico;
	public int indicadorTrocaRegistro;
	public int indicadorTrocaProtecao;
	public int idTipoReligacao;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	
	public OrdemServicoReligacaoVO() {
	}
	
	public OrdemServicoReligacaoVO(SoapObject object) {
		serialize(object);
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
