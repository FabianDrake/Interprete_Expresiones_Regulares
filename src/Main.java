import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    // Generador de Expresiones Regulares
    public static class RegexGenerator {
        private StringBuilder regex;

        public RegexGenerator() {
            regex = new StringBuilder();
        }

        public void addLetter() {
            regex.append("[a-zA-Z]");
        }

        public void addDigit() {
            regex.append("\\d");
        }

        public void addWhitespace() {
            regex.append("\\s");
        }

        public void addSpecificCharacter(char c) {
            regex.append(c);
        }

        public String getRegex() {
            return regex.toString();
        }
    }

    // Adaptador de Expresiones a Otros Lenguajes
    public static class RegexAdapter {
        public static String adaptToPython(String regex) {
            return "r\"" + regex + "\"";  // Sintaxis de cadenas raw en Python
        }

        public static String adaptToJavaScript(String regex) {
            return "/" + regex + "/";  // Sintaxis de regex en JavaScript
        }
    }

    // Validador de Expresiones Regulares
    public static class RegexValidator {
        private Pattern pattern;

        public RegexValidator(String regex) {
            pattern = Pattern.compile(regex);
        }

        public boolean validate(String input) {
            Matcher matcher = pattern.matcher(input);
            return matcher.matches();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegexGenerator generator = new RegexGenerator();

        System.out.println("Construcción de una expresión regular:");
        boolean building = true;
        while (building) {
            System.out.println("Seleccione una opción: \n1. Letra \n2. Dígito \n3. Espacio \n4. Caracter específico \n5. Finalizar");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (choice) {
                case 1:
                    generator.addLetter();
                    break;
                case 2:
                    generator.addDigit();
                    break;
                case 3:
                    generator.addWhitespace();
                    break;
                case 4:
                    System.out.println("Ingrese el carácter específico:");
                    char c = scanner.nextLine().charAt(0);
                    generator.addSpecificCharacter(c);
                    break;
                case 5:
                    building = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        String regex = generator.getRegex();
        System.out.println("Expresión regular generada: " + regex);

        // Adaptación a otros lenguajes
        System.out.println("Expresión regular adaptada a Python: " + RegexAdapter.adaptToPython(regex));
        System.out.println("Expresión regular adaptada a JavaScript: " + RegexAdapter.adaptToJavaScript(regex));

        // Validación de cadenas
        RegexValidator validator = new RegexValidator(regex);
        System.out.println("Ingrese una cadena para validar (o 'exit' para salir):");

        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                running = false;
            } else {
                boolean isValid = validator.validate(input);
                System.out.println("¿La cadena cumple con la expresión regular? " + isValid);
            }
        }
        scanner.close();
    }
}
