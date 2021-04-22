package org.ciberfarma.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FrmUsuario extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtFechaNacimiento;
	private JButton btnRegistrar;
	private JPasswordField txtClave;
	private JComboBox<String> cboTipo;
	private JComboBox<String> cboEstado;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JTextArea txtS;
	private JScrollPane scrollPane;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUsuario frame = new FrmUsuario();
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
	public FrmUsuario() {
		setTitle("Formulario de Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 11, 33, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(225, 11, 37, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(10, 36, 37, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(225, 36, 36, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Clave");
		lblNewLabel_4.setBounds(10, 61, 27, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fec. Nac.");
		lblNewLabel_5.setBounds(225, 61, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tipo");
		lblNewLabel_6.setBounds(10, 86, 20, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Estado");
		lblNewLabel_7.setBounds(225, 86, 33, 14);
		contentPane.add(lblNewLabel_7);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(73, 8, 111, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(282, 8, 111, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(73, 33, 111, 20);
		contentPane.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(282, 33, 111, 20);
		contentPane.add(txtUsuario);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(282, 58, 111, 20);
		contentPane.add(txtFechaNacimiento);
		
		cboTipo = new JComboBox<String>();
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione", "Administrativo", "Cliente"}));
		cboTipo.setBounds(73, 84, 111, 18);
		contentPane.add(cboTipo);
		
		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione", "Activo", "Eliminado"}));
		cboEstado.setBounds(282, 83, 111, 20);
		contentPane.add(cboEstado);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(448, 7, 89, 23);
		contentPane.add(btnRegistrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(448, 32, 89, 23);
		contentPane.add(btnActualizar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(448, 57, 89, 23);
		contentPane.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(448, 82, 89, 23);
		contentPane.add(btnEliminar);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(73, 58, 111, 17);
		contentPane.add(txtClave);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 527, 177);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(229, 111, 89, 23);
		contentPane.add(btnListar);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnListar) {
			btnListarActionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			btnBuscarActionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			btnEliminarActionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			btnActualizarActionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			btnRegistrarActionPerformed(e);
		}
	}
	
	protected void btnRegistrarActionPerformed(ActionEvent e) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = factory.createEntityManager();
		
		String nombre, apellido, usuario, clave, fechaNacimient;
		int tipo, estado;
		
		nombre = leerNombre();
		apellido = leerApellido();
		usuario = leerUsuario();
		clave = leerClave();
		fechaNacimient = leerFecha();
		tipo = leerTipo();
		estado = leerEstado();
		
		
		// Crear un objeto de Usuario a grabar
		Usuario u = new Usuario();
		// u.setCodigo(10); auto_increment
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setFnacim(fechaNacimient);
		u.setTipo(tipo);
		u.setEstado(estado);
		
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		
		aviso("Usuario registrado correctamente");
		em.close();
	}
	
	protected void btnActualizarActionPerformed(ActionEvent e) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = factory.createEntityManager();
		
		String nombre, apellido, usuario, clave, fechaNacimient;
		int codigo, tipo, estado;
		
		codigo = leerCodigo();
		nombre = leerNombre();
		apellido = leerApellido();
		usuario = leerUsuario();
		clave = leerClave();
		fechaNacimient = leerFecha();
		tipo = leerTipo();
		estado = leerEstado();
		
		
		// Crear un objeto de Usuario a grabar
		Usuario u = new Usuario();
		u.setCodigo(codigo);
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setFnacim(fechaNacimient);
		u.setTipo(tipo);
		u.setEstado(estado);
		
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		
		aviso("Usuario Actualizado correctamente");
		em.close();
	}
	protected void btnEliminarActionPerformed(ActionEvent e) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = factory.createEntityManager();
		
		int codigo = leerCodigo();
		
		Usuario usu = em.find(Usuario.class, codigo);
		System.out.println(usu.getNombre() +"");
		em.getTransaction().begin();
		em.remove(usu);
		em.getTransaction().commit();
		
		aviso("Usuario Eliminado correctamente");
		em.close();
		
	}
	
	protected void btnBuscarActionPerformed(ActionEvent e) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = factory.createEntityManager();
		
		int codigo = leerCodigo();
		
		Usuario usu = em.find(Usuario.class, codigo);
		if (usu != null) {
			txtNombre.setText(usu.getNombre());
			txtApellido.setText(usu.getApellido());
			txtUsuario.setText(usu.getUsuario());
			txtClave.setText(usu.getClave());
			txtFechaNacimiento.setText(usu.getFnacim());
			cboTipo.setSelectedIndex(usu.getTipo());
			cboEstado.setSelectedIndex(usu.getEstado());
			
		} else {
			aviso("Usuario con codigo  " + codigo + "no existe!!");
		}
		
	}
	
	protected void btnListarActionPerformed(ActionEvent e) {
		// obtener un listado de los usuarios
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = factory.createEntityManager();
		
		// TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		// List<Usuario> lista = consulta.getResultList();
		
		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAllWithType", Usuario.class);
		consulta.setParameter("xtipo", 1);
		List<Usuario> lista = consulta.getResultList();

		em.close();
		
		// pasar el listado al txt...
		for (Usuario u : lista) {
			txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" + u.getApellido() + "\n");
		}
	}
	
	private void aviso(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Aviso del Sistema", JOptionPane.WARNING_MESSAGE);
	}

	// Entradas
	private int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}
	
	private String leerNombre() {
		return txtNombre.getText();
	}
	
	private String leerApellido() {
		return txtApellido.getText();
	}
	
	private String leerUsuario() {
		return txtUsuario.getText();
	}
	
	private String leerClave() {
		return String.valueOf(txtClave.getPassword());
	}
	
	private String leerFecha() {
		return txtFechaNacimiento.getText();
	}
	
	private int leerTipo() {
		return cboTipo.getSelectedIndex();
	}
	
	private int leerEstado() {
		return cboEstado.getSelectedIndex();
	}

}
