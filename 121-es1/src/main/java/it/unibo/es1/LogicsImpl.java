package it.unibo.es1;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {

	private int size;
	private List<Integer> valuesList;
	private List<Boolean> enabledList;

	public LogicsImpl(int size) {
		this.size = size;
		this.valuesList = IntStream.range(0, size).mapToObj(x -> 0).collect(Collectors.toList());
		this.enabledList = IntStream.range(0, size).mapToObj(x -> true).collect(Collectors.toList());
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<Integer> values() {
		return Collections.unmodifiableList(valuesList);	
	}

	@Override
	public List<Boolean> enablings() {
		return Collections.unmodifiableList(enabledList);
	}

	@Override
	public int hit(int elem) {
		int value = this.valuesList.get(elem) + 1;
		this.valuesList.set(elem, value);
		if(value == this.size){
			this.enabledList.set(elem, false);
		}
		return value;
	}

	@Override
	public String result() {
		return this.valuesList.stream().map(i -> Integer.toString(i)).collect(Collectors.joining("|","<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		int value = valuesList.get(0);
		return valuesList.stream().allMatch(current -> current.equals(value));
	}
}
