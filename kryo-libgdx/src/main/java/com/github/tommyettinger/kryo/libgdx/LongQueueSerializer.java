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

import com.badlogic.gdx.utils.LongQueue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link LongQueue}s.
 */
public class LongQueueSerializer extends Serializer<LongQueue> {

    public LongQueueSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongQueue data) {
        output.writeVarInt(data.size, true);
        for (int i = 0; i < data.size; i++) {
            output.writeLong(data.get(i));
        }
    }

    @Override
    public LongQueue read(final Kryo kryo, final Input input, final Class<? extends LongQueue> dataClass) {
        final int len = input.readVarInt(true);
        LongQueue data = new LongQueue(len);
        for (int i = 0; i < len; i++) {
            data.addLast(input.readLong());
        }
        return data;
    }

    @Override
    public LongQueue copy(Kryo kryo, LongQueue original) {
        LongQueue q = new LongQueue(original.size);
        for (int i = 0; i < original.size; i++) {
            q.addLast(original.get(i));
        }
        return q;
    }
}