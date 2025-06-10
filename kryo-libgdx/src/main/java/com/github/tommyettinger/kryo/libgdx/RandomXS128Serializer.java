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

import com.badlogic.gdx.math.RandomXS128;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link RandomXS128}s.
 */
public class RandomXS128Serializer extends Serializer<RandomXS128> {

    public RandomXS128Serializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final RandomXS128 data) {
        output.writeLong(data.getState(0));
        output.writeLong(data.getState(1));
    }

    @Override
    public RandomXS128 read(final Kryo kryo, final Input input, final Class<? extends RandomXS128> dataClass) {
        return new RandomXS128(input.readLong(), input.readLong());
    }

    @Override
    public RandomXS128 copy(Kryo kryo, RandomXS128 original) {
        return new RandomXS128(original.getState(0), original.getState(1));
    }
}