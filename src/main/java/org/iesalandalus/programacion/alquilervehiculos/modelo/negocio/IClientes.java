//Joaquin Francisco Sanchez Capel
package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public interface IClientes {

	List<Cliente> get();

	

	void insertar(Cliente cliente) throws OperationNotSupportedException;

	Cliente buscar(Cliente clientes);

	void borrar(Cliente cliente) throws OperationNotSupportedException;

	void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;

}