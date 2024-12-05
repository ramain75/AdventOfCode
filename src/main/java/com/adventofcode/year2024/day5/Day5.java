package com.adventofcode.year2024.day5;

import java.util.*;

public class Day5 {

    private record PageOrderingRule (int x, int y) {}

    private record PagesToProduce (List<Integer> pages) {
        private PagesToProduce(List<Integer> pages) {
            // I want this to be mutable, but not the same object as was passed in, in case this is a copy
            this.pages = new ArrayList<>(pages);
        }

        private int getMiddlePageNumber() {
            if (pages.size() % 2 == 1) {
                return pages.get(pages.size() / 2);
            } else {
                throw new RuntimeException("No middle page number");
            }
        }
    }

    private Set<PageOrderingRule> pageOrderingRules = new HashSet<>();
    private List<PagesToProduce> updates = new ArrayList<>();

    public Day5(String input) {
        parseInput(input);
    }

    private void parseInput(String input) {
        String[] lines = input.split("\\r?\\n");
        int lineNumber = 0;
        while (lineNumber < lines.length && !lines[lineNumber].isBlank()) {
            String[] pages = lines[lineNumber].split("\\|");
            if (pages.length != 2) {
                throw new RuntimeException("Invalid page ordering rule on line " + lineNumber + ": " + lines[lineNumber]);
            }

            int x = Integer.parseInt(pages[0]);
            int y = Integer.parseInt(pages[1]);
            pageOrderingRules.add(new PageOrderingRule(x, y));

            lineNumber++;
        }

        // Skip the empty line
        lineNumber++;

        while (lineNumber < lines.length) {
            List<Integer> update = Arrays.stream(lines[lineNumber].split(",")).map(Integer::parseInt).toList();
            if (update.isEmpty()) {
                throw new RuntimeException("Invalid update on line " + lineNumber + ": " + lines[lineNumber]);
            }
            PagesToProduce pagesToProduce = new PagesToProduce(update);
            updates.add(pagesToProduce);

            lineNumber++;
        }
    }

    public int addMiddlePageNumbersForValidUpdates() {
        int sum = 0;
        for (int i = 0; i < updates.size(); i++) {
            sum += getMiddlePageNumberIfValidPrintOrder(updates.get(i));
        }

        return sum;
    }

    private int getMiddlePageNumberIfValidPrintOrder(PagesToProduce pagesToProduce) {
        // if nothing breaks the rules, get the middle element, otherwise return 0
        return isValidUpdate(pagesToProduce) ? pagesToProduce.getMiddlePageNumber() : 0;
    }

    private boolean isValidUpdate(PagesToProduce pagesToProduce) {
        // check all the pairs
        for (int i = 0; i < pagesToProduce.pages.size() - 1; i++) {
            for (int j = i + 1; j < pagesToProduce.pages.size(); j++) {
                PageOrderingRule rule = new PageOrderingRule(pagesToProduce.pages().get(i), pagesToProduce.pages().get(j));
                PageOrderingRule inverseRule = new PageOrderingRule(pagesToProduce.pages().get(j), pagesToProduce.pages().get(i));

                if (pageOrderingRules.contains(inverseRule) && !pageOrderingRules.contains(rule)) {
                    // rules broken so invalid
                    return false;
                }
            }
        }

        // valid
        return true;
    }

    public int fixAndAddMiddlePageNumbersForInvalidUpdates() {
        int sum = 0;
        for (int i = 0; i < updates.size(); i++) {
            if (!isValidUpdate(updates.get(i))) {
                // Incorrectly ordered so need to put in right order based on the rules
                PagesToProduce pagesToProduce = getCorrectOrder(updates.get(i));
                sum += pagesToProduce.getMiddlePageNumber();
            }
        }

        return sum;
    }

    private PagesToProduce getCorrectOrder(PagesToProduce pagesToProduce) {
        // I'm making a copy here so I don't update the original and the results are idempotent
        PagesToProduce localPages = new PagesToProduce(pagesToProduce.pages());
        // check all the pairs
        for (int i = 0; i < localPages.pages.size() - 1; i++) {
            for (int j = i + 1; j < localPages.pages.size(); j++) {
                PageOrderingRule rule = new PageOrderingRule(localPages.pages().get(i), localPages.pages().get(j));
                PageOrderingRule inverseRule = new PageOrderingRule(localPages.pages().get(j), localPages.pages().get(i));

                if (pageOrderingRules.contains(inverseRule) && !pageOrderingRules.contains(rule)) {
                    // rules broken so invalid, try swapping the values
                    List<Integer> pages = localPages.pages();
                    int ii = pages.get(i);
                    int jj = pages.get(j);
                    pages.set(i, jj);
                    pages.set(j, ii);
                }
            }
        }

        return localPages;
    }
}
