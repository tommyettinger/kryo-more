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
import com.github.tommyettinger.cringe.RandomChop128;

/**
 * Kryo {@link Serializer} for cringe {@link RandomChop128}s.
 */
public class RandomChop128Serializer extends Serializer<RandomChop128> {

    public RandomChop128Serializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final RandomChop128 data) {
        output.writeInt(data.stateA, false);
        output.writeInt(data.stateB, false);
        output.writeInt(data.stateC, false);
        output.writeInt(data.stateD, false);
    }

    @Override
    public RandomChop128 read(final Kryo kryo, final Input input, final Class<? extends RandomChop128> dataClass) {
        return new RandomChop128(input.readInt(false), input.readInt(false), input.readInt(false), input.readInt(false));
    }

    @Override
    public RandomChop128 copy(Kryo kryo, RandomChop128 original) {
        return original.copy();
    }
}