/*
 * Copyright (c) 2022 See AUTHORS file.
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
import com.github.tommyettinger.ds.IntObjectMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntObjectMap}s.
 */
public class IntObjectMapSerializer extends Serializer<IntObjectMap<?>> {

    public IntObjectMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntObjectMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(IntObjectMap.EntryIterator<?> it = new IntObjectMap.EntryIterator<>(data); it.hasNext();) {
            IntObjectMap.Entry<?> ent = it.next();
            output.writeVarInt(ent.key, false);
            kryo.writeClassAndObject(output, ent.value);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public IntObjectMap<?> read(final Kryo kryo, final Input input, final Class<? extends IntObjectMap<?>> dataClass) {
        int length = input.readInt(true);
        IntObjectMap<?> data = new IntObjectMap<>(length);
        IntObjectMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(input.readVarInt(false), kryo.readClassAndObject(input));
        return data;
    }
}