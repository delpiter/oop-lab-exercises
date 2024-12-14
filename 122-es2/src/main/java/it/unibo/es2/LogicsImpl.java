package it.unibo.es2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics{

    private static final String TEXT_TO_SHOW = "*";
    private List<List<String>> grid;

    /**
     * 
     * @param size the size of the matrix.
     */
    public LogicsImpl(final int size){
        this.grid = IntStream
            .range(0, size)
            .mapToObj(x -> 
                IntStream
                .range(0, size)
                .mapToObj(y -> " ")
                .collect(Collectors.toList()))
            .collect(Collectors.toList());
    }
    
    @Override
    public String getTextToShow(final Pair<Integer, Integer> position) {
        String show = this.grid.get(position.getX()).get(position.getY()) == " " ? TEXT_TO_SHOW : " "; 
        this.grid.get(position.getX()).set(position.getY(), show);
        return show;
    }

    @Override
    public boolean quit() {
        return checkColumns() || checkRows(); // 
    }

    private boolean checkRows(){
        return this.grid.stream().anyMatch((row -> row.stream().allMatch(x -> x.equals(TEXT_TO_SHOW))));
    }

    private boolean checkColumns(){
        return IntStream
            .range(0, this.grid.size())
            .mapToObj(i -> grid.stream().map(x -> x.get(i)).allMatch(x -> x.equals(TEXT_TO_SHOW))).anyMatch(x -> x == true);
    }

}