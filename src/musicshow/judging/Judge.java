/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.judging;

/**
 *
 * @author nicolas
 */
public class Judge {    
    private String name;
    private String specialty;

    public Judge(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Judge(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
