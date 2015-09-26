package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class ColetorWebVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;

	private int idColetor;
	private int icAgenteExterno;
	private int icCausaVazamento;
	private int icDiametroRede;
	private int icLocalOcorrencia;
	private int icMotivoEncerramento;
	private int icMaterial;
	private int icMaterialRede;
	private int icServicoTipo;
	private int icTipoPavimento;
	private int icTipoRede;
	private int icUsuarios;
	private int icOrdemServico;
	private int icReserva01;
	private int icReserva02;
	private int icReserva03;
	private int icReserva04;
	private int icReserva05;
	
	public ColetorWebVO() {
	}
	
	public ColetorWebVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdColetor() {
		return idColetor;
	}
	public void setIdColetor(int idColetor) {
		this.idColetor = idColetor;
	}
	public int getIcAgenteExterno() {
		return icAgenteExterno;
	}
	public void setIcAgenteExterno(int icAgenteExterno) {
		this.icAgenteExterno = icAgenteExterno;
	}
	public int getIcCausaVazamento() {
		return icCausaVazamento;
	}
	public void setIcCausaVazamento(int icCausaVazamento) {
		this.icCausaVazamento = icCausaVazamento;
	}
	public int getIcDiametroRede() {
		return icDiametroRede;
	}
	public void setIcDiametroRede(int icDiametroRede) {
		this.icDiametroRede = icDiametroRede;
	}
	public int getIcLocalOcorrencia() {
		return icLocalOcorrencia;
	}
	public void setIcLocalOcorrencia(int icLocalOcorrencia) {
		this.icLocalOcorrencia = icLocalOcorrencia;
	}
	public int getIcMotivoEncerramento() {
		return icMotivoEncerramento;
	}
	public void setIcMotivoEncerramento(int icMotivoEncerramento) {
		this.icMotivoEncerramento = icMotivoEncerramento;
	}
	public int getIcMaterial() {
		return icMaterial;
	}
	public void setIcMaterial(int icMaterial) {
		this.icMaterial = icMaterial;
	}
	public int getIcMaterialRede() {
		return icMaterialRede;
	}
	public void setIcMaterialRede(int icMaterialRede) {
		this.icMaterialRede = icMaterialRede;
	}
	public int getIcServicoTipo() {
		return icServicoTipo;
	}
	public void setIcServicoTipo(int icServicoTipo) {
		this.icServicoTipo = icServicoTipo;
	}
	public int getIcTipoPavimento() {
		return icTipoPavimento;
	}
	public void setIcTipoPavimento(int icTipoPavimento) {
		this.icTipoPavimento = icTipoPavimento;
	}
	public int getIcTipoRede() {
		return icTipoRede;
	}
	public void setIcTipoRede(int icTipoRede) {
		this.icTipoRede = icTipoRede;
	}
	public int getIcUsuarios() {
		return icUsuarios;
	}
	public void setIcUsuarios(int icUsuarios) {
		this.icUsuarios = icUsuarios;
	}
	public int getIcOrdemServico() {
		return icOrdemServico;
	}
	public void setIcOrdemServico(int icOrdemServico) {
		this.icOrdemServico = icOrdemServico;
	}
	public int getIcReserva01() {
		return icReserva01;
	}
	public void setIcReserva01(int icReserva01) {
		this.icReserva01 = icReserva01;
	}
	public int getIcReserva02() {
		return icReserva02;
	}
	public void setIcReserva02(int icReserva02) {
		this.icReserva02 = icReserva02;
	}
	public int getIcReserva03() {
		return icReserva03;
	}
	public void setIcReserva03(int icReserva03) {
		this.icReserva03 = icReserva03;
	}
	public int getIcReserva04() {
		return icReserva04;
	}
	public void setIcReserva04(int icReserva04) {
		this.icReserva04 = icReserva04;
	}
	public int getIcReserva05() {
		return icReserva05;
	}
	public void setIcReserva05(int icReserva05) {
		this.icReserva05 = icReserva05;
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
