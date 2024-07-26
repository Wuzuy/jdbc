package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizarRegistro {
    public static void main(String[] args) throws SQLException {

        String sql = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
        String select = "SELECT * FROM pessoas";

        Scanner entrada = new Scanner(System.in);
        Connection conexao = FabricaConexao.getConexao();
        Statement stmt = conexao.createStatement();
        ResultSet resultado = stmt.executeQuery(select);

        // Select *
        List<Pessoa> pessoas = new ArrayList<>();

        while(resultado.next()){
            int codigo = resultado.getInt("codigo");
            String nome = resultado.getString("nome");
            pessoas.add(new Pessoa(codigo, nome));
        }

        for(Pessoa p : pessoas) {
            System.out.println(p.getCodigo() + " -> " + p.getNome());
        }

        // Update
        System.out.print("Qual o id que você deseja substituir? \nID: ");
        int ID = entrada.nextInt();
        entrada.nextLine();  // Limpar o buffer do scanner

        System.out.print("Qual o novo valor desejado? \nValor: ");
        String valor = entrada.nextLine();
        PreparedStatement Pstmt = conexao.prepareStatement(sql);
        Pstmt.setString(1, valor);
        Pstmt.setInt(2, ID);
        Pstmt.executeUpdate();

        // Fechar todos os recursos
        conexao.close();
        entrada.close();
    }
}
