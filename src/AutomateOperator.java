import java.util.ArrayList;
import java.util.List;

public class AutomateOperator extends AutomateReconnaissance {


    public AutomateOperator() {
        this.type = "Operator";

        alphabet.add("-");
        alphabet.add("+");
        alphabet.add("/");
        alphabet.add("*");
        alphabet.add("=");
        alphabet.add("<");
        alphabet.add(">");
        alphabet.add("!");

        ensembleEtats.add("1");
        ensembleEtats.add("2");
        ensembleEtats.add("3");


        etatsInit.add("1");


        etatsFi.add("2");
        etatsFi.add("3");


        List<String> cell = new ArrayList<>();
        List<List<String>> row = new ArrayList<>();

        cell.add("2");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("2");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("2");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("2");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        fonctionTrans.add(row);
        row = new ArrayList<>();
        //fsdfsdfdsfdsffffffffffffffffffffffff

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        fonctionTrans.add(row);
        row = new ArrayList<>();
        //fsdfsdfdsfdsffffffffffffffffffffffff


        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("3");
        row.add(cell);
        cell = new ArrayList<>();

        fonctionTrans.add(row);
        row = new ArrayList<>();
        //fsdfsdfdsfdsffffffffffffffffffffffff

        auto = new Automate(alphabet, ensembleEtats, etatsInit, etatsFi, fonctionTrans);
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
