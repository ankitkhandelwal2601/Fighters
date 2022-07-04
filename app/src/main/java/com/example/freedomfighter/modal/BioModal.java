package com.example.freedomfighter.modal;

public class BioModal {

    private String bioId;
    private String bioName;
    private String description;
    private String bioBirth;
    private String bioImage;

    public BioModal(){}


    public String getBioId() {
        return bioId;
    }

    public void setBioId(String bioId) {
        this.bioId = bioId;
    }

    public BioModal(String bioName,String description,String bioBirth,String bioImage) {
        this.bioName = bioName;
        this.description = description;
        this.bioBirth = bioBirth;
        this.bioImage = bioImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBioName() {
        return bioName;
    }

    public String getBioBirth() {
        return bioBirth;
    }

    public void setBioBirth(String bioBirth) {
        this.bioBirth = bioBirth;
    }

    public String getBioImage() {
        return bioImage;
    }

    public void setBioImage(String bioImage) {
        this.bioImage = bioImage;
    }
}
