package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ModelCliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;
import entidad.Cliente;

public class FrmClienteConsultaNombre extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private Panel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmClienteConsultaNombre frame = new FrmClienteConsultaNombre();
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
	public FrmClienteConsultaNombre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaPorNombre = new JLabel("CONSULTA POR NOMBRE");
		lblConsultaPorNombre.setBounds(230, 24, 169, 14);
		contentPane.add(lblConsultaPorNombre);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(66, 52, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(159, 49, 188, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = txtNombre.getText().trim();
				
				ModelCliente model = new ModelCliente();
				List<Cliente> lstData = null;
				if(nombre.equals("")) {
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
		btnFiltrar.setBounds(398, 49, 89, 23);
		contentPane.add(btnFiltrar);
		
		panelReporte = new Panel();
		panelReporte.setBounds(30, 85, 539, 272);
		contentPane.add(panelReporte);
	}
}
