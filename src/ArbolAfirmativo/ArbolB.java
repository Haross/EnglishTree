package ArbolAfirmativo;

public class ArbolB {
  String Oracion="";
   
      Nodo raiz;
      int cont=0;
      public ArbolB() {
          raiz=null;
      }
      public Nodo getRaiz(){
          return raiz;
      }
      public void insertar(String datos){
        String [] datos2 = datos.split(",");
        for (int x=0;x<datos2.length ;x++ ) {
          insertado(datos2[x]);
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
                  reco.izq=nuevo;
                  break;
                  case 3:
                  reco.der=nuevo;
                  break;
              }
      }
    }


      public void getOracion (Nodo reco)
      {
          if (reco != null)
          {
            Oracion+=(reco.info + " ");
            getOracion (reco.izq);
            getOracion (reco.der);
          }
      }

      public String getOracion ()
      {
        getOracion (raiz);
          System.out.println();
          return Oracion;
      }



      public static void main (String[] args)
      {
          ArbolB abo = new ArbolB ();
          abo.insertar ("I,run,fast");
      /*    abo.insertar (50);
          abo.insertar (25);
          abo.insertar (75);
          abo.insertar (150);*/
          System.out.println ("Impresion preOrder: ");
          System.out.println(abo.getOracion ());

      /*    System.out.println ("Impresion inOrder: ");
          abo.inOrder ();
          System.out.println ("Impresion postOrder: ");
          abo.postOrder ();*/
      }
}
