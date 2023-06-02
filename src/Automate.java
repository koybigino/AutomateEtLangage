import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automate {

    public List<String> alphabet, ensembleEtats, etatsInitiaux, etatsFinaux;
    public List<List<List<String>>> fonctionDeTransition;

    public Automate(List<String> alphabet, List<String> ensembleEtats, List<String> etatsInitiaux, List<String> etatsFinaux, List<List<List<String>>> fonctionDeTransition) {
        this.alphabet = alphabet;
        this.ensembleEtats = ensembleEtats;
        this.etatsInitiaux = etatsInitiaux;
        this.etatsFinaux = etatsFinaux;
        this.fonctionDeTransition = fonctionDeTransition;
    }

    public boolean isAFD() {

        if (etatsInitiaux.size() != 1) return false;

        for (List<List<String>> column : fonctionDeTransition) {
            for (List<String> cell : column) { // Each cell contains the destination states of a transition
                if (cell.size() != 1) { // If there is a transition that contains more than 1 destination state
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isAFN() {

        if (etatsInitiaux.size() > 1) return true;

        for (List<List<String>> column : fonctionDeTransition) {
            for (List<String> cell : column) { // Each cell contains the destination states of a transition
                if (cell.size() != 1) { // If there is a transition that contains more than 1 destination state
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isEpsilonAFN() {

        int epsilonIndex = alphabet.indexOf("");
        if (!isAFN() || epsilonIndex == -1) return false;

        for (List<List<String>> row : fonctionDeTransition) {
            List<String> cell = row.get(epsilonIndex);
            if (cell.size() != 0) return true;
        }
        return false;
    }

    public Map<String, Object> determinisation() {
        List<String> initialState = this.etatsInitiaux;
        List<List<String>> finalStates = new ArrayList<>();
        List<List<String>> states = new ArrayList<>();
        List<List<String>> statesTransition = new ArrayList<>();
        List<List<List<String>>> transitionFonction = new ArrayList<>();
        List<String> transitionState = new ArrayList<>();

        states.add(initialState);

        for (String symbole : this.alphabet) {
            int i = this.alphabet.indexOf(symbole);
            for (List<String> state : states) {
                for (String s : state) {

                    int index = this.ensembleEtats.indexOf(s);

                    List<String> fonctionTransEi = this.fonctionDeTransition.get(index).get(i);

                    transitionState.addAll(fonctionTransEi);
                }

                statesTransition.add(transitionState);
                if(!states.containsAll(transitionState))
                    states.add(transitionState);

                transitionState.clear();
            }

            transitionFonction.add(statesTransition);
            statesTransition.clear();
        }

        for (String s : this.etatsFinaux) {
            for (List<String> st : states) {
                if(st.contains(s))
                    finalStates.add(st);
            }
        }

        Map<String, Object> AutomateDeterminise = new HashMap<>();

        AutomateDeterminise.put("alphabet", this.alphabet);
        AutomateDeterminise.put("ensembleEtats", states);
        AutomateDeterminise.put("etatsInitiaux", initialState);
        AutomateDeterminise.put("etatsFinaux", finalStates);
        AutomateDeterminise.put("fonctionDeTransition", transitionFonction);

        return AutomateDeterminise;
    }

}
