/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.contestants;



/**
 * Αναπαριστά τραγουδιστές
 * @author nicolas
 */
public class Singer extends Artist{

    public Singer(String name) {
        super(name);
    }
    
   
    
    @Override
    public void perform(){
        System.out.println("* I am " + name + " and I sing.");
    }
}
