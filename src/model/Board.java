package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<List<Cell>> cells;

    public Board(Integer size){
        cells = new ArrayList<>();
        for(int i = 0; i < size; i++){
            cells.add( new ArrayList<Cell>(size));
            for(int j = 0; j < size; j++){
                cells.get(i).add(new Cell());
            }
        }
        System.out.println(cells);
    }
}
