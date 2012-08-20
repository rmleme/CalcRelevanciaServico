//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 06:57:52 PM BRT 
//


package br.ipt.servico.relevancia.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tActivityContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tActivityContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tExtensibleElements">
 *       &lt;sequence>
 *         &lt;group ref="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}activity"/>
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
@XmlType(name = "tActivityContainer", propOrder = {
    "empty",
    "invoke",
    "receive",
    "reply",
    "assign",
    "wait",
    "_throw",
    "terminate",
    "flow",
    "_switch",
    "_while",
    "sequence",
    "pick",
    "scope"
})
@XmlSeeAlso({
    TSwitch.Case.class,
    TOnAlarm.class
})
public class TActivityContainer
    extends TExtensibleElements
{

    protected TEmpty empty;
    protected TInvoke invoke;
    protected TReceive receive;
    protected TReply reply;
    protected TAssign assign;
    protected TWait wait;
    @XmlElement(name = "throw")
    protected TThrow _throw;
    protected TTerminate terminate;
    protected TFlow flow;
    @XmlElement(name = "switch")
    protected TSwitch _switch;
    @XmlElement(name = "while")
    protected TWhile _while;
    protected TSequence sequence;
    protected TPick pick;
    protected TScope scope;

    /**
     * Gets the value of the empty property.
     * 
     * @return
     *     possible object is
     *     {@link TEmpty }
     *     
     */
    public TEmpty getEmpty() {
        return empty;
    }

    /**
     * Sets the value of the empty property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEmpty }
     *     
     */
    public void setEmpty(TEmpty value) {
        this.empty = value;
    }

    /**
     * Gets the value of the invoke property.
     * 
     * @return
     *     possible object is
     *     {@link TInvoke }
     *     
     */
    public TInvoke getInvoke() {
        return invoke;
    }

    /**
     * Sets the value of the invoke property.
     * 
     * @param value
     *     allowed object is
     *     {@link TInvoke }
     *     
     */
    public void setInvoke(TInvoke value) {
        this.invoke = value;
    }

    /**
     * Gets the value of the receive property.
     * 
     * @return
     *     possible object is
     *     {@link TReceive }
     *     
     */
    public TReceive getReceive() {
        return receive;
    }

    /**
     * Sets the value of the receive property.
     * 
     * @param value
     *     allowed object is
     *     {@link TReceive }
     *     
     */
    public void setReceive(TReceive value) {
        this.receive = value;
    }

    /**
     * Gets the value of the reply property.
     * 
     * @return
     *     possible object is
     *     {@link TReply }
     *     
     */
    public TReply getReply() {
        return reply;
    }

    /**
     * Sets the value of the reply property.
     * 
     * @param value
     *     allowed object is
     *     {@link TReply }
     *     
     */
    public void setReply(TReply value) {
        this.reply = value;
    }

    /**
     * Gets the value of the assign property.
     * 
     * @return
     *     possible object is
     *     {@link TAssign }
     *     
     */
    public TAssign getAssign() {
        return assign;
    }

    /**
     * Sets the value of the assign property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAssign }
     *     
     */
    public void setAssign(TAssign value) {
        this.assign = value;
    }

    /**
     * Gets the value of the wait property.
     * 
     * @return
     *     possible object is
     *     {@link TWait }
     *     
     */
    public TWait getWait() {
        return wait;
    }

    /**
     * Sets the value of the wait property.
     * 
     * @param value
     *     allowed object is
     *     {@link TWait }
     *     
     */
    public void setWait(TWait value) {
        this.wait = value;
    }

    /**
     * Gets the value of the throw property.
     * 
     * @return
     *     possible object is
     *     {@link TThrow }
     *     
     */
    public TThrow getThrow() {
        return _throw;
    }

    /**
     * Sets the value of the throw property.
     * 
     * @param value
     *     allowed object is
     *     {@link TThrow }
     *     
     */
    public void setThrow(TThrow value) {
        this._throw = value;
    }

    /**
     * Gets the value of the terminate property.
     * 
     * @return
     *     possible object is
     *     {@link TTerminate }
     *     
     */
    public TTerminate getTerminate() {
        return terminate;
    }

    /**
     * Sets the value of the terminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTerminate }
     *     
     */
    public void setTerminate(TTerminate value) {
        this.terminate = value;
    }

    /**
     * Gets the value of the flow property.
     * 
     * @return
     *     possible object is
     *     {@link TFlow }
     *     
     */
    public TFlow getFlow() {
        return flow;
    }

    /**
     * Sets the value of the flow property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFlow }
     *     
     */
    public void setFlow(TFlow value) {
        this.flow = value;
    }

    /**
     * Gets the value of the switch property.
     * 
     * @return
     *     possible object is
     *     {@link TSwitch }
     *     
     */
    public TSwitch getSwitch() {
        return _switch;
    }

    /**
     * Sets the value of the switch property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSwitch }
     *     
     */
    public void setSwitch(TSwitch value) {
        this._switch = value;
    }

    /**
     * Gets the value of the while property.
     * 
     * @return
     *     possible object is
     *     {@link TWhile }
     *     
     */
    public TWhile getWhile() {
        return _while;
    }

    /**
     * Sets the value of the while property.
     * 
     * @param value
     *     allowed object is
     *     {@link TWhile }
     *     
     */
    public void setWhile(TWhile value) {
        this._while = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link TSequence }
     *     
     */
    public TSequence getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSequence }
     *     
     */
    public void setSequence(TSequence value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the pick property.
     * 
     * @return
     *     possible object is
     *     {@link TPick }
     *     
     */
    public TPick getPick() {
        return pick;
    }

    /**
     * Sets the value of the pick property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPick }
     *     
     */
    public void setPick(TPick value) {
        this.pick = value;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link TScope }
     *     
     */
    public TScope getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link TScope }
     *     
     */
    public void setScope(TScope value) {
        this.scope = value;
    }

}
