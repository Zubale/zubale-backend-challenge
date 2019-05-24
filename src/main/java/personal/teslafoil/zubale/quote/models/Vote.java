package personal.teslafoil.zubale.quote.models;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Vote {

    public enum VoteValue {
        UP, DOWN
    }

    UUID quoteId;
    VoteValue voteValue;
}
