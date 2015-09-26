
package wsilva.com.br.mobileos.vo;

/**
 * <p>Classe Java de informarPavimento complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="informarPavimento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="coordLatitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coordLongitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idOrdemServico" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPavimentoRua" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idPavimentoCalcada" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="areaPavimentoRua" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaPavimentoCalcada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idUnidadeRepavimentadora" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class InformarPavimento {

    protected String idUsuario;
    protected String coordLatitude;
    protected String coordLongitude;
    protected int idOrdemServico;
    protected Integer idPavimentoRua;
    protected Integer idPavimentoCalcada;
    protected String areaPavimentoRua;
    protected String areaPavimentoCalcada;
    protected Integer idUnidadeRepavimentadora;

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
     * Obt�m o valor da propriedade idPavimentoRua.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdPavimentoRua() {
        return idPavimentoRua;
    }

    /**
     * Define o valor da propriedade idPavimentoRua.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdPavimentoRua(Integer value) {
        this.idPavimentoRua = value;
    }

    /**
     * Obt�m o valor da propriedade idPavimentoCalcada.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdPavimentoCalcada() {
        return idPavimentoCalcada;
    }

    /**
     * Define o valor da propriedade idPavimentoCalcada.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdPavimentoCalcada(Integer value) {
        this.idPavimentoCalcada = value;
    }

    /**
     * Obt�m o valor da propriedade areaPavimentoRua.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaPavimentoRua() {
        return areaPavimentoRua;
    }

    /**
     * Define o valor da propriedade areaPavimentoRua.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaPavimentoRua(String value) {
        this.areaPavimentoRua = value;
    }

    /**
     * Obt�m o valor da propriedade areaPavimentoCalcada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaPavimentoCalcada() {
        return areaPavimentoCalcada;
    }

    /**
     * Define o valor da propriedade areaPavimentoCalcada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaPavimentoCalcada(String value) {
        this.areaPavimentoCalcada = value;
    }

    /**
     * Obt�m o valor da propriedade idUnidadeRepavimentadora.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdUnidadeRepavimentadora() {
        return idUnidadeRepavimentadora;
    }

    /**
     * Define o valor da propriedade idUnidadeRepavimentadora.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdUnidadeRepavimentadora(Integer value) {
        this.idUnidadeRepavimentadora = value;
    }

}
