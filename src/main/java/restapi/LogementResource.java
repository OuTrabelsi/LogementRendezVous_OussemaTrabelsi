package restapi;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/logements")
public class LogementResource {
    LogementBusiness logementMetier = new LogementBusiness(); // ensure this is being properly created

    private static final List<Logement> logements = new ArrayList<>();

    // Get all logements
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogements() {
        return Response.ok(logements).build();
    }

    // Get logement by reference
    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Optional<Logement> logement = logements.stream()
                .filter(l -> l.getReference() == reference)
                .findFirst();
        return logement.isPresent()
                ? Response.ok(logement.get()).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    // Add a new logement
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        logements.add(logement);
        return Response.status(Response.Status.CREATED).build();
    }

    // Update an existing logement
    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement updatedLogement) {
        Optional<Logement> existing = logements.stream()
                .filter(l -> l.getReference() == reference)
                .findFirst();
        if (existing.isPresent()) {
            logements.remove(existing.get());
            logements.add(updatedLogement);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Delete a logement
    @DELETE
    @Path("/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean removed = logements.removeIf(l -> l.getReference() == reference);
        return removed
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}