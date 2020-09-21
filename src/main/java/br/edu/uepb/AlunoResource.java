package br.edu.uepb;

import br.edu.uepb.models.Aluno;
import br.edu.uepb.AlunoRepository;
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

@Path("alunos")
public class AlunoResource {

    private AlunoRepository alunoRepository = new AlunoRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunos() {
        return Response.ok(alunoRepository.getAll()).build();
    }

    @GET
    @Path("{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunoByMatricula(@PathParam("matricula") int matricula) {
        return Response.ok(alunoRepository.getByMatricula(matricula)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAluno(Aluno aluno) {
        alunoRepository.create(aluno);
        return Response.status(Response.Status.CREATED).entity(aluno).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAluno(@PathParam("id") int id, Aluno aluno) {
        Aluno a = alunoRepository.getById(id);
        if (a == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        try {
            alunoRepository.delete(aluno.getId());
            return Response.noContent().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAluno(@PathParam("id") int id, Aluno aluno) {
        Aluno a = alunoRepository.getById(id);
        if (a == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        try {
            aluno.setId(id);
            alunoRepository.edit(aluno);
            return Response.ok(aluno).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}

