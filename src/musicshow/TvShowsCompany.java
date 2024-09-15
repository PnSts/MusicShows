/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.util.ArrayList;
import java.util.List;

/**
 * Αναπαριστά την Εταιρεία Τηλε-προγραμμάτων
 * @author Nicolas Drosos
 */
public class TvShowsCompany {
    // Επωνυμία της εταιρείας
    private String name;
    // Υλοποιεί τη συσχέτιση "παρουσιάζει" με την κλάση MusicShow (Εκπομπή)     
    private List<MusicShow> musicShows;

    /**
     * O προκαθορισμένος δημιουργός ο οποίος δημιουργεί τη λίστα εκπομπών.
     */
    public TvShowsCompany() {        
        musicShows = new ArrayList<>();
    }
    
    /**
     * Δημιουργός της κλάσης με παράμετρο. Αρχικά καλεί τον προκαθορισμένο δημιουργό και στη συνέχεια αναθέτει τη δοθείσα επωνυμία
     * @param name Η επωνυμία που θα αποδοθεί στο αντικείμενο που θα δημιουργηθεί με αυτό το δημιουργό
     */
    public TvShowsCompany(String name) {
        this();
        this.name = name;
    }
    
    /**
     * Δημιουργεί μια εκπομπή (MusicShow) με τον δοθέν κωδικό και τίτλο και την προθέτει στη λίστα εκπομπών της εταιρείας. Επί της
     * ουσίας υλοποιεί το composition μεταξύ των 2 κλάσεων καθώς με τον τρόπο αυτό οι εκπομπές δημιουργούνται μόνο μέσω
     * της κλάσης της εταιρείας και δε μπορούν να υπάρχουν αυτόνομα. Αν καταστραφεί το object της εταιρείας θα καταστραφούν
     * και οι εκπομπές που της ανήκουν.
     * @param code Ο κωδικός της εκπομπής
     * @param title Ο τίτλος της εκπομπής που θα δημιουργηθεί
     */
    public void createMusicShow(int code, String title) {        
            musicShows.add(new MusicShow(code, title));
    }
    
    /**
     * Επιστρέφει την εκπομπή (MusicShow) που αντιστοιχεί στον δοθέν κωδικό
     * @param code Ο κωδικός της εκπομπής που ζητείται
     * @return Η εκπομπή που αντιστοιχεί στον δοθέν κωδικό
     */
    public MusicShow getMusicShow(int code){
        for (MusicShow s: musicShows)
            if (s.getCode() == code)
                return s;            
        return null;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
