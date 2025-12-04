package repository;
import modelo.*;

public class RepositorioFornecedor {
    private static final int CAPACIDADE_MAXIMA = 200;
    private Fornecedor[] repositorio;
    private int proximaPosicao;

    public RepositorioFornecedor() {
        this.repositorio = new Fornecedor[CAPACIDADE_MAXIMA];
        this.proximaPosicao = 0;
    }
    public Fornecedor[] getRepositorio() {
        return java.util.Arrays.copyOf(repositorio, proximaPosicao);
    }
    public void adicionarFornecedor(Fornecedor fornecedor) {
        if (proximaPosicao < CAPACIDADE_MAXIMA) {
            repositorio[proximaPosicao++] = fornecedor;
        }
    }
    public void removerFornecedor(Fornecedor fornecedor) {
        int idx = -1;
        for (int i = 0; i < proximaPosicao; i++) {
            if (repositorio[i].getId().equals(fornecedor.getId())) {
                idx = i;
                break;
            }
        }
        if (idx != -1) {
            for (int i = idx; i < proximaPosicao - 1; i++) {
                repositorio[i] = repositorio[i + 1];
            }
            repositorio[--proximaPosicao] = null;
        }
    }
    public Fornecedor buscarFornecedor(String ID) {
        for (int i = 0; i < proximaPosicao; i++) {
            if (repositorio[i].getId().equals(ID)) {
                return repositorio[i];
            }
        }
        return null;
    }
}
