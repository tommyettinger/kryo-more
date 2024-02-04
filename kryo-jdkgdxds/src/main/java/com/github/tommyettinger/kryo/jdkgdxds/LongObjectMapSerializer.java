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

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongObjectMap}s.
 */
public class LongObjectMapSerializer extends Serializer<LongObjectMap<?>> {

    public LongObjectMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongObjectMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(LongObjectMap.EntryIterator<?> it = new LongObjectMap.EntryIterator<>(data); it.hasNext();) {
            LongObjectMap.Entry<?> ent = it.next();
            output.writeVarLong(ent.key, false);
            kryo.writeClassAndObject(output, ent.value);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public LongObjectMap<?> read(final Kryo kryo, final Input input, final Class<? extends LongObjectMap<?>> dataClass) {
        int length = input.readInt(true);
        LongObjectMap<?> data = new LongObjectMap<>(length);
        LongObjectMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(input.readVarLong(false), kryo.readClassAndObject(input));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public LongObjectMap<?> copy(Kryo kryo, LongObjectMap<?> original) {
        LongObjectMap<?> map = new LongObjectMap<>(original.size(), original.getLoadFactor());
        kryo.reference(map);
        LongObjectMap rawMap = map;
        for(LongObjectMap.Entry ent : original) {
            rawMap.put(ent.key, kryo.copy(ent.value));
        }
        return map;
    }
}