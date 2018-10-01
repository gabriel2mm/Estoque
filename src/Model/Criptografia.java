package Model;

import Constantes.Severidade;
import Entity.Logs;
import Model.Dao.LogsDaoImpl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public class Criptografia {

    public static String Cripto(String pass){
        String senha = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(pass.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
             senha = hexString.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
               LogsDaoImpl.getInstance().CreateLog("Não foi possível Criptografar Senha", new Date(), Severidade.EXCECAO);
        }
        return senha;
    }

}
