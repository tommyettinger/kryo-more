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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.Xoshiro256MX3Random;

/**
 * Kryo {@link Serializer} for juniper {@link Xoshiro256MX3Random}s.
 */
public class Xoshiro256MX3RandomSerializer extends Serializer<Xoshiro256MX3Random> {

    public Xoshiro256MX3RandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Xoshiro256MX3Random data) {
        output.writeVarLong(data.getStateA(), false);
        output.writeVarLong(data.getStateB(), false);
        output.writeVarLong(data.getStateC(), false);
        output.writeVarLong(data.getStateD(), false);
    }

    @Override
    public Xoshiro256MX3Random read(final Kryo kryo, final Input input, final Class<? extends Xoshiro256MX3Random> dataClass) {
        return new Xoshiro256MX3Random(input.readVarLong(false), input.readVarLong(false),
                input.readVarLong(false), input.readVarLong(false));
    }

    @Override
    public Xoshiro256MX3Random copy(Kryo kryo, Xoshiro256MX3Random original) {
        return original.copy();
    }
}