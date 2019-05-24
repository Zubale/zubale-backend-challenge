package personal.teslafoil.zubale.quote.models;

import java.util.UUID;

import lombok.Data;

@Data
public class Quote {

    String quoteString;
    UUID id;
    int voteCount;
    int voteValue;

    public Quote(String quoteString) {
        this.quoteString = quoteString;
        id = UUID.randomUUID();
        voteCount = 0;
        voteValue = 0;
    }

    public String getQuoteString() {
        return quoteString;
    }

    public int voteUp() {
        voteCount++;
        return voteValue++;
    }

    public int voteDown() {
        voteCount++;
        return voteValue--;
    }
}
