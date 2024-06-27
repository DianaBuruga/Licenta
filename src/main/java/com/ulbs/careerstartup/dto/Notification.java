package com.ulbs.careerstartup.dto;

import java.util.Collection;
import java.util.UUID;

public record Notification(Collection<UUID> ids, String email, String name, String position) {
}
