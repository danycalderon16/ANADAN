package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Principal{

    public static void main(String[] args) throws Exception {
        String lexer                         = "C:/ANADAN/src/codigo/Lexer.flex";
        String cup                           = "C:/ANADAN/src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:/ANADAN/src/codigo/Sintax.cup"};
        generar(lexer, cup, rutaS);
    }

    private static void generarLexer(String path) {
        File file = new File(path);
        JFlex.Main.generate(file);
    }
     public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("C:/ANADAN/src/codigo/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("C:/ANADAN/sym.java"), 
                Paths.get("C:/ANADAN/src/codigo/sym.java")
        );
        Path rutaSin = Paths.get("C:/ANADAN/src/codigo/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("C:/ANADAN/Sintax.java"), 
                Paths.get("C:/ANADAN/src/codigo/Sintax.java")
        );
    }
}
