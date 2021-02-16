package com.example;

import com.example.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.Response.created;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Context
    UriInfo uriInfo;

    @GET
    public List<User> list() {
        return userRepository.listAll();
    }

    @POST
    @Transactional
    public Response saveUser(User user) {
        userRepository.persist(user);
        return created(
                uriInfo.getBaseUriBuilder()
                        .path("/{id}")
                        .build(user.getId())
        ).build();
    }
    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") Long id) {
        return userRepository.findById(id);
    }
}