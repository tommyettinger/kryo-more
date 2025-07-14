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
import com.github.tommyettinger.ds.LongLongMap;
import com.github.tommyettinger.ds.LongLongOrderedMap;
import com.github.tommyettinger.ds.OrderType;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongLongOrderedMap}s.
 */
public class LongLongOrderedMapSerializer extends Serializer<LongLongOrderedMap> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public LongLongOrderedMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongLongOrderedMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeVarInt(data.getOrderType().ordinal(), true);
        output.writeVarLong(data.getDefaultValue(), false);
        for(Iterator<LongLongMap.Entry> it = new LongLongOrderedMap.OrderedMapEntries(data).iterator(); it.hasNext();) {
            LongLongOrderedMap.Entry ent = it.next();
            output.writeVarLong(ent.key, false);
            output.writeVarLong(ent.value, false);
        }
    }

    @Override
    public LongLongOrderedMap read(final Kryo kryo, final Input input, final Class<? extends LongLongOrderedMap> dataClass) {
        int length = input.readInt(true);
        LongLongOrderedMap data = new LongLongOrderedMap(length, ORDER_TYPES[input.readVarInt(true)]);
        data.setDefaultValue(input.readVarLong(false));
        for (int i = 0; i < length; i++)
            data.put(input.readVarLong(false), input.readVarLong(false));
        return data;
    }

    @Override
    public LongLongOrderedMap copy(Kryo kryo, LongLongOrderedMap original) {
        return new LongLongOrderedMap(original);
    }
}