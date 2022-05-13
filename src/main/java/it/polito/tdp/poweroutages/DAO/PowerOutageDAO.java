package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutagesListByNercValue(String nercValue) {
		
		String sql = "SELECT p.id, n.value AS nerc, p.customers_affected, p.date_event_began, p.date_event_finished FROM poweroutages AS p, nerc AS n WHERE p.nerc_id = n.id AND n.value = ? ORDER BY p.date_event_began";
		List<PowerOutages> powerOutagesList = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nercValue);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				PowerOutages n = new PowerOutages(res.getInt("id"), res.getString("nerc"), res.getInt("customers_affected"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_began").toLocalDateTime());
				powerOutagesList.add(n);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return powerOutagesList;
	}
	

}
