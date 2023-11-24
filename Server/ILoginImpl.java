package rmi_project_3.Server;

import rmi_project_3.ILogin;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ILoginImpl extends UnicastRemoteObject implements ILogin, Serializable {

    protected ILoginImpl() throws RemoteException {
    }

    private static String hashCode(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());

        // Convert byte array to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }




    @Override
    public boolean login(String username, String password) throws RemoteException, NoSuchAlgorithmException {
        String hostUsername = hashCode("phuquocchamp");
        String hostPassword = hashCode("123456");

        if(username.equalsIgnoreCase(hostUsername) && password.equalsIgnoreCase(hostPassword)){
            return true;
        }
        return false;
    }
}
