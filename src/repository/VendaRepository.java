package repository;

import exceptions.IDExistenteException;
import exceptions.RepositorioCheioException;
import modelo.Venda;

import java.util.Arrays;

public class VendaRepository {
    private static final int CAPACIDADE_MAXIMA = 5000;
    private Venda[] historicoVendas = new Venda[CAPACIDADE_MAXIMA];
    private int proximaPosicao = 0;

    public void registrarVenda(Venda novaVenda) throws RepositorioCheioException, IDExistenteException {
        if (proximaPosicao >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O histórico de vendas está cheio.");
        }
        for (int i = 0; i < proximaPosicao; i++) {
            Venda vendaExistente = historicoVendas[i];
            if (vendaExistente.getId() == novaVenda.getId()) {
                throw new IDExistenteException(String.valueOf(novaVenda.getId()), "Venda");
            }
        }
        historicoVendas[proximaPosicao] = novaVenda;
        proximaPosicao++;
    }

    public Venda[] buscarTodas() {
        return Arrays.copyOf(historicoVendas, proximaPosicao);
    }
}