package quote.model;

import java.io.Serializable;
import java.util.Objects;

public class Quote  implements Comparable<Quote>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final Integer DEFAULT_NUMBER_OF_VOTES = 0;

    private long idQuote;
    private String quote;
    private Integer NumberOfVotes;

    public Quote() {
        this.setNumberOfVotes(DEFAULT_NUMBER_OF_VOTES);
    }

    public Quote(long idQuote, String quote) {
        this.idQuote = idQuote;
        this.quote = quote;
    }

    public Integer getNumberOfVotes() {
        return NumberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        NumberOfVotes = numberOfVotes;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public long getIdQuote() {
        return idQuote;
    }

    public void setIdQuote(long idQuote) {
        this.idQuote = idQuote;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote1 = (Quote) o;
        return NumberOfVotes == quote1.NumberOfVotes &&
                idQuote == quote1.idQuote &&
                Objects.equals(quote, quote1.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quote, NumberOfVotes, idQuote);
    }

    @Override
    public int compareTo(Quote o) {
        return  o.getNumberOfVotes().compareTo( getNumberOfVotes()) ;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "idQuote=" + idQuote +
                ", quote='" + quote + '\'' +
                ", NumberOfVotes=" + NumberOfVotes +
                '}';
    }
}
