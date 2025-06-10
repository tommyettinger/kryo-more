/*
 * Copyright (c) 2025 See AUTHORS file.
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

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.math.GridPoint2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link GridPoint2}s.
 */
public class GridPoint2Serializer extends Serializer<GridPoint2> {

    public GridPoint2Serializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final GridPoint2 data) {
        output.writeInt(data.x);
        output.writeInt(data.y);
    }

    @Override
    public GridPoint2 read(final Kryo kryo, final Input input, final Class<? extends GridPoint2> dataClass) {
        return new GridPoint2(input.readInt(), input.readInt());
    }

    @Override
    public GridPoint2 copy(Kryo kryo, GridPoint2 original) {
        return new GridPoint2(original);
    }
}