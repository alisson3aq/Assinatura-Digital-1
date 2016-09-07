package assinatura_digital;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Scanner;

public class AssinaturaDigital {

	public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Scanner ler = new Scanner(System.in);

		// Remetente Gera Assinatura Digital para uma Mensagem
		Remetente remetenteAssiDig = new Remetente();
		String mensagem = ler.nextLine();
		byte[] assinatura = remetenteAssiDig.geraAssinatura(mensagem);
		
		// Guarda Chave Publica para ser Enviada ao Destinatario
		PublicKey pubKey = remetenteAssiDig.getChavePublica();

		// Destinatario recebe dados correto
		Destinatario destinatarioAssiDig = new Destinatario();
		destinatarioAssiDig.recebeMensagem(pubKey, mensagem, assinatura);

		// Destinatario recebe mensagem alterada
		String msgAlterada = ler.nextLine();
		destinatarioAssiDig.recebeMensagem(pubKey, msgAlterada, assinatura);
		
		ler.nextLine();
		outraAssinaturaEChave(remetenteAssiDig, destinatarioAssiDig, pubKey, mensagem, assinatura, ler);

	}

	public static void outraAssinaturaEChave(Remetente remetenteAssiDig, Destinatario destinatarioAssiDig, 
			PublicKey pubKey, String mensagem, byte[] assinatura, Scanner sc) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		
		// Criando outra Assinatura
		String mensagem2 = "Exemplo de outra mensagem.";
		byte[] assinatura2 = remetenteAssiDig.geraAssinatura(mensagem2);
		// Guarda Chave Pública para ser Enviada ao Destinatário
		PublicKey pubKey2 = remetenteAssiDig.getChavePublica();
		
		// Destinatario recebe outra assinatura
		destinatarioAssiDig.recebeMensagem(pubKey, mensagem, assinatura2);
		
		sc.nextLine();
		// Destinatario recebe outra chave publica
		destinatarioAssiDig.recebeMensagem(pubKey2, mensagem, assinatura);

	}

}