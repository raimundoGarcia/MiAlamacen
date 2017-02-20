package Modelo;

public class Particular extends Cliente {

    private String dni;

    public String getDni() {
        return dni;
    }

    public String setDni(String dni) {
        String respuesta=null;
        try {
            
            if (dni.length() == 8 && ComprobarDni(dni)) {
                
                
                this.dni = dni + calcularLetra(dni);
                
            } else if (dni.length() == 9 && calcularLetra(dni).equalsIgnoreCase(dni.substring(8,9))){
                  //  && ComprobarDni(dni)) 
                    
                this.dni = dni;
            }else throw new MiError();
        } catch (MiError e) {
            respuesta=e.getMessage();

        }
        return respuesta;
    }

    @Override
    public String imprimir() {
        String res = super.imprimir() + " DNI: " + this.dni;
        return res;
    }

    public boolean ComprobarDni(String dni) {
        boolean check = true;
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                throw new MiError(dni);
            }
        }
        return check;
    }

    public String calcularLetra(String dni) {
        String leter;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        char[] RecorreLetras = letras.toCharArray();
        String dni1;
        int let, dni2;
        dni1 = dni.substring(0, 8);
        dni2 = Integer.parseInt(dni1);
        let = dni2 % 23;
        char letra = RecorreLetras[let];
        leter = String.valueOf(letra);
        
        return leter;
    }
}
