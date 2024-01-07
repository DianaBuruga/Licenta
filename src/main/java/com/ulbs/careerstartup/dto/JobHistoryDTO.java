package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobHistoryDTO {

    private UUID id;

    @NotNull
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String position;

    @NotNull
    private Timestamp startDate;

    @NotNull
    private Timestamp endDate;

    @NotNull
    private String description;

    @NotNull
    private Boolean needQualification;

    @NotNull
    private UserDTO userDTO;

    @NotNull
    private CompanyDTO companyDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistoryDTO that = (JobHistoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(position, that.position) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description) && Objects.equals(needQualification, that.needQualification) && Objects.equals(userDTO, that.userDTO) && Objects.equals(companyDTO, that.companyDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, startDate, endDate, description, needQualification, userDTO, companyDTO);
    }
}
