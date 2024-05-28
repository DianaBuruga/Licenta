package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDTO {

    private UUID id;

    @NotNull(message = "Message content is required")
    @NotEmpty(message = "Message content is required")
    @JsonProperty("messageContent")
    private String messageContent;

    @NotNull(message = "Send date is required")
    @NotEmpty(message = "Send date is required")
    private String sendDate;

    @NotNull(message = "Seen is required")
    @NotEmpty(message = "Seen is required")
    private Boolean seen;

    @NotNull(message = "Sender is required")
    @NotEmpty(message = "Sender is required")
    @JsonProperty("sender")
    private UserDTO senderDTO;

    @NotNull(message = "Receiver is required")
    @NotEmpty(message = "Receiver is required")
    @JsonProperty("receiver")
    private UserDTO receiverDTO;
}
