package com.parsakav.echorestapi.request;

import jakarta.validation.constraints.*;

public record OfferRequest (@Size(max=30)
                            @NotNull(message = "title must be not null")
                            @NotEmpty(message = "title must be not empty")
                            String title,
                            @NotNull(message = "text must be not null")
                            @NotEmpty(message = "text must be not empty")
                            String text,
                            @Min(1000000000) Long influencerPhoneNumber,
                                    @Min(1000000000)
         Long  buisnessOwnerPhoneNumber){


}
