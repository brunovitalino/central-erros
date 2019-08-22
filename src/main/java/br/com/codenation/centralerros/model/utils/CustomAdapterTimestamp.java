package br.com.codenation.centralerros.model.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CustomAdapterTimestamp extends XmlAdapter<String, Timestamp> {

	@Override
	public Timestamp unmarshal(String v) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date date = dateFormat.parse(v);
	    return new Timestamp(date.getTime());
	}

	@Override
	public String marshal(Timestamp v) throws Exception {
		return v.toString();
	}


}
