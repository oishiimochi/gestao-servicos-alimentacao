package modelo;

import exceptions.ValorInvalidoException;
import exceptions.ValorNuloException;

public class RequisitoReceita {
    private Produto produto;
    private double quantidadeNecessaria;
    private String unidadeQuantidade; // unidade na qual a quantidadeNecessaria foi informada (ex: "g", "kg", "ml", "l", "un")

    public RequisitoReceita(Produto produto, double quantidadeNecessaria) throws ValorNuloException, ValorInvalidoException {
        if (produto == null) {
            throw new ValorNuloException("O produto de um requisito da receita não pode ser nulo.");
        }
        if (quantidadeNecessaria <= 0) {
            throw new ValorInvalidoException("A quantidade necessária do requisito da receita deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidadeNecessaria = quantidadeNecessaria;
        this.unidadeQuantidade = null; // indica que a mesma unidade do produto foi usada
    }

    public RequisitoReceita(Produto produto, double quantidadeNecessaria, String unidadeQuantidade) throws ValorNuloException, ValorInvalidoException {
        if (produto == null) {
            throw new ValorNuloException("O produto de um requisito da receita não pode ser nulo.");
        }
        if (quantidadeNecessaria <= 0) {
            throw new ValorInvalidoException("A quantidade necessária do requisito da receita deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidadeNecessaria = quantidadeNecessaria;
        this.unidadeQuantidade = unidadeQuantidade == null ? null : unidadeQuantidade.trim().toLowerCase();
    }

    public Produto getProduto() {
        return produto;
    }

    public double getQuantidadeNecessaria() {
        return quantidadeNecessaria;
    }

    public double calcularCustoRequisito() {
        double quantidadeNaUnidadeDoProduto = quantidadeNecessaria;
        String unidadeProduto = produto.getUnidadeMedida();
        if (unidadeQuantidade != null && unidadeProduto != null && !unidadeQuantidade.equalsIgnoreCase(unidadeProduto)) {
            Double convertido = tryConverterUnidades(quantidadeNecessaria, unidadeQuantidade, unidadeProduto);
            if (convertido != null) {
                quantidadeNaUnidadeDoProduto = convertido;
            } else {
                // não foi possível converter: emitir aviso e usar valor informado (assume mesma unidade)
                System.err.println("[Aviso] Não foi possível converter unidade de '" + unidadeQuantidade + "' para '" + unidadeProduto + "' para o produto " + produto.getNome() + ". Usando valor bruto.");
            }
        }
        return produto.getCustoUnitario() * quantidadeNaUnidadeDoProduto;
    }

    private Double tryConverterUnidades(double valor, String de, String para) {
        if (de == null || para == null) return null;
        de = de.trim().toLowerCase();
        para = para.trim().toLowerCase();

        String famDe = unidadeFamilia(de);
        String famPara = unidadeFamilia(para);
        if (famDe == null || famPara == null) return null;
        if (!famDe.equals(famPara)) return null; // familias incompatíveis

        // fatores relativos à unidade base (kg para massa, l para volume, un para contagem)
        double fatorDe = fatorPorUnidade(de, famDe);
        double fatorPara = fatorPorUnidade(para, famPara);
        if (fatorDe <= 0 || fatorPara <= 0) return null;
        // valor * (fatorDe / fatorPara) converte de 'de' para 'para'
        return valor * (fatorDe / fatorPara);
    }

    private String unidadeFamilia(String u) {
        if (u == null) return null;
        u = u.trim().toLowerCase();
        switch (u) {
            case "kg": case "g": case "mg": return "massa";
            case "l": case "ml": return "volume";
            case "un": case "und": case "pc": case "unid": case "unit": return "contagem";
            default: return null;
        }
    }

    private double fatorPorUnidade(String u, String familia) {
        u = u.trim().toLowerCase();
        switch (familia) {
            case "massa":
                switch (u) {
                    case "kg": return 1.0; // base kg
                    case "g": return 0.001;
                    case "mg": return 0.000001;
                }
                break;
            case "volume":
                switch (u) {
                    case "l": return 1.0; // base litro
                    case "ml": return 0.001;
                }
                break;
            case "contagem":
                return 1.0; // todas equivalem a unidade inteira
        }
        return -1.0;
    }

    @Override
    public String toString() {
        String unidadeInfo = (unidadeQuantidade == null) ? produto.getUnidadeMedida() : unidadeQuantidade + " (sera convertido para " + produto.getUnidadeMedida() + ")";
        return String.format("Requisito: %s, Quantidade: %.3f %s",
                produto.getNome(), quantidadeNecessaria, unidadeInfo);
    }
}
