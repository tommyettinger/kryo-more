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

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link DelayedRemovalArray}s.
 */
public class DelayedRemovalArraySerializer extends Serializer<DelayedRemovalArray> {

    public DelayedRemovalArraySerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final DelayedRemovalArray data) {
        output.writeBoolean(data.ordered);
        output.writeVarInt(data.size, true);
        for(Object item : data) {
            kryo.writeClassAndObject(output, item);
        }
    }

    @Override
    public DelayedRemovalArray<?> read(final Kryo kryo, final Input input, final Class<? extends DelayedRemovalArray> dataClass) {
        final boolean ordered = input.readBoolean();
        final int len = input.readVarInt(true);
        DelayedRemovalArray data = new DelayedRemovalArray(ordered, len);
        for (int i = 0; i < len; i++) {
            data.add(kryo.readClassAndObject(input));
        }
        return data;
    }

    @Override
    public DelayedRemovalArray<?> copy(Kryo kryo, DelayedRemovalArray original) {
        return new DelayedRemovalArray<>(original);
    }
}