package entidad;

import java.util.Date;

public class Cliente {

	private int idCliente;
	private String nombres;
	private String apellidos;
	private String dni;
	private Date fechaNacimiento;
	private TipoCliente tipocliente;
	private String tipoclientenombre;
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public TipoCliente getTipocliente() {
		return tipocliente;
	}
	public void setTipocliente(TipoCliente tipocliente) {
		this.tipocliente = tipocliente;
	}
	public String getTipoclientenombre() {
		tipoclientenombre = tipocliente.getNombre();
		return tipoclientenombre;
	}
	public void setTipoclientenombre(String tipoclientenombre) {
		this.tipoclientenombre = tipoclientenombre;
	}
	
	
	
}
