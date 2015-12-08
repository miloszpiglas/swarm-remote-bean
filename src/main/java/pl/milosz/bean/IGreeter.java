package pl.milosz.bean;

import java.io.Serializable;


public interface IGreeter extends Serializable {
    public String greet();
	public String change();
}