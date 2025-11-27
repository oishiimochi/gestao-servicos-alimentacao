package repository;

import exceptions.IDExistenteException;
import exceptions.ItemNaoEncontradoException;
import exceptions.RepositorioCheioException;
import modelo.ItemVenda;
import java.util.Arrays;

public class ItemVendaRepository {
    private static final int CAPACIDADE_MAXIMA = 100;
    private ItemVenda[] catalogoItens = new ItemVenda[CAPACIDADE_MAXIMA];
    private int proximaPosicao = 0;

    public void adicionarItem(ItemVenda novoItem) throws RepositorioCheioException, IDExistenteException {
        if (proximaPosicao >= CAPACIDADE_MAXIMA) {
            throw new RepositorioCheioException("O catálogo de itens vendáveis está cheio.");
        }
        for (int i = 0; i < proximaPosicao; i++) {
            ItemVenda itemExistente = catalogoItens[i];
            if (itemExistente.getPrato().getId() == novoItem.getPrato().getId()) {
                throw new IDExistenteException(String.valueOf(novoItem.getPrato().getId()), "ItemVenda");
            }
        }
        catalogoItens[proximaPosicao] = novoItem;
        proximaPosicao++;
    }

    public ItemVenda buscarPorIdFichaTecnica(int idFichaTecnica) throws ItemNaoEncontradoException {
        for (int i = 0; i < proximaPosicao; i++) {
            if (catalogoItens[i].getPrato().getId() == idFichaTecnica) {
                return catalogoItens[i];
            }
        }
        throw new ItemNaoEncontradoException("Item com ID de Ficha Técnica " + idFichaTecnica + " não encontrado no catálogo.");
    }

    public void removerItem(int idFichaTecnica) throws ItemNaoEncontradoException {
        int indiceParaRemover = -1;
        for (int i = 0; i < proximaPosicao; i++) {
            if (catalogoItens[i].getPrato().getId() == idFichaTecnica) {
                indiceParaRemover = i;
                break;
            }
        }

        if (indiceParaRemover == -1) {
            throw new ItemNaoEncontradoException("Item com ID de Ficha Técnica " + idFichaTecnica + " não encontrado no catálogo.");
        }

        // Desloca os elementos para a esquerda para preencher o espaço
        for (int i = indiceParaRemover; i < proximaPosicao - 1; i++) {
            catalogoItens[i] = catalogoItens[i + 1];
        }
        catalogoItens[proximaPosicao - 1] = null; // Limpa a última posição
        proximaPosicao--;
    }

    public ItemVenda[] buscarTodos() {
        return Arrays.copyOf(catalogoItens, proximaPosicao);
    }
}