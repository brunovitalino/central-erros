package br.com.codenation.centralerros.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf;

	public EntityManager getEntityManager() {
		if (emf==null) emf = Persistence.createEntityManagerFactory("db_mySql_server");
		return emf.createEntityManager();
	}

	/*public void closeEntityManager() {
		if (emf!=null) emf.close();
	}*/
	
}
