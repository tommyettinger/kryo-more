/*
 * Copyright (c) 2022 See AUTHORS file.
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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.Xoshiro128PlusPlusRandom;

/**
 * Kryo {@link Serializer} for juniper {@link Xoshiro128PlusPlusRandom}s.
 */
public class Xoshiro128PlusPlusRandomSerializer extends Serializer<Xoshiro128PlusPlusRandom> {

    public Xoshiro128PlusPlusRandomSerializer() {
        setImmutable(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Xoshiro128PlusPlusRandom data) {
        output.writeVarInt((int)data.getStateA(), false);
        output.writeVarInt((int)data.getStateB(), false);
        output.writeVarInt((int)data.getStateC(), false);
        output.writeVarInt((int)data.getStateD(), false);
    }

    @Override
    public Xoshiro128PlusPlusRandom read(final Kryo kryo, final Input input, final Class<? extends Xoshiro128PlusPlusRandom> dataClass) {
        return new Xoshiro128PlusPlusRandom(input.readVarInt(false), input.readVarInt(false),
                input.readVarInt(false), input.readVarInt(false));
    }

    @Override
    public Xoshiro128PlusPlusRandom copy(Kryo kryo, Xoshiro128PlusPlusRandom original) {
        return original.copy();
    }
}