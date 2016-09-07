package assinatura_digital;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Scanner;

public class AssinaturaDigital {

	public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Scanner sc = new Scanner(System.in);

		// Remetente Gera Assinatura Digital para uma Mensagem
		RemetenteAssiDig remetenteAssiDig = new RemetenteAssiDig();
		String mensagem = sc.nextLine();
		byte[] assinatura = remetenteAssiDig.geraAssinatura(mensagem);
		// Guarda Chave Pública para ser Enviada ao Destinatário
		PublicKey pubKey = remetenteAssiDig.getPubKey();

		// Destinatário recebe dados correto
		DestinatarioAssiDig destinatarioAssiDig = new DestinatarioAssiDig();
		destinatarioAssiDig.recebeMensagem(pubKey, mensagem, assinatura);

		// Destinatário recebe mensagem alterada
		String msgAlterada = sc.nextLine();
		destinatarioAssiDig.recebeMensagem(pubKey, msgAlterada, assinatura);
		
		sc.nextLine();
		outraAssinaturaEChave(remetenteAssiDig, destinatarioAssiDig, pubKey, mensagem, assinatura, sc);

	}

	public static void outraAssinaturaEChave(RemetenteAssiDig remetenteAssiDig, DestinatarioAssiDig destinatarioAssiDig, 
			PublicKey pubKey, String mensagem, byte[] assinatura, Scanner sc) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		
		// Criando outra Assinatura
		String mensagem2 = "Exemplo de outra mensagem.";
		byte[] assinatura2 = remetenteAssiDig.geraAssinatura(mensagem2);
		// Guarda Chave Pública para ser Enviada ao Destinatário
		PublicKey pubKey2 = remetenteAssiDig.getPubKey();
		
		// Destinatário recebe outra assinatura
		destinatarioAssiDig.recebeMensagem(pubKey, mensagem, assinatura2);
		
		sc.nextLine();
		// Destinatário recebe outra chave pública
		destinatarioAssiDig.recebeMensagem(pubKey2, mensagem, assinatura);

	}

}