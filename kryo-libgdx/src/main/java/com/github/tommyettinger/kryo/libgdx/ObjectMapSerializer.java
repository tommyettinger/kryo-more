/*
 * Copyright (c) 2025 See AUTHORS file.
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

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link ObjectMap}s.
 */
public class ObjectMapSerializer extends Serializer<ObjectMap> {

    public ObjectMapSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ObjectMap data) {
        output.writeVarInt(data.size, true);
        for(Object item : data.keys()) {
            kryo.writeClassAndObject(output, item);
            kryo.writeClassAndObject(output, data.get(item));
        }
    }

    @Override
    public ObjectMap<?,?> read(final Kryo kryo, final Input input, final Class<? extends ObjectMap> dataClass) {
        final int len = input.readVarInt(true);
        ObjectMap data = new ObjectMap(len);
        for (int i = 0; i < len; i++) {
            data.put(kryo.readClassAndObject(input), kryo.readClassAndObject(input));
        }
        return data;
    }

    @Override
    public ObjectMap<?,?> copy(Kryo kryo, ObjectMap original) {
        return new ObjectMap<>(original);
    }
}