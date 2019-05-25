package personal.teslafoil.zubale.quote.models;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@ApiModel
public class Vote {

    public enum VoteValue {
        UP, DOWN
    }

    @NotNull(message = "Missing quoteId")
    @ApiModelProperty(required = true)
    UUID quoteId;

    @NotNull(message = "Missing voteValue")
    @ApiModelProperty(required = true)
    VoteValue voteValue;
}
