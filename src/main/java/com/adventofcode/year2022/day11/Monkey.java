package com.adventofcode.year2022.day11;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Objects;

// Monkey
//   - list of items
//     - contains worry level for each item, List in order will be inspected
//   - operation - to update the worry level
//   - test - shows how the monkey will decide where to throw the item
public class Monkey {

    private int number;
    private BigInteger divisibleBy = new BigInteger("-1");
    private int trueMonkey = -1;
    private int falseMonkey = -1;
    private char operator;
    private String operand;
    private LinkedList<Item> items = new LinkedList<>();
    private long itemInspectionCount;

    public Monkey(String number) {
        this.number = Integer.parseInt(number);
    }

    public void addItem(Item item) {
        if (null == item) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
    }

    public int getNumber() {
        return number;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public BigInteger getDivisibleBy() {
        return divisibleBy;
    }

    public void setDivisibleBy(BigInteger divisibleBy) {
        this.divisibleBy = divisibleBy;
    }

    public int getTrueMonkey() {
        return trueMonkey;
    }

    public void setTrueMonkey(int trueMonkey) {
        this.trueMonkey = trueMonkey;
    }

    public int getFalseMonkey() {
        return falseMonkey;
    }

    public void setFalseMonkey(int falseMonkey) {
        this.falseMonkey = falseMonkey;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public void incrementItemInspectionCount() {
        itemInspectionCount++;
    }

    public long getItemInspectionCount() {
        return itemInspectionCount;
    }

    public void resetItemInspectionCount() {
        itemInspectionCount = 0;
    }

    @Override
    public String toString() {
        return (number > 0 ? '\n' : "") +
                "Monkey{" +
                "number=" + number +
                ", divisibleBy=" + divisibleBy +
                ", trueMonkey=" + trueMonkey +
                ", falseMonkey=" + falseMonkey +
                ", operator='" + operator + '\'' +
                ", operand='" + operand + '\'' +
                ", items=" + items +
                '}';
    }

    public String getSummary() {
        StringBuffer sb = new StringBuffer();
        sb.append("Monkey ");
        sb.append(number);
        sb.append(':');
        sb.append(' ');
        boolean first = true;
        for (Item item : items) {
            if (first) {
                first = false;
            }
            else {
                sb.append(',');
                sb.append(' ');
            }
            sb.append(item.worryLevel);
        }
        sb.append('\n');

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monkey monkey = (Monkey) o;
        return number == monkey.number && divisibleBy.equals(monkey.divisibleBy) && trueMonkey == monkey.trueMonkey && falseMonkey == monkey.falseMonkey && Objects.equals(operator, monkey.operator) && Objects.equals(operand, monkey.operand) && Objects.equals(items, monkey.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, divisibleBy, trueMonkey, falseMonkey, operator, operand, items);
    }

    public String getInspectedItemsSummary() {
        StringBuffer sb = new StringBuffer();
        sb.append("Monkey ");
        sb.append(number);
        sb.append(" inspected items ");
        sb.append(itemInspectionCount);
        sb.append(" times.");
        sb.append('\n');

        return sb.toString();
    }
}
class Item {
    BigInteger worryLevel;

    public Item(String worryLevel) {
        this.worryLevel = new BigInteger(worryLevel);
    }

    public BigInteger getWorryLevel() {

        return worryLevel;
    }

    public void setWorryLevel(BigInteger worryLevel) {
        this.worryLevel = worryLevel;
    }

    @Override
    public String toString() {
        return "Item{" +
                "worryLevel=" + worryLevel +
                '}';
    }
}
