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
import com.github.tommyettinger.ds.IntLongMap;
import com.github.tommyettinger.ds.IntLongOrderedMap;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntLongOrderedMap}s.
 */
public class IntLongOrderedMapSerializer extends Serializer<IntLongOrderedMap> {

    public IntLongOrderedMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntLongOrderedMap data) {
        int length = data.size();
        output.writeInt(length, true);
        for(Iterator<IntLongMap.Entry> it = new IntLongOrderedMap.OrderedMapEntries(data).iterator(); it.hasNext();) {
            IntLongOrderedMap.Entry ent = it.next();
            output.writeVarInt(ent.key, false);
            output.writeVarLong(ent.value, false);
        }
    }

    @Override
    public IntLongOrderedMap read(final Kryo kryo, final Input input, final Class<? extends IntLongOrderedMap> dataClass) {
        int length = input.readInt(true);
        IntLongOrderedMap data = new IntLongOrderedMap(length);
        for (int i = 0; i < length; i++)
            data.put(input.readVarInt(false), input.readVarLong(false));
        return data;
    }

    @Override
    public IntLongOrderedMap copy(Kryo kryo, IntLongOrderedMap original) {
        return new IntLongOrderedMap(original);
    }
}