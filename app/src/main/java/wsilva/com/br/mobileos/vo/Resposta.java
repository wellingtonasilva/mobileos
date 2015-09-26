
package wsilva.com.br.mobileos.vo;

/**
 * <p>Classe Java de resposta complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="resposta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idObjetoAvaliado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucesso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class Resposta {

    protected String mensagem;
    protected String idObjetoAvaliado;
    protected boolean sucesso;

    /**
     * Obt�m o valor da propriedade mensagem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Define o valor da propriedade mensagem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensagem(String value) {
        this.mensagem = value;
    }

    /**
     * Obt�m o valor da propriedade idObjetoAvaliado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdObjetoAvaliado() {
        return idObjetoAvaliado;
    }

    /**
     * Define o valor da propriedade idObjetoAvaliado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdObjetoAvaliado(String value) {
        this.idObjetoAvaliado = value;
    }

    /**
     * Obt�m o valor da propriedade sucesso.
     * 
     */
    public boolean isSucesso() {
        return sucesso;
    }

    /**
     * Define o valor da propriedade sucesso.
     * 
     */
    public void setSucesso(boolean value) {
        this.sucesso = value;
    }

}
