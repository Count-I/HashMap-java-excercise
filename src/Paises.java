import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Paises {

    String pais, ciudad;
    ArrayList<String> ciudades;
    private HashMap<String,ArrayList<String>> ciudadesPais;

    public Paises(){
      ciudadesPais = new HashMap<>();
        iniciar();
    }

    public void iniciar(){

        String res="";
        do {
            registrarPais();
            res=pedirStringData("¿  desea ingresar otro pais?");
        }while (res.equalsIgnoreCase("si"));

        String menu2="Opcion\n";
        menu2+="1. Buscar ciudades por pais\n";
        menu2+="2. Consultar ciudad\n";
        menu2+="3. Salir\n";
        menu2+="Ingrese una opción\n";

        int opc=Integer.parseInt(JOptionPane.showInputDialog(menu2));

        switch (opc) {
            case 1: buscarCiudadesPorPais(pedirStringData("Ingrese el pais")); break;
            case 2: consultarCiudad(pedirStringData("Ingrese la ciudad")); break;
            case 3: System.out.println("Chao!");
            default:System.out.println("El codigo no existe!");
                break;
        }
    }

    private void consultarCiudad(String city) {
        for(Map.Entry<String, ArrayList<String>> entry : ciudadesPais.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String s : entry.getValue()) {
                if(s.equalsIgnoreCase(city)){
                    System.out.println(s);
                }
            }
        }
    }

    public void registrarPais(){
        pais = pedirStringData("Ingrese el pais");
        if (!ciudadesPais.containsKey(pais)) {
            ciudades = new ArrayList<>();
            registrarCiudades();
            ciudadesPais.put(pais, ciudades);
        }else {
            JOptionPane.showMessageDialog(null, "Ya existe, "
                    + "ingreselo de nuevo","ERROR",JOptionPane.ERROR_MESSAGE);
            registrarPais();
        }
    }
    public void registrarCiudades(){
        String res="";
        do{
            ciudades.add(pedirStringData("ingrese la ciudad"));
            res= pedirStringData("Desea agregar otra ciudad (si ó no)");
        }while(res.equalsIgnoreCase("si"));
    }
    public void buscarCiudadesPorPais(String pais){
        List<String> res = ciudadesPais.get(pais).stream().toList();
        res.forEach(p->System.out.println(p));
    }
    public static String pedirStringData(String message){
        String data="";
        do{
            data = JOptionPane.showInputDialog(message);
            if(data.isBlank() || data.isEmpty() || data == null){
                JOptionPane.showMessageDialog(null,"Ingrese un dato valido");
            }
        }while(data.isBlank() || data.isEmpty() || data == null);
        return data;
    }
}
