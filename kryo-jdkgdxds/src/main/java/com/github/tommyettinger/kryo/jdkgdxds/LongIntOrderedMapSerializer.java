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
import com.github.tommyettinger.ds.LongIntMap;
import com.github.tommyettinger.ds.LongIntOrderedMap;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongIntOrderedMap}s.
 */
public class LongIntOrderedMapSerializer extends Serializer<LongIntOrderedMap> {

    public LongIntOrderedMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongIntOrderedMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeBoolean(data.order() instanceof LongDeque);
        output.writeVarInt(data.getDefaultValue(), false);
        for(Iterator<LongIntMap.Entry> it = new LongIntOrderedMap.OrderedMapEntries(data).iterator(); it.hasNext();) {
            LongIntOrderedMap.Entry ent = it.next();
            output.writeVarLong(ent.key, false);
            output.writeVarInt(ent.value, false);
        }
    }

    @Override
    public LongIntOrderedMap read(final Kryo kryo, final Input input, final Class<? extends LongIntOrderedMap> dataClass) {
        int length = input.readInt(true);
        LongIntOrderedMap data = new LongIntOrderedMap(length, input.readBoolean());
        data.setDefaultValue(input.readVarInt(false));
        for (int i = 0; i < length; i++)
            data.put(input.readVarLong(false), input.readVarInt(false));
        return data;
    }

    @Override
    public LongIntOrderedMap copy(Kryo kryo, LongIntOrderedMap original) {
        return new LongIntOrderedMap(original);
    }
}