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
import com.github.tommyettinger.ds.ObjectLongOrderedMap;
import com.github.tommyettinger.ds.OrderType;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ObjectLongOrderedMap}s.
 */
public class ObjectLongOrderedMapSerializer extends Serializer<ObjectLongOrderedMap<?>> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public ObjectLongOrderedMapSerializer() {
        setAcceptsNull(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectLongOrderedMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeVarInt(data.getOrderType().ordinal(), true);
        output.writeVarLong(data.getDefaultValue(), false);
        for(Iterator<? extends ObjectLongOrderedMap.Entry<?>> it = new ObjectLongOrderedMap.OrderedMapEntries<>(data).iterator(); it.hasNext();) {
            ObjectLongOrderedMap.Entry<?> ent = it.next();
            kryo.writeClassAndObject(output, ent.key);
            output.writeVarLong(ent.value, false);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectLongOrderedMap<?> read(final Kryo kryo, final Input input, final Class<? extends ObjectLongOrderedMap<?>> dataClass) {
        int length = input.readInt(true);
        ObjectLongOrderedMap<?> data = new ObjectLongOrderedMap<>(length, ORDER_TYPES[input.readVarInt(true)]);
        data.setDefaultValue(input.readVarLong(false));
        ObjectLongOrderedMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put(kryo.readClassAndObject(input), input.readVarLong(false));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public ObjectLongOrderedMap<?> copy(Kryo kryo, ObjectLongOrderedMap<?> original) {
        ObjectLongOrderedMap<?> map = new ObjectLongOrderedMap<>(original.size(), original.getLoadFactor());
        kryo.reference(map);
        map.setDefaultValue(original.getDefaultValue());
        ObjectLongOrderedMap rawMap = map;
        for(ObjectLongOrderedMap.Entry ent : original) {
            rawMap.put(kryo.copy(ent.key), ent.value);
        }
        return map;
    }
}