package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultarPessoas2 {
    public static void main(String[] args) throws SQLException {

        //Capturar entrada
        Scanner entrada = new Scanner(System.in);

        // Conexão Fábrica
        Connection conexao = FabricaConexao.getConexao();

        // SQL executado
        String sql = "SELECT * from pessoas WHERE nome like ?";

        System.out.print("Informe o nome que está procurando: ");
        String valor = entrada.nextLine();

        //Anti SQL Injection e preparar entrada
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, '%' + valor + '%');
        stmt.execute();
        ResultSet resultado = stmt.executeQuery();

        List<Pessoa> pessoas = new ArrayList<>();

        while(resultado.next()) {
            int codigo = resultado.getInt("codigo");
            String nome = resultado.getString("nome");
            pessoas.add(new Pessoa(codigo, nome));
        }

        for (Pessoa p: pessoas) {
            System.out.println(p.getCodigo() + "-> " +  p.getNome());
        }
        stmt.close();
        conexao.close();
    }
}
