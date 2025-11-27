package repository;

import exceptions.IDExistenteException;
import exceptions.RepositorioCheioException;
import modelo.FichaTecnica;
import java.util.Arrays;

public class FichaTecnicaRepository {
    private static final int CAPACIDADE_MAXIMA = 200;
    private FichaTecnica[] fichas;
    private int proximaPosicao;

    public FichaTecnicaRepository() {
        this.fichas = new FichaTecnica[CAPACIDADE_MAXIMA];
        this.proximaPosicao = 0;
    }

    public void adicionarFichaTecnica(FichaTecnica novaFicha) throws RepositorioCheioException, IDExistenteException {
        if (proximaPosicao >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O repositório de fichas técnicas está cheio.");
        }
        for (int i = 0; i < proximaPosicao; i++) {
            if (fichas[i].getId() == novaFicha.getId()) {
                throw new IDExistenteException(String.valueOf(novaFicha.getId()), "FichaTecnica");
            }
        }
        fichas[proximaPosicao] = novaFicha;
        proximaPosicao++;
    }

    public FichaTecnica buscarPorId(int id) {
        for (int i = 0; i < proximaPosicao; i++) {
            if (fichas[i].getId() == id) {
                return fichas[i];
            }
        }
        return null; // Retorna null se não encontrar
    }

    public boolean removerFichaTecnica(int id) {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximaPosicao; i++) {
            if (fichas[i].getId() == id) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            return false; // Ficha não encontrada
        }

        // Desloca os elementos para a esquerda para preencher o espaço
        for (int i = indiceParaRemover; i < proximaPosicao - 1; i++) {
            fichas[i] = fichas[i + 1];
        }
        fichas[proximaPosicao - 1] = null; // Limpa a última posição
        proximaPosicao--;
        return true;
    }

    public FichaTecnica[] buscarTodas() {
        return Arrays.copyOf(fichas, proximaPosicao);
    }
}