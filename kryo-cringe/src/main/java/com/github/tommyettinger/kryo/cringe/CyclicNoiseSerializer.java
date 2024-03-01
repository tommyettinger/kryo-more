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
import com.github.tommyettinger.cringe.CyclicNoise;

/**
 * Kryo {@link Serializer} for cringe {@link CyclicNoise}s.
 */
public class CyclicNoiseSerializer extends Serializer<CyclicNoise> {

    public CyclicNoiseSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final CyclicNoise data) {
        output.writeInt(data.getSeed(), false);
        output.writeInt(data.getOctaves(), true);
        output.writeFloat(data.getFrequency());
    }

    @Override
    public CyclicNoise read(final Kryo kryo, final Input input, final Class<? extends CyclicNoise> dataClass) {
        return new CyclicNoise(input.readInt(false), input.readInt(true), input.readFloat());
    }

    @Override
    public CyclicNoise copy(Kryo kryo, CyclicNoise original) {
        return original.copy();
    }
}