package ch.hslu.ad.sw01ex;

import ch.hslu.ad.helper.AsciiTable;

public class Growth {

    public static double log2(long N) {
        return (double) (Math.log((double) N) / Math.log(2));
    }

    public static long factorial(long N) {
        if (N == 0) {
            return 1;
        } else {
            return (N * factorial(N - 1));
        }
    }

    public static void printGrowthTable() {
        AsciiTable table = new AsciiTable();

        table.getColumns().add(new AsciiTable.Column("log n"));
        table.getColumns().add(new AsciiTable.Column("ld n"));
        table.getColumns().add(new AsciiTable.Column("n"));
        table.getColumns().add(new AsciiTable.Column("n * log n"));
        table.getColumns().add(new AsciiTable.Column("n^2"));
        table.getColumns().add(new AsciiTable.Column("n^3"));
        table.getColumns().add(new AsciiTable.Column("2^n"));
        table.getColumns().add(new AsciiTable.Column("3^n"));
        table.getColumns().add(new AsciiTable.Column("n!"));

        int[] ns = {1, 2, 5, 10, 20, 50, 100};

        for (int n : ns) {
            AsciiTable.Row row = new AsciiTable.Row();
            table.getData().add(row);
            row.getValues().add(String.valueOf(Math.log(n)));
            row.getValues().add(String.valueOf(log2(n)));
            row.getValues().add(String.valueOf(n));
            row.getValues().add(String.valueOf(n * Math.log(n)));
            row.getValues().add(String.valueOf(n * n));
            row.getValues().add(String.valueOf(n * n * n));
            row.getValues().add(String.valueOf(Math.pow(2, n)));
            row.getValues().add(String.valueOf(Math.pow(3, n)));
            row.getValues().add(String.valueOf(factorial(n)));
        }

        table.calculateColumnWidth();
        table.render();
    }
}
