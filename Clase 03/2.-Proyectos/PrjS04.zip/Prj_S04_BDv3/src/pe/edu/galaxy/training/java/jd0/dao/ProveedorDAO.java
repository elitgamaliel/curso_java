package pe.edu.galaxy.training.java.jd0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.galaxy.training.java.jd0.beans.Proveedor;
import pe.edu.galaxy.training.java.jd0.util.BD;

public class ProveedorDAO {

	public List<Proveedor> listar() {

		try {
			Connection cn = BD.getConnection();
			String sql = "SELECT ID_PROVEEDOR,RAZON_SOCIAL,RUC FROM PROVEEDOR WHERE ESTADO='1'";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Proveedor> proveedores = new ArrayList<Proveedor>();

			while (rs.next()) {

				Proveedor proveedor = new Proveedor();
				proveedor.setCodigo(rs.getInt("ID_PROVEEDOR"));
				proveedor.setRazonSocial(rs.getString("RAZON_SOCIAL"));
				proveedor.setRuc(rs.getString("RUC"));
				proveedores.add(proveedor);
			}

			rs.close();
			ps.close();
			cn.close();
			return proveedores;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean insertar(Proveedor proveedor) {
		try {

			Connection cn = BD.getConnection();
			String sql = "INSERT INTO PROVEEDOR(ID_PROVEEDOR,RAZON_SOCIAL,RUC) VALUES(SEQ_PROVEEDOR.NEXTVAL,?,?)";

			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, proveedor.getRazonSocial());
			ps.setString(2, proveedor.getRuc());

			ps.executeUpdate();

			ps.close();
			cn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean actualizar(Proveedor proveedor) {
		try {

			Connection cn = BD.getConnection();
			String sql = "UPDATE PROVEEDOR SET  RAZON_SOCIAL=?,RUC=? WHERE ID_PROVEEDOR=?";

			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, proveedor.getRazonSocial());
			ps.setString(2, proveedor.getRuc());
			ps.setLong(3, proveedor.getCodigo());
			ps.executeUpdate();

			ps.close();
			cn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean eliminar(Integer codigo) {
		try {

			Connection cn = BD.getConnection();
			String sql = "UPDATE PROVEEDOR SET ESTADO='0' WHERE ID_PROVEEDOR=?";

			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setLong(1, codigo);
			ps.executeUpdate();

			ps.close();
			cn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
