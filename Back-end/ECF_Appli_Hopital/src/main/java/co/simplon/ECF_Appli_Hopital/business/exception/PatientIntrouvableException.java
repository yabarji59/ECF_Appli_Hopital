package co.simplon.ECF_Appli_Hopital.business.exception;

// définition de la classe PatientIntrouvableException qui hérite de RuntimeException
public class PatientIntrouvableException extends RuntimeException {

    // constructeur prenant un message en paramètre
    public PatientIntrouvableException(String message) {
        // appelle le constructeur de la classe parent (RuntimeException) avec le message spécifié
        super(message);
    }
}