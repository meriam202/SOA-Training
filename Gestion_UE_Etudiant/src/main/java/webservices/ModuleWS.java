package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Module")
public class ModuleWS {
    ModuleBusiness helper = new ModuleBusiness();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {
        if(helper.addModule(module)==true){
            return Response.status(201).entity("object added").build();
        }else return Response.status(400).entity("object not added").build();
    }

    @Path("/delete/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("Module deleted")
                    .build();
        }

        return Response.status(400)
                .entity("Module not deleted")
                .build();
    }


    @Path("/update/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {

        if (helper.updateModule(matricule, updatedModule)) {
            return Response.status(200).entity("module updated").build();
        }
        else {
            return Response.status(400).entity("module not updated").build();
        }
    }

}