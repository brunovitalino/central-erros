package br.com.codenation.centralerros.utils;

import java.util.List;
import java.util.Random;

import br.com.codenation.centralerros.dao.UsuarioDAO;

public class TokenUtil {
	
	public String getNewToken() {
		List<String> tokens = new UsuarioDAO().allTokens();
		String token;
		if (tokens.isEmpty()) token = gerarNewToken();
		else do token = gerarNewToken(); while(tokens.contains(token)); //se repetido, gere outro token
		return token;
	}

	private static String gerarNewToken() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		random.ints(33, 127).limit(20).forEach(e -> sb.append((char) e));
		return sb.toString();
	}

}
