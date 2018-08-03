
package com.telcordia.inpac.ws.npcdatatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NPCMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NPCMessageType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="WordSay" type="{http://ws.inpac.telcordia.com/npcDataTypes}WordSay"/&gt;
 *         &lt;element name="NumberSay" type="{http://ws.inpac.telcordia.com/npcDataTypes}NumberSay"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NPCMessageType", propOrder = {
    "wordSay",
    "numberSay"
})
public class NPCMessageType {

    @XmlElement(name = "WordSay")
    protected String wordSay;
    @XmlElement(name = "NumberSay")
    protected String numberSay;

    /**
     * Gets the value of the wordSay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWordSay() {
        return wordSay;
    }

    /**
     * Sets the value of the wordSay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWordSay(String value) {
        this.wordSay = value;
    }

    /**
     * Gets the value of the numberSay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberSay() {
        return numberSay;
    }

    /**
     * Sets the value of the numberSay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberSay(String value) {
        this.numberSay = value;
    }

}
