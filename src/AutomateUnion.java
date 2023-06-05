import java.util.ArrayList;
import java.util.List;

public class AutomateUnion extends AutomateReconnaissance {
    private AutomateReconnaissance A1, A2;

    public AutomateUnion(AutomateReconnaissance a1, AutomateReconnaissance a2) {
        A1 = a1;
        A2 = a2;

        etatsInit.addAll(a1.getEtatsInit());
        etatsInit.addAll(a2.getEtatsInit());

        alphabet.addAll(a1.getAlphabet());
        alphabet.addAll(a2.getAlphabet());

        type = a1.getType() + ", " + a2.getType();
    }

    public void unionAutomate() {
        List<List<String>> finalStates = new ArrayList<>();
        List<List<String>> states = new ArrayList<>();
        List<List<String>> statesPassed = new ArrayList<>();
        List<List<String>> allTrasitionForAState = new ArrayList<>();
        List<List<List<String>>> transitionFonction = new ArrayList<>();
        List<String> transitionStateForASymbol = new ArrayList<>();
        //List<String> state = this.etatsInitiaux;

        states.add(etatsInit);

        int j = 0;
        while(true){
            int size = states.size();
            List<String> state = states.get(j);
            for (String symbole : alphabet) {
                for (String s : state) {
                    List<String> transitionFonctionEi;

                    if (A1.getAlphabet().contains(symbole) && A1.getEnsembleEtats().contains(s)) {
                        int index = A1.getEnsembleEtats().indexOf(s);
                        int i = A1.getAlphabet().indexOf(symbole);
                        transitionFonctionEi = A1.getFonctionTrans().get(index).get(i);

                        transitionStateForASymbol.addAll(transitionFonctionEi);

                    }
                    else if(A2.getAlphabet().contains(symbole) && A2.getEnsembleEtats().contains(s)){
                        int index = A2.getEnsembleEtats().indexOf(s);
                        int i = A2.getAlphabet().indexOf(symbole);
                        transitionFonctionEi = A2.getFonctionTrans().get(index).get(i);

                        transitionStateForASymbol.addAll(transitionFonctionEi);
                    }
                }
                allTrasitionForAState.add(transitionStateForASymbol);

                if (!states.contains(transitionStateForASymbol) && !transitionStateForASymbol.isEmpty()) {
                    if(!transitionStateForASymbol.get(0).isEmpty()) states.add(transitionStateForASymbol);
                }
                transitionStateForASymbol = new ArrayList<>();
            }
            statesPassed.add(state);
            transitionFonction.add(allTrasitionForAState);
            allTrasitionForAState = new ArrayList<>();
            if(statesPassed.size() == states.size()) break;
            else j++;
        }


        for (List<String> state : states) {
            String etat = "";
            String etatFinal = "";
            for (String s : state) {
                if(!s.isBlank()) etat = etat + s;
                if (A1.getEtatsFi().contains(s) || A2.getEtatsFi().contains(s)) etatFinal = etatFinal + s;
            }
            if(etatFinal != "") etatsFi.add(etatFinal);
            if(etat != "") ensembleEtats.add(etat);
        }

        List<String> finialInitStates = new ArrayList<>();
        String e = "";
        for (String s : etatsInit) {
            e = e + s;
        }

        finialInitStates.add(e);
        etatsInit = finialInitStates;

        this.complement();

        auto = new Automate(alphabet, ensembleEtats, etatsInit, etatsFi, transitionFonction);
    }

    @Override
    public void reconnaissanceMot(String sentence){

        String[] sentenceArrayWord = sentence.split(" ");

        String output = "";

        for (String word : sentenceArrayWord) {
            String result = auto.reconnaissanceMot(word);

            if (result.equals("valide"))
                output = output + "<" + word + ":" + this.type + ">";
            else
                output = output + "<" + word + ":" + "unknow" + ">";
        }

        System.out.println(output);

        System.out.println(alphabet);
        System.out.println(ensembleEtats);
        System.out.println(etatsInit);
        System.out.println(etatsFi);

        System.out.println("Fonction de transition de l'automate déterminisé !");
        for (int i = 0; i < fonctionTrans.size(); i++) {
            for (int r = 0; r < fonctionTrans.get(i).size(); r++) {
                System.out.print(fonctionTrans.get(i).get(r) + " ");
            }
            System.out.println();
        }
    }
}
