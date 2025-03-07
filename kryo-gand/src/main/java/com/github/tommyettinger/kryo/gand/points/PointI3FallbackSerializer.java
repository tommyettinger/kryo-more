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
import com.github.tommyettinger.gdcrux.PointI3;

/**
 * Kryo {@link Serializer} for gand {@link PointI3}s.
 * <br>
 * This is meant to fill in for a broken {@link PointI3Serializer} with a less-efficient but working one.
 * However, this doesn't work any better than the original, and there's no indication why either would be overwriting
 * bytes earlier in the serialized stream, erroneously. Both do that, though.
 */
public class PointI3FallbackSerializer extends Serializer<PointI3> {

    public PointI3FallbackSerializer() {
        setAcceptsNull(false);
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final PointI3 data) {
        output.writeString(data.toString());
    }

    @Override
    public PointI3 read(final Kryo kryo, final Input input, final Class<? extends PointI3> dataClass) {
        return new PointI3().fromString(input.readString());
    }

    @Override
    public PointI3 copy(Kryo kryo, PointI3 original) {
        return new PointI3(original);
    }
}
