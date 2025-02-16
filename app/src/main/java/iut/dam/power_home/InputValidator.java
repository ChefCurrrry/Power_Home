package iut.dam.power_home;

public class InputValidator {

    /**
     * Vérifie si le prénom et le nom sont valides (entre 2 et 25 caractères).
     */
    public static boolean isValidName(String name) {
        return name != null && name.length() >= 2 && name.length() <= 25;
    }

    /**
     * Vérifie si l'e-mail est valide (doit contenir '@' et un domaine).
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Vérifie si le mot de passe respecte les règles :
     * - Au moins une lettre
     * - Au moins un chiffre
     * - Au moins un caractère spécial
     * - Au moins 8 caractères
     */
    public static boolean isValidPassword(String password) {
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[^a-zA-Z0-9].*");
        boolean isLongEnough = password.length() >= 8;

        return hasLetter && hasDigit && hasSpecialChar && isLongEnough;
    }

    /**
     * Fonction principale pour valider les champs (nom, prénom, email, mot de passe).
     */
    public static boolean validateInputs(String prenomInsert, String nomInsert, String emailInsert, String passwordInsert) {
        if (!isValidName(prenomInsert)) {
            System.out.println("Prenom Invalide");
            return false;
        }
        if (!isValidName(nomInsert)) {
            System.out.println("Nom Invalide");
            return false;
        }
        if (!isValidEmail(emailInsert)) {
            System.out.println("Email Invalide");
            return false;
        }
        if (!isValidPassword(passwordInsert)) {
            System.out.println("Mot de Passe Invalide");
            return false;
        }
        return true;
    }
}