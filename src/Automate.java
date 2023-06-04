import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Automate {

    public List<String> alphabet, ensembleEtats, etatsInitiaux, etatsFinaux;
    public List<List<List<String>>> fonctionDeTransition;

    public Automate(List<String> alphabet, List<String> ensembleEtats, List<String> etatsInitiaux,
            List<String> etatsFinaux, List<List<List<String>>> fonctionDeTransition) {
        this.alphabet = alphabet;
        this.ensembleEtats = ensembleEtats;
        this.etatsInitiaux = etatsInitiaux;
        this.etatsFinaux = etatsFinaux;
        this.fonctionDeTransition = fonctionDeTransition;
    }

    public boolean isAFD() {

        if (etatsInitiaux.size() != 1)
            return false;

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

        if (etatsInitiaux.size() > 1)
            return true;

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
        if (!isAFN() || epsilonIndex == -1)
            return false;

        for (List<List<String>> row : fonctionDeTransition) {
            List<String> cell = row.get(epsilonIndex);
            if (cell.size() != 0)
                return true;
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
                if (!states.containsAll(transitionState))
                    states.add(transitionState);

                transitionState.clear();
            }

            transitionFonction.add(statesTransition);
            statesTransition.clear();
        }

        for (String s : this.etatsFinaux) {
            for (List<String> st : states) {
                if (st.contains(s))
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

    public Automate minimisation() {
        System.out.println();
        List<String> etatsFinaux = new ArrayList<>(this.etatsFinaux);
        List<String> etatsNonFinaux = new ArrayList<>(this.ensembleEtats);
        etatsNonFinaux.removeAll(etatsFinaux); // Différence

        Set<List<String>> piZero = new HashSet<>();
        piZero.add(etatsFinaux);
        piZero.add(etatsNonFinaux);

        Set<List<String>> piNext = piZero;
        System.out.println("pi[" + 0 + "] : " + piNext);

        int i = 1;
        while (true) {
            Set<List<String>> piPrev = new HashSet<>(piNext);
            piNext = new HashSet<>();
            boolean skip = false;
            for (String character : this.alphabet) {
                if (skip)
                    continue;
                for (List<String> classs : piPrev) {
                    int numberTargetSets = 0;
                    Map<List<String>, List<String>> classesMap = new HashMap<>();
                    if (!skip) {

                        for (List<String> classs2 : piPrev) {
                            classesMap.put(classs2, new ArrayList<>());
                        }
                        for (String state : classs) {
                            // Getting transitions from state according to the transition table
                            List<String> transitionsOfState = this.fonctionDeTransition
                                    .get(this.ensembleEtats.indexOf(state)).get(this.alphabet.indexOf(character));
                            // We check the transitions between modules of pi[i]
                            for (List<String> statesSet2 : piPrev) {
                                // Calculating the intersection between the transitions from the transitions
                                // table
                                // and each states module from pi[i]
                                List<String> bufferer = new ArrayList<>(transitionsOfState);
                                bufferer.retainAll(statesSet2); // Intersection
                                // We verify if the intersection between the transition inside the transition
                                // table and each
                                // module of pi[i] is empty
                                if (!bufferer.isEmpty()) { // If the intersection is not empty
                                    classesMap.get(statesSet2).add(state);
                                    numberTargetSets++;
                                }
                            }
                        }
                    }

                    // If there is more than 1 set for which the intersection is not empty for the
                    // same character
                    // of the alphabet, it means that the set is not uniform on that character
                    // Then we must split that set
                    // System.out.println(maama);
                    if (numberTargetSets == 1 || skip) { // In normal contitions, this equality would be true
                        piNext.add(classs);
                    } else {
                        for (List<String> class2 : classesMap.keySet()) {
                            if (classesMap.get(class2).size() > 0) {
                                piNext.add(classesMap.get(class2));
                            }
                        }
                        skip = true;
                    }
                }
            }

            System.out.println("pi[" + i + "] : " + piNext);
            if (piNext.equals(piPrev)) {
                System.out.println("STABILITY");
                break;
            }
            i++;
        }

        return generateMinimisedAutomaton(new ArrayList<>(piNext));
    }

    private Automate generateMinimisedAutomaton(List<List<String>> pi) {

        Map<List<String>, String> matchingTable = new HashMap<>();
        for (int i = 0; i < pi.size(); i++) {
            matchingTable.put(pi.get(i), "" + i);
        }

        List<String> alphabet = this.alphabet;

        List<String> states = new ArrayList<>();
        for (List<String> classs : pi) {
            states.add(matchingTable.get(classs));
        }

        System.out.println(states);

        List<String> initialStates = new ArrayList<>();

        for (List<String> classs : pi) {
            List<String> bufferer = new ArrayList<>(classs);
            bufferer.retainAll(this.etatsInitiaux);
            if (!bufferer.isEmpty()) {
                initialStates.add(matchingTable.get(classs));
            }
        }

        System.out.println(initialStates);

        List<String> finalStates = new ArrayList<>();

        for (List<String> classs : pi) {
            List<String> bufferer = new ArrayList<>(classs);
            bufferer.retainAll(this.etatsFinaux);
            if (!bufferer.isEmpty()) {
                finalStates.add(matchingTable.get(classs));
            }
        }

        List<List<List<String>>> transtionTable = new ArrayList<>();
        for (List<String> classs : pi) {
            for (String state : classs) {
                List<List<String>> row = new ArrayList<>();
                transtionTable.add(row);
                for (String character : this.alphabet) {

                    List<String> transitions = this.fonctionDeTransition.get(this.ensembleEtats.indexOf(state))
                            .get(this.alphabet.indexOf(character));

                    Set<String> renamedTransitions = new HashSet<>();

                    for (List<String> classs2 : pi) {
                        List<String> bufferer = new ArrayList<>(classs2);
                        bufferer.retainAll(transitions);
                        if (!bufferer.isEmpty()) {
                            renamedTransitions.add(matchingTable.get(classs2));
                        }
                    }
                    row.add(new ArrayList<>(renamedTransitions));
                }
                break;
            }
        }

        return new Automate(alphabet, states, initialStates, finalStates, transtionTable);
    }
}
