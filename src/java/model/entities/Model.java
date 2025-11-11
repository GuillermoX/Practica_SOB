/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import model.entities.modelCharacteristics.Provider;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
//import java.util.Date;
import java.util.LinkedList;
import model.entities.modelCharacteristics.Capacity;
import model.entities.modelCharacteristics.License;
import service.DateAdapter;
/**
 *
 * @author guillermo
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Model.findByProvider",
                query = "SELECT e FROM Model e " +
                        "WHERE e.provider.name = :prov_name")
})
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
    
    @Temporal(TemporalType.DATE)
    @Column(name="TRAIN_D")
    @XmlElement
    @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
    private Date trainingDate;  
    
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_D")
    @XmlElement
    @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
    private Date lastUpdateDate ;  
    
    private String version;
    @Column(name="N_LANG")
    private int numLanguages;
    
    @ManyToOne
    @JoinColumn(name="PROVIDER_ID")
    private Provider provider;    
    
    @ManyToOne
     @JoinColumn(name="LICENSE_ID")
    private License license;  
    
    @ManyToMany
    @JoinTable(
        name = "MODEL_CAPACITY",
        joinColumns = @JoinColumn(name = "CAP_ID"),
        inverseJoinColumns = @JoinColumn(name = "MODEL_ID"))
    private Collection<Capacity> capacities;
    
    public Model(){
        capacities = new LinkedList<Capacity>();
    }
    

    //Setters and getters
    
    public int getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public Collection<Capacity> getCapacities() {
        return capacities;
    }

    public License getLicense() {
        return license;
    }

    public Date getTrainingDate() {
        return trainingDate;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getVersion() {
        return version;
    }

    public int getNumLanguages() {
        return numLanguages;
    }

    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setCapacities(Collection<Capacity> capacities) {
        this.capacities = capacities;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
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

    public void setLastUpdateDate(Date lastUpdate) {
        this.lastUpdateDate = lastUpdate;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setNumLanguages(int numLanguages) {
        this.numLanguages = numLanguages;
    }
    
    

    
}
