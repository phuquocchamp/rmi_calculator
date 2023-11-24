package rmi_project_3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public interface ILogin extends Remote {
    boolean login(String username, String password) throws RemoteException, NoSuchAlgorithmException;
}
