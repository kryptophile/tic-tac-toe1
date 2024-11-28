package enums;

public enum CellValue {
    EMPTY("_"),
    X("X"),
    O("O");

    private final String name;

    private CellValue(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

    }
