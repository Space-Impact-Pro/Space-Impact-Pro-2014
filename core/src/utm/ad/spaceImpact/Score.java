package utm.ad.spaceImpact;

import java.io.Serializable;

public class Score  implements Serializable {
    private static final long serialVersionUID = 3734905623241385712L;
    private final int score;
    private final String naam;

    public int getScore() {
        return score;
    }

    public String getNaam() {
        return naam;
    }

    public Score(String naam, int score) {
        this.score = score;
        this.naam = naam;
    }
    
}
