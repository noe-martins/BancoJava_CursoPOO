package BancoProjeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BancoApp {
	
	private static Scanner leitor;
	private static Scanner leitorNumero;
	private static Scanner leitorSaqueDeposito;
	private static boolean execute;
	private static List <ContaBanco> dados;
	
	public static void main(String[] args) {
		leitor = new Scanner (System.in);
		leitorNumero = new Scanner (System.in);
		leitorSaqueDeposito = new Scanner (System.in);
		execute = true;
		dados = new ArrayList<ContaBanco>();
		
		System.out.println("BEM VINDO AO JAVABANK");
		
		while (execute) {
			String opcao = Menu();
			
			if (opcao.equalsIgnoreCase("n")) {
				AbrirConta();
			} else if (opcao.equalsIgnoreCase("c")) {
				EstadoConta();
			} else if (opcao.equalsIgnoreCase("f")) {
				FecharConta();
			} else if (opcao.equalsIgnoreCase("d")) {
				Depositar();
			} else if (opcao.equalsIgnoreCase("s")) {
				Sacar();
			} else if (opcao.equalsIgnoreCase("p")) {
				PagamentoMensal();
			} else if (opcao.equalsIgnoreCase("x")){
				execute = false;
			} else {
				System.out.println("\nOpção Inválida!!\n");
			}
		}
		
		System.exit(0);
	}
	
	private static String Menu() {
		System.out.println("Selecione uma opção:");
		System.out.println("N - Abrir Nova Conta");
		System.out.println("C - Consultar Conta");
		System.out.println("F - Fechar conta");
		System.out.println("D - Depositar");
		System.out.println("S - Sacar");
		System.out.println("P - Fazer Pagamento Mensal");
		System.out.println("X - Sair");
		return leitor.nextLine();
	}
	
	private static void AbrirConta() {
		boolean cadastrando = true;
		
		do  {
			//Cadastro do Nome e Tipo de Conta
			System.out.println("\n\nCadastro de Conta");
			ContaBanco d = new ContaBanco();
			d.setDono(textInput("Nome:"));
			boolean validacaoTipo = true;
			do {
				System.out.println("(CC - Conta Corrente | CP - Conta Poupança)");
				d.setTipo(textInput("Tipo de Conta: "));
				if(d.getTipo().equalsIgnoreCase("cc")) {
					d.setSaldo(50.00);
					validacaoTipo = false;
				} else if(d.getTipo().equalsIgnoreCase("cp")) {
					d.setSaldo(150.00);
					validacaoTipo = false;
				} else {
					System.out.println("\nEsse tipo de conta não existe!");
					System.out.println("\n\tTente Novamente");
					validacaoTipo = true;
				}
			}while(validacaoTipo);
			d.setStatus(true);
			
			//Validação do Cadastro
			String cadastrar = textInput("Adicionar cadastro (S/N): ");
			if (cadastrar.equalsIgnoreCase("s")) {
				System.out.println("\n\tCadastro adicionado\n");
				dados.add(d);
			} else if (cadastrar.equalsIgnoreCase("n")) {
				System.out.println("\n\tCadastro ignorado\n");
			} else {
				System.out.println("\n\topção Inválida!!\n");
			}
			
			//Opção de Retorno ao Menu
			String continua = textInput("Continuar cadastrando (S/N): ");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
			} else if(continua.equalsIgnoreCase("s")) {
				// se for s sai do if e volta para o inicio do while
			} else {
				System.out.println("\nopção Inválida!!\n");
				cadastrando = false;
			}
			
		} while(cadastrando);
		
	}
	
	private static void EstadoConta() {
		boolean consultando = true;
		
		do {
			
			boolean isEmpty = dados.isEmpty();
			
			if(isEmpty) {
				System.out.println("Não tem contas cadastradas!");
			} else {
				
				//Apresenta todos as contas registradas com Número e Nome
				System.out.println("\n\nLista de Contas\n");
				for(int i = 0; i < dados.size(); i++) {
					ContaBanco d = dados.get(i);
					System.out.println("\tConta: " + i);
					System.out.println("\tNome: " + d.getDono());
					System.out.println();
				}
				System.out.println("\nFim da lista\n");
				
				//Escolha de qual conta pegar detalhe
				System.out.print("Qual é o número da sua conta, para mais detalhes: ");
				int numConta = leitorNumero.nextInt();
				
				ContaBanco d = dados.get(numConta);
				System.out.println("\n\tNome: " + d.getDono());
				if(d.getTipo().equalsIgnoreCase("cc")) {
					System.out.println("\tTipo: Conta Corrente");
				}else {
					System.out.println("\tTipo: Conta Poupança");
				}
				System.out.printf("\tSaldo: R$%.2f\n", d.getSaldo());
				if(d.isStatus() == true) {
					System.out.println("\tStatus: Conta Aberta\n");
				}else {
					System.out.println("\tStatus: Fechada\n");
				}
				
			}
			
			//Opção de retorno ao Menu
			String continua = textInput("Continuar consultando (S/N): ");
			if (continua.equalsIgnoreCase("n")) {
				consultando = false;
			} else if(continua.equalsIgnoreCase("s")) {
				// se for s: sai do if e volta para o inicio do while
			} else {
				System.out.println("\nopção Inválida!!\n");
				consultando = false;
			}
		
		} while (consultando);
		
	}

	private static void FecharConta() {
		boolean fechando = true;
		
		do {
			
			boolean isEmpty = dados.isEmpty();
			
			if(isEmpty) {
				System.out.println("Não tem contas cadastradas!");
			} else {
				
				//Apresenta todos as contas registradas com Número e Nome
				System.out.println("\n\nLista de Contas\n");
				for(int i = 0; i < dados.size(); i++) {
					ContaBanco d = dados.get(i);
					System.out.println("\tConta: " + i);
					System.out.println("\tNome: " + d.getDono());
					System.out.println();
				}
				System.out.println("\nFim da lista\n");
				
				//Escolha de qual conta fechar
				System.out.print("Qual é o número da sua conta que deseja fechar: ");
				int numConta = leitorNumero.nextInt();
				
				ContaBanco d = dados.get(numConta);
				if(d.getSaldo() == 0.0) {
					d.setStatus(false);
					System.out.println("Conta fechada com sucesso");
				}else if(d.getSaldo() > 0.0) {
					System.out.printf("\nVocê ainda tem: R$%.2f\n",d.getSaldo());
					System.out.println("\n\tVocê precisa sacar todo o valor de saldo da sua conta para fechá-la\n\n");
					String sacarTudo = textInput("Deseja sacar tudo (S/N): ");
					if (sacarTudo.equalsIgnoreCase("s")) {
						SacarTudo(numConta);
						d.setStatus(false);
						System.out.println("\n\tSaldo zerado");
						System.out.println("\tConta fechada com sucesso");
					} else if(sacarTudo.equalsIgnoreCase("n")) {
						// se for s: continua o código
					} else {
						System.out.println("\nopção Inválida!!\n");
					}
				}else {
					System.out.println("Sua conta está negativada. Regularize sua situação para fechá-la");
				}
				
			}
			
			//Opção de retorno ao Menu
			String continua = textInput("Fechar mais uma conta (S/N): ");
			if (continua.equalsIgnoreCase("n")) {
				fechando = false;
			} else if(continua.equalsIgnoreCase("s")) {
				// se for s: sai do if e volta para o inicio do while
			} else {
				System.out.println("\nopção Inválida!!\n");
				fechando = false;
			}
		
		} while (fechando);
		
	}
	
	private static void Depositar() {
		boolean depositando = true;
		
		do {
			
			boolean isEmpty = dados.isEmpty();
			
			if(isEmpty) {
				System.out.println("Não tem contas cadastradas!");
			} else {
				
				//Apresenta todos as contas registradas com Número e Nome
				System.out.println("\n\nLista de Contas\n");
				for(int i = 0; i < dados.size(); i++) {
					ContaBanco d = dados.get(i);
					System.out.println("\tConta: " + i);
					System.out.println("\tNome: " + d.getDono());
					System.out.println();
				}
				System.out.println("\nFim da lista\n");
				
				//Escolha de qual conta deseja depositar dinheiro
				System.out.print("Qual é o número da conta que deseja sacar dinheiro: ");
				int numConta = leitorNumero.nextInt();
				
				ContaBanco d = dados.get(numConta);
				if(d.isStatus() == true) {
					System.out.print("Quanto deseja depositar: ");
					double deposito = leitorSaqueDeposito.nextDouble();
					
					deposito += d.getSaldo();
					d.setSaldo(deposito);

				}else {
					System.out.println("\nA conta encontra-se Fechada");
				}
				
			}
			
			//Opção de retorno ao Menu
			String continua = textInput("Continuar fazendo depósito (S/N): ");
			if (continua.equalsIgnoreCase("n")) {
				depositando = false;
			} else if(continua.equalsIgnoreCase("s")) {
				// se for s: sai do if e volta para o inicio do while
			} else {
				System.out.println("\nopção Inválida!!\n");
				depositando = false;
			}
		
		} while (depositando);
	}
	
	private static void Sacar() {
		boolean sacando = true;
		
		do {
			
			boolean isEmpty = dados.isEmpty();
			
			if(isEmpty) {
				System.out.println("Não tem contas cadastradas!");
			} else {
				
				//Apresenta todos as contas registradas com Número e Nome
				System.out.println("\n\nLista de Contas\n");
				for(int i = 0; i < dados.size(); i++) {
					ContaBanco d = dados.get(i);
					System.out.println("\tConta: " + i);
					System.out.println("\tNome: " + d.getDono());
					System.out.println();
				}
				System.out.println("\nFim da lista\n");
				
				//Escolha de qual conta deseja sacar dinheiro
				System.out.print("Qual é o número da conta que deseja sacar dinheiro: ");
				int numConta = leitorNumero.nextInt();
				
				ContaBanco d = dados.get(numConta);
				if(d.isStatus() == true && d.getSaldo() > 0) {
					System.out.print("Quanto deseja sacar: ");
					double saque = leitorSaqueDeposito.nextDouble();
					
					if(saque <= d.getSaldo()) {
						// Cálculo do que será abatido do Saldo
						double diferenca = d.getSaldo() - saque;
						d.setSaldo(diferenca);
						System.out.printf("\n\tSaque de R$%.2f realizado!\n\n",saque);
					}else {
						System.out.println("\n\tSaldo insuficiente para saque");
					}

				}else {
					System.out.println("\nA conta encontra-se Fechada ou possui Saldo insuficiente!");
				}
				
			}
			
			//Opção de retorno ao Menu
			String continua = textInput("Continuar sacando dinheiro (S/N): ");
			if (continua.equalsIgnoreCase("n")) {
				sacando = false;
			} else if(continua.equalsIgnoreCase("s")) {
				// se for s: sai do if e volta para o inicio do while
			} else {
				System.out.println("\nopção Inválida!!\n");
				sacando = false;
			}
		
		} while (sacando);
	}
	
	private static void SacarTudo(int numeroConta) {
		ContaBanco d = dados.get(numeroConta);
		d.setSaldo(0);
	}
	
	private static void PagamentoMensal() {
		boolean pagando = true;
		
		do {
			
			boolean isEmpty = dados.isEmpty();
			if(isEmpty) {
				System.out.println("Não tem contas cadastradas!");
			} else {
				
				//Apresenta todos as contas registradas com Número e Nome
				System.out.println("\n\nLista de Contas\n");
				for(int i = 0; i < dados.size(); i++) {
					ContaBanco d = dados.get(i);
					System.out.println("\tConta: " + i);
					System.out.println("\tNome: " + d.getDono());
					System.out.println();
				}
				System.out.println("\nFim da lista\n");
				
				//Escolha de qual conta deseja sacar dinheiro
				System.out.print("Qual é o número da conta que deseja fazer o Pagamento Mensal: ");
				int numConta = leitorNumero.nextInt();
				System.out.println("OBS: O pagamento será descontado do Saldo, logo precisa estar com Saldo positivo");
				
				ContaBanco d = dados.get(numConta);
				if(d.isStatus() == true && d.getTipo().equalsIgnoreCase("cc") && d.getSaldo() >= 12.0) {
					double diferenca = d.getSaldo() - 12.0;
					d.setSaldo(diferenca);
					System.out.println("\n\tPagamento feito com sucesso\n");
				}else if(d.isStatus() == true && d.getTipo().equalsIgnoreCase("cp") && d.getSaldo() >= 20.0) {
					double diferenca = d.getSaldo() - 20.0;
					d.setSaldo(diferenca);
					System.out.println("\n\tPagamento feito com sucesso\n");
				}else if(d.isStatus() == true && d.getTipo().equalsIgnoreCase("cc") && d.getSaldo() < 12.0) {
					System.out.println("\n\tA conta não possui Saldo suficiente!\n");
				}else if(d.isStatus() == true && d.getTipo().equalsIgnoreCase("cp") && d.getSaldo() < 20.0) {
					System.out.println("\n\tA conta não possui Saldo suficiente!\n");
				}else if(d.isStatus() == false) {
					System.out.println("\n\tA conta está Fechada!\n");
				}
				
			}
			
			//Opção de retorno ao Menu
			String continua = textInput("Continuar fazendo pagamento (S/N): ");
			if (continua.equalsIgnoreCase("n")) {
				pagando = false;
			} else if(continua.equalsIgnoreCase("s")) {
				// se for s: sai do if e volta para o inicio do while
			} else {
				System.out.println("\nopção Inválida!!\n");
				pagando = false;
			}
		}while(pagando);
	}
	
	private static String textInput(String label) {
		System.out.print(label);
		return leitor.nextLine();
	}

}
