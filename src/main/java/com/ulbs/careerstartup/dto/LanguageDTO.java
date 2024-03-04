package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.LanguageLevel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LanguageDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private LanguageLevel listening;

    @NotNull
    private LanguageLevel reading;

    @NotNull
    private LanguageLevel speaking;

    @NotNull
    private LanguageLevel conversation;

    @NotNull
    private LanguageLevel writing;

    private FileDTO fileDTO;

    @NotNull
    private UserDTO userDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageDTO that = (LanguageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && listening == that.listening && reading == that.reading && speaking == that.speaking && conversation == that.conversation && writing == that.writing && Objects.equals(userDTO, that.userDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, listening, reading, speaking, conversation, writing, userDTO);
    }
}
