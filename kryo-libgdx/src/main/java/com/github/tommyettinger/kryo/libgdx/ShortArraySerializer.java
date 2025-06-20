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

import com.badlogic.gdx.utils.ShortArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link ShortArray}s.
 */
public class ShortArraySerializer extends Serializer<ShortArray> {

    public ShortArraySerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ShortArray data) {
        output.writeBoolean(data.ordered);
        output.writeVarInt(data.size, true);
        output.writeShorts(data.items, 0, data.size);
    }

    @Override
    public ShortArray read(final Kryo kryo, final Input input, final Class<? extends ShortArray> dataClass) {
        final boolean ordered = input.readBoolean();
        final int len = input.readVarInt(true);
        ShortArray data = new ShortArray(ordered, len);
        data.addAll(input.readShorts(len));
        return data;
    }

    @Override
    public ShortArray copy(Kryo kryo, ShortArray original) {
        return new ShortArray(original);
    }
}