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

import com.badlogic.gdx.math.collision.Ray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Ray}s.
 */
public class RaySerializer extends Serializer<Ray> {

    public RaySerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Ray data) {
        output.writeFloat(data.origin.x);
        output.writeFloat(data.origin.y);
        output.writeFloat(data.origin.z);
        output.writeFloat(data.direction.x);
        output.writeFloat(data.direction.y);
        output.writeFloat(data.direction.z);
    }

    @Override
    public Ray read(final Kryo kryo, final Input input, final Class<? extends Ray> dataClass) {
        Ray data = new Ray();
        data.origin.set(input.readFloat(), input.readFloat(), input.readFloat());
        data.direction.set(input.readFloat(), input.readFloat(), input.readFloat());
        return data;
    }

    @Override
    public Ray copy(Kryo kryo, Ray original) {
        Ray data = new Ray();
        data.origin.set(original.origin);
        data.direction.set(original.direction);
        return data;
    }
}