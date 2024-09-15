/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.contestants;


/**
 *
 * @author nicolas
 */
public class InstrumentPlayer extends Artist{

    public InstrumentPlayer(String name) {
        super(name);
    }
    
    
    
    @Override
    public void perform(){
        System.out.println("* I am " + name + " and I play an instrument.");
    }
    
}
