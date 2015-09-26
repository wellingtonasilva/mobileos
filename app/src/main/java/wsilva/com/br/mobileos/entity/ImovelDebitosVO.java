package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

public class ImovelDebitosVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int idImovelDebitos;
	private int idImovel;
	private int referencia;
	private Date dataVencimento;
	private String descricaoTipoDocumento;
	private Double valorDocumento;
	private Double valorAcrescimento;
	private Double valorTotal;
	private int numeroDiasVencido;
	
	public ImovelDebitosVO() {
	}
	
	public ImovelDebitosVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdImovelDebitos() {
		return idImovelDebitos;
	}
	public void setIdImovelDebitos(int idImovelDebitos) {
		this.idImovelDebitos = idImovelDebitos;
	}
	public int getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(int idImovel) {
		this.idImovel = idImovel;
	}
	public int getReferencia() {
		return referencia;
	}
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getDescricaoTipoDocumento() {
		return descricaoTipoDocumento;
	}
	public void setDescricaoTipoDocumento(String descricaoTipoDocumento) {
		this.descricaoTipoDocumento = descricaoTipoDocumento;
	}
	public Double getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(Double valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	public Double getValorAcrescimento() {
		return valorAcrescimento;
	}
	public void setValorAcrescimento(Double valorAcrescimento) {
		this.valorAcrescimento = valorAcrescimento;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getNumeroDiasVencido() {
		return numeroDiasVencido;
	}
	public void setNumeroDiasVencido(int numeroDiasVencido) {
		this.numeroDiasVencido = numeroDiasVencido;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			idImovelDebitos=Integer.parseInt(object.getProperty("idImovelDebitos").toString());
			idImovel=Integer.parseInt(object.getProperty("idImovel").toString());
			referencia=Integer.parseInt(object.getProperty("referencia").toString());
			//dataVencimento=Integer.parseInt(object.getProperty("").toString());
			descricaoTipoDocumento=object.getProperty("descricaoTipoDocumento").toString();
			valorDocumento=Double.parseDouble(object.getProperty("valorDocumento").toString());
			valorAcrescimento=Double.parseDouble(object.getProperty("valorAcrescimento").toString());
			valorTotal=Double.parseDouble(object.getProperty("valorTotal").toString());
			numeroDiasVencido=Integer.parseInt(object.getProperty("numeroDiasVencido").toString());
		}
	}
	

}
