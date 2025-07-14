/*
 * Copyright (c) 2022-2024 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.ds.LongDeque;
import com.github.tommyettinger.ds.LongOrderedSet;
import com.github.tommyettinger.ds.OrderType;
import com.github.tommyettinger.ds.Utilities;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongOrderedSet}s.
 */
public class LongOrderedSetSerializer extends Serializer<LongOrderedSet> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public LongOrderedSetSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongOrderedSet data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeVarInt(data.getOrderType().ordinal(), true);
        for(LongOrderedSet.LongOrderedSetIterator it = new LongOrderedSet.LongOrderedSetIterator(data); it.hasNext();)
            output.writeVarLong(it.nextLong(), false);
    }

    @Override
    public LongOrderedSet read(final Kryo kryo, final Input input, final Class<? extends LongOrderedSet> dataClass) {
        int length = input.readInt(true);
        LongOrderedSet data = new LongOrderedSet(length, Utilities.getDefaultLoadFactor(), ORDER_TYPES[input.readVarInt(true)]);
        for (int i = 0; i < length; i++)
            data.add(input.readVarLong(false));
        return data;
    }

    @Override
    public LongOrderedSet copy(Kryo kryo, LongOrderedSet original) {
        return new LongOrderedSet(original);
    }
}