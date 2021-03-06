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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tEventHandlers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tEventHandlers">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tExtensibleElements">
 *       &lt;sequence>
 *         &lt;element name="onMessage" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tOnMessage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="onAlarm" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tOnAlarm" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "tEventHandlers", propOrder = {
    "onMessage",
    "onAlarm"
})
public class TEventHandlers
    extends TExtensibleElements
{

    protected List<TOnMessage> onMessage;
    protected List<TOnAlarm> onAlarm;

    /**
     * Gets the value of the onMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TOnMessage }
     * 
     * 
     */
    public List<TOnMessage> getOnMessage() {
        if (onMessage == null) {
            onMessage = new ArrayList<TOnMessage>();
        }
        return this.onMessage;
    }

    /**
     * Gets the value of the onAlarm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onAlarm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnAlarm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TOnAlarm }
     * 
     * 
     */
    public List<TOnAlarm> getOnAlarm() {
        if (onAlarm == null) {
            onAlarm = new ArrayList<TOnAlarm>();
        }
        return this.onAlarm;
    }

}
