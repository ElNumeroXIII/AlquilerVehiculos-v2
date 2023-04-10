//Joaquin Francisco Sanchez Capel
package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

public class ModeloCascada extends Modelo {
	
	public ModeloCascada (FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));

	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getVehiculos().insertar(Vehiculo.copiar(vehiculo));

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (getClientes().buscar(alquiler.getCliente()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (getVehiculos().buscar(alquiler.getVehiculo()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehículo del alquiler.");
		}
		
		Cliente clienteAlquiler = getClientes().buscar(alquiler.getCliente());
		Vehiculo vehiculoAlquiler = getVehiculos().buscar(alquiler.getVehiculo());
		LocalDate dateAlquiler = alquiler.getFechaAlquiler();
		Alquiler alquilerNuevo = new Alquiler(clienteAlquiler, vehiculoAlquiler, dateAlquiler);
		
		getAlquileres().insertar(alquilerNuevo);

	}

	@Override
	public Cliente buscar(Cliente cliente) {
		return new Cliente(getClientes().buscar(cliente));
		
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		return Vehiculo.copiar(getVehiculos().buscar(vehiculo));
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(getAlquileres().buscar(alquiler));
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		getClientes().modificar(cliente, nombre, telefono);
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(cliente, fechaDevolucion);

	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(vehiculo, fechaDevolucion);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for(Alquiler alquiler:getAlquileres().get(cliente))
			getAlquileres().borrar(alquiler);
		getClientes().borrar(cliente);
	}
	

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for(Alquiler alquiler:getAlquileres().get(vehiculo))
			getAlquileres().borrar(alquiler);
		getVehiculos().borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
			getAlquileres().borrar(alquiler);
	}

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> listaADevolver = new ArrayList<>();
		for(Cliente cliente: getClientes().get())
			listaADevolver.add(new Cliente(cliente));
		
		return listaADevolver;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		List<Vehiculo> listaADevolver = new ArrayList<>();
		for(Vehiculo vehiculo: getVehiculos().get())
			listaADevolver.add(Vehiculo.copiar(vehiculo));
		return listaADevolver;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		List<Alquiler> listaADevolver = new ArrayList<>();
		for(Alquiler alquiler: getAlquileres().get())
			listaADevolver.add(new Alquiler(alquiler));
		
		return listaADevolver;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		List<Alquiler> listaADevolver = new ArrayList<>();
		for(Alquiler alquiler: getAlquileres().get(cliente))
			listaADevolver.add(new Alquiler(alquiler));
		
		return listaADevolver;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {
		List<Alquiler> listaADevolver = new ArrayList<>();
		for(Alquiler alquiler: getAlquileres().get(vehiculo))
			listaADevolver.add(new Alquiler(alquiler));
		
		return listaADevolver;
	}

}
