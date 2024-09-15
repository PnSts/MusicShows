/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.contestants;

import java.util.Date;


/**
 *
 * @author nicolas
 */
public class Band extends Contestant{    
    private Date formationDate;
    

     public Band(String name) {
        super(name);
    }

    public Band(String name, Date formationDate) {
        super(name);
        this.formationDate = formationDate;
    }
     
     

    public Date getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(Date formationDate) {
        this.formationDate = formationDate;
    }
    
    
    
    @Override
    public void perform(){
        System.out.println("* We are the \"" + name + "\" band.");
    }
     
    

}
