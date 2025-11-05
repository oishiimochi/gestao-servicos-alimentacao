package repository;

import exceptions.RepositorioCheioException;
import modelo.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    private final List<Venda> historicoVendas = new ArrayList<>();
    private static final int CAPACIDADE_MAXIMA = 5000;

    public void registrarVenda(Venda venda) throws RepositorioCheioException {
        if (historicoVendas.size() >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O histórico de vendas está cheio.");
        }
        historicoVendas.add(venda);
    }

    public List<Venda> buscarTodas() {
        return new ArrayList<>(historicoVendas); // Retorna uma cópia para proteger a lista original
    }
}