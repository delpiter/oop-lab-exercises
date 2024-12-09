package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

	private int size;
	private List<Integer> valuesList;
	private List<Boolean> enabledList;

	public LogicsImpl(int size) {
		this.size = size;
		this.valuesList = new ArrayList<>(this.size);
		this.enabledList = new ArrayList<>(this.size);
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toQuit'");
	}
}
