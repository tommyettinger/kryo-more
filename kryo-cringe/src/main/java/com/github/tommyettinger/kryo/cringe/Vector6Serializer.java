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
import com.github.tommyettinger.cringe.Vector6;

/**
 * Kryo {@link Serializer} for cringe {@link Vector6}s.
 */
public class Vector6Serializer extends Serializer<Vector6> {

    public Vector6Serializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Vector6 data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
        output.writeFloat(data.z);
        output.writeFloat(data.w);
        output.writeFloat(data.u);
        output.writeFloat(data.v);
    }

    @Override
    public Vector6 read(final Kryo kryo, final Input input, final Class<? extends Vector6> dataClass) {
        return new Vector6(input.readFloat(), input.readFloat(), input.readFloat(),
                input.readFloat(), input.readFloat(), input.readFloat());
    }

    @Override
    public Vector6 copy(Kryo kryo, Vector6 original) {
        return original.cpy();
    }
}