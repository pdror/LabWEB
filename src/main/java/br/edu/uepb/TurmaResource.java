package br.edu.uepb;

import br.edu.uepb.models.Turma;
import br.edu.uepb.TurmaRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("turmas")
public class TurmaResource {
    private TurmaRepository turmaRepository = new TurmaRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmas() {
        return Response.ok(turmaRepository.getAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmaById(@PathParam("id") int id) {
		Turma turma = turmaRepository.getById(id);

		if (turma == null)
			return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(turma).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTurma(Turma turma) {
        turmaRepository.create(turma);
        return Response.status(Response.Status.CREATED).entity(turma).build();
    }

    @PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editTurma(@PathParam("id") int id, Turma turma) {
		Turma turmaBuscada = turmaRepository.getById(id);
		if (turmaBuscada == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		try {
			turma.setId(id);
			turmaRepository.edit(turma);
			return Response.ok(turma).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTurma(@PathParam("id") int id) {
		Turma turma = turmaRepository.getById(id);
		if (turma == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		try {
			turmaRepository.delete(turma.getId());
			return Response.noContent().build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
}