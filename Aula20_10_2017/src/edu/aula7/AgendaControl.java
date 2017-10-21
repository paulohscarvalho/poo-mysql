package edu.aula7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AgendaControl implements TableModel {
	
	private List<Agenda> lista = new ArrayList<Agenda>();
	
	public void adicionar(Agenda a) throws SQLException { 
		//lista.add( a );
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/agenda";
		Connection con = DriverManager.getConnection(url, "root", "aluno");
		System.out.println("Conexão bem sucedida");
		
		PreparedStatement stmt = con.prepareStatement();
		String cmd = "INSERT INTO contatos (id, nome, telefone, email, descricao) "
				+ "VALUES (?, ?, ?, ?, ?)";
		int i = stmt.executeUpdate(cmd);
		System.out.println("Foram afetados " + i + " registros");
	}
	
	public List<Agenda> pesquisar(String nome) { 
		List<Agenda> listaTemp = new ArrayList<Agenda>();
		for( Agenda a : lista ) { 
			if (a.getNome().contains( nome )) { 
				listaTemp.add( a );
			}
		}
		return listaTemp;
	}
	
	public Agenda getItem( int i ) { 
		return lista.get( i );
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		switch( coluna ) { 
			case 0 : return String.class;
			case 1 : return String.class;
			case 2 : return String.class;
		}
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int coluna) {
		switch( coluna ) { 
			case 0 : return "Nome";
			case 1 : return "Telefone";
			case 2 : return "Email";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Agenda a = lista.get( linha );
		switch( coluna ) { 
			case 0 : return a.getNome();
			case 1 : return a.getTelefone();
			case 2 : return a.getEmail();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}
