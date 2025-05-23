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
import com.github.tommyettinger.ds.IntDeque;
import com.github.tommyettinger.ds.IntIntMap;
import com.github.tommyettinger.ds.IntIntOrderedMap;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntIntOrderedMap}s.
 */
public class IntIntOrderedMapSerializer extends Serializer<IntIntOrderedMap> {

    public IntIntOrderedMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntIntOrderedMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeBoolean(data.order() instanceof IntDeque);
        output.writeVarInt(data.getDefaultValue(), false);
        for(Iterator<IntIntMap.Entry> it = new IntIntOrderedMap.OrderedMapEntries(data).iterator(); it.hasNext();) {
            IntIntOrderedMap.Entry ent = it.next();
            output.writeVarInt(ent.key, false);
            output.writeVarInt(ent.value, false);
        }
    }

    @Override
    public IntIntOrderedMap read(final Kryo kryo, final Input input, final Class<? extends IntIntOrderedMap> dataClass) {
        int length = input.readInt(true);
        IntIntOrderedMap data = new IntIntOrderedMap(length, input.readBoolean());
        data.setDefaultValue(input.readVarInt(false));
        for (int i = 0; i < length; i++)
            data.put(input.readVarInt(false), input.readVarInt(false));
        return data;
    }

    @Override
    public IntIntOrderedMap copy(Kryo kryo, IntIntOrderedMap original) {
        return new IntIntOrderedMap(original);
    }
}