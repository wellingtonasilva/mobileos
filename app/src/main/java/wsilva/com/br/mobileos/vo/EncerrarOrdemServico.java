
package wsilva.com.br.mobileos.vo;

/**
 * <p>Classe Java de encerrarOrdemServico complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="encerrarOrdemServico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idFuncinarioExecutor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coordLatitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordLongitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idOrdemServico" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idMotivo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="parecer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataEncerramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class EncerrarOrdemServico {

    protected String idUsuario;
    protected int idFuncinarioExecutor;
    protected String coordLatitude;
    protected String coordLongitude;
    protected int idOrdemServico;
    protected int idMotivo;
    protected String parecer;
    protected String dataEncerramento;

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
     * Obt�m o valor da propriedade idFuncinarioExecutor.
     * 
     */
    public int getIdFuncinarioExecutor() {
        return idFuncinarioExecutor;
    }

    /**
     * Define o valor da propriedade idFuncinarioExecutor.
     * 
     */
    public void setIdFuncinarioExecutor(int value) {
        this.idFuncinarioExecutor = value;
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

}