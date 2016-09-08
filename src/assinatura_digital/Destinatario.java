package assinatura_digital;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class Destinatario {

	public void recebeMensagem(PublicKey pubKey, String mensagem, byte[] assinatura)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		Signature clientSig = Signature.getInstance("DSA");
		clientSig.initVerify(pubKey);
		clientSig.update(mensagem.getBytes());

		if (clientSig.verify(assinatura)) {
			// Mensagem corretamente assinada
			String linha = "";
			linha += "**A Mensagem recebida foi assinada corretamente.** \n"
					+ "Mensagem : " + mensagem.toString() + "\n"
					+ "Assinatura: " + assinatura.toString();
			System.out.println(linha);
		} else {
			// Mensagem nao pode ser validada
			String linha = "";
			linha += "**A Mensagem recebida NAO pode ser validada.** \n"
					+ "Mensagem : " + mensagem.toString() + "\n"
					+ "Assinatura: " + assinatura.toString();
			System.out.println(linha);
		}
	}

}