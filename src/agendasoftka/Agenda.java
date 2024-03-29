package agendasoftka;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agenda {

    private final int maxContactos;
    private int contactosRegistrados;
    private Contacto[] contactos;
    private final String fichero;
    private final char separador;

    public Agenda(int maxContactos) {
        this.maxContactos = maxContactos;
        this.contactosRegistrados = 0;
        this.contactos = new Contacto[maxContactos];
        this.fichero = "agenda.txt";
        this.separador = '#';
    }

    public int getMaxContactos() {
        return maxContactos;
    }

    public int getContactosRegistrados() {
        return contactosRegistrados;
    }

    public void contactoNuevo(String nombre, String telefono, String correo, String saldodolares) {
        contactos[contactosRegistrados] = new Contacto(nombre, telefono, correo, saldodolares);
        contactosRegistrados++;
    }

    public String infoContacto(int posicion) {
        return contactos[posicion].getNombre() + " # " + contactos[posicion].getNumero()+ " # " + 
                contactos[posicion].getCorreo()+ " # " + contactos[posicion].getSaldodolares();
    }

    public String buscarNumeroPorNombre(String nombre) {
        boolean encontrado = false;
        String numero = "Contacto no encontrado";
        for (int i = 0; i < contactosRegistrados && !encontrado; i++) {
            if (nombre.equals(contactos[i].getNombre())) {
                numero = contactos[i].getNumero();
                encontrado = true;
            }
        }
        return numero;
    }

    public void ordenarAgendaPorNombre() {
        Arrays.sort(contactos, 0, contactosRegistrados);
    }
    
    public void ordenarAgendaPorNumero() {
        Arrays.sort(contactos, 0, contactosRegistrados, new OrdenarPorNumero());
    }

    void guardarAgenda() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < contactosRegistrados; i++) {
                bw.write(contactos[i].getNombre() + separador + contactos[i].getNumero() + System.lineSeparator());
            }
            bw.close();
            EntradaSalida.imprimirCadena("Agenda guardada correctamente.");
        } catch (IOException ex) {
            System.out.println("Error al guardar.");
        }
    }

    void cargarAgenda() {

        String cadena;
        int numContactos = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            while ((cadena = br.readLine()) != null) {
                String nombre = cadena.substring(0, cadena.indexOf(separador));
                String numero = cadena.substring(cadena.indexOf(separador) + 1, cadena.length());
                String correo = cadena.substring(2, cadena.indexOf(separador));
                String saldodolares = cadena.substring(3, cadena.indexOf(separador));
                contactos[numContactos] = new Contacto(nombre, numero, correo, saldodolares);
                numContactos++;
            }
            this.contactosRegistrados = numContactos;
            br.close();
            EntradaSalida.imprimirCadena("Éxito al cargar la agenda.");
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (ArrayIndexOutOfBoundsException aoe) {
            System.out.println("Los datos en la agenda guardada exeden las capacidades de la agenda");
        } catch (IOException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public String ingresoSaldo(String saldodolares, String nombre) {
        boolean encontrado = false;
        String saldo;
        String numero = "Contacto no encontrado";
        for (int i = 0; i < contactosRegistrados && !encontrado; i++) {
            if (nombre.equals(contactos[i].getNombre())) {
                numero = contactos[i].getNumero();
                saldo = contactos[i].getSaldodolares();
                encontrado = true;
                saldodolares = saldo+saldodolares;
            }
        }
        return numero;
    }
      
        public String buscarSaldoPorNombre(String nombre) {
        boolean encontrado = false;
        String numero = "Contacto no encontrado";
        for (int i = 0; i < contactosRegistrados && !encontrado; i++) {
            if (nombre.equals(contactos[i].getNombre())) {
                numero = contactos[i].getSaldodolares();
                encontrado = true;
            }
        }
        return numero;
    }
        
      public void sumar(String valor1, String valor2){
          int valor = 0;
          int valora = Integer.parseInt(valor1);
          int valorb = Integer.parseInt(valor2);
          valor = valora+valorb;
      }

    
}
