
public class Main {
    public static void main(String[] args) {
        AutomateInitialisation autoInit = new AutomateInitialisation();

        String sentence = "if a = 5 then 124 else 324";

        AutomateOperator autoOpe = new AutomateOperator();
        AutomateEntier autoEn = new AutomateEntier();

        autoOpe.reconnaissanceMot(sentence);

        autoEn.reconnaissanceMot(sentence);

        AutomateUnion autoU = new AutomateUnion(autoOpe.complement(), autoEn.complement());

        autoU.unionAutomate();

        autoU.reconnaissanceMot(sentence);

        Automate automate = new Automate(); // Automate qui reconnait le langage des caractères
        System.out.println(automate.reconnaissanceMot("/*c**/"));
        System.out.println(automate.reconnaissanceMot("null"));

    }
}