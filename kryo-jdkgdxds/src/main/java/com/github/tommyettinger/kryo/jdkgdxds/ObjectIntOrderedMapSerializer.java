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
import com.github.tommyettinger.ds.ObjectIntMap;
import com.github.tommyettinger.ds.ObjectIntOrderedMap;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ObjectIntOrderedMap}s.
 */
public class ObjectIntOrderedMapSerializer extends Serializer<ObjectIntOrderedMap<?>> {

    public ObjectIntOrderedMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectIntOrderedMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(Iterator<? extends ObjectIntMap.Entry<?>> it = new ObjectIntOrderedMap.OrderedMapEntries<>(data).iterator(); it.hasNext();) {
            ObjectIntOrderedMap.Entry<?> ent = it.next();
            kryo.writeClassAndObject(output, ent.key);
            output.writeVarInt(ent.value, false);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectIntOrderedMap<?> read(final Kryo kryo, final Input input, final Class<? extends ObjectIntOrderedMap<?>> dataClass) {
        int length = input.readInt(true);
        ObjectIntOrderedMap<?> data = new ObjectIntOrderedMap<>(length);
        ObjectIntOrderedMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(kryo.readClassAndObject(input), input.readVarInt(false));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectIntOrderedMap<?> copy(Kryo kryo, ObjectIntOrderedMap<?> original) {
        ObjectIntOrderedMap<?> map = new ObjectIntOrderedMap<>(original.size(), original.getLoadFactor());
        kryo.reference(map);
        ObjectIntOrderedMap rawMap = map;
        for(ObjectIntOrderedMap.Entry ent : original) {
            rawMap.put(kryo.copy(ent.key), ent.value);
        }
        return map;
    }
}