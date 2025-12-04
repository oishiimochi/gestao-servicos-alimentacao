package repository;

import exceptions.RepositorioCheioException;
import java.util.Arrays;

public class RepositorioRelatorio {

    private static final int CAPACIDADE_MAXIMA = 100;
    private String[] historicoRelatorios;
    private int proximaPosicao;

    public RepositorioRelatorio() {
        this.historicoRelatorios = new String[CAPACIDADE_MAXIMA];
        this.proximaPosicao = 0;
    }


    public void adicionarRelatorio(String textoRelatorio) throws RepositorioCheioException {
        if (proximaPosicao >= CAPACIDADE_MAXIMA) {
            // Lança a exceção para quem chamou tratar
            throw new RepositorioCheioException("O histórico de relatórios está cheio. Não é possível salvar novos relatórios.");
        }

        historicoRelatorios[proximaPosicao] = textoRelatorio;
        proximaPosicao++;
    }

    public String[] buscarTodos() {
        return Arrays.copyOf(historicoRelatorios, proximaPosicao);
    }
}
