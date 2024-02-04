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
import com.github.tommyettinger.ds.LongObjectMap;
import com.github.tommyettinger.ds.LongObjectOrderedMap;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongObjectOrderedMap}s.
 */
public class LongObjectOrderedMapSerializer extends Serializer<LongObjectOrderedMap<?>> {

    public LongObjectOrderedMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongObjectOrderedMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(Iterator<? extends LongObjectMap.Entry<?>> it = new LongObjectOrderedMap.OrderedMapEntries<>(data).iterator(); it.hasNext();) {
            LongObjectOrderedMap.Entry<?> ent = it.next();
            output.writeVarLong(ent.key, false);
            kryo.writeClassAndObject(output, ent.value);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public LongObjectOrderedMap<?> read(final Kryo kryo, final Input input, final Class<? extends LongObjectOrderedMap<?>> dataClass) {
        int length = input.readInt(true);
        LongObjectOrderedMap<?> data = new LongObjectOrderedMap<>(length);
        LongObjectOrderedMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(input.readVarLong(false), kryo.readClassAndObject(input));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public LongObjectOrderedMap<?> copy(Kryo kryo, LongObjectOrderedMap<?> original) {
        LongObjectOrderedMap<?> map = new LongObjectOrderedMap<>(original.size(), original.getLoadFactor());
        kryo.reference(map);
        LongObjectOrderedMap rawMap = map;
        for(LongObjectOrderedMap.Entry ent : original) {
            rawMap.put(ent.key, kryo.copy(ent.value));
        }
        return map;
    }
}