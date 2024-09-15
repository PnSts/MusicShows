/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.util.ArrayList;
import java.util.Collections;
import musicshow.contestants.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import musicshow.judging.Judge;

/**
 * Αναπαριστά ένα Επεισόδιο Εκπομπής
 * @author nicolas
 */
public class Episode {
    // Αριθμός επεισοδίου
    private int num;
    // Ημερομηνία προβολής
    private Date date;
    // Διάρκεια
    private int durationMin;
    // Λίστα διαγωνιζομένων. Υλοποιεί τη συσχέτιση "διαγωνίζονται" με την κλάση Διαγωνιζόμενος
    private List<Contestant> contestants;
    // Λίστα βαθμολογιών. Υλοποιεί τη συσχέτιση "αφορά σε παράσταση" με την κλάση Βαθμολογία
    private final List<Score> scores; 

    /**
     * O προκαθορισμένος δημιουργός ο οποίος δημιουργεί τις λίστες διαγωνιζομένων και βαθμολογιών.
     */
    public Episode() {
        scores = new  ArrayList<>();
        contestants = new ArrayList<>(); 
    }
    
    /**
     * Δημιουργός της κλάσης με παραμέτρους. Αρχικά καλεί τον προκαθορισμένο δημιουργό και στη συνέχεια αναθέτει το δοθέν αριθμό
     * επεισοδίου, ημερομηνία και διάρκεια
     * @param num Ο αριθμός επεισοδίου
     * @param date Η ημερομηνία προβολής
     * @param durationMin Η διάρκεια σε λεπτά
     */
    public Episode(int num, Date date, int durationMin) {
        this();        
        this.num = num;
        this.date = date;
        this.durationMin = durationMin;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    /**
     * Προσθέτει ένα διαγωνιζόμενο στο επεισόδιο
     * @param c 
     */
    public void addContestant(Contestant c){
        contestants.add(c);                
    }
  
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public List<Contestant> getContestants() {
        return contestants;
    }

    public void setContestants(List<Contestant> contestants) {
        this.contestants = contestants;
    }
    
   
    public void createContestantScore(Judge j, Contestant c, int grade, String comment){
        double s;   //τοπική μεταβλητή
        //αν ο διαγωνιζόμενος είναι αντικείμενο τύπου Artist
        if (c instanceof Artist){
            //αν ο κριτής είναι sing expert
            if ("sing expert".equals(j.getSpecialty())){
                //αν ο διαγωνιζόμενος είναι αντικείμενο τύπου Singer
                if (c instanceof Singer){
                    s = grade * 0.4; 
                }else{
                    s = grade * 0.1;
                }
            //αν ο κριτής είναι instrument playing expert    
            } else if ("instrument playing expert".equals(j.getSpecialty())){
                //αν ο διαγωνιζόμενος είναι αντικείμενο τύπου InstrumentPlayer
                if (c instanceof InstrumentPlayer){
                    s = grade * 0.4;
                }else{
                    s = grade * 0.1;
                }
            //αν ο κριτής είναι song writing expert   
            } else if ("song writing expert".equals(j.getSpecialty())){
                //αν ο διαγωνιζόμενος είναι αντικείμενο τύπου SongWriter
                if (c instanceof SongWriter){
                    s = grade * 0.4;
                }else{
                    s = grade * 0.1;
                }
            //αν ο κριτής είναι radiobroadcaster    
            } else if ("radiobroadcaster".equals(j.getSpecialty())){
                s = grade * 0.3;
            //αν ο κριτής είναι οτιδήποτε άλλο   
            } else {
                s = grade * 0.1;
            }
        //αν ο διαγωνιζόμενος είναι αντικείμενο τύπου Band                
        } else {
            //βαθμολόγηση ανάλογα με τη εξειδίκευση του κριτή
            if ("sing expert".equals(j.getSpecialty())){
                s = grade * 0.3;
            } else if ("instrument playing expert".equals(j.getSpecialty())){
                s = grade * 0.25;
            } else if ("song writing expert".equals(j.getSpecialty())){
                s = grade * 0.2;
            } else if ("radiobroadcaster".equals(j.getSpecialty())){
                s = grade * 0.15; 
            } else {
                s = grade * 0.1;
            }
        }
        //δημιουργία νέας διαμορφωμένης βαθμολογίας και προσθήκη της στη λίστα 
        //βαθμολογιών του συγκεκριμένου διαγωνιζόμενου         
        c.addScore(new Score((int)s, comment, c, this, j));   
    }
    
    
    
    //μέθοδος που ταξινομεί τις τελικές βαθμολογίες των διαγωνιζομένων του 
    //επεισοδίου και ταυτόχρονα τους αντίστοιχους διαγωνιζόμενους          
    public void sortEpisodeFinalScores(){
        //τοπική μεταβλητή για υπολογισμό του αθροίσματος των βαθμολογιών των κριτών
        int finalScore; 
        //αντικείμενο Score
        List<Score> sc; 
        //τοπική λίστα για προσωρινή χρήση
        List<Integer> finalScores = new ArrayList<>();
         
        //για κάθε διαγωνιζόμενο του επεισοδίου καλεί τη μέθοδο 
        //sumContestantEpisodeScores με την οποία υπολογίζει τη συνολική  
        //βαθμολογία του στο επεισόδιο και την εισάγει στην προσωρινή λίστα
        //finalScores
        for(int i=0; i<this.getContestants().size(); i++){
            finalScore = this.getContestants().get(i).sumContestantEpisodeScores(this);
            //προσθήκη συνολικής βαθμολογίας διαγωνιζόμενου στην προσωρινή λίστα
            finalScores.add(finalScore);
        }
             
        //πηγή κώδικα για ταυτόχρονη ταξινόμιση δύο λιστών 
        //"https://stackoverflow.com/questions/25552209/sorting-two-lists"
        List<Contestant> cList;
        //δημιουργία HashMap
        HashMap<Integer, List<Contestant>> hashmap = new HashMap<>();
        
        //για κάθε τελική βαθμολογία
        for (int i = 0; i < finalScores.size(); i++) {
            //αν ο hashmap περιέχει ήδη το κλειδί 
            if (hashmap.containsKey(finalScores.get(i))){
                //εισάγει ως δεύτερο αντικείμενο στη λίστα
                hashmap.get(finalScores.get(i)).add(contestants.get(i));
                
            //αν ο hashmap δεν περιέχει το κλειδί
            } else {
                //δημιουργεί νέα λίστα
                cList = new ArrayList<>();
                //εισάγει ως πρώτο αντικείμενο στη λίστα
                cList.add(contestants.get(i));
                //δημιουργεί αντιστοιχία κλειδιού και νέας λίστας
                hashmap.put(finalScores.get(i), cList);
            }        
        }
        
        //ταξινομεί τη λίστα με τις τελικές βαθμολογίες        
        Collections.sort(finalScores);
        //ταξινομεί τη λίστα με φθίνουσα σειρά   
        Collections.reverse(finalScores);
        //αδειάζει τη λίστα των διαγωνιζομένων του επεισοδίου       
        contestants.clear();
        
        int fscore;  //τοπική μεταβλητή
        
        //για κάθε τελική βαθμολογία της ταξινομημένης λίστας
        for (int i=0; i<finalScores.size(); i++){            
            //καταχώριση βαθμολογίας
            fscore = finalScores.get(i);
            //για κάθε αντικείμενο (τύπου Contestant) της λίστας που αντιστοιχεί
            //στο κλειδί fscore του hashmap
            for (int j=0; j<hashmap.get(fscore).size(); j++){
                //εισάγει το αντικείμενο στη λίστα contestants του επεισοδίου
                contestants.add(hashmap.get(fscore).get(j));
            }
            //αύξηση δείκτη τόσο, όσες οι ίδιες βαθμολογίες που μπορεί να έχουν
            //δοθεί σε διαγωνιζόμενους
            i += hashmap.get(fscore).size() - 1;              
        }
    }
    
    
    
    public Contestant findLastEpisodeContestant(){      
        //επιστρέφει τον τελευταίο Contestant της list
        return this.getContestants().get(contestants.size() - 1); 
    }
   
    
    public Contestant findTopEpisodeContestant(){
        //επιστρέφει τον πρώτο Contestant της list
        return this.getContestants().get(0);
    }
    
    
}
