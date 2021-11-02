/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author juani
 */
public class CodigoIntermedio {
    
        private String codigo;

        public CodigoIntermedio(String segmento) {
            this.codigo = this.codigo + segmento;
        }

        public String getCodigoIntermedio() {
            return this.codigo;
        }

        public void setCodigoIntermedio(String segmento) {
            this.codigo = segmento;
        }

        @Override
        public String toString() {
            return "void setup() {\n" + "\n" + "}" + 
                    this.codigo+ "\n" + "}";
        }
    
}
