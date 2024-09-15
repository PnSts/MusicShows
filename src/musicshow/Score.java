/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import musicshow.contestants.Contestant;
import musicshow.judging.Judge;

/**
 * Αναπαριστά τη Βαθμολογία
 * @author nicolas
 */
public class Score {
    // Ο βαθμός που δίνει ένας κριτής σε ένα διαγωνιζόμενο
    private int grade;
    // Το σχόλιο του κριτή
    private String jugmentComments;
    // Ο διαγωνιζόμενος που βαθμολογείται
    private Contestant contestant;
    // Το επεισόδιο που αφορά η βαθμολογία
    private Episode episode;
    // Ο κριτής που βαθμολογεί
    private Judge judge;

    public Score(int grade, String jugmentComments, Contestant contestant, Episode episode, Judge judge) {
        this.grade = grade;
        this.jugmentComments = jugmentComments;
        this.contestant = contestant;
        this.episode = episode;
        this.judge = judge;
    }
    
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getJugmentComments() {
        return jugmentComments;
    }

    public void setJugmentComments(String jugmentComments) {
        this.jugmentComments = jugmentComments;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }
    
    
}
