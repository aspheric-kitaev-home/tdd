package ru.niioep.tdd;

import ru.niioep.tdd.money.Dollar;
import ru.niioep.tdd.money.Money;

public class Main {
    public static void main(String[] args) {
        Dollar dollar = new Dollar(5);
        Dollar dollar1 = new Dollar(4);

        System.out.println(dollar.equals(dollar1));
    }
}
