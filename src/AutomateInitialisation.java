import java.util.*;

public class AutomateInitialisation {

    public List<String> initialisationAlphabet(String mot) {
        Scanner sc = new Scanner(System.in);

        String symbole;

        System.out.printf("Entrez la liste des %s séparés des virgule : ", mot);

        symbole = sc.nextLine();

        List<String> symboles = new ArrayList<>();


        for (String s : symbole.split(",")) {
            symboles.add(s);
        }

        return symboles;
    }

    public List<String> initialisationEnsembleEtats() {
        return this.initialisationAlphabet("états");
    }

    public List<String> initialisationEtatsInitiaux(List<String> ensembleEtats, String mot) {
        List<String> etatsInit = this.initialisationAlphabet("états " + mot);

        while (!ensembleEtats.containsAll(etatsInit)) {
            System.out.printf("Vos états %s doivent etre compris dans l'ensemble de tous les états!\nVeillez réessayer !\n", mot);
            etatsInit = this.initialisationAlphabet("états" + mot);
        }


        return etatsInit;
    }

    public List<String> initialisationEtatsFinaux(List<String> ensembleEtats) {
        return this.initialisationEtatsInitiaux(ensembleEtats, "finaux");
    }

    public List<List<List<String>>> initialisationFonctionTransition(List<String> ensembleEtats, List<String> alphabet) {

        Scanner sc = new Scanner(System.in);
        List<List<List<String>>> fonction = new ArrayList<>();

        for (int i = 0; i < ensembleEtats.size(); i++) {
            List<List<String>> table = new ArrayList<>();

            for (int j = 0; j < alphabet.size(); j++) {
                String etats;
                System.out.printf("Etant à l'état %s lorsqu'on lit %s on va vers l'état (écrivé les états d'arrivés séparés pardes virgules) : ", ensembleEtats.get(i), alphabet.get(j));

                etats = sc.nextLine();

                if (etats.isBlank() || etats.isEmpty()){
                    List<String> states = new ArrayList<>();
                    table.add(states);
                }else {
                    List<String> states = new ArrayList<>();

                    for (String e : etats.split(",")) {
                        states.add(e);
                    }

                    while (!ensembleEtats.containsAll(states)) {

                        System.out.println("Vos états doivent etre compris dans l'ensemble de tous les états!\nVeillez réessayer !\n");

                        System.out.printf("Etant à l'état %s lorsqu'on lit %s on va vers l'état (écrivé les états d'arrivés séparés pardes virgules) : ", ensembleEtats.get(i), alphabet.get(j));

                        etats = sc.nextLine();

                        if (etats.isBlank() || etats.isEmpty()) {
                            states = new ArrayList<>();
                        }

                        states.clear();
                    }

                    table.add(states);
                }
            }

            fonction.add(table);
        }

        return fonction;
    }
}