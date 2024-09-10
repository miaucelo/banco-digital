package BancoDigital.Banco;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            Cliente cliente;
            ContaCorrente cc;
            ContaPoupanca poupanca;

            int opcaoCriarCliente = -1;
            while (opcaoCriarCliente != 0 && opcaoCriarCliente != 1) {
                System.out.println("Deseja criar um novo cliente? (1 - Sim / 0 - Não)");
                if (scanner.hasNextInt()) {
                    opcaoCriarCliente = scanner.nextInt();
                } else {
                    System.out.println("Entrada inválida! Digite 1 para Sim ou 0 para Não.");
                    scanner.next(); 
                }
            }

            if (opcaoCriarCliente == 1) {
                cliente = new Cliente();
                scanner.nextLine(); 
                String nomeCliente;
                
                do {
                    System.out.println("Digite o nome do cliente:");
                    nomeCliente = scanner.nextLine();
                    if (nomeCliente.trim().isEmpty()) {
                        System.out.println("Nome inválido! O nome não pode ser vazio.");
                    }
                } while (nomeCliente.trim().isEmpty());

                cliente.setNome(nomeCliente);

                cc = new ContaCorrente(cliente);
                poupanca = new ContaPoupanca(cliente);

                cc.depositar(1000);

                System.out.println("Novo cliente e contas criados com sucesso!");
            } else {
                cliente = new Cliente();
                cliente.setNome("João");

                cc = new ContaCorrente(cliente);
                poupanca = new ContaPoupanca(cliente);

                cc.depositar(1000);
            }

            boolean continuarTransferencias = true;

            while (continuarTransferencias) {
                System.out.println("\nSaldo atual da Conta Corrente:");
                cc.imprimirExtrato();

                System.out.println("\nSaldo atual da Conta Poupança:");
                poupanca.imprimirExtrato();

                System.out.println("\nDigite o valor a ser transferido:");
                double valorTransferencia = -1;
                while (valorTransferencia <= 0) {
                    if (scanner.hasNextDouble()) {
                        valorTransferencia = scanner.nextDouble();
                        if (valorTransferencia <= 0) {
                            System.out.println("Valor inválido! O valor deve ser maior que zero.");
                        }
                    } else {
                        System.out.println("Entrada inválida! Digite um valor numérico.");
                        scanner.next(); 
                    }
                }

                int escolha = -1;
                while (escolha != 1 && escolha != 2) {
                    System.out.println("Transferir para: \n1 - Conta Corrente \n2 - Conta Poupança");
                    if (scanner.hasNextInt()) {
                        escolha = scanner.nextInt();
                        if (escolha != 1 && escolha != 2) {
                            System.out.println("Opção inválida! Digite 1 para Conta Corrente ou 2 para Conta Poupança.");
                        }
                    } else {
                        System.out.println("Entrada inválida! Digite 1 para Conta Corrente ou 2 para Conta Poupança.");
                        scanner.next(); 
                    }
                }

                boolean saldoSuficiente = false;

                switch (escolha) {
                    case 1:
                        if (poupanca.getSaldo() >= valorTransferencia) {
                            poupanca.transferir(valorTransferencia, cc);
                            System.out.println("Transferência de R$" + valorTransferencia + " para Conta Corrente realizada com sucesso!");
                            saldoSuficiente = true;
                        } else {
                            System.out.println("Saldo insuficiente na Conta Poupança para realizar a transferência.");
                        }
                        break;
                    case 2:
                        if (cc.getSaldo() >= valorTransferencia) {
                            cc.transferir(valorTransferencia, poupanca);
                            System.out.println("Transferência de R$" + valorTransferencia + " para Conta Poupança realizada com sucesso!");
                            saldoSuficiente = true;
                        } else {
                            System.out.println("Saldo insuficiente na Conta Corrente para realizar a transferência.");
                        }
                        break;
                }

                if (saldoSuficiente) {
                    System.out.println("\nSaldo atualizado:");
                    System.out.println("Conta Corrente:");
                    cc.imprimirExtrato();

                    System.out.println("Conta Poupança:");
                    poupanca.imprimirExtrato();
                }

                int opcao = -1;
                while (opcao != 0 && opcao != 1) {
                    System.out.println("\nDeseja realizar outra transferência? (1 - Sim / 0 - Não)");
                    if (scanner.hasNextInt()) {
                        opcao = scanner.nextInt();
                        if (opcao != 0 && opcao != 1) {
                            System.out.println("Entrada inválida! Digite 1 para Sim ou 0 para Não.");
                        }
                    } else {
                        System.out.println("Entrada inválida! Digite 1 para Sim ou 0 para Não.");
                        scanner.next(); 
                    }
                }

                if (opcao == 0) {
                    continuarTransferencias = false;
                    System.out.println("Encerrando as transferências.");
                }
            }

            int opcaoNovoCliente = -1;
            while (opcaoNovoCliente != 0 && opcaoNovoCliente != 1) {
                System.out.println("\nDeseja criar um novo cliente? (1 - Sim / 0 - Não)");
                if (scanner.hasNextInt()) {
                    opcaoNovoCliente = scanner.nextInt();
                    if (opcaoNovoCliente != 0 && opcaoNovoCliente != 1) {
                        System.out.println("Entrada inválida! Digite 1 para Sim ou 0 para Não.");
                    }
                } else {
                    System.out.println("Entrada inválida! Digite 1 para Sim ou 0 para Não.");
                    scanner.next(); 
                }
            }

            if (opcaoNovoCliente == 0) {
                continuar = false;
                System.out.println("Encerrando o programa. Obrigado por usar o MSBANK!");
            }
        }

        scanner.close();
    }
}
