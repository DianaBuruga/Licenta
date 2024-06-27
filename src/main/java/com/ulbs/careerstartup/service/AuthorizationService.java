package com.ulbs.careerstartup.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final ApplicationContext context;

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T callMethodByName(String className, UUID id, Principal principal) {
        Object serviceBean = context.getBean(Class.forName(className));
        Method method = serviceBean.getClass().getMethod("isOwner", UUID.class, Principal.class);
        return (T) method.invoke(serviceBean, id, principal);
    }
}
