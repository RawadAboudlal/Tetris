package com.rawad.tetris.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.rawad.tetris.entity.ETetrominoes;

/**
 * @author Rawad
 *
 */
public class TetrominoGenerator {
	
	private static final List<ETetrominoes> TETROMINOES_LIST = Collections.unmodifiableList(Arrays
			.asList(ETetrominoes.values()));
	
	private LinkedList<ETetrominoes> tetrominoesBag = new LinkedList<ETetrominoes>();
	
	private Random random = new Random();
	
	public ETetrominoes generateTetromino() {
		
		if(tetrominoesBag.isEmpty()) this.refillBag();
		
		return tetrominoesBag.remove(random.nextInt(tetrominoesBag.size()));
		
	}
	
	private void refillBag() {
		tetrominoesBag.clear();
		tetrominoesBag.addAll(TETROMINOES_LIST);
	}
	
}
