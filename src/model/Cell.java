package model;

import enums.CellValue;

public class Cell {
    private CellValue value;
    private User player;

    public Cell(){
        this.player = null;
        this.value = CellValue.EMPTY;
    }

    public void setValue(User player, CellValue cellValue){
        //can add validations
        if(this.player != null || this.value != CellValue.EMPTY){
            System.out.println("Invalid cell value set call"); //try raise exceptions

            return;
        }

        this.player = player;
        this.value = cellValue;
    }

    public CellValue getValue(){
        return this.value;
    }
}
