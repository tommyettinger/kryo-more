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
import com.github.tommyettinger.gdcrux.PointF5;

/**
 * Kryo {@link Serializer} for gdcrux {@link PointF5}s.
 */
public class PointF5Serializer extends Serializer<PointF5> {

    public PointF5Serializer() {
        setAcceptsNull(false);
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final PointF5 data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
        output.writeFloat(data.z);
        output.writeFloat(data.w);
        output.writeFloat(data.u);
    }

    @Override
    public PointF5 read(final Kryo kryo, final Input input, final Class<? extends PointF5> dataClass) {
        return new PointF5(input.readFloat(), input.readFloat(), input.readFloat(),
                input.readFloat(), input.readFloat());
    }

    @Override
    public PointF5 copy(Kryo kryo, PointF5 original) {
        return new PointF5(original);
    }
}