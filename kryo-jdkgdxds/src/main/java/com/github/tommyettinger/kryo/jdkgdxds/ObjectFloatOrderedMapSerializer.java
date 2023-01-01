/*
 * Copyright (c) 2022-2023 See AUTHORS file.
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
import com.github.tommyettinger.ds.ObjectFloatMap;
import com.github.tommyettinger.ds.ObjectFloatOrderedMap;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ObjectFloatOrderedMap}s.
 */
public class ObjectFloatOrderedMapSerializer extends Serializer<ObjectFloatOrderedMap<?>> {

    public ObjectFloatOrderedMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectFloatOrderedMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(Iterator<? extends ObjectFloatMap.Entry<?>> it = new ObjectFloatOrderedMap.OrderedMapEntries<>(data).iterator(); it.hasNext();) {
            ObjectFloatOrderedMap.Entry<?> ent = it.next();
            kryo.writeClassAndObject(output, ent.key);
            output.writeFloat(ent.value);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectFloatOrderedMap<?> read(final Kryo kryo, final Input input, final Class<? extends ObjectFloatOrderedMap<?>> dataClass) {
        int length = input.readInt(true);
        ObjectFloatOrderedMap<?> data = new ObjectFloatOrderedMap<>(length);
        ObjectFloatOrderedMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(kryo.readClassAndObject(input), input.readFloat());
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectFloatOrderedMap<?> copy(Kryo kryo, ObjectFloatOrderedMap<?> original) {
        ObjectFloatOrderedMap<?> map = new ObjectFloatOrderedMap<>(original.size(), original.getLoadFactor());
        kryo.reference(map);
        ObjectFloatOrderedMap rawMap = map;
        for(ObjectFloatOrderedMap.Entry ent : original) {
            rawMap.put(kryo.copy(ent.key), ent.value);
        }
        return map;
    }
}