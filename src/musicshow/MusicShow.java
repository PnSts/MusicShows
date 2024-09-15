/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import musicshow.judging.JudgingPanel;
import java.util.List;
import musicshow.contestants.Contestant;
import musicshow.judging.Judge;

/**
 * Αναπαριστά την Εκπομπή
 * @author nicolas
 */
public class MusicShow {
    // Το όριο επεισοδίων για μια εκπομπή
    public static final int EPISODES_LIMIT = 10;
    // Ο κωδικός της εκπομπής
    private int code;
    // Ο τίτλος της εκπομπής
    private String title;
    // Η λίστα επεισοδίων που περιλαμβάνει η εκπομπή. Υλοποιεί τη συσχέτιση "περιλαμβάνει" με την κλάση Επεισόδιο 
    private List<Episode> episodes;
    // Η κριτική επιτροπή που συμμετέχει στην εκπομπή. Υλοποιεί τη συσχέτιση "συμμετέχει" με την κλάση Κριτική Επιτροπή
    private JudgingPanel judgingPanel;

    /**
     * O προκαθορισμένος δημιουργός ο οποίος δημιουργεί τη λίστα επεισοδίων.
     */
    public MusicShow() {
        episodes = new ArrayList<>();
    }
        
    /**
     * Δημιουργός της κλάσης με παραμέτρους. Αρχικά καλεί τον προκαθορισμένο δημιουργό και στη συνέχεια αναθέτει το δοθέν κωδικό και τίτλο
     * @param code Ο κωδικός της Εκπομπής
     * @param title  Ο τίτλος της Εκπομπής
     */
    public MusicShow(int code, String title) {
        this();
        this.code = code;
        this.title = title;        
    }
    
    /**
     * Δημιουργεί ένα Επεισόδιο με τον δοθέν αριθμό επεισοδίου, ημερομηνία και διάρκεια και το προθέτει στη λίστα επεισοδίων της εκπομπής. Επί της
     * ουσίας υλοποιεί το composition μεταξύ των 2 κλάσεων καθώς με τον τρόπο αυτό τα επεισόδια δημιουργούνται μόνο μέσω
     * της κλάσης της Εκπομπής και δε μπορούν να υπάρχουν αυτόνομα. Αν καταστραφεί το object της εκπομπής θα καταστραφούν
     * και τα επεισόδια που της ανήκουν. Επιπλέον υλοποιεί την πολλαπλότητα 1:10 του διαγράμματος κλάσεων καθώς απαγορεύει τη δημιουργία
     * περισσότερων από 10 επεισοδίων
     * @param num Ο αριθμός του επεισοδίου
     * @param date Η ημερομηνία που θα προβληθεί το επεισόδιο
     * @param durationMin Η διάρκεια του επεισοδίου
     * @throws EpisodesExceededException Δημιουργείται σε περίπτωση που ο αριθμός επεισοδίων υπερβεί το μέγιστο επιτρεπόμενο
     */
    public void createEpisode(int num, Date date, int durationMin) throws EpisodesExceededException{ 
        if (episodes.size()<EPISODES_LIMIT)
            episodes.add(new Episode(num, date, durationMin));
        else throw new EpisodesExceededException("Υπέρβαση αριθμού επεισοδίων για τη συγκεκριμένη εκπομπή!");
    }
    
    /**
     * Επιστρέφει το επεισόδιο που αντιστοιχεί στον δοθέν αριθμό
     * @param num Ο αριθμός του επεισοδίου που ζητείται
     * @return  Το επεισόδιο που αντιστοιχεί στον δοθέν αριθμό
     */
    public Episode getEpisode(int num){
        for (Episode e:episodes)
            if (e.getNum() == num)
                return e;
        return null;
    }
    
    /**
     * Αναθέτει τη δοθείσα κριτική επιτροπή στην Εκπομπή
     * @param p Η κριτική επιτροπή
     */
    public void addJudgingPanel(JudgingPanel p){
        judgingPanel = p;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JudgingPanel getJudgingPanel() {
        return judgingPanel;
    }

    public void setJudgingPanel(JudgingPanel judgingPanel) {
        this.judgingPanel = judgingPanel;
    }
    
    //μέθοδος επιστροφής τυχαίας τιμής
    private static int getRandomNumberInRange(int min, int max) {	
	Random r = new Random();
	return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();		
    }
    
    
    public void runEpisodes(){
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Episode ep;     //αντικείμενο Episode
        Contestant cont; //αντικείμενο Contestant
        String cName;   //όνομα διαγωνιζόμενου
        Judge judge;    //αντικείμενο Judge
        int jgrade;     //βαθμολογία κριτή
        
        System.out.println('\t' + "THE MUSICSHOW STARTS");      
        System.out.println('\t' + "====================");
        
        //για κάθε επεισόδιο
        for (int i = 0; i < EPISODES_LIMIT; i++) {
            //καταχώριση αντικειμένου Episode από λίστα episodes στο ep
            ep = episodes.get(i);
            //εμφάνιση στοιχείων επεισοδίου
            System.out.print('\n');
            System.out.print("Episode Νο " + ep.getNum() + " - ");
            System.out.print("Date: " +  dateformat.format(ep.getDate()) + " - ");
            System.out.print("Duration: " + ep.getDurationMin() + '\n');
            System.out.println("----------------------------------------------");
            
            //για κάθε διαγωνιζόμενο του επεισοδίου
            for (int j = 0; j < ep.getContestants().size(); j++) {
                //καταχώριση αντικειμένου Contestant από λίστα contestants
                cont = ep.getContestants().get(j);
                //παρουσίαση διαγωνιζόμενου
                cont.perform();
                //καταχώριση ονόματος διαγωνιζόμενου
                cName = cont.getName();
                
                //για κάθε κριτή της κριτικής επιτροπής
                for (int k = 0; k < judgingPanel.getJudges().size(); k++) {
                    //καταχώριση αντικειμένου Judge από λίστα judges
                    judge = judgingPanel.getJudges().get(k);
                    //εμφάνιση βαθμολόγησης
                    System.out.print('\t' + "- Judge " + judge.toString());
                    System.out.print(" - " + judge.getSpecialty());
                    System.out.print(" gives " + cName);
                    //καταχώριση τυχαίας τιμής μεταξύ 0 και 10 ως βαθμολογία
                    jgrade = getRandomNumberInRange(0, 10);
                    System.out.print(" grade " + jgrade + "." + '\n'); 
                    
                    //δημιουργία βαθμολογίας κριτή ανάλογα με την εξειδίκευσή του
                    //και καταχώρισή της στη λίστα scores του διαγωνιζόμενου
                    ep.createContestantScore(judge, cont, jgrade, null);
                    //καταχώριση επεισοδίου στη λίστα episodes του διαγωνιζόμενου
                    cont.addEpisode(ep);  
                }
                System.out.print('\n');
            }
            //ταξινόμιση τελικών βαθμολογιών και διαγωνιζομένων
            ep.sortEpisodeFinalScores();
            //δημιουργεί αντίγραφο της λίστας διαγωνιζομένων
            List<Contestant> nextList = new ArrayList<>();
            nextList.addAll(ep.getContestants());
            
            //καταχώριση λίστας διαγωνιζόμενων στο επόμενο επεισόδιο
            if (i != 0) {   //επόμενο επεισόδιο: διαφορετικό του Νο 2
                if (i != EPISODES_LIMIT - 1){  //επόμενο επεισόδιο: Νο 3 - 10
                    //αφαίρεση τελευταίου διαγωνιζόμενου
                    nextList.remove(ep.findLastEpisodeContestant());
                    //καταχώριση στο επόμενο επεισόδιο
                    episodes.get(i+1).setContestants(nextList); 
                } else {    //δεν υπάρχει άλλο επεισόδιο
                    System.out.println('\t' + "=== END OF EPISODES ===");
                    System.out.println('\n');
                    System.out.println('\t' + "WINNER ANNOUNCEMENT");
                    System.out.println('\t' + "-------------------");
                    System.out.println("* W I N N E R :  " + ep.findTopEpisodeContestant().getName());
                    System.out.println("*   SECOND    :  " + ep.findLastEpisodeContestant().getName());                    
                }                
            }  else {       //επόμενο επεισόδιο: Νο 2
                //διατήρηση ίδιων διαγωνιζόμενων               
                episodes.get(i+1).setContestants(nextList);                
            }            
        }        
    }
    
    
}
