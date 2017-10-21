package edu.aula7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AgendaControl implements TableModel {
	
	private List<Agenda> lista = new ArrayList<Agenda>();
	
	public void adicionar(Agenda a) { 
		try {
			Connection con = DBUtil.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO contatos " +
					" (id, nome, telefone, email, descricao) " + 
				    "VALUES (?, ?, ?, ?, ?)" );

			stmt.setInt(1, 0);
			stmt.setString(2, a.getNome() );
			stmt.setString(3, a.getTelefone() );
			stmt.setString(4, a.getEmail() );
			stmt.setString(5, "" );
			stmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Agenda> pesquisar(String nome) { 
		try {
			Connection con = DBUtil.getInstance().getConnection();
			String cmd = "SELECT * FROM contatos WHERE nome like ?";
			PreparedStatement stmt = con.prepareStatement( cmd );
			stmt.setString(1,  "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			lista.clear();
			while (rs.next()) { 
				Agenda a = new Agenda();
				a.setEmail( rs.getString("email") );
				a.setNome(rs.getString("nome") );
				a.setTelefone( rs.getString("telefone") );
				lista.add( a );
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
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
