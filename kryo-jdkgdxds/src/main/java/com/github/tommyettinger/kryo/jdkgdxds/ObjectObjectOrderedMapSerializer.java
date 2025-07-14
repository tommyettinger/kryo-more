/*
 * Copyright (c) 2023-2024 See AUTHORS file.
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
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.github.tommyettinger.ds.ObjectObjectOrderedMap;
import com.github.tommyettinger.ds.OrderType;

public class ObjectObjectOrderedMapSerializer extends MapSerializer<ObjectObjectOrderedMap<?, ?>> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public ObjectObjectOrderedMapSerializer() {
        super();
        setKeysCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, ObjectObjectOrderedMap<?, ?> data) {
        output.writeVarInt(data.getOrderType().ordinal(), true);
        kryo.writeClassAndObject(output, data.getDefaultValue());
    }

    @Override
    protected ObjectObjectOrderedMap<?, ?> create(Kryo kryo, Input input, Class<? extends ObjectObjectOrderedMap<?, ?>> type, int size) {
        ObjectObjectOrderedMap<Object, Object> m = new ObjectObjectOrderedMap<>(size, ORDER_TYPES[input.readVarInt(true)]);
        m.setDefaultValue(kryo.readClassAndObject(input));
        return m;
    }

    @Override
    protected ObjectObjectOrderedMap<?, ?> createCopy(Kryo kryo, ObjectObjectOrderedMap<?, ?> original) {
        ObjectObjectOrderedMap<Object, Object> m = new ObjectObjectOrderedMap<>(original.size(), original.getLoadFactor(), original.getOrderType());
        m.setDefaultValue(original.getDefaultValue());
        return m;
    }
}
