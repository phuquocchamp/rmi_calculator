package rmi_project_3.Client;



import rmi_project_3.ILogin;
import rmi_project_3.IMath;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class RMIClient {
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
    public static void main(String[] args) {

        try(Scanner inp = new Scanner(System.in)){
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            IMath iMathStub = (IMath) registry.lookup("Calculator");
            ILogin iLoginStub = (ILogin) registry.lookup("Login");
            boolean checkAuth = true;
            while(checkAuth){

                System.out.println("1. Login into program");
                System.out.println("2. Exit into program");
                System.out.print("Enter your choice: ");
                int authOption = Integer.parseInt(inp.nextLine());
                if(authOption == 1){
                    System.out.print("Enter your username : ");
                    String username = inp.nextLine();
                    System.out.print("Enter your password : ");
                    String password = inp.nextLine();

                    if(iLoginStub.login(hashCode(username), hashCode(password))){
                        int option;
                        boolean check = true;
                        while (check){
                            System.out.println("1. Cal sum of two number");
                            System.out.println("2. Cal subtract of two number");
                            System.out.println("3. Cal multiple of two number");
                            System.out.println("4. Cal divide of two number");
                            System.out.println("5. Exit");
                            System.out.print("Enter your choice: ");
                            option = Integer.parseInt(inp.nextLine());
                            System.out.print("Enter number A: ");
                            double a = Double.parseDouble(inp.nextLine());
                            System.out.print("Enter number B: ");
                            double b = Double.parseDouble(inp.nextLine());
                            switch (option){
                                case 1:
                                    System.out.println("Sum of A and B is :" + iMathStub.add(a, b));
                                    break;
                                case 2:
                                    System.out.println("Subtract of A and B is :" + iMathStub.sub(a, b));
                                    break;
                                case 3:
                                    System.out.println("Multiple of A and B is :" + iMathStub.mul(a, b));
                                    break;
                                case 4:
                                    System.out.println("Divide of A and B is :" + iMathStub.div(a, b));
                                    break;
                                case 5:
                                    check = false;
                                    break;
                            }
                        }

                    }else{
                        System.out.println("Please login again");
                    }
                }else{
                    break;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
