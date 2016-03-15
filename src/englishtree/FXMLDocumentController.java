/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package englishtree;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Javier
 */
public class FXMLDocumentController implements Initializable {
    
    ArrayList<Token> tokens = new ArrayList();
    @FXML
    private Label label;

      @FXML
    private TextArea entrada,resultado;
    

    private void createLexFile(String path){
        File file = new File(path);
        jflex.Main.generate(file);
        System.out.println("--------------- creado ------------");
    }
    
    @FXML
    private void accion(ActionEvent e){
        try {
            probarLexerFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
      
        
    }
    
    public void crear(){
        for (int i = 0; i < tokens.size(); i++) {
            switch(tokens.get(i)){
                case not:
                    break;
            }
        }
    }
    
    
   public void probarLexerFile() throws FileNotFoundException, IOException{
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
                resultado.setText(Resultados);
                return;
                
            }
            tokens.add(token);
            switch(token){
                case ERROR:
                    Resultados = Resultados + "ERROR, sintaxís erronea \n";
                    break;
                
                    
                default:
                    Resultados = Resultados + "\nToken: "+token+" "+lexer.save+"\n";
                    break;
                    
            }
            
            
        }
       
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String aux = "src/englishtree/lexico.flex";
        createLexFile(aux);
    }    
    
}