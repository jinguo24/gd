package com.simple.model.api;

import java.io.Serializable;
import java.util.List;

public class AllKaiKe implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<KaiKeHeJi> kaike;

	public List<KaiKeHeJi> getKaike() {
		return kaike;
	}

	public void setKaike(List<KaiKeHeJi> kaike) {
		this.kaike = kaike;
	}
}
