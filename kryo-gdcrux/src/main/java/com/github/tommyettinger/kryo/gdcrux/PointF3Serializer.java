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
import com.github.tommyettinger.gdcrux.PointF3;

/**
 * Kryo {@link Serializer} for gand {@link PointF3}s.
 */
public class PointF3Serializer extends Serializer<PointF3> {

    public PointF3Serializer() {
        setAcceptsNull(false);
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final PointF3 data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
        output.writeFloat(data.z);
    }

    @Override
    public PointF3 read(final Kryo kryo, final Input input, final Class<? extends PointF3> dataClass) {
        return new PointF3(input.readFloat(), input.readFloat(), input.readFloat());
    }

    @Override
    public PointF3 copy(Kryo kryo, PointF3 original) {
        return new PointF3(original);
    }
}