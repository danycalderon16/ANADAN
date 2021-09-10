/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

/**
 *
 * @author danyc
 */
public class InformacionLexema {

    public static int linea;
    public static int columna;
    public static String token;

    public static void guardarInformacionCodigo(int lineaG, int columnaG, String tokenG) {
        linea = lineaG;
        columna = columnaG;
        token = tokenG;
    }

}
