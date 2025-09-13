import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Trabalho {
    private List<Veiculo> veiculos;

    public Trabalho() {
        veiculos = new ArrayList<>();
        carregarVeiculos();
    }

    private void carregarVeiculos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String marca = parts[0];
                    String modelo = parts[1];
                    String placa = parts[2];
                    int ano = Integer.parseInt(parts[3]);
                    veiculos.add(new Veiculo(marca, modelo, placa, ano));
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    private void salvarVeiculos() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("veiculos.txt"));
            for (Veiculo v : veiculos) {
                pw.println(v.getMarca() + "," + v.getModelo() + "," + v.getPlaca() + "," + v.getAno());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar veículos.");
        }
    }

    private boolean existePlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarVeiculo(Scanner sc) {
        System.out.println("Digite a marca:");
        String marca = sc.nextLine();

        System.out.println("Digite o modelo:");
        String modelo = sc.nextLine();

        System.out.println("Digite o ano:");
        int ano = Integer.parseInt(sc.nextLine());

        System.out.println("Digite a placa:");
        String placa = sc.nextLine();

        if (existePlaca(placa)) {
            System.out.println("Erro: Veículo com esta placa já cadastrado.");
            return;
        }

        Veiculo novo = new Veiculo(marca, modelo, placa, ano);
        veiculos.add(novo);
        salvarVeiculos();
        System.out.println("Veículo cadastrado com sucesso!");
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        System.out.println("Lista de Veículos:");
        for (Veiculo v : veiculos) {
            System.out.println(v.toString());
        }
    }

    public void excluirVeiculo(Scanner sc) {
        System.out.println("Digite a placa do veículo para exclusão:");
        String placa = sc.nextLine();

        java.util.Iterator<Veiculo> it = veiculos.iterator();
        while (it.hasNext()) {
            Veiculo v = it.next();
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                it.remove();
                salvarVeiculos();
                System.out.println("Veículo removido com sucesso.");
                return;
            }
        }
        System.out.println("Erro: Placa não encontrada.");
    }

    public void pesquisarVeiculo(Scanner sc) {
        System.out.println("Pesquisar por: \n1 - Placa\n2 - Modelo");
        int opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1:
                System.out.println("Digite a placa para pesquisa:");
                String placa = sc.nextLine();
                boolean encontradoPlaca = false;
                for (Veiculo v : veiculos) {
                    if (v.getPlaca().equalsIgnoreCase(placa)) {
                        System.out.println(v.toString());
                        encontradoPlaca = true;
                        break;
                    }
                }
                if (!encontradoPlaca) {
                    System.out.println("Nenhum veículo encontrado com esta placa.");
                }
                break;

            case 2:
                System.out.println("Digite parte do modelo para pesquisa:");
                String modeloBusca = sc.nextLine().toLowerCase();
                boolean encontradoModelo = false;
                for (Veiculo v : veiculos) {
                    if (v.getModelo().toLowerCase().contains(modeloBusca)) {
                        System.out.println(v.toString());
                        encontradoModelo = true;
                    }
                }
                if (!encontradoModelo) {
                    System.out.println("Nenhum veículo encontrado com este modelo.");
                }
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

    public static void main(String[] args) {
        Trabalho trabalho = new Trabalho();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar Veículo");
            System.out.println("2 - Listar Veículos");
            System.out.println("3 - Excluir Veículo");
            System.out.println("4 - Pesquisar Veículo");
            System.out.println("0 - Sair");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    trabalho.cadastrarVeiculo(sc);
                    break;
                case 2:
                    trabalho.listarVeiculos();
                    break;
                case 3:
                    trabalho.excluirVeiculo(sc);
                    break;
                case 4:
                    trabalho.pesquisarVeiculo(sc);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}
