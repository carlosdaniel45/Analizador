package analizador;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.table.DefaultTableModel;

public class OptimizarCodigo {

String fichero = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\Analizador\\Codigo.txt"; //Pega en disco Raiz C. //Si no te lo toma, prueba con otra carpeta
int contlineas = 1;
String linea;
String[] lineavec = new String[100];
String[] lineavec2 = new String[100];
String[] tokens = new String[100];
        String[] tokens2 = new String[100];
        String[] guardolinea = new String[100];
        String[][] tablaerrores = new String[100][3];
        String[][] tablasimbolos = new String[100][5];
        String[] lineastripletas = new String[100];
        String[][] tablaopsides = new String[5][2];
        String[][] tablaopsrelides = new String[8][2];
        String[][] tablatripletas = new String[100][4];
        int conterrores = 0;
        int contsimbolos = 0;
       // int contlineas = 1;
        int conttokens = 1;
        int conttokens2 = 1;
        int contlineatripletas = 0;
        int contide = 1;
        int conttripletas = 0;
        int conttrip = 0;
        int contT = 1;
        int contC = 1;
        int contTR = 1;
        int apuntadorfor = 0;
        int apuntadorvueltafor = 0;
        boolean banderasimbol = false;
        boolean banderavar = false;
        boolean banderaops = false;
        boolean banderaconst = false;
        boolean banderaerror = false;
        boolean banderafor = false;
        String tipodatos[] = {"int", "boolean", "double", "String","float"};
        String tipodatosNum[] = {"1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","52","55"};
        String tipodatosNum2[] = {"1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","52","55"};
        String tipovariables[] = {"x","y","w","z","a","b","c","d"};
        String operadores[] = {"+", "-", "*", "/", "%", "="};
        DefaultTableModel modelo, modelo2, modelo3;
        int numint;
        boolean valbool;
        double numdouble;
        String valStr;
        String tipo = "";
        boolean validaasigna = false;
        int contaVar = 0;
     //boolean validacase;
        int contador = 1;
        int contadorv = 1;
        int auxconttrip = 1;
String OptimizarCodigoDaVinciDatos(String direccion2){
String Cadena = "";

//modelo2 = (DefaultTableModel) jTable4.getModel();
        
        
        try {
            String codigoz = "";
            FileInputStream fis = new FileInputStream(fichero);
            InputStreamReader isr = new InputStreamReader(fis,"utf8");
            BufferedReader br = new BufferedReader(isr);
            String lineadetexto;
            BufferedReader lectura;
            try {
                lectura = new BufferedReader(new FileReader(fichero));
                String codigo;
                while (lectura.ready()) {
                    codigo = lectura.readLine();
                   // jTextArea1.setText(jTextArea1.getText() + contlineas + ":      " +  codigo + "\n");
                   // jTextoDatos.setText(jTextoDatos.getText() +codigo+"\n");
                    Cadena += codigo + "\n";
                    contlineas++;
                }
            } catch (IOException e) {}
            
            fis.close();
        }
        catch(IOException e){}
return Cadena;
}

String OptimizarCodigoDaVinci(String direccion){
String Cadena = "";

//modelo2 = (DefaultTableModel) jTable4.getModel();
        
        
        try {
            String codigoz = "";
            FileInputStream fis = new FileInputStream(fichero);
            InputStreamReader isr = new InputStreamReader(fis,"utf8");
            BufferedReader br = new BufferedReader(isr);
            String lineadetexto;
            BufferedReader lectura;
            try {
                lectura = new BufferedReader(new FileReader(fichero));
                String codigo;
                while (lectura.ready()) {
                    codigo = lectura.readLine();
                   // jTextArea1.setText(jTextArea1.getText() + contlineas + ":      " +  codigo + "\n");
                   // jTextoDatos.setText(jTextoDatos.getText() +codigo+"\n");
                    Cadena += codigo + "\n";
                    contlineas++;
                }
            } catch (IOException e) {}
            
            fis.close();
        }
        catch(IOException e){}
        String[] palabras = {    
                                 "a + a","a + b","a + c","a + d","a + w","a + x","a + y","a + z",
                                 "b + a","b + b","b + c","b + d","b + w","b + x","b + y","b + z",
                                 "c + a","c + b","c + c","c + d","c + w","c + x","c + y","c + z",
                                 "d + a","d + b","d + c","d + d","d + w","d + x","d + y","d + z",
                                 "w + a","w + b","w + c","w + d","w + w","w + x","w + y","w + z",
                                 "x + a","x + b","x + c","x + d","x + w","x + x","x + y","x + z",
                                 "y + a","y + b","y + c","y + d","y + w","y + x","y + y","y + z",
                                 "z + a","z + b","z + c","z + d","z + w","z + x","z + y","z + z",
                                 
                                 "a - a","a - b","a - c","a - d","a - w","a - x","a - y","a - z",
                                 "b - a","b - b","b - c","b - d","b - w","b - x","b - y","b - z",
                                 "c - a","c - b","c - c","c - d","c - w","c - x","c - y","c - z",
                                 "d - a","d - b","d - c","d - d","d - w","d - x","d - y","d - z",
                                 "w - a","w - b","w - c","w - d","w - w","w - x","w - y","w - z",
                                 "x - a","x - b","x - c","x - d","x - w","x - x","x - y","x - z",
                                 "y - a","y - b","y - c","y - d","y - w","y - x","y - y","y - z",
                                 "z - a","z - b","z - c","z - d","z - w","z - x","z - y","z - z",
                                 
                                 "a / a","a / b","a / c","a / d","a / w","a / x","a / y","a / z",
                                 "b / a","b / b","b / c","b / d","b / w","b / x","b / y","b / z",
                                 "c / a","c / b","c / c","c / d","c / w","c / x","c / y","c / z",
                                 "d / a","d / b","d / c","d / d","d / w","d / x","d / y","d / z",
                                 "w / a","w / b","w / c","w / d","w / w","w / x","w / y","w / z",
                                 "x / a","x / b","x / c","x / d","x / w","x / x","x / y","x / z",
                                 "y / a","y / b","y / c","y / d","y / w","y / x","y / y","y / z",
                                 "z / a","z / b","z / c","z / d","z / w","z / x","z / y","z / z",
                                 
                                 "a * a","a * b","a * c","a * d","a * w","a * x","a * y","a * z",
                                 "b * a","b * b","b * c","b * d","b * w","b * x","b * y","b * z",
                                 "c * a","c * b","c * c","c * d","c * w","c * x","c * y","c * z",
                                 "d * a","d * b","d * c","d * d","d * w","d * x","d * y","d * z",
                                 "w * a","w * b","w * c","w * d","w * w","w * x","w * y","w * z",
                                 "x * a","x * b","x * c","x * d","x * w","x * x","x * y","x * z",
                                 "y * a","y * b","y * c","y * d","y * w","y * x","y * y","y * z",
                                 "z * a","z * b","z * c","z * d","z * w","z * x","z * y","z * z"
            };
    
    //String[] lineaCadena; //new String[100];
    String[][] TablaCadena = new String[100][6];
    
    String lineaCadena[] = Cadena.split("\\n");
    String CadenaTemp = "";
    String CadenaTempAnt = "";
    String CadenaTemp2 = "";
    String CadenaTempAnt2 = "";
    int contador = 0;
    int tam = 0;
    Cadena ="";
    for(int com = 0; com < lineaCadena.length ; com++)
    { 
        CadenaTemp = lineaCadena[com];
        if(contador >= 1)
        CadenaTempAnt = lineaCadena[contador-1];
        else{
            if(contador <=0){
            CadenaTempAnt = lineaCadena[contador];
            }
        }
        
        if(!lineaCadena[com].equals(""))
            System.out.println(contador+1+": "+lineaCadena[com]);
        
        System.out.println(CadenaTemp);
        System.out.println(CadenaTempAnt);
        CadenaTemp2 = CadenaTemp.replaceAll(" ","");
        CadenaTempAnt2 = CadenaTempAnt.replaceAll( " ","");
        
        System.out.println(CadenaTemp);
        System.out.println(CadenaTempAnt);
        
        if(CadenaTempAnt2.matches(".*x=x-5.*")||
           CadenaTempAnt2.matches(".*x=x-1.*")||CadenaTempAnt2.matches(".*x=x-2.*")||
           CadenaTempAnt2.matches(".*x=x-3.*")||CadenaTempAnt2.matches(".*x=x-4.*")||
           CadenaTempAnt2.matches(".*x=x-6.*")||CadenaTempAnt2.matches(".*x=x-7.*")||
           CadenaTempAnt2.matches(".*x=x-8.*")||CadenaTempAnt2.matches(".*x=x-9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*x=1+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 1 + x;";
           }
           if(CadenaTemp2.matches(".*x=2+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 2 + x;";
           }
           if(CadenaTemp2.matches(".*x=3+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 3 + x;";
           }
           if(CadenaTemp2.matches(".*x=4+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 4 + x;";
           }
           if(CadenaTemp2.matches(".*x=5+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 5 + x;";
           }
           if(CadenaTemp2.matches(".*x=6+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 6 + x;";
           }
           if(CadenaTemp2.matches(".*x=7+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 7 + x;";
           }
           if(CadenaTemp2.matches(".*x=8+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 8 + x;";
           }
           if(CadenaTemp2.matches(".*x=9+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 9 + x;";
           }
           
       }
       //Para x y Y
       if(CadenaTempAnt2.matches(".*y=x-5.*")||
           CadenaTempAnt2.matches(".*y=x-1.*")||CadenaTempAnt2.matches(".*y=x-2.*")||
           CadenaTempAnt2.matches(".*y=x-3.*")||CadenaTempAnt2.matches(".*y=x-4.*")||
           CadenaTempAnt2.matches(".*y=x-6.*")||CadenaTempAnt2.matches(".*y=x-7.*")||
           CadenaTempAnt2.matches(".*y=x-8.*")||CadenaTempAnt2.matches(".*y=x-9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*x=1+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 1 + y;";
           }
           if(CadenaTemp2.matches(".*x=2+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 2 + y;";
           }
           if(CadenaTemp2.matches(".*x=3+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 3 + y;";
           }
           if(CadenaTemp2.matches(".*x=4+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 4 + y;";
           }
           if(CadenaTemp2.matches(".*x=5+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 5 + y;";
           }
           if(CadenaTemp2.matches(".*x=6+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 6 + y;";
           }
           if(CadenaTemp2.matches(".*x=7+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 7 + y;";
           }
           if(CadenaTemp2.matches(".*x=8+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 8 + y;";
           }
           if(CadenaTemp2.matches(".*x=9+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 9 + y;";
           }
           
       }
       //para y en y
       if(CadenaTempAnt2.matches(".*y=x-5.*")||
           CadenaTempAnt2.matches(".*y=x-1.*")||CadenaTempAnt2.matches(".*y=x-2.*")||
           CadenaTempAnt2.matches(".*y=x-3.*")||CadenaTempAnt2.matches(".*y=x-4.*")||
           CadenaTempAnt2.matches(".*y=x-6.*")||CadenaTempAnt2.matches(".*y=x-7.*")||
           CadenaTempAnt2.matches(".*y=x-8.*")||CadenaTempAnt2.matches(".*y=x-9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*y=1+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 1 + y;";
           }
           if(CadenaTemp2.matches(".*y=2+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 2 + y;";
           }
           if(CadenaTemp2.matches(".*y=3+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 3 + y;";
           }
           if(CadenaTemp2.matches(".*y=4+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 4 + y;";
           }
           if(CadenaTemp2.matches(".*y=5+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 5 + y;";
           }
           if(CadenaTemp2.matches(".*y=6+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 6 + y;";
           }
           if(CadenaTemp2.matches(".*y=7+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 7 + y;";
           }
           if(CadenaTemp2.matches(".*y=8+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 8 + y;";
           }
           if(CadenaTemp2.matches(".*y=9+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 9 + y;";
           }
           
       }
       
       //para y en y y z
       if(CadenaTempAnt2.matches(".*y=z-5.*")||
           CadenaTempAnt2.matches(".*y=z-1.*")||CadenaTempAnt2.matches(".*y=z-2.*")||
           CadenaTempAnt2.matches(".*y=z-3.*")||CadenaTempAnt2.matches(".*y=z-4.*")||
           CadenaTempAnt2.matches(".*y=z-6.*")||CadenaTempAnt2.matches(".*y=z-7.*")||
           CadenaTempAnt2.matches(".*y=z-8.*")||CadenaTempAnt2.matches(".*y=z-9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*y=1+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 1 + y;";
           }
           if(CadenaTemp2.matches(".*y=2+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 2 + y;";
           }
           if(CadenaTemp2.matches(".*y=3+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 3 + y;";
           }
           if(CadenaTemp2.matches(".*y=4+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 4 + y;";
           }
           if(CadenaTemp2.matches(".*y=5+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 5 + y;";
           }
           if(CadenaTemp2.matches(".*y=6+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 6 + y;";
           }
           if(CadenaTemp2.matches(".*y=7+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 7 + y;";
           }
           if(CadenaTemp2.matches(".*y=8+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 8 + y;";
           }
           if(CadenaTemp2.matches(".*y=9+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "y = 9 + y;";
           }
           
       }
       
       //Casos Unico- Por falta de espacio.
       if(CadenaTempAnt2.matches(".*x=x/5.*")||
           CadenaTempAnt2.matches(".*x=x/1.*")||CadenaTempAnt2.matches(".*x=x/2.*")||
           CadenaTempAnt2.matches(".*x=x/3.*")||CadenaTempAnt2.matches(".*x=x/4.*")||
           CadenaTempAnt2.matches(".*x=x/6.*")||CadenaTempAnt2.matches(".*x=x/7.*")||
           CadenaTempAnt2.matches(".*x=x/8.*")||CadenaTempAnt2.matches(".*x=x/9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*x=9+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 9 + x;";
           }
           if(CadenaTemp2.matches(".*x=8+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 8 + x;";
           }
           if(CadenaTemp2.matches(".*x=7+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 7 + x;";
           }
           if(CadenaTemp2.matches(".*x=6+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 6 + x;";
           }
           if(CadenaTemp2.matches(".*x=5+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 5 + x;";
           }
           if(CadenaTemp2.matches(".*x=4+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 4 + x;";
           }
           if(CadenaTemp2.matches(".*x=3+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 3 + x;";
           }
           if(CadenaTemp2.matches(".*x=2+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 2 + x;";
           }
           if(CadenaTemp2.matches(".*x=1+.*") && 
             (CadenaTemp2.matches(".*x/9.*")||
              CadenaTemp2.matches(".*x/8.*")||CadenaTemp2.matches(".*x/7.*")||
              CadenaTemp2.matches(".*x/6.*")||CadenaTemp2.matches(".*x/5.*")||
              CadenaTemp2.matches(".*x/4.*")||CadenaTemp2.matches(".*x/3.*")||
              CadenaTemp2.matches(".*x/2.*")||CadenaTemp2.matches(".*x/1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 1 + x;";
           }
        }
       if(CadenaTempAnt2.matches(".*y=z-5.*")||
           CadenaTempAnt2.matches(".*y=z-1.*")||CadenaTempAnt2.matches(".*y=z-2.*")||
           CadenaTempAnt2.matches(".*y=z-3.*")||CadenaTempAnt2.matches(".*y=z-4.*")||
           CadenaTempAnt2.matches(".*y=z-6.*")||CadenaTempAnt2.matches(".*y=z-7.*")||
           CadenaTempAnt2.matches(".*y=z-8.*")||CadenaTempAnt2.matches(".*y=z-9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*z=9+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 9 + y;";
           }
           if(CadenaTemp2.matches(".*z=8+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 8 + y;";
           }
           if(CadenaTemp2.matches(".*z=7+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 7 + y;";
           }
           if(CadenaTemp2.matches(".*z=6+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 6 + y;";
           }
           if(CadenaTemp2.matches(".*z=5+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 5 + y;";
           }
           if(CadenaTemp2.matches(".*z=4+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 4 + y;";
           }
           if(CadenaTemp2.matches(".*z=3+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 3 + y;";
           }
           if(CadenaTemp2.matches(".*z=2+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 2 + y;";
           }
           if(CadenaTemp2.matches(".*z=1+.*") && 
             (CadenaTemp2.matches(".*z-9.*")||
              CadenaTemp2.matches(".*z-8.*")||CadenaTemp2.matches(".*z-7.*")||
              CadenaTemp2.matches(".*z-6.*")||CadenaTemp2.matches(".*z-5.*")||
              CadenaTemp2.matches(".*z-4.*")||CadenaTemp2.matches(".*z-3.*")||
              CadenaTemp2.matches(".*z-2.*")||CadenaTemp2.matches(".*z-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 1 + y;";
           }
        }
       if(CadenaTempAnt2.matches(".*y=x-5.*")||
           CadenaTempAnt2.matches(".*y=x-1.*")||CadenaTempAnt2.matches(".*y=x-2.*")||
           CadenaTempAnt2.matches(".*y=x-3.*")||CadenaTempAnt2.matches(".*y=x-4.*")||
           CadenaTempAnt2.matches(".*y=x-6.*")||CadenaTempAnt2.matches(".*y=x-7.*")||
           CadenaTempAnt2.matches(".*y=x-8.*")||CadenaTempAnt2.matches(".*y=x-9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*z=9+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 9 + y;";
           }
           if(CadenaTemp2.matches(".*z=8+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 8 + y;";
           }
           if(CadenaTemp2.matches(".*z=7+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 7 + y;";
           }
           if(CadenaTemp2.matches(".*z=6+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 6 + y;";
           }
           if(CadenaTemp2.matches(".*z=5+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 5 + y;";
           }
           if(CadenaTemp2.matches(".*z=4+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 4 + y;";
           }
           if(CadenaTemp2.matches(".*z=3+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 3 + y;";
           }
           if(CadenaTemp2.matches(".*z=2+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 2 + y;";
           }
           if(CadenaTemp2.matches(".*z=1+.*") && 
             (CadenaTemp2.matches(".*x-9.*")||
              CadenaTemp2.matches(".*x-8.*")||CadenaTemp2.matches(".*x-7.*")||
              CadenaTemp2.matches(".*x-6.*")||CadenaTemp2.matches(".*x-5.*")||
              CadenaTemp2.matches(".*x-4.*")||CadenaTemp2.matches(".*x-3.*")||
              CadenaTemp2.matches(".*x-2.*")||CadenaTemp2.matches(".*x-1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "z = 1 + y;";
           }
        }
       
       if(CadenaTempAnt2.matches(".*x=x*5.*")||
           CadenaTempAnt2.matches(".*x=x*1.*")||CadenaTempAnt2.matches(".*x=x*2.*")||
           CadenaTempAnt2.matches(".*x=x*3.*")||CadenaTempAnt2.matches(".*x=x*4.*")||
           CadenaTempAnt2.matches(".*x=x*6.*")||CadenaTempAnt2.matches(".*x=x*7.*")||
           CadenaTempAnt2.matches(".*x=x*8.*")||CadenaTempAnt2.matches(".*x=x*9.*")){
           System.out.println("Si lo encontro");
           
           if(CadenaTemp2.matches(".*x=9+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".* x*1 .*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 9 + x;";
           }
           if(CadenaTemp2.matches(".*x=8+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 8 + x;";
           }
           if(CadenaTemp2.matches(".*x=7+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 7 + x;";
           }
           if(CadenaTemp2.matches(".*x=6+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 6 + x;";
           }
           if(CadenaTemp2.matches(".*x=5+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 5 + x;";
           }
           if(CadenaTemp2.matches(".*x=4+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 4 + x;";
           }
           if(CadenaTemp2.matches(".*x=3+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 3 + x;";
           }
           if(CadenaTemp2.matches(".*x=2+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 2 + x;";
           }
           if(CadenaTemp2.matches(".*x=1+.*") && 
             (CadenaTemp2.matches(".*x*9.*")||
              CadenaTemp2.matches(".*x*8.*")||CadenaTemp2.matches(".*x*7.*")||
              CadenaTemp2.matches(".*x*6.*")||CadenaTemp2.matches(".*x*5.*")||
              CadenaTemp2.matches(".*x*4.*")||CadenaTemp2.matches(".*x*3.*")||
              CadenaTemp2.matches(".*x*2.*")||CadenaTemp2.matches(".*x*1.*"))){
                System.out.println("Si lo encontro2");
                CadenaTemp = "x = 1 + x;";
           }
        }
        Cadena += CadenaTemp+"\r\n";       
        contador ++;
    }  
return Cadena;
}    
}
