package com.allenmiln.ArcanoidHelpers;


import com.allenmiln.ArcanoidGameObjects.Block;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class BlockHelpers {
    private ArrayList<Block> blockMatrix = new ArrayList<>();

    public void load(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MathUtils.random(0, 1) == 1) {
                    blockMatrix.add(new Block(20 + j * 20, 5 + i * 20, 15, 15));
                }
            }
        }
    }

    public ArrayList<Block> getBlockMatrix() {
        return blockMatrix;
    }

    public ArrayList<Block> getAliveBlockMatrix() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (Block block: blockMatrix) {
            if (block.isAlive()) {
                blocks.add(block);
            }
        }

        return blocks;
    }

}
