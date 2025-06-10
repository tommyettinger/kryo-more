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

import com.badlogic.gdx.utils.LongMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link LongMap}s.
 */
public class LongMapSerializer extends Serializer<LongMap> {

    public LongMapSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongMap data) {
        output.writeVarInt(data.size, true);
        LongMap.Keys keys = data.keys();
        for (long item; keys.hasNext;) {
            item = keys.next();
            output.writeLong(item);
            kryo.writeClassAndObject(output, data.get(item));
        }
    }

    @Override
    public LongMap<?> read(final Kryo kryo, final Input input, final Class<? extends LongMap> dataClass) {
        final int len = input.readVarInt(true);
        LongMap data = new LongMap(len);
        for (int i = 0; i < len; i++) {
            data.put(input.readLong(), kryo.readClassAndObject(input));
        }
        return data;
    }

    @Override
    public LongMap<?> copy(Kryo kryo, LongMap original) {
        return new LongMap<>(original);
    }
}