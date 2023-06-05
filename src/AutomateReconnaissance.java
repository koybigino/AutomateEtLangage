import java.util.ArrayList;
import java.util.List;

public class AutomateReconnaissance {
    protected List<String> alphabet = new ArrayList<>();
    protected List<String> ensembleEtats = new ArrayList<>();
    protected List<String> etatsInit = new ArrayList<>();
    protected List<String> etatsFi = new ArrayList<>();
    protected List<List<List<String>>> fonctionTrans = new ArrayList<>();
    protected Automate auto ;
    protected  String type;

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

    public AutomateReconnaissance complement(){
        List<String> list = new ArrayList<>();
        for (String e : this.ensembleEtats) {
            if(!this.etatsFi.contains(e)) list.add(e);
        }

        this.etatsFi = list;

        return this;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<String> getEnsembleEtats() {
        return ensembleEtats;
    }

    public List<String> getEtatsInit() {
        return etatsInit;
    }

    public List<String> getEtatsFi() {
        return etatsFi;
    }

    public List<List<List<String>>> getFonctionTrans() {
        return fonctionTrans;
    }

    public Automate getAuto() {
        return auto;
    }

    public String getType() {
        return type;
    }
}
