/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
//import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author guillermo
 */

@Entity
@XmlRootElement
public class Model implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id 
    @SequenceGenerator(name="Model_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Model_Gen")
    private int id;
   
    private String name;
    @Column(name="DESCR")
    private String description;
    @Column(name="CL")
    private int contextLenght;
    @Column(name="QI")
    private float qualityIndex;
    @Column(name="PRIV")
    private boolean priv;
    
    @Temporal(TemporalType.DATE)
    @Column(name="TRAIN_D")
    private Date trainingDate;  
    
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_D")
    private Date lastUpdateDate ;  
    
    private String version;
    @Column(name="N_LANG")
    private int numLanguages;
    
    @ManyToOne
    @JoinColumn(name="PROVIDER_ID")
    private Provider provider;    
    
    @ManyToOne
    private License license;  
    
    @ManyToMany
    @JoinTable(
        name = "MODEL_CAPACITY",
        joinColumns = @JoinColumn(name = "MODEL_ID"),
        inverseJoinColumns = @JoinColumn(name = "CAP_ID"))
    private Collection<Capability> capabilities;
    
   
    public Model(){
        capabilities = new LinkedList<Capability>();
    }
    
    public static boolean checkValues(Model m){
        float qi = m.getQualityIndex();
        int nLang = m.getNumLanguages();
        
        return((m.getName().isBlank() && m.getDescription().isBlank() &&
                m.getDescription().isBlank() && m.getVersion().isBlank() &&
                m.getVersion().isBlank() && (qi>=0) && (qi<=1) && (nLang>0) && 
               (nLang<300) && (m.getContextLenght()>0)));
        
    }
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getContextLenght() {
        return contextLenght;
    }

    public float getQualityIndex() {
        return qualityIndex;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getVersion() {
        return version;
    }

    public int getNumLanguages() {
        return numLanguages;
    }

    public Provider getProvider() {
        return provider;
    }

    public License getLicense() {
        return license;
    }

    public Collection<Capability> getCapabilities() {
        return capabilities;
    }

    public boolean isPriv() {
        return priv;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContextLenght(int contextLenght) {
        this.contextLenght = contextLenght;
    }

    public void setQualityIndex(float qualityIndex) {
        this.qualityIndex = qualityIndex;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setNumLanguages(int numLanguages) {
        this.numLanguages = numLanguages;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public void setCapabilities(Collection<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public void setPriv(boolean priv) {
        this.priv = priv;
    }
    
    
 
    

    
}
