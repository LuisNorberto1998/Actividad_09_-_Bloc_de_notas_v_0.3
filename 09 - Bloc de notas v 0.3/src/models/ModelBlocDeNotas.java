package models;

import java.io.*;

/**
 *
 * @author Norberto
 */
public class ModelBlocDeNotas {

    private String text;
    private String path;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void fileRead(String path) {
        try {
            String row;
            String txt = "";
            try (FileReader file = new FileReader(path)) {
                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(file);
                while ((row = bufferedReader.readLine()) != null) {
                    txt = txt + row + "\n";
                    System.out.println(row);
                }
                this.setText(txt);
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error 2: " + e.getMessage());
        }
    }

    public void writeFile(String path, String text) {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, false);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(text);
                printWriter.close();
            }
        } catch (IOException err) {
            System.err.println("error " + err.getMessage());
        }
    }

    public void encryptFile(String path, String text) {
        try {
            int ascii;
            String encrypt = "";
            char character;
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, false);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                for (int x = 0; x < text.length(); x++) {
                    character = text.charAt(x);
                    ascii = (int) character;
                    ascii += 15;
                    encrypt += (char) ascii;
                }
                printWriter.println(encrypt);
                this.setText(encrypt);
                printWriter.close();
            }
        } catch (IOException err) {
            System.err.println("error " + err.getMessage());
        }
    }

    public void decryptFile(String path) {
        try {
            String row;
            int ascii;
            char caracter;
            char descifrado;
            String mensaje = "";
            try (FileReader file = new FileReader(path)) {
                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(file);
                while ((row = bufferedReader.readLine()) != null) {
                    for (int x = 0; x < row.length(); x++) {
                        caracter = row.charAt(x);
                        ascii = (int) caracter;
                        ascii -= 15;
                        descifrado = (char) ascii;
                        mensaje += descifrado;
                    }
                    mensaje += "\n";
                }

                this.setText(mensaje);
                bufferedReader.close();
            } catch (FileNotFoundException err) {
                System.out.println("Error! " + err.getMessage());
            }
        } catch (IOException err) {
            System.out.println("Error! " + err.getMessage());
        }
    }
}
