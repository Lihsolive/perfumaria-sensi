package main;

import model.Product;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Store {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> cart;

    public static void main(String[] args) {
        products = new ArrayList<>();
        //HashMap é a classe que implementa a interface do Map
        cart = new HashMap<>();
        menu();
    }

    private static void menu() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("----------------- Bem-Vindo(a) a Perfumaria Sensi! --------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-------------------------- O que deseja fazer? ------------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("|       1 - Cadastar Produtos          |");
        System.out.println("|       2 - Listar Produtos            |");
        System.out.println("|       3 - Comprar                    |");
        System.out.println("|       4 - Ver carrinho               |");
        System.out.println("|       5 - Sair                       |");
        System.out.println("-----------------------------------------------------------------------");

        int option = input.nextInt();

        switch (option) {
            case 1:
                registerProducts();
                break;
            case 2:
                listProducts();
                break;
            case 3:
                buyProducts();
                break;
            case 4:
                seeCart();
                break;
            case 5:
                System.out.println("Obrigado pela preferência, volte sempre!");
                System.exit(0);
            default:
                System.out.println("Opção inválida! Tente novamente.");
                menu();
                break;
        }
    }

    private static void registerProducts() {
        System.out.println("Nome do produto: ");
        String name = input.next();

        System.out.println("Preço do produto: ");
        Double price = input.nextDouble();

        Product product = new Product(name, price);
        products.add(product);

        System.out.println(product.getName() + " cadastrado com sucesso!");
        menu();
    }

    private static void listProducts() {
        if (products.size() > 0) {
            System.out.println("Lista de produtos: \n");

            for (Product p : products) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto cadastrado.");
        }
        menu();
    }

    private static void buyProducts() {
        //verifica o tamanho da lista
        if (products.size() > 0) {
            //mostra os produtos disponiveis
            System.out.println("----------------- Produtos disponíveis --------------------");
            for (Product p : products) {
                System.out.println(p + "\n");
            }
            System.out.println("Código do produto: \n");
            //se tiver produtos na lista, é pedido para o usuário digitar o código do produto que deseja comprar e armazena na variável id
            int id = input.nextInt();
            boolean isPresent = false; //variável para indicar que o produto não está no carrinho
            //percorre a lista de produtos e verifica se há um produto com o id digitado pelo usuário
            for (Product p : products) {
                if (p.getId() == id) {
                    int qtd = 0;
                    try {
                        qtd = cart.get(p);
                        //se o produto já existe no carrinho, se sim, irá incrementar, do contrário, irá adicionar
                        cart.put(p, qtd +1);
                    } catch (NullPointerException e) {
                        //verifica se o produto é o primeiro no carrinho
                        cart.put(p, 1);
                    }
                    //mostra que o produto foi adicionado ao carrinho
                    System.out.println(p.getName() + " adicionando ao carrinho.");
                    isPresent = true;
                    //pergunta ao usuário se ele quer adicionar mais produtos ou finalizar a compra
                    if (isPresent) {
                        System.out.println("Deseja adicionar outro produto ao carrinho?");
                        System.out.println("Digite 1 para SIM ou 0 para FINALIZAR A COMPRA. ");
                        int optional = Integer.parseInt(input.next());

                        if (optional == 1) {
                            buyProducts(); //chama o método comprar novamente
                        } else if (optional == 0){
                            finish(); //chama o método finalizar compra
                        }
                    }
                }
            }
            if (!isPresent) {
                System.out.println("Produto não encontrado!");
                menu();
            }
            } else {
            System.out.println("Não existem produtos cadastrados!");
            menu();
            }
        }
    private static void seeCart() {
        System.out.println("----------------- Produtos no seu carrinho --------------------");
        if (cart.size() > 0) {
            for (Product p : cart.keySet()) {
                System.out.println("Produto: " + p + "\nQuantidade: " + cart.get(p));
            }
        } else {
            System.out.println("Seu carrinho está vazio!");
        }
        menu();
    }

    private static void finish() {
        Double purchaseAmount = 0.0;
        System.out.println("Seus produtos: ");

        for (Product p : cart.keySet()) {
            int qtd = cart.get(p);
            purchaseAmount += p.getPrice() * qtd;
            System.out.println(p);
            System.out.println("Quantidade: " + qtd);
            System.out.println("--------------------");
        }
        System.out.println("O valor da sua compra é: " + Utils.doubleToString(purchaseAmount));
        cart.clear();
        System.out.println("Compra realizada com sucesso!");
        menu();
    }
}