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
import com.github.tommyettinger.gand.utils.FlowRandom;

/**
 * Kryo {@link Serializer} for juniper {@link FlowRandom}s.
 */
public class FlowRandomSerializer extends Serializer<FlowRandom> {

    public FlowRandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final FlowRandom data) {
        output.writeVarLong(data.stateA, false);
        output.writeVarLong(data.stateB, false);
    }

    @Override
    public FlowRandom read(final Kryo kryo, final Input input, final Class<? extends FlowRandom> dataClass) {
        return new FlowRandom(input.readVarLong(false), input.readVarLong(false));
    }

    @Override
    public FlowRandom copy(Kryo kryo, FlowRandom original) {
        return original.copy();
    }
}