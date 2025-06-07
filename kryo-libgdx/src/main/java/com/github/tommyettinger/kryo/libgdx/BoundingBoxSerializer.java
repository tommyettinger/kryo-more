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

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link BoundingBox}s.
 */
public class BoundingBoxSerializer extends Serializer<BoundingBox> {

    public BoundingBoxSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final BoundingBox data) {
        output.writeFloat(data.min.x);
        output.writeFloat(data.min.y);
        output.writeFloat(data.min.z);
        output.writeFloat(data.max.x);
        output.writeFloat(data.max.y);
        output.writeFloat(data.max.z);
    }

    @Override
    public BoundingBox read(final Kryo kryo, final Input input, final Class<? extends BoundingBox> dataClass) {
        return new BoundingBox(
                new Vector3(input.readFloat(), input.readFloat(), input.readFloat()),
                new Vector3(input.readFloat(), input.readFloat(), input.readFloat())
        );
    }

    @Override
    public BoundingBox copy(Kryo kryo, BoundingBox original) {
        return new BoundingBox(original);
    }
}