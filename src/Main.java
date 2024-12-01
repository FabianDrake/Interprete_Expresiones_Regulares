import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    /**
     * Clase encargada de construir manualmente expresiones regulares
     * a partir de las opciones seleccionadas por el usuario.
     */
    public static class RegexGenerator {
        private StringBuilder regex;

        public RegexGenerator() {
            regex = new StringBuilder();
        }

        /**
         * Agrega una expresión para una letra (mayúscula o minúscula).
         */
        public void addLetter() {
            regex.append("[a-zA-Z]");
        }

        /**
         * Agrega una expresión para un dígito (0-9).
         */
        public void addDigit() {
            regex.append("[0-9]");
        }

        /**
         * Agrega una expresión para un espacio en blanco.
         */
        public void addWhitespace() {
            regex.append("\\s");
        }

        /**
         * Agrega un carácter específico a la expresión regular.
         * @param c el carácter que el usuario desea agregar.
         */
        public void addSpecificCharacter(char c) {
            regex.append(c);
        }

        /**
         * Retorna la expresión regular construida hasta el momento.
         * @return la expresión regular como cadena.
         */
        public String getRegex() {
            return regex.toString();
        }

        /**
         * Reinicia el generador, limpiando la expresión regular actual.
         */
        public void reset() {
            regex.setLength(0);
        }
    }

    /**
     * Clase encargada de adaptar las expresiones regulares generadas
     * para ser utilizadas en otros lenguajes como Python o JavaScript.
     */
    public static class RegexAdapter {
        /**
         * Adapta una expresión regular para su uso en Python.
         * @param regex la expresión regular a adaptar.
         * @return la expresión adaptada en formato Python.
         */
        public static String adaptToPython(String regex) {
            return "r\"" + regex + "\"";
        }

        /**
         * Adapta una expresión regular para su uso en JavaScript.
         * @param regex la expresión regular a adaptar.
         * @return la expresión adaptada en formato JavaScript.
         */
        public static String adaptToJavaScript(String regex) {
            return "/" + regex + "/";
        }
    }

    /**
     * Clase encargada de validar una cadena de texto contra la expresión regular generada.
     * Este validador no utiliza librerías externas y valida carácter por carácter.
     */
    public static class RegexValidator {
        private String regex;

        public RegexValidator(String regex) {
            this.regex = regex;
        }

        /**
         * Valida si la cadena ingresada cumple con la expresión regular.
         * @param input la cadena a validar.
         * @return true si la cadena cumple con la expresión regular, de lo contrario false.
         */
        public boolean validate(String input) {
            return input.matches(regex);
        }
    }

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Generador de Expresiones Regulares");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        RegexGenerator generator = new RegexGenerator();

        // Panel superior para la construcción de la expresión regular
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(2, 1));
        JLabel label = new JLabel("Seleccione una opción para construir la expresión regular:");
        JTextArea generatedRegexArea = new JTextArea(2, 20);
        generatedRegexArea.setEditable(false);
        panelTop.add(label);
        panelTop.add(generatedRegexArea);

        // Panel de botones para agregar partes a la expresión regular
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 6));

        JButton addLetterButton = new JButton("Letra");
        JButton addDigitButton = new JButton("Dígito");
        JButton addWhitespaceButton = new JButton("Espacio");
        JButton addSpecificCharButton = new JButton("Caracter");
        JButton finishButton = new JButton("Finalizar");
        JButton clearButton = new JButton("Limpiar");

        buttonPanel.add(addLetterButton);
        buttonPanel.add(addDigitButton);
        buttonPanel.add(addWhitespaceButton);
        buttonPanel.add(addSpecificCharButton);
        buttonPanel.add(finishButton);
        buttonPanel.add(clearButton);

        // Panel inferior para validar cadenas y mostrar adaptaciones
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(6, 1));

        JTextField inputField = new JTextField(20);
        JLabel resultLabel = new JLabel("Ingrese una cadena para validar:");
        JButton validateButton = new JButton("Validar");
        JLabel validationResult = new JLabel("");
        JLabel pythonLabel = new JLabel("Expresión regular adaptada a Python: ");
        JLabel jsLabel = new JLabel("Expresión regular adaptada a JavaScript: ");

        panelBottom.add(resultLabel);
        panelBottom.add(inputField);
        panelBottom.add(validateButton);
        panelBottom.add(validationResult);
        panelBottom.add(pythonLabel);
        panelBottom.add(jsLabel);

        // Acciones de los botones
        addLetterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatedRegexArea.append("[a-zA-Z]");
            }
        });

        addDigitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatedRegexArea.append("[0-9]");
            }
        });

        addWhitespaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatedRegexArea.append("\\s");
            }
        });

        addSpecificCharButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String charInput = JOptionPane.showInputDialog(frame, "Ingrese el carácter específico:");
                if (charInput != null && charInput.length() > 0) {
                    generatedRegexArea.append(charInput);
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regex = generatedRegexArea.getText();
                String pythonRegex = RegexAdapter.adaptToPython(regex);
                String jsRegex = RegexAdapter.adaptToJavaScript(regex);

                JOptionPane.showMessageDialog(frame, "Expresión regular generada: " + regex);
                pythonLabel.setText("Expresión regular adaptada a Python: " + pythonRegex);
                jsLabel.setText("Expresión regular adaptada a JavaScript: " + jsRegex);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatedRegexArea.setText("");
            }
        });

        validateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regex = generatedRegexArea.getText();
                RegexValidator validator = new RegexValidator(regex);
                String input = inputField.getText();

                if (validator.validate(input)) {
                    validationResult.setText("Cadena válida.");
                } else {
                    validationResult.setText("Cadena inválida.");
                }
            }
        });

        // Agregar paneles al frame principal
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}