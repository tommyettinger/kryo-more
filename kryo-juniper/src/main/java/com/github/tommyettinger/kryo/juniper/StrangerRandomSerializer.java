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
import com.github.tommyettinger.random.StrangerRandom;

/**
 * Kryo {@link Serializer} for juniper {@link StrangerRandom}s.
 */
public class StrangerRandomSerializer extends Serializer<StrangerRandom> {

    public StrangerRandomSerializer() {
        setImmutable(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final StrangerRandom data) {
        output.writeVarLong(data.getStateA(), false);
        output.writeVarLong(data.getStateB(), false);
        output.writeVarLong(data.getStateC(), false);
        output.writeVarLong(data.getStateD(), false);
    }

    @Override
    public StrangerRandom read(final Kryo kryo, final Input input, final Class<? extends StrangerRandom> dataClass) {
        return new StrangerRandom(input.readVarLong(false), input.readVarLong(false),
                input.readVarLong(false), input.readVarLong(false));
    }

    @Override
    public StrangerRandom copy(Kryo kryo, StrangerRandom original) {
        return new StrangerRandom(original.getStateA(), original.getStateB(), original.getStateC(), original.getStateD());
    }
}