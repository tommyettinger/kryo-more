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
import com.github.tommyettinger.ds.ObjectFloatMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ObjectFloatMap}s.
 */
public class ObjectFloatMapSerializer extends Serializer<ObjectFloatMap<?>> {

    public ObjectFloatMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectFloatMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for (ObjectFloatMap.EntryIterator<?> it = new ObjectFloatMap.EntryIterator<>(data); it.hasNext(); ) {
            ObjectFloatMap.Entry<?> ent = it.next();
            kryo.writeClassAndObject(output, ent.key);
            output.writeFloat(ent.value);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectFloatMap<?> read(final Kryo kryo, final Input input, final Class<? extends ObjectFloatMap<?>> dataClass) {
        int length = input.readInt(true);
        ObjectFloatMap<?> data = new ObjectFloatMap<>(length);
        ObjectFloatMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(kryo.readClassAndObject(input), input.readFloat());
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectFloatMap<?> copy(Kryo kryo, ObjectFloatMap<?> original) {
        ObjectFloatMap<?> map = new ObjectFloatMap<>(original.size(), original.getLoadFactor());
        kryo.reference(map);
        ObjectFloatMap rawMap = map;
        for (ObjectFloatMap.Entry ent : original) {
            rawMap.put(kryo.copy(ent.key), ent.value);
        }
        return map;
    }
}