
package wsilva.com.br.mobileos.vo;

/**
 * <p>Classe Java de informarHoraExecucao complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="informarHoraExecucao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coordLatitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordLongitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idOrdemServico" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dataExecucao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inicioExecucao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fimExecucao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idAtividade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InformarHoraExecucao {

    protected String idUsuario;
    protected String coordLatitude;
    protected String coordLongitude;
    protected int idOrdemServico;
    protected String dataExecucao;
    protected String inicioExecucao;
    protected String fimExecucao;
    protected String idAtividade;

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
     * Obt�m o valor da propriedade dataExecucao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataExecucao() {
        return dataExecucao;
    }

    /**
     * Define o valor da propriedade dataExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataExecucao(String value) {
        this.dataExecucao = value;
    }

    /**
     * Obt�m o valor da propriedade inicioExecucao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInicioExecucao() {
        return inicioExecucao;
    }

    /**
     * Define o valor da propriedade inicioExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInicioExecucao(String value) {
        this.inicioExecucao = value;
    }

    /**
     * Obt�m o valor da propriedade fimExecucao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFimExecucao() {
        return fimExecucao;
    }

    /**
     * Define o valor da propriedade fimExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFimExecucao(String value) {
        this.fimExecucao = value;
    }

    /**
     * Obt�m o valor da propriedade idAtividade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAtividade() {
        return idAtividade;
    }

    /**
     * Define o valor da propriedade idAtividade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAtividade(String value) {
        this.idAtividade = value;
    }

}
