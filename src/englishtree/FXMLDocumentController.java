/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package englishtree;

import ArbolAfirmativo.ArbolB;
import ArbolAfirmativo.ArbolGraficoA;
import ArbolIn.ArbolABB;
import ArbolNegativo.ArbolAN;
import ArbolNegativo.ArbolGraficoAN;
import Arboles.ArbolGraficoIN;
import Arboles.Ineg;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import englishtree.Token;
import static englishtree.Token.not;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Javier
 */
public class FXMLDocumentController implements Initializable {
    
    ArrayList<Token> tokens = new ArrayList();
  Ineg interrogativoNe = new Ineg();
  ArbolABB interrogativoA = new ArbolABB();
  ArbolB afirmativo = new ArbolB();
  ArbolAN Anegativo = new ArbolAN();
  @FXML
    private Rectangle R1,R2,R3,R4;
    @FXML
    private FontAwesomeIconView icon1,icon2,icon3,icon4,icon5;
      @FXML
    private TextArea entrada;
      @FXML
    private Button C1;
      @FXML
    private Label positivo,negativo,interrogativo,interrogativoN;
    
      String sujeto = "", verbo="", predicado="",not="", aux="";
    private void createLexFile(String path){
        File file = new File(path);
        jflex.Main.generate(file);
        System.out.println("--------------- creado ------------");
    }
    
    @FXML
    private void accion(ActionEvent e){
        try {
            sujeto = "";
            verbo="";
            predicado="";
            not="";
            aux="";
            probarLexerFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
      crearOraciones();
        
    }
    
   @FXML
    public void showArbolIN(ActionEvent ev) {
        createAndSetSwingContent(interrogativoNe);
    }

    private void createAndSetSwingContent(Ineg ar) {

        SwingUtilities.invokeLater(() -> {
            
            JFrame pane = new JFrame("Arbol de Huffman");
            pane.add(new ArbolGraficoIN(ar));
            pane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //para terminar hilo
            pane.setSize(1700, 720);
            pane.setVisible(true);
           
        });

    }
    @FXML
    public void showArbolI(ActionEvent ev) {
        
        createAndSetSwingContent();
    }
     private void createAndSetSwingContent() {

        SwingUtilities.invokeLater(() -> {
            
            JFrame pane = new JFrame("Arbol de Huffman");
            pane.add(interrogativoA.getdibujo());
            pane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //para terminar hilo
            pane.setSize(1700, 720);
            pane.setVisible(true);
           
        });

    }
     @FXML
    public void showArbolA(ActionEvent ev) {
        
        createAndSetSwingContent(afirmativo);
    }
     private void createAndSetSwingContent(ArbolB arbol) {

        SwingUtilities.invokeLater(() -> {
            
            JFrame pane = new JFrame("Arbol de Huffman");
            pane.add(new ArbolGraficoA(arbol));
            pane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //para terminar hilo
            pane.setSize(1700, 720);
            pane.setVisible(true);
           
        });

    }
       @FXML
    public void showArbolAN(ActionEvent ev) {
        
        createAndSetSwingContent(Anegativo);
    }
     private void createAndSetSwingContent(ArbolAN arbol) {

        SwingUtilities.invokeLater(() -> {
            
            JFrame pane = new JFrame("Arbol de Huffman");
            pane.add(new ArbolGraficoAN(arbol));
            pane.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //para terminar hilo
            pane.setSize(1700, 720);
            pane.setVisible(true);
           
        });

    }
    public void crearOraciones(){
        
        interrogativoNe = new Ineg();
        interrogativoNe.insertar(getAux()+ ",not,"+sujeto+","+getVerbSF()+","+ predicado +",?");
        
        interrogativoN.setText(interrogativoNe.getOracion());
        
        interrogativoA = new ArbolABB();
        interrogativoA.inserta(getAux(),sujeto,getVerbSF(),predicado,"?");
        interrogativo.setText(interrogativoA.getOracion());
        
        afirmativo = new ArbolB();
        
        afirmativo.insertar(sujeto+","+verbo+","+predicado);
        positivo.setText(afirmativo.getOracion());
        
        Anegativo = new ArbolAN();
        Anegativo.InsertarNegativo(sujeto, getAux(), "not", getVerbSF(), predicado);
        negativo.setText(Anegativo.getOracion());
        
    }
    
    
    public String getVerbSF(){
        
        if(sujeto.equals("she") || sujeto.equals("he") || sujeto.equals("it") ||sujeto.equals("She") || sujeto.equals("He") || sujeto.equals("Tt")){
            return verbo.substring(0,verbo.length()); 
        }else{
            return verbo;
        }
    }
    public String getAux(){
        if(sujeto.equals("she") || sujeto.equals("he") || sujeto.equals("it") ||sujeto.equals("She") || sujeto.equals("He") || sujeto.equals("Tt")){
            return "does";
        }else{
            return "do";
        }
    }
    
   public boolean probarLexerFile() throws FileNotFoundException, IOException{
        File fichero = new File("fichero.txt");
        PrintWriter writer;
        try {
            writer = new  PrintWriter(fichero);
            writer.print(entrada.getText());
            writer.close();
        } catch (Exception ex) {
            
        }
        Reader reader;
        reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer(reader);
        String Resultados="";
        
        
        while (true) {            
            Token token = lexer.yylex();
            if(token ==null){
                Resultados = Resultados ;
                
                return true;
                
            }
            tokens.add(token);
            switch(token){
                case S:
                case S3:
                    if("".equals(sujeto)){
                        sujeto = lexer.save;
                    }else{
                        errorAlert();
                        return false;
                    }
                    break;
                case V:
                    if("".equals(verbo)){
                        verbo = lexer.save;
                        System.out.println(verbo+"--");
                    }else{
                        errorAlert();
                        return false;
                    }
                    break;
                case P:
                    
                    predicado += lexer.save +" ";
                    System.out.println("Predicado "+predicado);
                    break;
                case not:
                    if("".equals(not)){
                        not = lexer.save;
                    }else{
                        predicado += lexer.save +" ";
                    }
                    break;
                case do1:
                case does1:
                    if("".equals(aux)){
                        aux = lexer.save;
                        System.out.println("aux: "+aux);
                    }else{
                        errorAlert();
                        return false;
                    }
                    break;
                case ERROR:
                    System.out.println("ERROR");
                    break;
   
                default:
                    Resultados = Resultados + "\nToken: "+token+" "+lexer.save+"\n";
                    break;
                    
            }
            
            
        }
      
       
    }
   
    public boolean validacion(){
        if(sujeto.equals("she") || sujeto.equals("he") || sujeto.equals("it") ||sujeto.equals("She") || sujeto.equals("He") || sujeto.equals("Tt")){
            
            if("".equals(aux) && verbo.length()>0){               
                if (verbo.charAt(verbo.length()-1) != 's') {
                     errorAlert();
                return false;
                }
               
            }else if(aux.equals("does") || aux.equals("Does")  ){
                errorAlert();
                return false;
            }       
            
        }
        
        return true;
    }
   
    public void errorAlert(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Aprende ingles :P");

        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       efectos();
        
       String aux = "src/englishtree/lexico.flex";
        createLexFile(aux);
    }  
    
    private void efectos(){
          R1.setOnMouseEntered( e -> {          
            icon1.setStyle("-fx-fill: #53D37D;");          
        });
         R1.setOnMouseExited(e -> {          
            icon1.setStyle("-fx-fill: black;");           
        });
         positivo.setOnMouseEntered( e -> {          
            icon1.setStyle("-fx-fill: #53D37D;");          
        });
         positivo.setOnMouseExited(e -> {          
            icon1.setStyle("-fx-fill: black;");           
        });
        R2.setOnMouseEntered( e -> {           
            icon2.setStyle("-fx-fill:  #D05151");           
        });
         R2.setOnMouseExited(e -> {           
            icon2.setStyle("-fx-fill: black;");           
        });
         negativo.setOnMouseEntered( e -> {           
            icon2.setStyle("-fx-fill:  #D05151");           
        });
         negativo.setOnMouseExited(e -> {           
            icon2.setStyle("-fx-fill: black;");           
        });
        R3.setOnMouseEntered( e -> {           
            icon3.setStyle("-fx-fill: #58BBB7;");          
        });
         R3.setOnMouseExited(e -> {          
            icon3.setStyle("-fx-fill: black;");          
        });
         interrogativo.setOnMouseEntered( e -> {           
            icon3.setStyle("-fx-fill: #58BBB7;");          
        });
         interrogativo.setOnMouseExited(e -> {          
            icon3.setStyle("-fx-fill: black;");          
        });
        R4.setOnMouseEntered( e -> {          
            icon4.setStyle("-fx-fill: #ED9349;"); 
            icon5.setStyle("-fx-fill:#ED9349;");          
        });
         R4.setOnMouseExited(e -> {           
            icon4.setStyle("-fx-fill: black;"); 
             icon5.setStyle("-fx-fill: black;");         
        });
         interrogativoN.setOnMouseEntered( e -> {          
            icon4.setStyle("-fx-fill: #ED9349;"); 
            icon5.setStyle("-fx-fill:#ED9349;");          
        });
         interrogativoN.setOnMouseExited(e -> {           
            icon4.setStyle("-fx-fill: black;"); 
             icon5.setStyle("-fx-fill: black;");         
        });
        
    }
    
}
//Arbol positivo
        //insertar(sujeto,verbo,predicado);
        //Arbol negativo
        //Insertar(sujeto, getAux() + "not", getVerb(), predicado);
        //Arbol interrogativo negativo
        //Insertar(getAux(), "not", sujeto, verbo.substring[0,verbo.length-1], predicado,"?");
        //Arbol interrogativo
        //Insertar(getAux(), sujeto, verbo, predicado,"?");
        //if(validacion()){
        //positivo.setText(sujeto + " "+ verbo +" "+ predicado);
        //negativo.setText(sujeto + " "+ getAux()+" not " +getVerbSF()+" "+ predicado);
        //interrogativo.setText(getAux()+" "+sujeto+" "+getVerbSF() +" "+predicado+"?") ;