package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ColetorWebVO extends EntityVO
{
	private static final long serialVersionUID = 1L;
	public int idColetor;
	public int icAgenteExterno;
	public int icCausaVazamento;
	public int icDiametroRede;
	public int icLocalOcorrencia;
	public int icMotivoEncerramento;
	public int icMaterial;
	public int icMaterialRede;
	public int icServicoTipo;
	public int icTipoPavimento;
	public int icTipoRede;
	public int icUsuarios;
	public int icOrdemServico;
	public int icReserva01;
	public int icReserva02;
	public int icReserva03;
	public int icReserva04;
	public int icReserva05;
	
	public ColetorWebVO() {
	}
	
	public ColetorWebVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idColetor=Integer.parseInt(object.getProperty("idColetor").toString());
			this.icAgenteExterno=Integer.parseInt(object.getProperty("icAgenteExterno").toString());
			this.icCausaVazamento=Integer.parseInt(object.getProperty("icCausaVazamento").toString());
			this.icDiametroRede=Integer.parseInt(object.getProperty("icDiametroRede").toString());
			this.icLocalOcorrencia=Integer.parseInt(object.getProperty("icLocalOcorrencia").toString());
			this.icMotivoEncerramento=Integer.parseInt(object.getProperty("icMotivoEncerramento").toString());
			this.icMaterial=Integer.parseInt(object.getProperty("icMaterial").toString());
			this.icMaterialRede=Integer.parseInt(object.getProperty("icMaterialRede").toString());
			this.icServicoTipo=Integer.parseInt(object.getProperty("icServicoTipo").toString());
			this.icTipoPavimento=Integer.parseInt(object.getProperty("icServicoTipo").toString());
			this.icTipoRede=Integer.parseInt(object.getProperty("icTipoRede").toString());
			this.icUsuarios=Integer.parseInt(object.getProperty("icUsuarios").toString());
			this.icOrdemServico=Integer.parseInt(object.getProperty("icOrdemServico").toString());
			this.icReserva01=Integer.parseInt(object.getProperty("icReserva01").toString());
			this.icReserva02=Integer.parseInt(object.getProperty("icReserva02").toString());
			this.icReserva03=Integer.parseInt(object.getProperty("icReserva03").toString());
			this.icReserva04=Integer.parseInt(object.getProperty("icReserva04").toString());
			this.icReserva05=Integer.parseInt(object.getProperty("icReserva05").toString());
		}
	}
	
}
