package com.i�ce.control;

import java.util.Date;
import java.util.List;

import com.i�ce.entity.Insulin;

public interface IInsulinOperations {

	public boolean addInsulin(Insulin insulin);

	public List<Insulin> listInsulin();
	
	public List<Insulin> getInsulins(Date date);

}
