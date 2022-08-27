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
import com.github.tommyettinger.ds.ObjectLongMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ObjectLongMap}s.
 */
public class ObjectLongMapSerializer extends Serializer<ObjectLongMap<?>> {

    public ObjectLongMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectLongMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(ObjectLongMap.EntryIterator<?> it = new ObjectLongMap.EntryIterator<>(data); it.hasNext();) {
            ObjectLongMap.Entry<?> ent = it.next();
            kryo.writeClassAndObject(output, ent.key);
            output.writeVarLong(ent.value, false);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectLongMap<?> read(final Kryo kryo, final Input input, final Class<? extends ObjectLongMap<?>> dataClass) {
        int length = input.readInt(true);
        ObjectLongMap<?> data = new ObjectLongMap<>(length);
        ObjectLongMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(kryo.readClassAndObject(input), input.readVarLong(false));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectLongMap<?> copy(Kryo kryo, ObjectLongMap<?> original) {
        ObjectLongMap<?> map = new ObjectLongMap<>(original.size(), original.getLoadFactor());
        ObjectLongMap rawMap = map;
        for(ObjectLongMap.Entry ent : original) {
            rawMap.put(kryo.copy(ent.key), ent.value);
        }
        return map;
    }
}