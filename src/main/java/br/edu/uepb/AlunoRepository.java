package br.edu.uepb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.uepb.models.Aluno;

public class AlunoRepository {
    private TurmaRepository turmaRepository = new TurmaRepository();
    private static HashMap<Integer, Aluno> alunos = new HashMap<>();

    public List<Aluno> getAll() {
        List<Aluno> alunosList = new ArrayList<Aluno>(alunos.values());

        for (Aluno aluno : alunosList){
            aluno.setTurma(turmaRepository.getById(aluno.getIdTurma()));
            if (aluno.getTurma() == null)
                aluno.setIdTurma(0);
        }
        
        return alunosList;
    }

    public Aluno getByMatricula(int matricula) {
        Aluno aluno = alunos.get(matricula);

        if (aluno != null){
            aluno.setTurma(turmaRepository.getByMatricula(aluno.getIdTurma()));
            if (aluno.getTurma() == null)
                aluno.setIdTurma(0);
        }

        return aluno;
    }

    public void create(Aluno aluno) {
        if (aluno.getId() == 0)
            aluno.setId(generateId(alunos.size() + 1));

        aluno.setTurma(turmaRepository.getById(aluno.getIdTurma()));
        alunos.put(aluno.getId(), aluno);
    }

    public void delete(int matricula) {
        alunos.remove(matricula);
    }

    public void edit(Aluno aluno) {
        alunos.remove(aluno.getMatricula());
        alunos.put(aluno.getMatricula(), aluno);
    }

    private int generateId(final int possible) {
        if (alunos.containsKey(possible))
            return generateId(possible + 1);
        return possible;
    }
}