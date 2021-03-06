
package wsilva.com.br.mobileos.vo;

import java.math.BigDecimal;


/**
 * <p>Classe Java de encerrarOrdemServicoReligacao complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="encerrarOrdemServicoReligacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idFuncionarioExecutor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coordLatitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordLongitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idOrdemServico" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idMotivo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parecer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataEncerramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idFuncionarioResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataReligacao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMotivoNaoCobranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percentualCobranca" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="quantidadeParcelas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="valorAtual" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class EncerrarOrdemServicoReligacao {

    protected String idUsuario;
    protected int idFuncionarioExecutor;
    protected String coordLatitude;
    protected String coordLongitude;
    protected int idOrdemServico;
    protected int idMotivo;
    protected String parecer;
    protected String dataEncerramento;
    protected String idFuncionarioResp;
    protected String dataReligacao;
    protected String idMotivoNaoCobranca;
    protected BigDecimal percentualCobranca;
    protected Integer quantidadeParcelas;
    protected BigDecimal valorAtual;

    /**
     * Obt�m o valor da propriedade idUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define o valor da propriedade idUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUsuario(String value) {
        this.idUsuario = value;
    }

    /**
     * Obt�m o valor da propriedade idFuncionarioExecutor.
     * 
     */
    public int getIdFuncionarioExecutor() {
        return idFuncionarioExecutor;
    }

    /**
     * Define o valor da propriedade idFuncionarioExecutor.
     * 
     */
    public void setIdFuncionarioExecutor(int value) {
        this.idFuncionarioExecutor = value;
    }

    /**
     * Obt�m o valor da propriedade coordLatitude.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordLatitude() {
        return coordLatitude;
    }

    /**
     * Define o valor da propriedade coordLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordLatitude(String value) {
        this.coordLatitude = value;
    }

    /**
     * Obt�m o valor da propriedade coordLongitude.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordLongitude() {
        return coordLongitude;
    }

    /**
     * Define o valor da propriedade coordLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordLongitude(String value) {
        this.coordLongitude = value;
    }

    /**
     * Obt�m o valor da propriedade idOrdemServico.
     * 
     */
    public int getIdOrdemServico() {
        return idOrdemServico;
    }

    /**
     * Define o valor da propriedade idOrdemServico.
     * 
     */
    public void setIdOrdemServico(int value) {
        this.idOrdemServico = value;
    }

    /**
     * Obt�m o valor da propriedade idMotivo.
     * 
     */
    public int getIdMotivo() {
        return idMotivo;
    }

    /**
     * Define o valor da propriedade idMotivo.
     * 
     */
    public void setIdMotivo(int value) {
        this.idMotivo = value;
    }

    /**
     * Obt�m o valor da propriedade parecer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParecer() {
        return parecer;
    }

    /**
     * Define o valor da propriedade parecer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParecer(String value) {
        this.parecer = value;
    }

    /**
     * Obt�m o valor da propriedade dataEncerramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataEncerramento() {
        return dataEncerramento;
    }

    /**
     * Define o valor da propriedade dataEncerramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEncerramento(String value) {
        this.dataEncerramento = value;
    }

    /**
     * Obt�m o valor da propriedade idFuncionarioResp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFuncionarioResp() {
        return idFuncionarioResp;
    }

    /**
     * Define o valor da propriedade idFuncionarioResp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFuncionarioResp(String value) {
        this.idFuncionarioResp = value;
    }

    /**
     * Obt�m o valor da propriedade dataReligacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataReligacao() {
        return dataReligacao;
    }

    /**
     * Define o valor da propriedade dataReligacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataReligacao(String value) {
        this.dataReligacao = value;
    }

    /**
     * Obt�m o valor da propriedade idMotivoNaoCobranca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMotivoNaoCobranca() {
        return idMotivoNaoCobranca;
    }

    /**
     * Define o valor da propriedade idMotivoNaoCobranca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMotivoNaoCobranca(String value) {
        this.idMotivoNaoCobranca = value;
    }

    /**
     * Obt�m o valor da propriedade percentualCobranca.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentualCobranca() {
        return percentualCobranca;
    }

    /**
     * Define o valor da propriedade percentualCobranca.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentualCobranca(BigDecimal value) {
        this.percentualCobranca = value;
    }

    /**
     * Obt�m o valor da propriedade quantidadeParcelas.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    /**
     * Define o valor da propriedade quantidadeParcelas.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantidadeParcelas(Integer value) {
        this.quantidadeParcelas = value;
    }

    /**
     * Obt�m o valor da propriedade valorAtual.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    /**
     * Define o valor da propriedade valorAtual.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorAtual(BigDecimal value) {
        this.valorAtual = value;
    }

}
