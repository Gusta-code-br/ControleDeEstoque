import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SistemaProduto {
    private String nome;
    private double preco;
    private int quantidade;

    public SistemaProduto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return nome + " - Preço: R$" + String.format("%.2f", preco) + " - Estoque: " + quantidade + " unidades";
    }
}

public class SistemaProdutos {
    private static final int MAX_PRODUTOS = 50;
    private List<SistemaProduto> produtos = new ArrayList<>();

    public static void main(String[] args) {
        SistemaProdutos sistema = new SistemaProdutos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir produto");
            System.out.println("2. Exibir produtos");
            System.out.println("3. Ordenar produtos por nome");
            System.out.println("4. Ordenar produtos por preço");
            System.out.println("5. Buscar produto por nome");
            System.out.println("6. Remover produtos duplicados");
            System.out.println("7. Calcular média de preço");
            System.out.println("8. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    sistema.inserirProduto(scanner);
                    break;
                case 2:
                    sistema.exibirProdutos();
                    break;
                case 3:
                    sistema.ordenarProdutosPorNome(scanner);
                    break;
                case 4:
                    sistema.ordenarProdutosPorPreco(scanner);
                    break;
                case 5:
                    sistema.buscarProdutoPorNome(scanner);
                    break;
                case 6:
                    sistema.removerProdutosDuplicados();
                    break;
                case 7:
                    sistema.calcularMediaPreco();
                    break;
                case 8:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void inserirProduto(Scanner scanner) {
        if (produtos.size() < MAX_PRODUTOS) {
            System.out.print("Nome do produto: ");
            String nome = scanner.next();
            System.out.print("Preço do produto: ");
            double preco = scanner.nextDouble();
            System.out.print("Quantidade em estoque: ");
            int quantidade = scanner.nextInt();

            SistemaProduto novoProduto = new SistemaProduto(nome, preco, quantidade);

            // Verificar se o produto já existe
            int index = produtos.indexOf(novoProduto);
            if (index != -1) {
                produtos.set(index, novoProduto);
            } else {
                produtos.add(novoProduto);
            }
        } else {
            System.out.println("Limite de produtos atingido.");
        }
    }

    public void exibirProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("Lista de produtos:");
            for (SistemaProduto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public void ordenarProdutosPorNome(Scanner scanner) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado para ordenar.");
            return;
        }

        System.out.print("Ordenar em ordem crescente (asc) ou decrescente (desc)? ");
        String order = scanner.next();

        if (order.equalsIgnoreCase("asc")) {
            Collections.sort(produtos, Comparator.comparing(SistemaProduto::getNome));
        } else if (order.equalsIgnoreCase("desc")) {
            Collections.sort(produtos, Comparator.comparing(SistemaProduto::getNome).reversed());
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }

        System.out.println("Produtos ordenados por nome:");
        for (SistemaProduto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void ordenarProdutosPorPreco(Scanner scanner) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado para ordenar.");
            return;
        }

        System.out.print("Ordenar em ordem crescente (asc) ou decrescente (desc)? ");
        String order = scanner.next();

        if (order.equalsIgnoreCase("asc")) {
            Collections.sort(produtos, Comparator.comparing(SistemaProduto::getPreco));
        } else if (order.equalsIgnoreCase("desc")) {
            Collections.sort(produtos, Comparator.comparing(SistemaProduto::getPreco).reversed());
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }

        System.out.println("Produtos ordenados por preço:");
        for (SistemaProduto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void buscarProdutoPorNome(Scanner scanner) {
        System.out.print("Digite o nome do produto que deseja buscar: ");
        String nome = scanner.next();

        for (SistemaProduto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Produto encontrado: " + produto);
                return;
            }
        }

        System.out.println("Produto '" + nome + "' não encontrado.");
    }

    public void removerProdutosDuplicados() {
        Set<SistemaProduto> uniqueProdutos = new HashSet<>(produtos);
        produtos.clear();
        produtos.addAll(uniqueProdutos);
    }

    public void calcularMediaPreco() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado para calcular a média de preço.");
            return;
        }

        double totalPreco = 0.0;
        for (SistemaProduto produto : produtos) {
            totalPreco += produto.getPreco();
        }

        double mediaPreco = totalPreco / produtos.size();
        System.out.println("Média de preço dos produtos: R$" + String.format("%.2f", mediaPreco));
    }
}
