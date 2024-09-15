/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.contestants;

import java.util.ArrayList;
import java.util.List;
import musicshow.Episode;
import musicshow.Score;

/**
 * Αφηρημένη κλάση που αναπαριστά τον Διαγωνιζόμενο
 * @author nicolas
 */
public abstract class Contestant{
    protected String name;
    private String phone;
    private String addesss;        
    protected List<Score> scores;
    private List<Episode> episodes;

    public Contestant() {
        scores = new ArrayList<>();
        episodes = new ArrayList<>();
    }
    
    public Contestant(String name) {
        this();
        this.name = name;        
    }
    
    
    public void addScore(Score s){
        scores.add(s);
    }
    
    public void addEpisode(Episode e){
        episodes.add(e);
    }

    public List<Score> getScores() {
        return scores;
    }
    
    public List<Episode> getEpisodes() {
        return episodes;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddesss() {
        return addesss;
    }

    public void setAddesss(String addesss) {
        this.addesss = addesss;
    }

    public abstract void perform();
    
    //μέθοδος η οποία υπολογίζει το άθροισμα των βαθμολογιών (διαμορφωμένων 
    //με βάση την εξειδίκευση των κριτών) του διαγωνιζόμενου στο επεισόδιο και
    //υπολογίζει-επιστρέφει την τελική βαθμολογία του σ’ αυτό
    public int sumContestantEpisodeScores(Episode e){
        int finalScore = 0; //αρχικοποίηση τοπικής μεταβλητής
        Score sc;   //αντικείμενο Score
        
        //διατρέχει τη λίστα scores του διαγωνιζόμενου και επιστρέφει για το 
        //επεισόδιο e το άθροισμα των βαθμολογιών
        for(int i=0; i<this.getScores().size(); i++){
            sc = this.getScores().get(i);
            if (e.equals(sc.getEpisode())){
                finalScore += sc.getGrade();
            }        
        }
        return finalScore;
    }
}
