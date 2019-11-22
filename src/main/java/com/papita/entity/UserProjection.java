package com.papita.entity;

import org.springframework.data.rest.core.config.Projection;

/**
 * @version 1.0.0
 */
@Projection(
        name = "user",
        types = {User.class})
public interface UserProjection {
    Long getId();

    String getUsername();

    Status getStatus();

}
