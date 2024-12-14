package it.unibo.es3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicsImpl implements Logics {

    private Set<Pair<Integer,Integer>> cells = new HashSet<>();
    private final List<Pair<Integer, Integer>> helper = new LinkedList<>();
    private Integer size;

    public LogicsImpl(int size) {
        this.size = size;
        Random rd = new Random();
        while (this.cells.size() < 3) {
            this.cells.add(new Pair<Integer,Integer>(rd.nextInt(size), rd.nextInt(size)));
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                helper.add(new Pair<Integer, Integer>(i, j));
            }
        }
    }

    @Override
    public void executeNextStep() {
        this.cells = this.cells.stream().flatMap(e -> this.getNeighbour(e)).collect(Collectors.toSet());
    }

    private Stream<Pair<Integer,Integer>> getNeighbour(Pair<Integer, Integer> source){
        return this.helper.stream().filter(current -> this.isNeighbour(current, source));
    }
        
    private boolean isNeighbour(Pair<Integer,Integer> current, Pair<Integer,Integer> source) {
        return Math.abs(current.getX() - source.getX()) <= 1 && Math.abs(current.getY() - source.getY()) <= 1;
    }
        
    @Override
    public String setText(Pair<Integer, Integer> pair) {
        return cells.contains(pair) ? "*" : "";
    }

    @Override
    public boolean doClose() {
        return cells.size() == this.size * this.size;
    }

}
