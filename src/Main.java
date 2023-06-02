import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutomateInitialisation autoInit = new AutomateInitialisation();

        List<String> alphabet = autoInit.initialisationAlphabet("symbole");

        List<String> ensembleEtats = autoInit.initialisationEnsembleEtats();

        List<String> etatsInit = autoInit.initialisationEtatsInitiaux(ensembleEtats, "initiaux");

        List<String> etatsFi = autoInit.initialisationEtatsFinaux(ensembleEtats);

        List<List<List<String>>> fonctionTrans = autoInit.initialisationFonctionTransition(ensembleEtats, alphabet);

        Automate auto = new Automate(alphabet, ensembleEtats, etatsInit, etatsFi, fonctionTrans);

        System.out.printf("L'automate est-il un AFD ? %b\n", auto.isAFD());
        System.out.printf("L'automate est-il un AFN ? %b\n", auto.isAFN());
        System.out.printf("L'automate est-il un EpsilonAFN ? %b\n", auto.isEpsilonAFN());

        auto.determinisation();





        /*for (int i = 0; i < fonctionTrans.size(); i++) {
            for (int j = 0; j < fonctionTrans.get(i).size(); j++) {
                System.out.print("[");

                for (int k = 0; k < fonctionTrans.get(i).get(j).size(); k++) {
                    if(k == fonctionTrans.get(i).get(j).size() - 1)
                        System.out.printf("%s", fonctionTrans.get(i).get(j).get(k));
                    else
                        System.out.printf("%s,", fonctionTrans.get(i).get(j).get(k));
                }

                if(j == fonctionTrans.get(i).size() - 1)
                    System.out.printf("]\n", fonctionTrans.get(i).get(j));
                else
                    System.out.print("] ");
            }
        }*/
    }
}