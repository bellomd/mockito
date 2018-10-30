package com.etcbase.mockito;

import java.util.Random;

public class EtcBase {
	
	private Long id;
	private String branchName;
	private String branchAddress;

	public EtcBase() {
		this.id = new Random().nextLong();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
}
