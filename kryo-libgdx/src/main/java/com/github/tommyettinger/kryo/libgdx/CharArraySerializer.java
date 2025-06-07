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

import com.badlogic.gdx.utils.CharArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link CharArray}s.
 */
public class CharArraySerializer extends Serializer<CharArray> {

    public CharArraySerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final CharArray data) {
        output.writeBoolean(data.ordered);
        output.writeVarInt(data.size, true);
        output.writeChars(data.items, 0, data.size);
    }

    @Override
    public CharArray read(final Kryo kryo, final Input input, final Class<? extends CharArray> dataClass) {
        final boolean ordered = input.readBoolean();
        final int len = input.readVarInt(true);
        CharArray data = new CharArray(ordered, len);
        data.addAll(input.readChars(len));
        return data;
    }

    @Override
    public CharArray copy(Kryo kryo, CharArray original) {
        return new CharArray(original);
    }
}