//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 06:57:52 PM BRT 
//


package br.ipt.servico.relevancia.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tWait complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tWait">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tActivity">
 *       &lt;attribute name="for" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tDuration-expr" />
 *       &lt;attribute name="until" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tDeadline-expr" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tWait")
public class TWait
    extends TActivity
{

    @XmlAttribute(name = "for")
    protected String _for;
    @XmlAttribute
    protected String until;

    /**
     * Gets the value of the for property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFor() {
        return _for;
    }

    /**
     * Sets the value of the for property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFor(String value) {
        this._for = value;
    }

    /**
     * Gets the value of the until property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUntil() {
        return until;
    }

    /**
     * Sets the value of the until property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUntil(String value) {
        this.until = value;
    }

}