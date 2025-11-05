package repository;

import exceptions.ItemNaoEncontradoException;
import exceptions.RepositorioCheioException;
import modelo.ItemVenda;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ItemVendaRepository {
    private final Map<Integer, ItemVenda> catalogoItens = new HashMap<>();
    private static final int CAPACIDADE_MAXIMA = 100; // Exemplo de capacidade

    public void adicionarItem(ItemVenda item) throws RepositorioCheioException {
        if (catalogoItens.size() >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O catálogo de itens vendáveis está cheio.");
        }
        // A chave do mapa é o ID da FichaTecnica associada ao ItemVenda
        catalogoItens.put(item.getPrato().getId(), item);
    }

    public ItemVenda buscarPorIdFichaTecnica(int idFichaTecnica) throws ItemNaoEncontradoException {
        return Optional.ofNullable(catalogoItens.get(idFichaTecnica))
                .orElseThrow(() -> new ItemNaoEncontradoException("Item com ID de Ficha Técnica " + idFichaTecnica + " não encontrado no catálogo."));
    }

    public void removerItem(int idFichaTecnica) throws ItemNaoEncontradoException {
        if (catalogoItens.remove(idFichaTecnica) == null) {
            throw new ItemNaoEncontradoException("Não foi possível remover: Item com ID de Ficha Técnica " + idFichaTecnica + " não encontrado.");
        }
    }
}