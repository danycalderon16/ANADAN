/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Calderon
 */
public class Gramatica {

        private String produccion;
        private String error;

        public Gramatica(String produccion, String error) {
            this.produccion = produccion;
            this.error = error;
        }

        public String getProduccion() {
            return produccion;
        }

        public void setProduccion(String produccion) {
            this.produccion = produccion;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        @Override
        public String toString() {
            return "Gramatica{" + "produccion=" + produccion + ", error=" + error + '}';
        }
        
       
    }