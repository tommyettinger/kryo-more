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

package com.github.tommyettinger.kryo.gand.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gand.utils.Choo32Random;

/**
 * Kryo {@link Serializer} for juniper {@link Choo32Random}s.
 */
public class Choo32RandomSerializer extends Serializer<Choo32Random> {

    public Choo32RandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Choo32Random data) {
        output.writeVarInt(data.getStateA(), false);
        output.writeVarInt(data.getStateB(), false);
        output.writeVarInt(data.getStateC(), false);
        output.writeVarInt(data.getStateD(), false);
    }

    @Override
    public Choo32Random read(final Kryo kryo, final Input input, final Class<? extends Choo32Random> dataClass) {
        return new Choo32Random(input.readVarInt(false), input.readVarInt(false),
                input.readVarInt(false), input.readVarInt(false));
    }

    @Override
    public Choo32Random copy(Kryo kryo, Choo32Random original) {
        return original.copy();
    }
}