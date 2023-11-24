package rmi_project_3.Server;

import rmi_project_3.ILogin;
import rmi_project_3.IMath;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try{
            // Create a registry
            Registry registry = LocateRegistry.createRegistry(1099);
            // Create a skeleton
            IMath iMathSkeleton = new IMathImpl();

            ILogin iLoginSkeleton = new ILoginImpl();

            // registry object
            registry.rebind("Calculator", iMathSkeleton);
            registry.rebind("Login", iLoginSkeleton);
            while (true){
                System.out.println("Server is running");
                Thread.sleep(5000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
