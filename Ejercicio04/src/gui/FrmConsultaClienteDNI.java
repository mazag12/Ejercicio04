package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ModelCliente;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import entidad.Cliente;
import reporte.GeneradorReporte;
import util.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmConsultaClienteDNI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDNI;
	private JPanel controlPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaClienteDNI frame = new FrmConsultaClienteDNI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmConsultaClienteDNI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 548);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaDeCliente = new JLabel("CONSULTA DE CLIENTE POR DNI");
		lblConsultaDeCliente.setFont(new Font("Arial", Font.BOLD, 18));
		lblConsultaDeCliente.setBounds(287, 24, 382, 14);
		contentPane.add(lblConsultaDeCliente);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(44, 67, 46, 14);
		contentPane.add(lblDni);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(126, 64, 232, 20);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		JButton btnFiltro = new JButton("FILTRO");
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dni = txtDNI.getText().trim();
				
				ModelCliente model = new ModelCliente();
				
				List<Cliente> lstData = null;
				if(!dni.matches(Validaciones.DNI))
				{
					mensaje("El DNI debe contener 8 digitos");
					return;
				}else {
					lstData = model.ConsultarClienteDNI(dni);
				}
				
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstData);
				
				String file = "reporteClienteNombre.jasper";
				JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);
				
				JRViewer jrViewer = new JRViewer(jasperPrint);
				
				controlPanel.removeAll();
				controlPanel.add(jrViewer);
				controlPanel.repaint();
				controlPanel.revalidate();
				
			}
		});
		btnFiltro.setBounds(747, 57, 96, 34);
		contentPane.add(btnFiltro);
		
		controlPanel = new JPanel();
		controlPanel.setBounds(10, 104, 882, 395);
		contentPane.add(controlPanel);
		controlPanel.setLayout(new BorderLayout(0, 0));
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	
}
