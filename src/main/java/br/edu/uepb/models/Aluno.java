package br.edu.uepb.models;

@Getter
@Setter
public class Aluno {
    private int matricula;
    private String nome;
    private String curso;
    private Turma turma;

    private int idTurma;

    public Aluno() {
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
        this.turma = new Turma();
    }
}