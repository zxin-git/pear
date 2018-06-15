package com.zxin.apache.http.rs;

import java.util.Calendar;

public class CustomerServiceImpl implements CustomerService {

	public Customer findCustomerById(String id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(id);
		customer.setBirthday(Calendar.getInstance().getTime());
		return customer;
	}

	public Customer findCustomerByName(String name) {
		Customer customer = new Customer();
		customer.setId(name);
		customer.setName(name);
		customer.setBirthday(Calendar.getInstance().getTime());
		return customer;
	}
}