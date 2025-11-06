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
public enum License {
    @XmlEnumValue("Permisive Open Source License")
    POSL,
    @XmlEnumValue("Propietary")
    PROPIETARY,
    @XmlEnumValue("Custom")
    CUSTOM;
}
