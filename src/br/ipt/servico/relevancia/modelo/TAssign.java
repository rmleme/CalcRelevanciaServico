//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 06:57:52 PM BRT 
//


package br.ipt.servico.relevancia.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tAssign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tAssign">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tActivity">
 *       &lt;sequence>
 *         &lt;element name="copy" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tCopy" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tAssign", propOrder = {
    "copy"
})
public class TAssign
    extends TActivity
{

    @XmlElement(required = true)
    protected List<TCopy> copy;

    /**
     * Gets the value of the copy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCopy }
     * 
     * 
     */
    public List<TCopy> getCopy() {
        if (copy == null) {
            copy = new ArrayList<TCopy>();
        }
        return this.copy;
    }

}