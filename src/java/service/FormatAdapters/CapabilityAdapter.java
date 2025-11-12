/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.FormatAdapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import model.entities.model.Capability;

public class CapabilityAdapter extends XmlAdapter<String, Capability> {


    @Override
    public Capability unmarshal(String v) throws Exception {
        //No implementat
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String marshal(Capability v) throws Exception {
        return v.getName();
    }


}