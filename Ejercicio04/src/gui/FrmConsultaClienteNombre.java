package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Cliente;
import model.ModelCliente;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class FrmConsultaClienteNombre extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JPanel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaClienteNombre frame = new FrmConsultaClienteNombre();
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
	public FrmConsultaClienteNombre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 181));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setBounds(34, 45, 81, 17);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(156, 45, 188, 20);
		contentPane.add(txtNombre);
		
		JLabel lblConsultaPorNombre = new JLabel("CONSULTA POR NOMBRE");
		lblConsultaPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsultaPorNombre.setBounds(411, 11, 209, 20);
		contentPane.add(lblConsultaPorNombre);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = txtNombre.getText().trim();
				
				ModelCliente model = new ModelCliente();
				List<Cliente> lstData = null;
				if(nombre.equals("")) {
					mensaje("No se ingreso ninguna letra");
					return;
				}else {
					lstData = model.ConsultarClienteNombre(nombre);
				}
				
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstData);
				
				String file = "reporteClienteNombre.jasper";
				
				JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);
				
				JRViewer jRViewer = new JRViewer(jasperPrint);
				
				panelReporte.removeAll();
				panelReporte.add(jRViewer);
				panelReporte.repaint();
				panelReporte.revalidate();
				
			}
		});
		btnFiltrar.setBounds(830, 39, 101, 32);
		contentPane.add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setForeground(SystemColor.inactiveCaptionBorder);
		panelReporte.setBounds(0, 82, 976, 550);
		contentPane.add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));
	}
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
