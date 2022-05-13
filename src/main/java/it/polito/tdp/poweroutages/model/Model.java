package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutages> sol = new ArrayList<PowerOutages>();
	int personeCoinvolte = 0;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public int getPersoneCoinvolte() {
		return this.personeCoinvolte;
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutages> getPowerOutagesListByNerc(String nerc) {
		return podao.getPowerOutagesListByNercValue(nerc);
	}
	
	public List<PowerOutages> trovaWorstCase(String nerc, int maxAnni, int maxOre) {
		List<PowerOutages> parziale = new ArrayList<PowerOutages>();
		
		this.cerca(parziale, this.getPowerOutagesListByNerc(nerc), maxAnni, maxOre);
		return sol;
	}

	private void cerca(List<PowerOutages> parziale, List<PowerOutages> lista, int maxAnni, int maxOre) {
		if(calcolaPersoneCoinvolte(parziale)>this.personeCoinvolte) {
			personeCoinvolte = calcolaPersoneCoinvolte(parziale);
			this.sol = new ArrayList<PowerOutages>(parziale);
		}
		for(PowerOutages po : lista) {
			if(aggiuntaValida(po, parziale, maxAnni, maxOre) && !parziale.contains(po)) {
				parziale.add(po);
				this.cerca(parziale, lista, maxAnni, maxOre);
				parziale.remove(po);
			}
		}
		
	}

	private boolean aggiuntaValida(PowerOutages po, List<PowerOutages> parziale, int maxAnni, int maxOre) {
		int annoMin = po.getDateEventBegan().getYear(), annoMax = po.getDateEventFinished().getYear();
		int ore = 0;
		for(PowerOutages p : parziale) {
			if(p.getDateEventBegan().getYear()<annoMin)
				annoMin = p.getDateEventBegan().getYear();
			if(p.getDateEventFinished().getYear()>annoMax)
				annoMax = p.getDateEventFinished().getYear();
		}
		if((annoMax-annoMin)>maxAnni)
			return false;
		
		for(PowerOutages p : parziale) {
			int inizio = p.getDateEventBegan().getHour() + (p.getDateEventBegan().getMinute()/60)+(p.getDateEventBegan().getSecond()/3600);
			int fine = p.getDateEventFinished().getHour() + (p.getDateEventFinished().getMinute()/60)+(p.getDateEventFinished().getSecond()/3600);
			int durata = fine - inizio + ((p.getDateEventFinished().getDayOfMonth()-p.getDateEventBegan().getDayOfMonth())*60);
			ore += durata;
		}
		
		if(ore>maxOre)
			return false;
		
		return true;
	}

	private int calcolaPersoneCoinvolte(List<PowerOutages> parziale) {
		int totPers = 0;
		for(PowerOutages po : parziale) {
			totPers+=po.getCustomerAffected();
		}
		return totPers;
	}

}
