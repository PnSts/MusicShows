/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshows;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import musicshow.Episode;
import musicshow.contestants.*;

/**
 *
 * @author PanDim
 */
public class ReportShow {
    
    List<Episode> episodes;
    private static final String FILE_NAME = "outputShow.xml";  
    
    
    public ReportShow (List<Episode> episodes) {
        this.episodes = episodes;
    }

    public void reportShow() {
        StringBuilder sb = new StringBuilder();
        String category;
        Band band;
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        
        sb.append("<ΕΚΠΟΜΠΗ>\n");
        for(Episode epis : episodes){
            sb.append("\t<ΕΠΕΙΣΟΔΙΟ>\n"); 
                sb.append("\t\t<ΑΡΙΘΜΟΣ_ΕΠΕΙΣΟΔΙΟΥ>");
                sb.append(epis.getNum());
                sb.append("</ΑΡΙΘΜΟΣ_ΕΠΕΙΣΟΔΙΟΥ>\n");
                sb.append("\t\t<ΗΜΕΡΟΜΗΝΙΑ_ΕΠΕΙΣΟΔΙΟΥ>");
                sb.append(dateformat.format(epis.getDate()));
                sb.append("</ΗΜΕΡΟΜΗΝΙΑ_ΕΠΕΙΣΟΔΙΟΥ>\n");
                sb.append("\t\t<ΔΙΑΡΚΕΙΑ_ΕΠΕΙΣΟΔΙΟΥ>");
                sb.append(epis.getDurationMin());
                sb.append("</ΔΙΑΡΚΕΙΑ_ΕΠΕΙΣΟΔΙΟΥ>\n");
                    sb.append("\t\t\t<ΚΑΤΑΤΑΞΗ_ΔΙΑΓΩΝΙΖΟΜΕΝΩΝ>\n");                   
                    for (Contestant cont : epis.getContestants()){                        
                        sb.append("\t\t\t\t<ΚΑΤΗΓΟΡΙΑ>");
                        if (cont instanceof Singer){
                            category = "Singer";
                        } else if (cont instanceof InstrumentPlayer) {
                            category = "Instrument player";
                        } else if (cont instanceof SongWriter) {
                            category = "Song writer";
                        } else {
                            category = "Band";
                        } 
                        sb.append(category);
                        sb.append("</ΚΑΤΗΓΟΡΙΑ>\n");
                        sb.append("\t\t\t\t<ΟΝΟΜΑ>");
                        sb.append(cont.getName());
                        sb.append("</ΟΝΟΜΑ>\n");
                        sb.append("\t\t\t\t<ΒΑΘΜΟΛΟΓΙΑ>");
                        sb.append(cont.sumContestantEpisodeScores(epis));
                        sb.append("</ΒΑΘΜΟΛΟΓΙΑ>\n");
                        if (category.equals("Band")) {
                            band = (Band) cont;
                            sb.append("\t\t\t\t<ΗΜΕΡΟΜΗΝΙΑ_ΙΔΡΥΣΗΣ>");
                            sb.append(dateformat.format(band.getFormationDate()));
                            sb.append("</ΗΜΕΡΟΜΗΝΙΑ_ΙΔΡΥΣΗΣ>\n");
                        }
                        sb.append("\t\t\t\t---\n");
                    }   
                    sb.append("\t\t\t</ΚΑΤΑΤΑΞΗ_ΔΙΑΓΩΝΙΖΟΜΕΝΩΝ>\n");  
            sb.append("\t</ΕΠΕΙΣΟΔΙΟ>\n");     
        }
            sb.append("\t<ΝΙΚΗΤΗΣ>");
            sb.append(episodes.get(9).getContestants().get(0).getName());
            sb.append("</ΝΙΚΗΤΗΣ>\n");
            sb.append("\t<ΔΕΥΤΕΡΟΣ>");
            sb.append(episodes.get(9).getContestants().get(1).getName());
            sb.append("</ΔΕΥΤΕΡΟΣ>\n");
        sb.append("<ΕΚΠΟΜΠΗ>\n");
        try {
            //Δημιουργούμε έναν νέο BufferedWriter για το αρχείο 
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            //Στον writer γράφουμε τα δεδομένα του sb καλώντας την μέθοδο write
            writer.write(sb.toString());
            //τέλος κλείνουμε το αρχείο
            writer.close();
            System.out.println("\nΔημιουργήθηκε το αρχείο xml.\n");
        }
        catch (IOException e) {
            //αν προκύψει εξαίρεση εμφανίζουμε το μήνυμα της εξαίρεσης
            System.out.println("Πρόβλημα εγγραφής στο αρχείο: "+ e.getLocalizedMessage());
        }
    }
    
    
    
}

