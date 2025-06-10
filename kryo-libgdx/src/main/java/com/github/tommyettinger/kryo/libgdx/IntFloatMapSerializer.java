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

import com.badlogic.gdx.utils.IntFloatMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link IntFloatMap}s.
 */
public class IntFloatMapSerializer extends Serializer<IntFloatMap> {

    public IntFloatMapSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntFloatMap data) {
        output.writeVarInt(data.size, true);
        IntFloatMap.Keys keys = data.keys();
        for (int item; keys.hasNext;) {
            item = keys.next();
            output.writeInt(item);
            output.writeFloat(data.get(item, 0));
        }
    }

    @Override
    public IntFloatMap read(final Kryo kryo, final Input input, final Class<? extends IntFloatMap> dataClass) {
        final int len = input.readVarInt(true);
        IntFloatMap data = new IntFloatMap(len);
        for (int i = 0; i < len; i++) {
            data.put(input.readInt(), input.readFloat());
        }
        return data;
    }

    @Override
    public IntFloatMap copy(Kryo kryo, IntFloatMap original) {
        return new IntFloatMap(original);
    }
}