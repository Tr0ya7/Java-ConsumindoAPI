import java.util.ArrayList;
import java.util.List;

public class User {
    private float value;
    private List<String> buysList = new ArrayList<>();

    double getValue() {
        return value;
    }

    void setValue(float addValue) {
        value += addValue;
    }

    List getBuyList() {
        return buysList;
    }

    void setBuyList(String productName) {
        buysList.add(productName);
    }

    boolean withDraw(float withDrawValue) {
        if(value >= withDrawValue) {
            value -= withDrawValue;
            return true;
        }
        System.out.println("Erro, valor a ser retirado maior que o valor da conta");
        return false;
    }

    boolean transaction(float transactionValue) {
        if(value >= transactionValue) {
           withDraw(transactionValue);
           return true;
        }
        return false;
    }
}