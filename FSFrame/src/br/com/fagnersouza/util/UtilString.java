/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.util;

/**
 *
 * @author fagner.souza
 */
public class UtilString {
    public static String removeAccent(String text)
    {
            String accents [][] = new String [][]
            {
                {"á","a"},
                {"Á","A"},
                {"à","a"},
                {"À","A"},
                {"â","a"},
                {"Â","A"},
                {"ã","a"},
                {"Ã","A"},
                {"é","e"},
                {"É","E"},
                {"ê","e"},
                {"Ê","E"},
                {"í","i"},
                {"Í","I"},
                {"ó","o"},
                {"Ó","O"},
                {"ô","o"},
                {"Ô","O"},
                {"õ","o"},
                {"Õ","O"},
                {"ú","u"},
                {"Ú","U"},
                {"ç","c"},
                {"Ç","C"}
            };

            for (String[] accent : accents) {
                text = text.replace(accent[0], accent[1]);
            }

            return text;
    }    
}
