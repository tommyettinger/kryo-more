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
import com.github.tommyettinger.ds.ObjectIntMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ObjectIntMap}s.
 */
public class ObjectIntMapSerializer extends Serializer<ObjectIntMap<?>> {

    public ObjectIntMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectIntMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for (ObjectIntMap.EntryIterator<?> it = new ObjectIntMap.EntryIterator<>(data); it.hasNext(); ) {
            ObjectIntMap.Entry<?> ent = it.next();
            kryo.writeClassAndObject(output, ent.key);
            output.writeVarInt(ent.value, false);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectIntMap<?> read(final Kryo kryo, final Input input, final Class<? extends ObjectIntMap<?>> dataClass) {
        int length = input.readInt(true);
        ObjectIntMap<?> data = new ObjectIntMap<>(length);
        ObjectIntMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(kryo.readClassAndObject(input), input.readVarInt(false));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectIntMap<?> copy(Kryo kryo, ObjectIntMap<?> original) {
        ObjectIntMap<?> map = new ObjectIntMap<>(original.size(), original.getLoadFactor());
        ObjectIntMap rawMap = map;
        for (ObjectIntMap.Entry ent : original) {
            rawMap.put(kryo.copy(ent.key), ent.value);
        }
        return map;
    }
}