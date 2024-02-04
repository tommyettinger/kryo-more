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
import com.github.tommyettinger.random.Jsf32Random;

/**
 * Kryo {@link Serializer} for juniper {@link Jsf32Random}s.
 */
public class Jsf32RandomSerializer extends Serializer<Jsf32Random> {

    public Jsf32RandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Jsf32Random data) {
        output.writeVarInt((int)data.getStateA(), false);
        output.writeVarInt((int)data.getStateB(), false);
        output.writeVarInt((int)data.getStateC(), false);
        output.writeVarInt((int)data.getStateD(), false);
    }

    @Override
    public Jsf32Random read(final Kryo kryo, final Input input, final Class<? extends Jsf32Random> dataClass) {
        return new Jsf32Random(input.readVarInt(false), input.readVarInt(false),
                input.readVarInt(false), input.readVarInt(false));
    }

    @Override
    public Jsf32Random copy(Kryo kryo, Jsf32Random original) {
        return original.copy();
    }
}