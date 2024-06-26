package com.ulbs.careerstartup.security.isOwnerRole.course;

import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.service.CourseService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.ulbs.careerstartup.security.isOwnerRole.RoleVerificationUtil.hasAdminRole;

@Aspect
@Component
public class IsCourseAspect {

    @Autowired
    private CourseService courseService;

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.course.IsCourseOwner) && args(courseDTO,..)")
    public void checkOwnership(CourseDTO courseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = courseService.isCourseOwner(courseDTO.getId(), authentication);
        if (!isOwner) {
            throw new AccessDeniedException("User is not the owner of this course");
        }
    }

    @Before("@annotation(com.ulbs.careerstartup.security.isOwnerRole.course.IsCourseOwner) && args(id,..)")
    public void checkOwnership(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isOwner = courseService.isCourseOwner(id, authentication);
        if(hasAdminRole(authentication) || isOwner){
            return;
        }
        throw new AccessDeniedException("User is not the owner of this course");
    }
}
