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

import com.badlogic.gdx.utils.IntIntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link IntIntMap}s.
 */
public class IntIntMapSerializer extends Serializer<IntIntMap> {

    public IntIntMapSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntIntMap data) {
        output.writeVarInt(data.size, true);
        IntIntMap.Keys keys = data.keys();
        for (int item; keys.hasNext;) {
            item = keys.next();
            output.writeInt(item);
            output.writeInt(data.get(item, 0));
        }
    }

    @Override
    public IntIntMap read(final Kryo kryo, final Input input, final Class<? extends IntIntMap> dataClass) {
        final int len = input.readVarInt(true);
        IntIntMap data = new IntIntMap(len);
        for (int i = 0; i < len; i++) {
            data.put(input.readInt(), input.readInt());
        }
        return data;
    }

    @Override
    public IntIntMap copy(Kryo kryo, IntIntMap original) {
        return new IntIntMap(original);
    }
}