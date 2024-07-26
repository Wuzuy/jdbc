package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcluirPessoa {
    public static void main(String[] args) throws SQLException {
        Connection conexao = FabricaConexao.getConexao();

        // Select *
        String select = "SELECT * FROM pessoas";
        Statement Sstmt = conexao.createStatement();
        ResultSet resultado = Sstmt.executeQuery(select);
        List<Pessoa> pessoas = new ArrayList<>();

        while(resultado.next()){
            int codigo = resultado.getInt("codigo");
            String nome = resultado.getString("nome");
            pessoas.add(new Pessoa(codigo, nome));
        }

        for(Pessoa p : pessoas) {
            System.out.println(p.getCodigo() + " -> " + p.getNome());
        }

        Scanner entrada = new Scanner(System.in);
        System.out.print("Informe o código a ser excluido: ");
        int ID = entrada.nextInt();
        entrada.nextLine(); // Limpar Buffer

        String sql = "DELETE FROM pessoas WHERE codigo = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, ID);

        int contador = stmt.executeUpdate();

        if(contador > 0) {
            System.out.println("Pessoa excluida com sucesso. " +
                    "\nLinhas afetadas: " + contador);
        }else {
            System.out.println("Ninguem foi excluido!");
        }

        conexao.close();
        entrada.close();
    }
}
