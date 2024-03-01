/*
 * Copyright (c) 2022-2024 See AUTHORS file.
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

package com.github.tommyettinger.kryo.cringe;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.RandomAce320;
import com.github.tommyettinger.cringe.RandomAce320;

/**
 * Kryo {@link Serializer} for cringe {@link RandomAce320}s.
 */
public class RandomAce320Serializer extends Serializer<RandomAce320> {

    public RandomAce320Serializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final RandomAce320 data) {
        output.writeLong(data.getStateA(), false);
        output.writeLong(data.getStateB(), false);
        output.writeLong(data.getStateC(), false);
        output.writeLong(data.getStateD(), false);
        output.writeLong(data.getStateE(), false);
    }

    @Override
    public RandomAce320 read(final Kryo kryo, final Input input, final Class<? extends RandomAce320> dataClass) {
        return new RandomAce320(input.readLong(false), input.readLong(false), input.readLong(false), input.readLong(false), input.readLong(false));
    }

    @Override
    public RandomAce320 copy(Kryo kryo, RandomAce320 original) {
        return original.copy();
    }
}