/*
 * Copyright 2004-present Facebook. All Rights Reserved.
 */
package com.facebook.presto.block;

import com.facebook.presto.TupleInfo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.Iterator;

public class BlockUtils
{
    public static BlockIterable toBlocks(Block firstBlock, Block... rest)
    {
        return new BlockIterableAdapter(firstBlock.getTupleInfo(), ImmutableList.<Block>builder().add(firstBlock).add(rest).build());
    }

    public static BlockIterable toBlocks(Iterable<Block> blocks)
    {
        return new BlockIterableAdapter(Iterables.get(blocks, 0).getTupleInfo(), blocks);
    }

    private static class BlockIterableAdapter implements BlockIterable
    {
        private final TupleInfo info;
        private final Iterable<Block> blocks;

        public BlockIterableAdapter(TupleInfo info, Iterable<Block> blocks)
        {
            this.info = info;
            this.blocks = blocks;
        }

        @Override
        public Iterator<Block> iterator()
        {
            return blocks.iterator();
        }
    }
}