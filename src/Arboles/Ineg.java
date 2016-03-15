package Arboles;

public class Ineg {
    

Nodo raiz;
      String x = "";
      int cont = 0;
      public Ineg() {
          raiz=null;
          cont = 0;
      }
      public Nodo getRaiz(){
          return raiz;
      }

      public void insertar(String datos){
        String []datos2= datos.split(",");
        for(int i = 0; i < datos2.length; i++){
            System.out.println(i);
          insertado(datos2[i]);
        }
      }

      public void insertado (String info)
      {
          Nodo nuevo;
          nuevo = new Nodo ();
          nuevo.info = info;
          nuevo.izq = null;
          nuevo.der = null;
          cont++;
          if (raiz == null)
              raiz = nuevo;
          else
          {
              Nodo anterior = null, reco;  //reco se utiliza en toso el programa para recorrer el arbol
              reco = raiz;
              switch(cont){
                  case 2:
                    reco.izq = nuevo;
                  break;
                  case 3:
                    reco = reco.izq;
                    reco.izq = nuevo;
                  break;
                  case 4:
                    reco = reco.izq;
                    reco.der = nuevo;
                  break;
                  case 5:
                    reco.der = nuevo;
                  break;
                  case 6:
                      System.out.println("si lo inserta");
                    reco = reco.der;
                    reco.izq = nuevo;
                    System.out.println(raiz.der.izq.info);
              }
            }

      }


      public void getOracion (Nodo reco)
      {
          if (reco != null)
          {
              x += reco.info + " ";
              getOracion (reco.izq);
              getOracion (reco.der);
          }
      }

      public String getOracion ()
      {
          getOracion (raiz);
          return x;
      }



      public static void main (String [] ar)
      {
        Ineg a = new Ineg();
        a.insertar("Does,Your Mother,have,a little dog,?");

        System.out.println("La oracion es:" + a.getOracion());



      }
}
