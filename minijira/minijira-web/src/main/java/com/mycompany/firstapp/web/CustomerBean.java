package com.mycompany.firstapp.web;

import com.mycompany.firstapp.ejbapi.dto.CustomerDto;
import java.util.List;
import javax.annotation.PostConstruct;


public class CustomerBean {

	private CustomerDto tmpCustomer;
	private List<CustomerDto> list;
	private SampleBean sample;

	public CustomerBean() {
	}
    
	@PostConstruct
	public void init() {	
		tmpCustomer = new CustomerDto();
		list = sample.getCustomers();
	}
	
	public void setSample(SampleBean s) {
		sample = s;
	}
	
	
	public void setTmpCustomer(CustomerDto s) {
		tmpCustomer = s;
	}
	
	public CustomerDto getTmpCustomer() {
		return tmpCustomer;
	}

	
	public void remove(int id) {
		sample.getDatabaseInterface().removeCustomer(id);
		list = sample.getCustomers();
	}
	
	
	
	public void saveChanges() {
		sample.getDatabaseInterface().updateCustomer(tmpCustomer);
		list = sample.getCustomers();
	}
	
	public void saveCustomer() {
		sample.getDatabaseInterface().newCustomer(tmpCustomer);
		list = sample.getCustomers();
	}

	
	public String getUrlEdit(int id) {
		int i = 0;
		tmpCustomer.setId(id);
		for (CustomerDto sk : list)
			if (sk.getId() == id) {
				tmpCustomer = list.get(i);
				break;
			} else ++i;
		return "edit_customer.jsf";
	}
	
	public String getUrlAdd() {
		tmpCustomer = new CustomerDto();
		return "add_customer.jsf";
	}
}
