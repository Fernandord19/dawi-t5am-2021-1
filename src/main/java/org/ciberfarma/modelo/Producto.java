package org.ciberfarma.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_productos")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto {

	@Id
	@Column(name = "id_prod")
	private String id;
	
	@Column(name = "des_prod")
	private String descripcion;
	
	@Column(name = "stk_prod")
	private int stock;
	
	@Column(name = "pre_prod")
	private double precio;
	
	@Column(name = "idcategoria")
	private int idCategoria;
	
	@Column(name = "est_prod")
	private int estado;
	
	@Column(name = "idprovedor")
	private int idProveedor;
	
	public Producto(String id, String descripcion, int stock, double precio, int idCategoria, int estado,
			int idProveedor) {
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.idCategoria = idCategoria;
		this.estado = estado;
		this.idProveedor = idProveedor;
	}

	public Producto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
}
