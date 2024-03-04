/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.ReferralDTO;
import com.ulbs.careerstartup.entity.Referral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ReferralMapper {

    ReferralMapper INSTANCE = Mappers.getMapper(ReferralMapper.class);

    @Mapping(source = "teacher", target = "teacherDTO")
    @Mapping(source = "student", target = "studentDTO")
    ReferralDTO referralToReferralDTO(Referral referral);

    @Mapping(source = "teacherDTO", target = "teacher")
    @Mapping(source = "studentDTO", target = "student")
    Referral referralDTOToReferral(ReferralDTO referralDTO);
}*/
