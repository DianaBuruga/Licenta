package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

    @NotNull
    private String messageContent;

    @NotNull
    private Timestamp sendDate;

    @NotNull
    private Boolean seen;

    @NotNull
    private UserDTO senderDTO;

    @NotNull
    private UserDTO receiverDTO;
}
