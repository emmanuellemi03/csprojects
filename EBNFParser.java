// Emmanuel Lemi


import java.io.*;
import java.util.*;

public class EBNFParser {
    
    // Character arrays and lists
    static char[] singleChar = {'-', '+', '*', '/', '=', '[', ']', '{', '}', '(', ')', ';', ' ', '<', '>'};
    static char[] listOfNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static List<String> localVars = new ArrayList<>();
    static String[] keywords = {"int", "void", "while", "float"};
    static boolean previouslyKeyword = false;

    // Token list
    static List<Map.Entry<String, String>> vectorToken = new ArrayList<>();
    static int globalIndex = 0;
    static String errorMessage = "";

    // Helper methods
    public static boolean checkChar(char input) {
        for (char c : singleChar) {
            if (input == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(char input) {
        for (char c : listOfNumbers) {
            if (input == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLocalVar(String variableName) {
        return localVars.contains(variableName);
    }

    // Parsing logic
    public static String parseWord(String input, int i) {
        StringBuilder result = new StringBuilder();
        if (!isNumber(input.charAt(i))) {
            while (!checkChar(input.charAt(i))) {
                result.append(input.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static String parseKeywords(String input) {
        if (checkLocalVar(input)) {
            previouslyKeyword = false;
            return "IDENTIFIER";
        }
        if (previouslyKeyword) {
            previouslyKeyword = false;
            localVars.add(input);
            return "IDENTIFIER";
        }

        if (input.equals("void") || input.equals("int") || input.equals("while") || input.equals("float")) {
            previouslyKeyword = true;
            return "KEYWORD";
        }
        return "NULL";
    }

    public static List<Map.Entry<String, String>> parseTokens(String input) {
        List<Map.Entry<String, String>> resultVec = new ArrayList<>();
        String s;
        for (int i = 0; i < input.length(); i++) {
            // Skip whitespace
            if (Character.isWhitespace(input.charAt(i))) {
                continue; // Ignore spaces
            }

            s = "";
            
            if (isNumber(input.charAt(i))) {
                int subIndex = i + 1;
                s += input.charAt(i);
                while (subIndex < input.length() && isNumber(input.charAt(subIndex))) {
                    s += input.charAt(subIndex);
                    subIndex += 1;
                }
                resultVec.add(new AbstractMap.SimpleEntry<>(s, "DIGIT"));
                i = subIndex - 1; // Update i to the last processed index
            } else {
                if (checkChar(input.charAt(i))) {
                    switch (input.charAt(i)) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                        case '=':
                        case '<':
                        case '>':
                            s += input.charAt(i);
                            resultVec.add(new AbstractMap.SimpleEntry<>(s, "OPERATOR"));
                            break;

                        case '(':
                        case '[':
                        case '{':
                            s += input.charAt(i);
                            resultVec.add(new AbstractMap.SimpleEntry<>(s, "L_BRACE"));
                            break;

                        case ')':
                        case ']':
                        case '}':
                            s += input.charAt(i);
                            resultVec.add(new AbstractMap.SimpleEntry<>(s, "R_BRACE"));
                            break;

                        case ';':
                            s += input.charAt(i);
                            resultVec.add(new AbstractMap.SimpleEntry<>(s, "SEMICOLON"));
                            break;

                        default:
                            s += input.charAt(i);
                            resultVec.add(new AbstractMap.SimpleEntry<>(s, "UNKNOWN"));
                            break;
                    }
                } else {
                    s = parseWord(input, i);
                    i += s.length() - 1; // Update i to the last processed index
                    resultVec.add(new AbstractMap.SimpleEntry<>(s, parseKeywords(s)));
                }
            }
        }
        return resultVec;
    }

    // Parsing functions based on EBNF grammar

    public static boolean expected(String token, String expectedToken) {
        if (token.equals(expectedToken)) {
            globalIndex++;
            return true;
        }
        errorMessage = "Expected " + expectedToken + " but found " + token;
        return false;
    }

    public static boolean checkKeyword() {
        if (vectorToken.get(globalIndex).getValue().equals("KEYWORD")) {
            globalIndex++;
            return true;
        }
        errorMessage = "Expected a keyword but found " + vectorToken.get(globalIndex).getKey();
        return false;
    }

    public static boolean checkIdentifier() {
        if (vectorToken.get(globalIndex).getValue().equals("IDENTIFIER")) {
            globalIndex++;
            return true;
        }
        errorMessage = "Expected an identifier but found " + vectorToken.get(globalIndex).getKey();
        return false;
    }

    public static boolean checkConst() {
        if (vectorToken.get(globalIndex).getValue().equals("DIGIT")) {
            globalIndex++;
            return true;
        }
        errorMessage = "Expected a constant but found " + vectorToken.get(globalIndex).getKey();
        return false;
    }

    public static boolean checkDeclares() {
        if (checkKeyword() && checkIdentifier() && expected(vectorToken.get(globalIndex).getKey(), ";")) {
            if (!vectorToken.get(globalIndex).getKey().equals("}")) {
                return checkDeclares();
            }
            return true;
        }
        return false;
    }

    public static boolean checkAssign() {
        if (checkIdentifier() && expected(vectorToken.get(globalIndex).getKey(), "=") && checkConst() && expected(vectorToken.get(globalIndex).getKey(), ";")) {
            return true;
        }
        return false;
    }

    public static boolean checkForLoop() {
        if (expected(vectorToken.get(globalIndex).getKey(), "for") && expected(vectorToken.get(globalIndex).getKey(), "(")) {
            if (checkAssign() && checkAssign() && expected(vectorToken.get(globalIndex).getKey(), ")") && checkAssign()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkProgram() {
        if (checkKeyword() && checkIdentifier() && expected(vectorToken.get(globalIndex).getKey(), "(") &&
            expected(vectorToken.get(globalIndex).getKey(), ")") &&
            expected(vectorToken.get(globalIndex).getKey(), "{")) {
            if (checkDeclares() && checkAssign() && checkForLoop() && expected(vectorToken.get(globalIndex).getKey(), "}")) {
                System.out.println("The program is generated by the EBNF grammar.");
                return true;
            }
        }
        System.out.println("The program cannot be generated by the EBNF grammar.");
        System.out.println("Error: " + errorMessage);
        return false;
    }

    // Main function
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the EBNF Parser Project");
        System.out.println("Please type '1' to designate TEST PROGRAM 1. Please type '2' to designate TEST PROGRAM 2");

        String userInput = scanner.nextLine();

        while (!userInput.equals("1") && !userInput.equals("2")) {
            System.out.println("ERROR: Invalid input. Please input a '1' or '2'.");
            userInput = scanner.nextLine();
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("<-> Parsing Pairs {Lexemes : Tokens} <->\n");

        File file;
        if (userInput.equals("1")) {
            file = new File("TestProgramOne.txt");
        } else {
            file = new File("TestProgramTwo.txt");
        }

        StringBuilder input = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String newLine;
            while ((newLine = br.readLine()) != null) {
                input.append(newLine);
            }
        }

        vectorToken = parseTokens(input.toString()); // Lexical Analyzer

        for (Map.Entry<String, String> token : vectorToken) {
            System.out.println(token.getKey() + " : " + token.getValue());
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("<-> Syntax Analysis {EBNF Grammar} <->\n");
        checkProgram(); // Recursive Descent Parser + EBNF Analyzer
    }
}
