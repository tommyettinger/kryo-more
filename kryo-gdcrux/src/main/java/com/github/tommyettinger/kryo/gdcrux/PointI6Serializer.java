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

package com.github.tommyettinger.kryo.gdcrux;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gdcrux.PointI6;

/**
 * Kryo {@link Serializer} for gdcrux {@link PointI6}s.
 */
public class PointI6Serializer extends Serializer<PointI6> {

    public PointI6Serializer() {
        setAcceptsNull(false);
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final PointI6 data) {
        output.writeInt(data.x, true);
        output.writeInt(data.y, true);
        output.writeInt(data.z, true);
        output.writeInt(data.w, true);
        output.writeInt(data.u, true);
        output.writeInt(data.v, true);
    }

    @Override
    public PointI6 read(final Kryo kryo, final Input input, final Class<? extends PointI6> dataClass) {
        return new PointI6(
                input.readInt(true),
                input.readInt(true),
                input.readInt(true),
                input.readInt(true),
                input.readInt(true),
                input.readInt(true));
    }

    @Override
    public PointI6 copy(Kryo kryo, PointI6 original) {
        return new PointI6(original);
    }
}
