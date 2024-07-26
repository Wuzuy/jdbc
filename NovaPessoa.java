package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NovaPessoa {
    public static void main(String[] args) throws SQLException {

        Scanner entrada = new Scanner(System.in);
        System.out.print("Informe o nome: ");
        String nome = entrada.next();

        String sql = "INSERT INTO pessoas(nome, codigo) VALUES (?);";

        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql); // Cuidando de SQL Injection

            if (!nome.equalsIgnoreCase("stop")) {
                stmt.setString(1, nome);

                stmt.execute();
                System.out.println("Nome inserido: " + nome);

            } else {
                System.out.println("Encerrado com sucesso.");
            }
        entrada.close();
    }
}
