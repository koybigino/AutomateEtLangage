import java.util.ArrayList;
import java.util.List;

public class AutomateEntier extends  AutomateReconnaissance {
    public AutomateEntier() {
        this.type = "Entier";

        alphabet.add("0");
        alphabet.add("1");
        alphabet.add("2");
        alphabet.add("3");
        alphabet.add("4");
        alphabet.add("5");
        alphabet.add("6");
        alphabet.add("7");
        alphabet.add("8");
        alphabet.add("9");

        ensembleEtats.add("A");

        etatsInit.add("A");

        etatsFi.add("A");

        List<String> cell = new ArrayList<>();
        List<List<String>> row = new ArrayList<>();
        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        cell.add("A");
        row.add(cell);
        cell = new ArrayList<>();

        fonctionTrans.add(row);
        row = new ArrayList<>();
        //fsdfsdfdsfdsffffffffffffffffffffffff

        auto = new Automate(alphabet, ensembleEtats, etatsInit, etatsFi, fonctionTrans);
    }
}
