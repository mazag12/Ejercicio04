package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.TipoCliente;
import util.MySqlDBConexion;

public class ModelCliente {

	public List<Cliente> ConsultarClienteNombre(String nombre){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			con = MySqlDBConexion.getConexion();
			
			String sql = "select c.*,t.nombre\r\n" + 
						"from cliente c inner join tipo_cliente t on c.idTipoCliente = t.idTpoCliente\r\n" + 
						"where nombres like  CONCAT('%',?,'%')";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Cliente objCliente = null;
			TipoCliente objTipoCliente = null;
			while(rs.next()){
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));
				
				objTipoCliente = new TipoCliente();
				objTipoCliente.setIdTipoCliente(rs.getInt(6));
				objTipoCliente.setNombre(rs.getString(7));
				
				objCliente.setTipocliente(objTipoCliente);
				
				data.add(objCliente);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return data;
	}
	
	public List<Cliente> ConsultarClienteDNI(String dni){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			con = MySqlDBConexion.getConexion();
			
			String sql = "select c.*,t.nombre\r\n" + 
						"from cliente c inner join tipo_cliente t on c.idTipoCliente = t.idTpoCliente\r\n" + 
						"where dni = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dni);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Cliente objCliente = null;
			TipoCliente objTipoCliente = null;
			while(rs.next()){
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));
				
				objTipoCliente = new TipoCliente();
				objTipoCliente.setIdTipoCliente(rs.getInt(6));
				objTipoCliente.setNombre(rs.getString(7));
				
				objCliente.setTipocliente(objTipoCliente);
				
				data.add(objCliente);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return data;
	}
	
}
