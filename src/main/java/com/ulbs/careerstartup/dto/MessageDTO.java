package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO that = (MessageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(messageContent, that.messageContent) && Objects.equals(sendDate, that.sendDate) && Objects.equals(seen, that.seen) && Objects.equals(senderDTO, that.senderDTO) && Objects.equals(receiverDTO, that.receiverDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageContent, sendDate, seen, senderDTO, receiverDTO);
    }
}
