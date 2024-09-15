/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import musicshow.EpisodesExceededException;
import musicshow.MusicShow;
import musicshow.judging.Judge;
import musicshow.judging.JudgingPanel;

/**
 *
 * @author PanDim
 */
public class MusicShowSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //δημιουργία εκπομπής
        MusicShow ms = new MusicShow();
        
        //πηγή παρακάτω κώδικα για πρόσθεση ημερών σε ημερομηνία 
        //"https://beginnersbook.com/2017/10/java-add-days-to-date/"
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        //δημιουργία αντικειμένου ημερολογίου
        Calendar cal = Calendar.getInstance();
        //δημιουργία αντικειμένου Date και αρχικοποίησή του με την τρέχουσα 
        //ημερομηνία
        Date date = new Date();
        
        //δημιουργία 10 επεισοδίων
        for (int i = 1; i <= 10; i++) {
            //καταχώριση της ημερομηνίας στο ημερολόγιο c
            cal.setTime(date);
            
            try {
                //δημιουργία νέου επεισοδίου
                ms.createEpisode(i, date, 45);
                
            } catch (EpisodesExceededException ex) {
                Logger.getLogger(MusicShowSimulator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //αύξηση της ημέρας κατά 7 (εβδομαδιαίο επεισόδιο)
            cal.add(Calendar.DAY_OF_MONTH, 7);
            //καταχώριση της νέας ημερομηνίας στο date 
            date = cal.getTime();   
        }
        
         
        //δημιουργία κριτικής επιτροπής
        JudgingPanel p = new JudgingPanel();
        //προσθήκη των νέων αντικειμένων Judge στη λίστα κριτών της κριτικής επιτροπής 
        p.addJudge(new Judge("Έλενα Παπαρίζου", "sing expert"));
        p.addJudge(new Judge("Σάκης Ρουβάς", "instrument playing expert"));
        p.addJudge(new Judge("Γιώργος Θεοφάνους", "song writing expert"));
        p.addJudge(new Judge("Μιχάλης Τσαουσόπουλος", "radiobroadcaster"));
        p.addJudge(new Judge("Φωκάς Ευαγγελινός", "performance expert"));
        //καταχώριση της κριτικής επιτροπής στην εκπομπή
        ms.addJudgingPanel(p);
        
        //καταχώριση διαγωνιζομένων
        //δημιουργία και καταχώριση αντικειμένων τύπου Artist
        ms.getEpisode(1).addContestant(new musicshow.contestants.Singer("Frank Sinatra"));
        ms.getEpisode(1).addContestant(new musicshow.contestants.Singer("Adele"));
        ms.getEpisode(1).addContestant(new musicshow.contestants.Singer("Madonna"));
        ms.getEpisode(1).addContestant(new musicshow.contestants.SongWriter("John Lennon"));
        ms.getEpisode(1).addContestant(new musicshow.contestants.SongWriter("Bob Dylan"));
        ms.getEpisode(1).addContestant(new musicshow.contestants.InstrumentPlayer("Gary Moore"));
        ms.getEpisode(1).addContestant(new musicshow.contestants.InstrumentPlayer("Keith Richards"));
        //δημιουργία και καταχώριση αντικειμένων τύπου Band
        try {
            ms.getEpisode(1).addContestant(new musicshow.contestants.Band("Scorpions", dateformat.parse("01/03/1965")));
        } catch (ParseException ex) {
            Logger.getLogger(MusicShowSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ms.getEpisode(1).addContestant(new musicshow.contestants.Band("Beatles", dateformat.parse("25/08/1959")));
        } catch (ParseException ex) {
            Logger.getLogger(MusicShowSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ms.getEpisode(1).addContestant(new musicshow.contestants.Band("Rolling Stones", dateformat.parse("12/10/1950")));
        } catch (ParseException ex) {
            Logger.getLogger(MusicShowSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        //έναρξη εκπομπής
        ms.runEpisodes();
        //δημιουργία αντικειμένου τύπου ReportShow με παράμετρο τη λίστα των 
        //επεισοδίων
        ReportShow report = new ReportShow(ms.getEpisodes());
        //δημιουργία αρχείου xml
        report.reportShow();
    }
    
}
