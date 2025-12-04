package servico;

import exceptions.IDExistenteException;
import exceptions.RepositorioCheioException;
import exceptions.ValorInvalidoException;
import modelo.FichaTecnica;
import repository.FichaTecnicaRepository;

public class CadastroFichaTecnica {
    private final FichaTecnicaRepository repositorio;

    public CadastroFichaTecnica() {
        this.repositorio = new FichaTecnicaRepository();
    }
    
    public void cadastrarFichaTecnica(int id, String nome, String categoria, String modoPreparo, int rendimento) throws RepositorioCheioException, IDExistenteException {
        FichaTecnica novaFicha = new FichaTecnica(id, nome, categoria, modoPreparo, rendimento);
        repositorio.adicionarFichaTecnica(novaFicha);
    }
    
    public FichaTecnica buscarFichaTecnicaPorId(int id) {
        return repositorio.buscarPorId(id);
    }
    
    public boolean removerFichaTecnica(int id) {
        return repositorio.removerFichaTecnica(id);
    }

    public String[] getFichasTecnicasParaExibicao() {
        FichaTecnica[] todasAsFichas = repositorio.buscarTodas();
        if (todasAsFichas.length == 0) {
            return new String[]{"Nenhuma ficha técnica cadastrada."};
        }
        
        String[] fichasExibicao = new String[todasAsFichas.length];
        for (int i = 0; i < todasAsFichas.length; i++) {
            FichaTecnica ficha = todasAsFichas[i];
            fichasExibicao[i] = String.format("ID: %d | Nome: %s | Categoria: %s | Custo Ingredientes/Porção: R$%.2f",
                    ficha.getId(), ficha.getNome(), ficha.getCategoria(), ficha.calcularCustoPorPorcao());
        }
        return fichasExibicao;
    }
    
    public void adicionarRequisito(int idFicha, modelo.RequisitoReceita requisito) throws exceptions.ValorInvalidoException, exceptions.ValorNuloException {
        FichaTecnica ficha = buscarFichaTecnicaPorId(idFicha);
        if (ficha == null) throw new exceptions.ValorNuloException("Ficha técnica não encontrada.");
        ficha.adicionarRequisito(requisito);
    }

    public FichaTecnicaRepository getRepositorio() {
        return repositorio;
    }
}
