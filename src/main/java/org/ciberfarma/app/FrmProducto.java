package org.ciberfarma.app;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.ciberfarma.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmProducto extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblPrecio;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtId;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox<String> cboCategoria;
	private JComboBox<String> cboEstado;
	private JComboBox<String> cboProveedor;
	private JButton btnRegistrar;
	private JButton btnBuscar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTable tbProducto;
	private JScrollPane scrollPane;
	private DefaultTableModel tb;

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
					FrmProducto frame = new FrmProducto();
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
	public FrmProducto() {
		setTitle(".:. Mantenimiento de Productos .:.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setBounds(10, 11, 18, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Descripcion: ");
		lblNewLabel_1.setBounds(10, 36, 61, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Stock: ");
		lblNewLabel_2.setBounds(10, 61, 33, 14);
		contentPane.add(lblNewLabel_2);

		lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(10, 86, 36, 14);
		contentPane.add(lblPrecio);

		lblNewLabel_3 = new JLabel("Categoría: ");
		lblNewLabel_3.setBounds(10, 111, 54, 14);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Estado: ");
		lblNewLabel_4.setBounds(10, 136, 40, 14);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Proveedor");
		lblNewLabel_5.setBounds(10, 161, 50, 14);
		contentPane.add(lblNewLabel_5);

		txtId = new JTextField();
		txtId.setBounds(80, 8, 276, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(80, 33, 276, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtStock = new JTextField();
		txtStock.setBounds(80, 58, 276, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(80, 83, 276, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);

		cboCategoria = new JComboBox<String>();
		cboCategoria.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Seleccione", "Pastillas", "Jarabe", "Cremas", "Tocador", "Cuidado", "Otros" }));
		cboCategoria.setBounds(80, 107, 276, 22);
		contentPane.add(cboCategoria);

		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione", "Activo", "Eliminado" }));
		cboEstado.setBounds(80, 132, 276, 22);
		contentPane.add(cboEstado);

		cboProveedor = new JComboBox<String>();
		cboProveedor.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Seleccione", "Pharmalab", "Minsa", "La Naturista" }));
		cboProveedor.setBounds(80, 157, 276, 22);
		contentPane.add(cboProveedor);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(458, 21, 150, 35);
		contentPane.add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(458, 57, 150, 35);
		contentPane.add(btnBuscar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(458, 93, 150, 35);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(458, 129, 150, 35);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 645, 267);
		contentPane.add(scrollPane);

		tb = new DefaultTableModel();
		tb.addColumn("ID");
		tb.addColumn("Descripción");
		tb.addColumn("Stock");
		tb.addColumn("Precio");
		tb.addColumn("ID Categoría");
		tb.addColumn("Estado");
		tb.addColumn("ID Proveedor");

		tbProducto = new JTable();
		tbProducto.addMouseListener(this);
		tbProducto.setFillsViewportHeight(true);
		tbProducto.setModel(tb);
		scrollPane.setViewportView(tbProducto);

		listarProductos();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			btnEliminarActionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			btnActualizarActionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			btnRegistrarActionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			btnBuscarActionPerformed(e);
		}
	}

	protected void listarProductos() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		TypedQuery<Producto> consulta = em.createNamedQuery("Producto.findAll", Producto.class);
		List<Producto> lista = consulta.getResultList();
		em.close();

		tb.setRowCount(0);

		for (Producto prod : lista) {
			Object[] fila = { prod.getId(), prod.getDescripcion(), prod.getStock(), prod.getPrecio(),
					prod.getIdCategoria(), prod.getEstado(), prod.getIdProveedor() };

			tb.addRow(fila);
		}
	}

	protected void btnRegistrarActionPerformed(ActionEvent e) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		String id, descripcion;
		int stock, categoria, estado, proveedor;
		double precio;

		id = leerId();
		descripcion = leerDescripcion();
		stock = leerStock();
		precio = leerPrecio();
		categoria = leerCategoria();
		estado = leerEstado();
		proveedor = leerProveedor();

		if (descripcion == null || stock == -1 || precio == -1 || categoria == 0 || estado == 0 || proveedor == 0) {
			aviso("Corrija los datos para poder actualizar el producto", JOptionPane.WARNING_MESSAGE);
		} else {

			Producto prod = new Producto(id, descripcion, stock, precio, categoria, estado, proveedor);
			try {
				em.getTransaction().begin();
				em.persist(prod);
				em.getTransaction().commit();

				aviso("Producto registrado correctamente.", JOptionPane.INFORMATION_MESSAGE);

				listarProductos();
				limpiar();
			} catch (RollbackException ex) {
				em.getTransaction().rollback();
				aviso("Error al registrar: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
			} finally {
				em.close();
			}
		}

	}

	protected void btnBuscarActionPerformed(ActionEvent e) {

		String id = leerId();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		try {

			em.getTransaction().begin();
			Producto prod = em.find(Producto.class, id);
			em.getTransaction().commit();

			if (prod == null) {
				aviso("No existe usuario con id: " + id, JOptionPane.WARNING_MESSAGE);
				limpiar();
			} else {
				txtDescripcion.setText(prod.getDescripcion());
				txtStock.setText(prod.getStock() + "");
				txtPrecio.setText(prod.getPrecio() + "");
				cboCategoria.setSelectedIndex(prod.getIdCategoria());
				cboEstado.setSelectedIndex(prod.getEstado());
				cboProveedor.setSelectedIndex(prod.getIdProveedor());
			}

		} catch (RollbackException ex) {
			em.getTransaction().rollback();
			aviso("Error al buscar: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		} finally {
			em.close();
		}

	}

	protected void btnActualizarActionPerformed(ActionEvent e) {
		String id = leerId();

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		try {

			em.getTransaction().begin();
			Producto prod = em.find(Producto.class, id);

			if (prod == null) {
				aviso("No existe usuario con id: " + id, JOptionPane.WARNING_MESSAGE);
			} else {

				String descipcion = leerDescripcion();
				int stock = leerStock();
				double precio = leerPrecio();
				int categoria = leerCategoria();
				int estado = leerEstado();
				int proveedor = leerProveedor();

				if (descipcion == null || stock == -1 || precio == -1 || categoria == 0 || estado == 0
						|| proveedor == 0) {
					aviso("Corrija los datos para poder actualizar el producto", JOptionPane.WARNING_MESSAGE);
				} else {
					prod.setDescripcion(leerDescripcion());
					prod.setStock(leerStock());
					prod.setPrecio(leerPrecio());
					prod.setIdCategoria(leerCategoria());
					prod.setEstado(leerEstado());
					prod.setIdProveedor(leerProveedor());
					em.merge(prod);
					aviso("Producto Actualizado Correctamente", JOptionPane.INFORMATION_MESSAGE);
					limpiar();

				}

			}

			em.getTransaction().commit();

			listarProductos();

		} catch (RollbackException ex) {
			em.getTransaction().rollback();
			aviso("Error al buscar: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
		} finally {
			em.close();
		}
	}

	protected void btnEliminarActionPerformed(ActionEvent e) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		String id = leerId();

		em.getTransaction().begin();
		Producto prod = em.find(Producto.class, id);
		if (prod == null) {
			aviso("No existe producto con id: " + id, JOptionPane.WARNING_MESSAGE);
		} else {
			em.remove(prod);
			aviso("Producto eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
		}
		em.getTransaction().commit();

		limpiar();
		listarProductos();

	}

	protected void tbProductoMouseClicked(MouseEvent e) {
		int fila = tbProducto.getSelectedRow();

		txtId.setText(tbProducto.getValueAt(fila, 0).toString());
		txtDescripcion.setText(tbProducto.getValueAt(fila, 1).toString());
		txtStock.setText(tbProducto.getValueAt(fila, 2).toString());
		txtPrecio.setText(tbProducto.getValueAt(fila, 3).toString());
		cboCategoria.setSelectedIndex((int) tbProducto.getValueAt(fila, 4));
		cboEstado.setSelectedIndex((int) tbProducto.getValueAt(fila, 5));
		cboProveedor.setSelectedIndex((int) tbProducto.getValueAt(fila, 6));
	}

	protected void aviso(String mensaje, int tipoAviso) {
		JOptionPane.showMessageDialog(this, mensaje, "Aviso del sistema", tipoAviso);
	}

	protected void limpiar() {
		txtId.setText("");
		txtDescripcion.setText("");
		txtStock.setText("");
		txtPrecio.setText("");
		cboCategoria.setSelectedIndex(0);
		cboEstado.setSelectedIndex(0);
		cboProveedor.setSelectedIndex(0);
	}

	// Entradas
	protected String leerId() {
		return txtId.getText();
	}

	protected String leerDescripcion() {
		if (txtDescripcion.getText().matches("[a-zA-Z\s]{5,25}")) {
			return txtDescripcion.getText();
		} else {
			aviso("El campo descripción de tener mínimo 5 y máximo 25 caracteres.", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	protected int leerStock() {
		if (txtStock.getText().matches("[0-9]{1,}")) {
			return Integer.parseInt(txtStock.getText());
		} else {
			aviso("El campo stock debe ser un número entero", JOptionPane.ERROR_MESSAGE);
			return -1;
		}

	}

	protected double leerPrecio() {
		if (txtPrecio.getText().matches("[0-9]*\\.?[0-9]{1,2}$")) {
			return Double.parseDouble(txtPrecio.getText());
		} else {
			aviso("El campo precio debe tener el siguiente formato 0.00 y debe ser mayor que 0",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}

	}

	protected int leerCategoria() {
		if (cboCategoria.getSelectedIndex() == 0)
			aviso("Debe seleccionar una categoria", JOptionPane.ERROR_MESSAGE);
		return (cboCategoria.getSelectedIndex());

	}

	protected int leerEstado() {
		if (cboEstado.getSelectedIndex() == 0)
			aviso("Debe seleccionar una estado", JOptionPane.ERROR_MESSAGE);
		return cboEstado.getSelectedIndex();
	}

	protected int leerProveedor() {
		if (cboProveedor.getSelectedIndex() == 0)
			aviso("Debe seleccionar una categoria", JOptionPane.ERROR_MESSAGE);
		return cboProveedor.getSelectedIndex();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tbProducto) {
			tbProductoMouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
