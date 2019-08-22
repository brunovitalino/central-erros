package br.com.codenation.centralerros.model.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Wrapper<T> {

	@XmlElement
	private List<T> items = new ArrayList<T>();
	
	public Wrapper(List<T> items) {
		this.items = items;
	}

	@XmlAnyElement(lax=true)
	public List<T> getItems() {
	    return items;
	}
}
