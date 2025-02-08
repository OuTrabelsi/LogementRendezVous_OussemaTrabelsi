package restapi;

import entities.RendezVous;
import metiers.RendezVousBusiness;
import entities.Logement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/rendezvous")
public class RendezVousResource {
    RendezVousBusiness rendezVousMetier = new RendezVousBusiness();

    // Get all rendezvous
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousMetier.getListeRendezVous();
        return Response.ok(rendezVousList).build();
    }

    // Get rendezvous by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = rendezVousMetier.getRendezVousById(id);
        return rendezVous != null
                ? Response.ok(rendezVous).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    // Get rendezvous by logement reference
    @GET
    @Path("/logement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogementReference(@PathParam("reference") int reference) {
        List<RendezVous> rendezVousList = rendezVousMetier.getListeRendezVousByLogementReference(reference);
        return rendezVousList.isEmpty()
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(rendezVousList).build();
    }

    // Add a new rendezvous
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = rendezVousMetier.addRendezVous(rendezVous);
        return added
                ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    // Update an existing rendezvous
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean updated = rendezVousMetier.updateRendezVous(id, updatedRendezVous);
        return updated
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    // Delete a rendezvous
    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousMetier.deleteRendezVous(id);
        return deleted
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
