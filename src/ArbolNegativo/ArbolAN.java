package ArbolNegativo;

import java.io.Serializable;

public class ArbolAN implements Serializable {
    
      Nodo raiz;

      public ArbolAN() {
          raiz=null;
      }
      
      public Nodo getRaiz(){
          return raiz;
      }
      
      
      public void InsertarNegativo(String sujeto,String dosdoes, String not ,String verbo, String Predicado ){
          Nodo nuevo = new Nodo();
          if(raiz == null){
              raiz = nuevo;
              nuevo.info = sujeto;
              Nodo recoder = new Nodo();
              Nodo recoizq = new Nodo();
              nuevo.der = recoder;
              recoder.info=Predicado;
              nuevo.izq = recoizq;
              recoizq.info = dosdoes;
              Nodo rereizq = new Nodo();
              Nodo rereder = new Nodo();
              recoizq.izq = rereizq;
              rereizq.info = not;
              recoizq.der = rereder;
              rereder.info = verbo;     
          }
      }
      
     
    public synchronized String getOracion(){
        String pre=ayudantepreorden(raiz);
        return(pre);
        }
    private String ayudantepreorden(Nodo reco){
        String cadena=new String();
            if(reco!=null){
//return;
//System.out.print(nodo.dato+" ");
            cadena=cadena+String.valueOf(reco.info+" ");
            cadena=cadena+ayudantepreorden(reco.izq);
            cadena=cadena+ayudantepreorden(reco.der);
            }else{
                cadena="";
            }
            return(cadena);
}
      
      

/*
      private void preOrder (Nodo reco)
      {
          if (reco != null)
          {
              System.out.print(reco.info + " ");
              preOrder (reco.izq);
              preOrder (reco.der);
          }
      }

      public void preOrder ()
      {
          preOrder (raiz);
          System.out.println();
      }

      private void inOrder (Nodo reco)
      {
          if (reco != null)
          {    
              inOrder (reco.izq);
              System.out.print(reco.info + " ");
              inOrder (reco.der);
          }
      }

      public void inOrder ()
      {
          inOrder (raiz);
          System.out.println();
      }


      private void postOrder (Nodo reco)
      {
          if (reco != null)
          {
              postOrder (reco.izq);
              postOrder (reco.der);
              System.out.print(reco.info + " ");
          }
      }


      public void postOrder ()
      {
          postOrder (raiz);
          System.out.println();
      }*/
}