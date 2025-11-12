/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.FormatAdapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import model.entities.model.Capability;
import model.entities.model.License;

public class LicenseAdapter extends XmlAdapter<String, License> {



    @Override
    public String marshal(License v) throws Exception {
        return v.getName();
    }

    @Override
    public License unmarshal(String v) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
