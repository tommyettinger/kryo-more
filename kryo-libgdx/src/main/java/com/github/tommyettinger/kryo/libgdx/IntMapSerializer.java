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

import com.badlogic.gdx.utils.IntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link IntMap}s.
 */
public class IntMapSerializer extends Serializer<IntMap> {

    public IntMapSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntMap data) {
        output.writeVarInt(data.size, true);
        IntMap.Keys keys = data.keys();
        for (int item; keys.hasNext;) {
            item = keys.next();
            output.writeInt(item);
            kryo.writeClassAndObject(output, data.get(item));
        }
    }

    @Override
    public IntMap<?> read(final Kryo kryo, final Input input, final Class<? extends IntMap> dataClass) {
        final int len = input.readVarInt(true);
        IntMap data = new IntMap(len);
        for (int i = 0; i < len; i++) {
            data.put(input.readInt(), kryo.readClassAndObject(input));
        }
        return data;
    }

    @Override
    public IntMap<?> copy(Kryo kryo, IntMap original) {
        return new IntMap<>(original);
    }
}