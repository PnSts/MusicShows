/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.judging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class JudgingPanel {
    private Date formationDate;
    private List<Judge> judges; 

    public JudgingPanel() {
        judges = new ArrayList<>(); 
    }
        

    public JudgingPanel(Date formationDate) {
        this();
        this.formationDate = formationDate;
    }
    
    public void addJudge(Judge j){
        judges.add(j);
    }

    public Date getFormationDate() {
        return formationDate;
    }

    public void setFormationDate(Date formationDate) {
        this.formationDate = formationDate;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public void setJudges(List<Judge> judges) {
        this.judges = judges;
    } 
    
}
