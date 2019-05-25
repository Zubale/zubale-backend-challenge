package personal.teslafoil.zubale.quote.models;

import java.util.UUID;

import lombok.Data;
import personal.teslafoil.zubale.quote.models.Vote.VoteValue;

@Data
public class Quote implements Comparable<Quote> {

    String text;
    UUID id;
    int voteCount;
    int voteScore;

    /**
     * Create a new Quote object with given text, a generated ID, and reset vote
     * count and score.
     * 
     * @param quoteText
     *            String text of the quote.
     */
    public Quote(String quoteText) {
        this.text = quoteText;
        id = UUID.randomUUID();
        voteCount = 0;
        voteScore = 0;
    }

    /**
     * Apply a vote value to a quote object.
     * 
     * @param voteValue
     *            either UP or DOWN
     * @return
     */
    public int vote(VoteValue voteValue) {
        if (voteValue.equals(VoteValue.UP)) {
            voteScore++;
        } else {
            voteScore--;
        }
        voteCount++;
        return voteScore;
    }

    /**
     * A custom compareTo that is based on score of the quote.<br>
     * If the score is tied the one with fewer vote is ranked higher.
     */
    @Override
    public int compareTo(Quote target) {
        if (voteScore == target.voteScore) {
            return target.voteCount - voteCount;
        } else {
            return voteScore - target.voteScore;
        }
    }
}
