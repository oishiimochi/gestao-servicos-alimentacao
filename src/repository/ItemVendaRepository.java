package repository;

import exceptions.IDExistenteException;
import exceptions.ItemNaoEncontradoException;
import exceptions.RepositorioCheioException;
import modelo.ItemVenda;

import java.util.ArrayList;
import java.util.List;

public class ItemVendaRepository {
    private List<ItemVenda> catalogoItens = new ArrayList<>();
    private static int CAPACIDADE_MAXIMA = 100; // Exemplo de capacidade

    public void adicionarItem(ItemVenda item) throws RepositorioCheioException, IDExistenteException {
        if (catalogoItens.size() >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O catálogo de itens vendáveis está cheio.");
        }
        for (ItemVenda i : catalogoItens) {
            if (i.getPrato().getId() == item.getPrato().getId()) {
                throw new IDExistenteException(item.getPrato().getId());
            }
        }
        catalogoItens.add(item);
    }

    public ItemVenda buscarPorIdFichaTecnica(int idFichaTecnica) throws ItemNaoEncontradoException {
        for (ItemVenda item : catalogoItens) {
            if (item.getPrato().getId() == idFichaTecnica) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("Item com ID de Ficha Técnica " + idFichaTecnica + " não encontrado no catálogo.");
    }

    public void removerItem(int idFichaTecnica) throws ItemNaoEncontradoException {
        ItemVenda itemParaRemover = buscarPorIdFichaTecnica(idFichaTecnica);
        catalogoItens.remove(itemParaRemover);
    }

    public List<ItemVenda> buscarTodos() {
        return new ArrayList<>(catalogoItens);
    }
}
