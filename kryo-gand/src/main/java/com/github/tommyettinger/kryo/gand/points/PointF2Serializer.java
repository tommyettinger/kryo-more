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

package com.github.tommyettinger.kryo.gand.points;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gand.points.PointF2;

/**
 * Kryo {@link Serializer} for gand {@link PointF2}s.
 */
public class PointF2Serializer extends Serializer<PointF2> {

    public PointF2Serializer() {
        setAcceptsNull(false);
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final PointF2 data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
    }

    @Override
    public PointF2 read(final Kryo kryo, final Input input, final Class<? extends PointF2> dataClass) {
        return new PointF2(input.readFloat(), input.readFloat());
    }

    @Override
    public PointF2 copy(Kryo kryo, PointF2 original) {
        return new PointF2(original);
    }
}