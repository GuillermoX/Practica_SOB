/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 *
 * @author guillermo
 */
@XmlType(name = "ColorType")
@XmlEnum
public enum Capacity {
    @XmlEnumValue("chat-completion")
    CHAT_COMPLETION,
    
    @XmlEnumValue("code-generation")
    CODE_GENERATION,
    
    @XmlEnumValue("audio-generation")
    AUDIO_GENERATION,
    
    @XmlEnumValue("text-to-image")
    TEXT_TO_IMAGE,
    
    @XmlEnumValue("text-to-speech")
    TEXT_TO_SPEECH;
}
