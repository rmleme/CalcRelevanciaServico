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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for tActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tActivity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tExtensibleElements">
 *       &lt;sequence>
 *         &lt;element name="target" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tTarget" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="source" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tSource" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="joinCondition" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tBoolean-expr" />
 *       &lt;attribute name="suppressJoinFailure" type="{http://schemas.xmlsoap.org/ws/2003/03/business-process/}tBoolean" default="no" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tActivity", propOrder = {
    "target",
    "source"
})
@XmlSeeAlso({
    TTerminate.class,
    TEmpty.class,
    TInvoke.class,
    TPick.class,
    TWait.class,
    TAssign.class,
    TReply.class,
    TFlow.class,
    TWhile.class,
    TSwitch.class,
    TReceive.class,
    TThrow.class,
    TSequence.class,
    TCompensate.class,
    TScope.class
})
public class TActivity
    extends TExtensibleElements
{

    protected List<TTarget> target;
    protected List<TSource> source;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute
    protected String joinCondition;
    @XmlAttribute
    protected TBoolean suppressJoinFailure;

    /**
     * Gets the value of the target property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the target property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTarget }
     * 
     * 
     */
    public List<TTarget> getTarget() {
        if (target == null) {
            target = new ArrayList<TTarget>();
        }
        return this.target;
    }

    /**
     * Gets the value of the source property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the source property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSource }
     * 
     * 
     */
    public List<TSource> getSource() {
        if (source == null) {
            source = new ArrayList<TSource>();
        }
        return this.source;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the joinCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJoinCondition() {
        return joinCondition;
    }

    /**
     * Sets the value of the joinCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJoinCondition(String value) {
        this.joinCondition = value;
    }

    /**
     * Gets the value of the suppressJoinFailure property.
     * 
     * @return
     *     possible object is
     *     {@link TBoolean }
     *     
     */
    public TBoolean getSuppressJoinFailure() {
        if (suppressJoinFailure == null) {
            return TBoolean.NO;
        } else {
            return suppressJoinFailure;
        }
    }

    /**
     * Sets the value of the suppressJoinFailure property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBoolean }
     *     
     */
    public void setSuppressJoinFailure(TBoolean value) {
        this.suppressJoinFailure = value;
    }

}
